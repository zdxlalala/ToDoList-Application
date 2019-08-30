package model;

import model.Exceptions.SameThingException;
import model.Exceptions.TooManyThingsToDoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RunToDoList implements Saveable, Loadable{
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<Item> toDoItems = new ArrayList<>();
    private List<Item> crOuItems = new ArrayList<>();
    private ToDoList ToDoList = new ToDoList(toDoItems, "ToDoList");
    private ToDoList CrOuList = new ToDoList(crOuItems, "CrOuList");
    private Map<ToDoList, List<Item>> toDoListItemMap = new HashMap<>();


    public ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    @Override
    public void save(ToDoList toDoList, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");

        for (Item i: toDoList.getItems()){
            String ItemString;
            ItemString = i.itemToString(i);
            writer.println(ItemString);
        }
        writer.close();
    }

    @Override
    public void load(List<String> lines, ToDoList toDoList) throws ParseException {
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Item urgentItem = new UrgentItem("", false, sdf.parse("1111-1111-11"));
            Item regularItem = new RegularItem("", false, sdf.parse("1111-1111-11"));
            if (partsOfLine.get(1) == "(Urgent)"){
                loadItem(partsOfLine,toDoList,urgentItem);
            }
            else {
                loadItem(partsOfLine,toDoList,regularItem);
            }
            toDoListItemMap.put(toDoList,toDoList.getItems());
        }
    }

    public void loadItem(List<String> strings, ToDoList toDoList,Item item) throws ParseException {
        item.setItem(item,strings.get(0),false,
                sdf.parse(strings.get(2)), item.getType(),toDoList);
        toDoList.setItems(item);
    }


    public void runApp() throws ParseException, IOException {
        int operation;

        System.out.println("Load a todo List? " + "[1] yes, " + "[2] no");
        operation = scanner.nextInt();
        if (operation == 1) {
            List<String> lines = Files.readAllLines(Paths.get("savedlist.txt"));
            load(lines, ToDoList);
        }
        run();
    }

    public void run() throws ParseException, UnsupportedEncodingException, FileNotFoundException {
        tdList();
        ToDoList.listDescription();
        CrOuList.listDescription();
    }

    public void scanItem(Item i) throws ParseException, SameThingException, TooManyThingsToDoException {
        System.out.println("Enter the Item name");
        scanner.nextLine();
        i.setName(scanner.nextLine());
        i.setStatus(false);

        System.out.println("Enter due date for the item (yyyy-MM-dd)");

        String newDate = scanner.nextLine();
        i.setDueDate(sdf.parse(newDate));
        ToDoList.addItemToToDoList(i,ToDoList);
        toDoListItemMap.put(ToDoList,ToDoList.getItems());
    }

    public void tryScanItem(Item item) throws ParseException {
        try {
            scanItem(item);
        } catch (SameThingException sameThingException) {
            System.out.println("Same Item already exist!");
        } catch (TooManyThingsToDoException e) {
            System.out.println("Too many completed items!");
        } finally {
            System.out.println("Continue!");
        }
    }

    public void operationAdd(int operation,Item regularItem, Item urgentItem) throws ParseException {
        if(operation == 1)
            tryScanItem(regularItem);
        else
            tryScanItem(urgentItem);
    }

    public void tdList() throws ParseException, FileNotFoundException, UnsupportedEncodingException {
        int operation;
        int selected;
        Helper helper = new Helper();
        Date defaultD = sdf.parse("1111-11-11");

        while (true) {
            Item regularItem = new RegularItem("", false, defaultD);
            Item urgentItem = new UrgentItem("", false, defaultD);

            System.out.println("what would you like to do?" +
                    "\n[1] add a regular item" +
                    "\n[2] add an urgent item" +
                    "\n[3] cross off an item" +
                    "\n[4] show all the items");
            operation = scanner.nextInt();

            if (helper.equal(operation, 1) || helper.equal(operation, 2)) {
                operationAdd(operation,regularItem,urgentItem);
                continue;
            }
            if (helper.equal(operation, 3)) {
                System.out.println("which Item would you like to cross off");
                ToDoList.showList();
                selected = scanner.nextInt();
                int numberOfItems = (ToDoList.getItems().size() -1);
                String listType = ToDoList.getType();

                System.out.println("By crossing off this item, you have "
                        +numberOfItems+
                        " number of items left on your "+listType+" list!");
                CrOuList.setItems(toDoListItemMap.get(ToDoList).get(helper.minusOne(selected)));
                ToDoList.removeItem(helper.minusOne(selected));

            }
            if (helper.equal(operation, 4)) {
                int answer;
                System.out.println("Do you want to save the lists? " +
                        "\n[1] yes" +
                        "\n[2] no");
                scanner.nextLine();
                answer = scanner.nextInt();

                if (answer == 1) {
                    save(ToDoList,"todolist.txt");
                }
                break;
            }
        }
    }


}
