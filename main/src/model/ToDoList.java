package model;

import model.Exceptions.SameThingException;
import model.Exceptions.TooManyThingsToDoException;
import model.Obeserver.ToDoListObserver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ToDoList implements ToDoListObserver, Loadable, Saveable{
    private List<Item> items;
    private String type;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ToDoList(List<Item> items, String type){
        this.items = items;
        this.type = type;
    }

    public void setItems(Item item){
        if(!items.contains(item)){
            items.add(item);
            item.setToDoList(this);
        }
    }

    public void addItemToToDoList(Item item, ToDoList toDoList) throws TooManyThingsToDoException, SameThingException {
        int finishedItem = 0;
        List<Item> toDoItems = toDoList.getItems();
        int sizeList = toDoItems.size();
        if(sizeList == 0) {
            toDoList.setItems(item);
        }
        else{
            for (Item i : toDoItems) {
                if (i.getStatus())
                    finishedItem++;
            }
            if (finishedItem >= 3)
                throw new TooManyThingsToDoException();

            for (Item i : toDoItems) {
                if (i.getName().equals(item.getName())) {
                    throw new SameThingException();
                }
            }
            toDoList.setItems(item);
        }
    }

    public void removeItem(int index){
        items.remove(index);
    }

    public void setType(String type){
        this.type = type;
    }

    public List<Item> getItems(){
        return items;
    }

    public String getType() {
        return type;
    }

    public void getItemsDescription(){
        List<String> listOfId = new ArrayList<>();
        for (Item i: items){
            listOfId.add("\t"+i.getName() +"(" + i.getType() + ")" + ":" + " Due " + i.showDueDate(i));
        }
        assert (listOfId.size() == items.size());

        for (String s: listOfId){
            System.out.println(s);
        }
    }

    //REQUIRES: n > 0
    //EFFECTS: show the items in the given list with index
    public void showList() {
        int n = 1;
        for (Item i : items) {
            String name = i.getName();
            System.out.println("[" + n++ + "]" + name);
        }

    }

    public void listDescription(){
        System.out.println(this.getType() + ":");
        this.getItemsDescription();
    }

    public Boolean stringStatus(String status){
        if(status.equals("Completed"))
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDoList)) return false;
        ToDoList toDoList = (ToDoList) o;
        return Objects.equals(type, toDoList.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type);
    }

    @Override
    public void update(ToDoList toDoList) {
        System.out.println("One new item is added to the list, "
                +"you have "+toDoList.getItems().size()+" items in your "
                + toDoList.getType() + "!");
    }

    @Override
    public void load(List<String> lines, ToDoList loi) throws ParseException {
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            if (partsOfLine.get(1) == "(Urgent)") {
                items.add(new UrgentItem(partsOfLine.get(0), stringStatus(partsOfLine.get(3)), sdf.parse(partsOfLine.get(2))));
            } else {
                items.add(new RegularItem(partsOfLine.get(0), stringStatus(partsOfLine.get(3)), sdf.parse(partsOfLine.get(2))));
            }
        }

    }

    @Override
    public void save(ToDoList loi, String filename) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filename,"UTF-8");

        for (Item i: items){
            String ItemString;
            ItemString = i.itemToString(i);
            writer.println(ItemString);
        }
        writer.close();
    }

    public ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" : ");
        return new ArrayList<>(Arrays.asList(splits));
    }


}
