import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class TrPascala extends JFrame{

    JFrame frame;
    JButton przycisk;
    JTextField wpisywanie;
    JTextArea trojkat;

    public TrPascala (){
        setTitle("Trójkąt Pascala c++");
        setSize(900, 700);
        setLayout(new BorderLayout());

        przycisk = new JButton("Generuj");
        add(przycisk, BorderLayout.SOUTH);
        przycisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                stworz(Integer.parseInt(wpisywanie.getText()));
            }
        });

        wpisywanie = new JTextField();
        add(wpisywanie, BorderLayout.NORTH);

        trojkat = new JTextArea();
        trojkat.setEditable(false);
        add(trojkat, BorderLayout.CENTER);
    }

    private void stworz(int n){
    try {
            ProcessBuilder pr = new ProcessBuilder("./TrojkatPascala", Integer.toString(n));
            java.lang.Process p = pr.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = p.waitFor();
            if (exitCode == 0) {
                trojkat.setText(output.toString());
            } else {
                trojkat.setText("Błąd wykonania programu C++");
            }

        } catch (Exception e) {
            trojkat.setText("Błąd: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        TrPascala okno = new TrPascala();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
    }
}
