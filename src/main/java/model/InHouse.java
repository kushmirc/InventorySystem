package model;

/** Class InHouse is a child of the Parts class.
 * It is used to create instances of InHouse parts, and contains the machineId instance field,
 * which is unique to this class.
 * @author Kush Mirchandani*/
public class InHouse extends Part {

    /** declares a machineId variable to be used for In-house parts*/
    private int machineId;

    /** This is the InHouse constructor method.
     * This method is used to create instances of InHouse parts.
     * @param id the id of the new InHouse part
     * @param name the name of the new InHouse part
     * @param price the price of the new InHouse part
     * @param stock the stock of the new InHouse part
     * @param min the minimum stock of the new InHouse part
     * @param max the maximum stock of the new InHouse part
     * @param machineId the machine ID of the new InHouse part*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /** @return the machineId */
    public int getMachineId() {
        return machineId;
    }

    /** @param machineId  the machineId to set */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

}
