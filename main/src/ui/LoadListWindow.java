package ui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoadListWindow extends JFrame{
    private JPanel loadListWindow;
    private JButton removeItemButton;
    private JButton addItemButton;
    private JScrollPane scrollPanel;
    private JButton saveButton;
    private JTable toDoListTable;
    private JButton extendButton;
    private JButton completeButton;
    private DefaultTableModel defaultTableModel;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public LoadListWindow() {
        setTitle("ToDoList Application");
        setContentPane(this.loadListWindow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(950,500);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = this.getHeight();
        int width = this.getWidth();
        setLocation(screenWidth-width/2, screenHeight-height/2);

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if(cmd.equals("Add Item")){
                    new AddItemWindow(LoadListWindow.this);
                }
            }
        });

        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = toDoListTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) toDoListTable.getModel();

                if(row < 0) {
                    new EmptySelectionWindow();
                }else{
                    model.removeRow(row);
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoList toDoList = new ToDoList(new ArrayList<>(),"ToDoList");

                for(int i = 0; i<toDoListTable.getRowCount(); i++) {
                    Item item;
                    Date date;
                    Boolean status;

                    try {
                        date = sdf.parse(toDoListTable.getValueAt(i, 2).toString());

                        if (toDoListTable.getValueAt(i, 3).toString().equals("In progress")) {
                            status = false;
                        } else
                            status = true;

                        if (toDoListTable.getValueAt(i, 1).toString().equals("(Urgent)")) {
                            item = new UrgentItem(toDoListTable.getValueAt(i, 0).toString(), status, date);
                        } else
                            item = new RegularItem(toDoListTable.getValueAt(i, 0).toString(), status, date);

                        toDoList.setItems(item);

                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }

                try {
                    toDoList.save(toDoList,"savedlist.txt");
                    new SaveWindow();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = toDoListTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) toDoListTable.getModel();

                if(row < 0) {
                    new EmptySelectionWindow();
                }else{
                    Object obj = "Completed";
                    model.setValueAt(obj,row,3);
                }
            }
        });

        extendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if(cmd.equals("Extend"))
                    new ExtendWindow(LoadListWindow.this);
            }
        });
    }

    public JTable getTable(){
        return toDoListTable;
    }


    private void createUIComponents() throws ParseException, IOException {
        // TODO: place custom component creation code here
        setTable(new ArrayList<>());
        toDoListTable = new JTable(defaultTableModel);
    }

    public void setTable(List<Item> toDoItems) throws IOException, ParseException {
        ToDoList tdList = new ToDoList(toDoItems, "ToDoList");
        List<String> lines = Files.readAllLines(Paths.get("savedlist.txt"));

        tdList.load(lines,tdList);

        Object[][] obj = new Object[tdList.getItems().size()][4];
        String[] columnsName = {"Name","Type","Due Date (YYYY-MM-DD)","Status"};

        for(int i = 0; i<tdList.getItems().size(); i++){
            for (int j = 0; j<4; j++) {
                switch (j) {
                    case 0:
                        obj[i][j] = tdList.getItems().get(i).getName();
                        break;
                    case 1:
                        obj[i][j] = tdList.getItems().get(i).getType();
                        break;
                    case 2:
                        obj[i][j] = tdList.getItems().get(i).showDueDate(tdList.getItems().get(i));
                        break;
                    case 3:
                        obj[i][j] = tdList.getItems().get(i).itemStatus();
                        break;

                }
            }
        }
        defaultTableModel = new DefaultTableModel(obj,columnsName);
    }
}
