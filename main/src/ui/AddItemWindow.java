package ui;

import model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddItemWindow extends JFrame{
    private JTextField itemDueDate;
    private JTextField itemName;
    private JButton urgentButton;
    private JButton regularButton;
    private JPanel addItemWindow;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public AddItemWindow(LoadListWindow loadListWindow) {
        setTitle("Add Item");
        setContentPane(this.addItemWindow);
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

        regularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = itemName.getText();
                String date = itemDueDate.getText();
                Boolean contain = false;
                DefaultTableModel model = (DefaultTableModel) loadListWindow.getTable().getModel();
                Object[] obj = new Object[4];



                for (int i = 0; i<model.getRowCount(); i++){
                    if(model.getValueAt(i,0).equals(name))
                        contain = true;
                }

                if( name.equals("") || contain) {
                    new ErrorWindow();
                } else {
                    try {
                        sdf.parse(date);
                        model.addRow(obj);
                        int rowIndex = model.getRowCount() - 1;
                        model.setValueAt(name, rowIndex, 0);
                        model.setValueAt("Regular", rowIndex, 1);
                        model.setValueAt(date, rowIndex, 2);
                        model.setValueAt("In progress", rowIndex, 3);

                        new SuccessWindow();
                        setVisible(false);
                    } catch (ParseException e1) {
                        new WrongFormatWindow();
                    }
                }
            }
        });


        urgentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = itemName.getText();
                String date = itemDueDate.getText();
                Boolean contain = false;
                DefaultTableModel model = (DefaultTableModel) loadListWindow.getTable().getModel();
                Object[] obj = new Object[4];


                for (int i = 0; i<model.getRowCount(); i++){
                    if(model.getValueAt(i,0).equals(name))
                        contain = true;
                }

                if( name.equals("") || contain) {
                    new ErrorWindow();
                }else {
                    try {
                        sdf.parse(date);
                        model.addRow(obj);
                        int rowIndex = model.getRowCount() - 1;
                        model.setValueAt(name, rowIndex, 0);
                        model.setValueAt("Urgent", rowIndex, 1);
                        model.setValueAt(date, rowIndex, 2);
                        model.setValueAt("In progress", rowIndex, 3);

                        new SuccessWindow();
                        setVisible(false);
                    } catch (ParseException e1) {
                        new WrongFormatWindow();
                    }
                }
            }
        });
    }

    public AddItemWindow(NewListWindow newListWindow) throws HeadlessException {
        setTitle("Add Item");
        setContentPane(this.addItemWindow);
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

        regularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = itemName.getText();
                String date = itemDueDate.getText();
                Boolean contain = false;
                DefaultTableModel model = (DefaultTableModel) newListWindow.getTable().getModel();
                Object[] obj = new Object[4];

                if(model.getRowCount()>=0) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (model.getValueAt(i, 0).equals(name))
                            contain = true;
                    }

                    if (name.equals("") || contain) {
                        new ErrorWindow();
                    } else {
                        try {
                            sdf.parse(date);
                            model.addRow(obj);
                            int rowIndex = model.getRowCount() - 1;
                            model.setValueAt(name, rowIndex, 0);
                            model.setValueAt("Regular", rowIndex, 1);
                            model.setValueAt(date, rowIndex, 2);
                            model.setValueAt("In progress", rowIndex, 3);

                            new SuccessWindow();
                            setVisible(false);
                        } catch (ParseException e1) {
                            new WrongFormatWindow();
                        }
                    }
                }else {
                    if (name.equals("") || contain) {
                        new ErrorWindow();
                    } else {
                        try {
                            sdf.parse(date);
                            model.addRow(obj);
                            int rowIndex = model.getRowCount() - 1;
                            model.setValueAt(name, rowIndex, 0);
                            model.setValueAt("Regular", rowIndex, 1);
                            model.setValueAt(date, rowIndex, 2);
                            model.setValueAt("In progress", rowIndex, 3);

                            new SuccessWindow();
                            setVisible(false);
                        } catch (ParseException e1) {
                            new WrongFormatWindow();
                        }
                    }
                }
            }
        });


        urgentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = itemName.getText();
                String date = itemDueDate.getText();
                Boolean contain = false;
                DefaultTableModel model = (DefaultTableModel) newListWindow.getTable().getModel();
                Object[] obj = new Object[4];

                if(model.getRowCount()>=0) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (model.getValueAt(i, 0).equals(name))
                            contain = true;
                    }
                    if (name.equals("") || contain) {
                        new ErrorWindow();
                    } else {
                        try {
                            sdf.parse(date);
                            model.addRow(obj);
                            int rowIndex = model.getRowCount() - 1;
                            model.setValueAt(name, rowIndex, 0);
                            model.setValueAt("Urgent", rowIndex, 1);
                            model.setValueAt(date, rowIndex, 2);
                            model.setValueAt("In progress", rowIndex, 3);

                            new SuccessWindow();
                            setVisible(false);
                        } catch (ParseException e1) {
                            new WrongFormatWindow();
                        }
                    }

                }else {
                    if (name.equals("") || contain) {
                        new ErrorWindow();
                    } else {
                        try {
                            sdf.parse(date);
                            model.addRow(obj);
                            int rowIndex = model.getRowCount() - 1;
                            model.setValueAt(name, rowIndex, 0);
                            model.setValueAt("Urgent", rowIndex, 1);
                            model.setValueAt(date, rowIndex, 2);
                            model.setValueAt("In progress", rowIndex, 3);

                            new SuccessWindow();
                            setVisible(false);
                        } catch (ParseException e1) {
                            new WrongFormatWindow();
                        }
                    }
                }
            }
        });
    }
}
