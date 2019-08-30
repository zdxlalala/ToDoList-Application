package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SelectionWindow extends JFrame{
    private JPanel selectionPanel;
    private JButton loadAnExistListButton;
    private JButton createANewListButton;
    private JLabel description;

    public SelectionWindow() throws HeadlessException {
        setTitle("Selection Window");
        setContentPane(this.selectionPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(600,360);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = this.getHeight();
        int width = this.getWidth();
        setLocation(screenWidth-width/2, screenHeight-height/2);

        loadAnExistListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if(cmd.equals("Load an exist list")){
                    new LoadListWindow();
                    setVisible(false);
                }
            }
        });
        createANewListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();

                if(cmd.equals("Create a new list")){
                    new NewListWindow();
                    setVisible(false);
                }
            }
        });
    }
}
