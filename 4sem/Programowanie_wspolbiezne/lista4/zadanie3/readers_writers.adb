with Ada.Text_IO; use Ada.Text_IO;
with Ada.Numerics.Float_Random; use Ada.Numerics.Float_Random;
with Random_Seeds; use Random_Seeds;
with Ada.Real_Time; use Ada.Real_Time;
with Monitor_Package; use Monitor_Package;

procedure Readers_Writers is

   -- Liczba procesów: 10 czytelników + 5 pisarzy
   Nr_Of_Processes : constant Integer := 15;

   Min_Steps : constant Integer := 50;
   Max_Steps : constant Integer := 100;

   Min_Delay : constant Duration := 0.01;
   Max_Delay : constant Duration := 0.05;

   -- Stany procesu
   type Process_State is (
      Local_Section,
      Start,
      Reading_Room,
      Stop
   );

   -- Definicja planszy 2D
   Board_Width  : constant Integer := Nr_Of_Processes;
   Board_Height : constant Integer := Process_State'Pos(Process_State'Last) + 1;

   -- Czas startu
   Start_Time : Time := Clock;

   -- Generatory losowości dla tasków
   Seeds : Seed_Array_Type(1 .. Nr_Of_Processes) := Make_Seeds(Nr_Of_Processes);

   -- Typy i procedury
   type Position_Type is record
      X: Integer range 0 .. Board_Width - 1;
      Y: Integer range 0 .. Board_Height - 1;
   end record;

   type Trace_Type is record
      Time_Stamp: Duration;
      Id : Integer;
      Position: Position_Type;
      Symbol: Character;
   end record;

   type Trace_Array_Type is array(0 .. Max_Steps) of Trace_Type;

   type Traces_Sequence_Type is record
      Last: Integer := -1;
      Trace_Array: Trace_Array_Type;
   end record;

   procedure Print_Trace(Trace : Trace_Type) is
      Symbol : String := (' ', Trace.Symbol);
   begin
      Put_Line(
         Duration'Image(Trace.Time_Stamp) & " " &
         Integer'Image(Trace.Id) & " " &
         Integer'Image(Trace.Position.X) & " " &
         Integer'Image(Trace.Position.Y) & " " &
         Symbol
      );
   end Print_Trace;

   procedure Print_Traces(Traces : Traces_Sequence_Type) is
   begin
      for I in 0 .. Traces.Last loop
         Print_Trace(Traces.Trace_Array(I));
      end loop;
   end Print_Traces;

   -- Task Printer do zbierania i drukowania śladów
   task Printer is
      entry Report(Traces : Traces_Sequence_Type);
   end Printer;

   task body Printer is
   begin
      for I in 1 .. Nr_Of_Processes loop
         accept Report(Traces : Traces_Sequence_Type) do
            Print_Traces(Traces);
         end Report;
      end loop;

      Put(
         "-1 " &
         Integer'Image(Nr_Of_Processes) & " " &
         Integer'Image(Board_Width) & " " &
         Integer'Image(Board_Height) & " "
      );
      for I in Process_State'Range loop
         Put(I'Image & ";");
      end loop;
      Put_Line("READERS=10;WRITERS=5;");
   end Printer;

   -- Monitor do zarządzania czytelnią
   protected Room_Monitor is
      entry Start_Read;
      entry Stop_Read;
      entry Start_Write;
      entry Stop_Write;
   private
      Reader_Count : Integer := 0;
      Writer_Active : Boolean := False;
      Readers_Waiting : Boolean := False;
      Writers_Waiting : Boolean := False;
   end Room_Monitor;

   protected body Room_Monitor is
      entry Start_Read when not Writer_Active and not Writers_Waiting is
      begin
         Reader_Count := Reader_Count + 1;
      end Start_Read;

      entry Stop_Read when True is
      begin
         Reader_Count := Reader_Count - 1;
         if Reader_Count = 0 then
            Writers_Waiting := False;
         end if;
      end Stop_Read;

      entry Start_Write when Reader_Count = 0 and not Writer_Active is
      begin
         Writer_Active := True;
      end Start_Write;

      entry Stop_Write when True is
      begin
         Writer_Active := False;
         Readers_Waiting := False;
      end Stop_Write;
   end Room_Monitor;

   -- Typ procesu
   type Process_Type is record
      Id: Integer;
      Symbol: Character;
      Position: Position_Type;
   end record;

   -- Task dla procesów (czytelników i pisarzy)
   task type Process_Task_Type is
      entry Init(Id: Integer; Seed: Integer; Symbol: Character);
      entry Start;
   end Process_Task_Type;

   task body Process_Task_Type is
      G : Generator;
      Process : Process_Type;
      Time_Stamp : Duration;
      Nr_of_Steps: Integer;
      Traces: Traces_Sequence_Type;

      procedure Store_Trace is
      begin
         Traces.Last := Traces.Last + 1;
         Traces.Trace_Array(Traces.Last) := (
            Time_Stamp => Time_Stamp,
            Id => Process.Id,
            Position => Process.Position,
            Symbol => Process.Symbol
         );
      end Store_Trace;

      procedure Change_State(State: Process_State) is
      begin
         Time_Stamp := To_Duration(Clock - Start_Time);
         Process.Position.Y := Process_State'Pos(State);
         Store_Trace;
      end;

   begin
      accept Init(Id: Integer; Seed: Integer; Symbol: Character) do
         Reset(G, Seed);
         Process.Id := Id;
         Process.Symbol := Symbol;
         Process.Position := (X => Id, Y => Process_State'Pos(Local_Section));
         Nr_of_Steps := Min_Steps + Integer(Float(Max_Steps - Min_Steps) * Random(G));
         Time_Stamp := To_Duration(Clock - Start_Time);
         Store_Trace;
      end Init;

      accept Start do
         null;
      end Start;

      for Step in 0 .. Nr_of_Steps / 4 - 1 loop
         -- Local_Section
         delay Min_Delay + (Max_Delay - Min_Delay) * Duration(Random(G));

         Change_State(Start);

         if Process.Symbol = 'R' then
            -- Logika dla czytelników
            Room_Monitor.Start_Read;

            Change_State(Reading_Room);
            delay Min_Delay + (Max_Delay - Min_Delay) * Duration(Random(G));

            Change_State(Stop);
            Room_Monitor.Stop_Read;

         else
            -- Logika dla pisarzy
            Room_Monitor.Start_Write;

            Change_State(Reading_Room);
            delay Min_Delay + (Max_Delay - Min_Delay) * Duration(Random(G));

            Change_State(Stop);
            Room_Monitor.Stop_Write;
         end if;

         Change_State(Local_Section);
      end loop;

      Printer.Report(Traces);
   end Process_Task_Type;

   -- Lokalne zmienne głównego tasku
   Process_Tasks: array(0 .. Nr_Of_Processes - 1) of Process_Task_Type;

begin
   -- Inicjalizacja tasków: 10 czytelników (R) i 5 pisarzy (W)
   for I in 0 .. 9 loop
      Process_Tasks(I).Init(I, Seeds(I + 1), 'R');
   end loop;
   for I in 10 .. 14 loop
      Process_Tasks(I).Init(I, Seeds(I + 1), 'W');
   end loop;

   -- Start tasków
   for I in Process_Tasks'Range loop
      Process_Tasks(I).Start;
   end loop;

end Readers_Writers;