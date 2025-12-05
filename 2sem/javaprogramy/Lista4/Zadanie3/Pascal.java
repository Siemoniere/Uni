import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
@SuppressWarnings("deprecation")
public class Pascal{

    public static void main(String[] args) {
        new Pascal();
    }
        JFrame okno;
        JTextArea trójkąt;
        JButton przycisk;
        JTextField wpisywanie;

    public Pascal() {
        okno = new JFrame("Trójkąt Pascala");
        trójkąt = new JTextArea();
        przycisk = new JButton("Generuj");
        wpisywanie = new JTextField();
        
        okno.setSize(new Dimension(800, 1000));
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.add(trójkąt, BorderLayout.CENTER);
        okno.add(wpisywanie, BorderLayout.NORTH);
        okno.add(przycisk, BorderLayout.SOUTH);

        przycisk.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent e){
            try{
                generowanie(wpisywanie.getText());
            } catch (NumberFormatException ex ){
                System.out.println("błąd");
                }
            }
            });
            okno.setVisible(true);
        }

    public void generowanie (String komenda){
        try{
            Process proces = Runtime.getRuntime().exec("./TrojkatPascala "+ komenda);
            String wynik= czytanie(proces.getInputStream());
            String error = czytanie(proces.getErrorStream());
            int wartość = proces.waitFor();
            if(wartość==0){
                trójkąt.setText(wynik);
            } else trójkąt.setText("błąd"+error);
        }
        catch(InterruptedException ex){
            trójkąt.setText("błąd "+ex.getMessage());
        } catch (IOException ex){
            trójkąt.setText("błąd IO"+ex.getMessage() );
        } catch  (SecurityException ex){
            trójkąt.setText("Brak uprawnien "+ ex.getMessage());
        }
    }
    public String czytanie (InputStream strumień) throws IOException{
        Scanner scanner = new Scanner(strumień).useDelimiter("\\A");
        String line;
        if(scanner.hasNext()){
            line = scanner.next();
        } else line = "";
        scanner.close();
        return line;
    }
}