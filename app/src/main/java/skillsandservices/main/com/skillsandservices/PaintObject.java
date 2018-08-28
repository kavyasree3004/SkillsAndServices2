package skillsandservices.main.com.skillsandservices;

public class PaintObject {
    private String noOfOrders;

    private String typeOfPainting;
    private String dateToBeDelivered;

    public PaintObject() {
    }

    public PaintObject(String noOfOrders, String typeOfPainting, String dateToBeDelivered) {
        this.noOfOrders = noOfOrders;
        this.typeOfPainting = typeOfPainting;
        this.dateToBeDelivered = dateToBeDelivered;
    }

    public String getNoOfOrders() {
        return noOfOrders;
    }

    public void setNoOfOrders(String noOfOrders) {
        this.noOfOrders = noOfOrders;
    }

    public String getTypeOfPainting() {
        return typeOfPainting;
    }

    public void setTypeOfPainting(String typeOfPainting) {
        this.typeOfPainting = typeOfPainting;
    }

    public String getDateToBeDelivered() {
        return dateToBeDelivered;
    }

    public void setDateToBeDelivered(String dateToBeDelivered) {
        this.dateToBeDelivered = dateToBeDelivered;
    }
}
