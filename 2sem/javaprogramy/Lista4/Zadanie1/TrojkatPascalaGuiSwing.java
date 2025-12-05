import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrojkatPascalaGuiSwing implements ActionListener{

    public static void main(String[] args) {
        new TrojkatPascalaGuiSwing();
    }
        JFrame okno;
        JPanel panel;
        JButton przycisk;
        JTextField wpisywanie;
        JLabel label;
        int n;

    public TrojkatPascalaGuiSwing() {
        okno = new JFrame("Trójkąt Pascala");
        panel = new JPanel();
        przycisk = new JButton("Generuj");
        wpisywanie = new JTextField();

        okno.setSize(800, 1000);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //panel.setBackground(Color.BLUE);
        przycisk.addActionListener(this);
        
        okno.add(wpisywanie, BorderLayout.NORTH);
        okno.add(przycisk, BorderLayout.SOUTH);
        okno.add(panel);
        okno.setVisible(true);
    }

    @Override
    public void actionPerformed( ActionEvent e){
        try{
            n= Integer.parseInt(wpisywanie.getText());
            if (n>=21){
                label = new JLabel("Zła dana");
                panel.add(label);
                okno.revalidate();
            }else{
            panel.setLayout(new GridLayout(n, 1));
            panel.removeAll();
            for (int i=0; i<n; i++){
                Rysowanie rysowanie = new Rysowanie(i);
                String s="";
                for (int j=0; j<i+1;j++){
                    s+=rysowanie.element(j)+ " ";
                }
                label = new JLabel(s);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(label);
                }
                okno.revalidate();
            }
        }catch (NumberFormatException exception){
            System.out.println("błąd");
        }
    }
}
