package model;

import model.Obeserver.Subject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Item extends Subject{
    protected String name;
    protected Boolean status;
    protected Date dueDate;
    protected String type;
    protected List<ToDoList> toDoList;

    public Item(String name, Boolean status, Date date, String type) {
        this.name = name;
        this.status = status;
        this.dueDate = date;
        this.type = type;
        this.toDoList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: set the name of an item to the given string
    public void setName(String n){
        this.name = n;
        }

    //MODIFIES: this
    //EFFECTS: set the status of an item to the given status
    public void setStatus(Boolean s){
        this.status = s;
    }

    //MODIFIES: this
    //EFFECTS: set the dueDate to the given dueDate
    public void setDueDate(Date d)  {
        this.dueDate = d;
    }

    //EFFECTS: set the type to the given type
    public void setType(String type) {
        this.type = type;
    }

    public void setToDoList(ToDoList tdList){
        if(!toDoList.contains(tdList)){
            toDoList.add(tdList);
            tdList.setItems(this);
            notifyObserver(tdList);
        }
        addObserver(tdList);
    }

    //EFFECTS: set an item
    public void setItem(Item i, String n, Boolean b, Date d, String t, ToDoList tdList){
        i.setName(n);
        i.setStatus(b);
        i.setDueDate(d);
        i.setType(t);
        i.setToDoList(tdList);
    }

    //EFFECTS: check whether the item has passed the due date
    //from https://stackoverflow.com/questions/15925509/java-compare-two-dates/15925576#15925576
    public boolean isPassDueDate(Item i, Date date){
        return i.getDueDate().compareTo(date) > 0;
    }

    //EFFECTS: get the item's name
    public String getName (){
        return name;
    }

    //EFFECTS: get the item's status
    public Boolean getStatus(){
        return status;
    }

    //EFFECTS: get the item's due date
    public Date getDueDate(){
        return dueDate;
    }

    //EFFECTS: get the item's type
    public String getType() {
        return type;
    }

    public List<ToDoList> getToDoList() {
        return toDoList;
    }

    //MODIFIES: this
    //EFFECTS: extend the due date by given days
    public static void extendDueDate(Item i, String delay) {
        Date d = i.getDueDate();
        long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
        d.setTime(myTime * 1000);
    }


    //EFFECTS: return the due date of given item in string
    public String showDueDate(Item i){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String DueDate = "";
        DueDate = sdf.format(i.getDueDate());
        return DueDate;
    }

    //REQUIRES: an unfinished item
    //MODIFIES: this
    //EFFECTS: set the given item's status to finished(true)
    public void finishedItem(){
        setStatus(true);
    }

    public String itemToString(Item i){
        return i.getName() +" : " +"(" + i.getType() + ")" + " : " + i.showDueDate(i) + " : " + i.itemStatus();
    }

    public String itemStatus(){
        if (status)
            return "Completed";
        else
            return "In progress";
    }

    //MODIFIES: this
    //EFFECTS: change the type of an item
    public abstract void changeType(Item item);

    //EFFECTS: show string of type of an item
    public abstract String typeString();

}

