package model;


import java.util.Date;
import java.util.List;


public class RegularItem extends Item {

    public RegularItem(String name, Boolean status, Date date) {
        super(name,status,date,"Regular");
    }

    @Override
    public void changeType(Item item) {
        if (item.getType() == "Regular"){
            item.setType("Urgent");
        }
    }

    @Override
    public String typeString() {
        return "Regular Item";
    }
}


