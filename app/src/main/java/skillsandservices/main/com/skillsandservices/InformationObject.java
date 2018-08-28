package skillsandservices.main.com.skillsandservices;

import java.util.Date;

public class InformationObject {
    private String itemName;
    private String Quantity;
    private String date;

    public InformationObject() {

    }

    public InformationObject(String itemName, String quantity, String date) {
        this.itemName = itemName;
        Quantity = quantity;
        this.date = date;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

