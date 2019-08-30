package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuccessWindow extends JFrame{
    private JPanel successWindow;
    private JButton continueButton;

    public SuccessWindow() {
        setTitle("Success");
        setContentPane(this.successWindow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(300,180);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = this.getHeight();
        int width = this.getWidth();
        setLocation(screenWidth-width/2, screenHeight-height/2);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if(cmd.equals("Continue")) {
                    setVisible(false);
                }
            }
        });
    }
}
