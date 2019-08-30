package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExtendWindow extends JFrame{
    private JTextField newDate;
    private JPanel extendWindow;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel label;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ExtendWindow(NewListWindow newListWindow) {
        setTitle("ExtendWindow");
        setContentPane(this.extendWindow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(400,220);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = this.getHeight();
        int width = this.getWidth();
        setLocation(screenWidth-width/2, screenHeight-height/2);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = newDate.getText();
                int row = newListWindow.getTable().getSelectedRow();

                try {
                    sdf.parse(date);
                    newListWindow.getTable().setValueAt(date,row,2);

                    setVisible(false);
                } catch (ParseException e1) {
                    new WrongFormatWindow();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public ExtendWindow(LoadListWindow loadListWindow) {
        setTitle("ExtendWindow");
        setContentPane(this.extendWindow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(400,220);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = this.getHeight();
        int width = this.getWidth();
        setLocation(screenWidth-width/2, screenHeight-height/2);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = newDate.getText();
                int row = loadListWindow.getTable().getSelectedRow();

                try {
                    sdf.parse(date);
                    loadListWindow.getTable().setValueAt(date,row,2);

                    setVisible(false);
                } catch (ParseException e1) {
                    new WrongFormatWindow();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
