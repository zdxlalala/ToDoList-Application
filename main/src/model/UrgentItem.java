package model;

import java.util.Date;
import java.util.List;

public class UrgentItem extends Item {

    public UrgentItem(String name, Boolean status, Date date) {
        super(name, status, date, "Urgent");
    }

    @Override
    public void changeType(Item item){
        if (item.getType() == "Urgent"){
            item.setType("Regular");
        }
    }

    @Override
    public String typeString() {
        return "Urgent Item";
    }
}
