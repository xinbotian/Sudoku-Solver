import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// inherited from JFrame
class Myframe extends JFrame {
    public static Object obj = new Object();
    // crate interface
    public final static JTextField[][] filed = new JTextField[9][9];

    public Myframe() {
        // Init interface，set all slots to be empty
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
                filed[a][b] = new JTextField();
                filed[a][b].setText("");
            }
        }

        // Design layout，put textfield into the layout
        JPanel jpan = new JPanel();
        jpan.setLayout(new GridLayout(9, 9));
        for (int a = 8; a > -1; a--) {
            for (int b = 0; b < 9; b++) {
                jpan.add(filed[b][a]);
            }
        }

        // Layout: center
        add(jpan, BorderLayout.CENTER);
        JPanel jpb = new JPanel();

        // two buttons for calculate and close
        JButton button1 = new JButton("calc");
        JButton button2 = new JButton("close");

        // put button on interface
        jpb.add(button1);
        jpb.add(button2);

        // Add listener to button
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                synchronized (obj) {
                    for (int a = 0; a < 9; a++) {
                        for (int b3 = 0; b3 < 9; b3++) {
                            int pp = 0;
                            // get the value from the slots
                            if (!(filed[a][b3].getText().trim().equals(""))) {
                                pp = Integer.parseInt(filed[a][b3].getText()
                                        .trim());
                                Calculate.b[a][b3] = pp;
                            }
                        }
                    }
                }

                synchronized (obj) {
                    // init thread to calculate
                    new Thread(new Calculate()).start();
                }
            }
        });

        // button2 use api to close the program
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        // Setting layout
        add(jpb, BorderLayout.SOUTH);
    }
}