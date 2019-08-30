package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmptySelectionWindow extends JFrame{
    private JPanel emptySelectionWindow;
    private JButton backButton;

    public EmptySelectionWindow() {
        setTitle("Error");
        setContentPane(this.emptySelectionWindow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(300,180);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if(cmd.equals("Back")) {
                    setVisible(false);
                }
            }
        });
    }
}
