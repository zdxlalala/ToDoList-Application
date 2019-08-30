package ui;

import model.parser.LibraryParser;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;

public class ListDemo extends JFrame{
    private JLabel title;
    private JPanel rootPanel;
    private JButton enterButton;
    private JLabel apiLabel;


    public ListDemo() {
        String path = "Abstract 3.jpg";
        ImageIcon background = new ImageIcon(path);
        JLabel imagelabel = new JLabel(background);
        imagelabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        setTitle("ToDoList Application");
        setContentPane(this.rootPanel);
        this.getLayeredPane().add(imagelabel, new Integer(Integer.MIN_VALUE));
        JPanel imagepanel = (JPanel) this.getContentPane();
        imagepanel.setOpaque(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = this.getHeight();
        int width = this.getWidth();
        setLocation(screenWidth-width/2, screenHeight-height/2);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("Start")){
                    new SelectionWindow();
                    setVisible(false);
                }
            }
        });
    }


    public String apiInfo() throws IOException {
        BufferedReader br = null;
        String info = null;
        try {
            String apikey = "aabf20d12f6f6a0423025a69010973d4"; //fill this in with the API key they email you
            String vancouverweatherquery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
            String theURL=vancouverweatherquery+apikey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            LibraryParser libParser = new LibraryParser();

            String line;
            String fileName = "library.json";

            StringBuilder sb = new StringBuilder();

            PrintWriter writer = new PrintWriter(fileName,"UTF-8");

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append("\n");
                writer.println(line);
                writer.close();

            }

            info = libParser.parseInfo2(new JSONObject(sb.toString()));
        } catch (JSONException e) {
            System.out.println("Error parsing JSON data");
            e.printStackTrace();
        } finally {

            if (br != null) {
                br.close();
            }
        }
        return info;
    }

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        title = new JLabel("ToDoList Application");

        String info = apiInfo();
        apiLabel = new JLabel(info);
    }

    public static void main(String[] args) {
        new ListDemo().setVisible(true);
    }
}
