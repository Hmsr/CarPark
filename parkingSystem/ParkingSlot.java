import java.util.Date;
/**
 * Project 1 - Parking Slot Class
 * @author Hamish Sandys-Renton
 * @version JDK 1.8
 * @date Created on 5th September 2022
 */
public class ParkingSlot{
    // instance variables
    private String slotID;
    private boolean isStaffSlot;
    private Car car;

    /**
     * Constructor for objects of class ParkingSlot
     */
    public ParkingSlot(String slotID,boolean isStaffSlot){
        this.slotID = slotID;
        this.isStaffSlot = isStaffSlot;

    }

    
    /**
     * Getters and Setters 
     */
    public String getSlotID(){
        return slotID;
    }

    
    public void setSlotID(String slotID){ 
        this.slotID = slotID;
    }

    
    public boolean getIsStaffSlot() {
        return isStaffSlot;
    }

    
    public void setIsStaffSlot(boolean isStaffSlot) {
        this.isStaffSlot = isStaffSlot;
    }

    
    public String getOwner() {
        return this.car.getCarOwner();
    }

    
    public boolean getStaff() {
        return this.car.getIsStaffMember();
    }

    
    /**
     * Method to check is Occupied
     * @return boolean - if a slot is occupied
     */
    public boolean isOccupied() {
        boolean occupied; 
        if (this.car == null) {
            occupied = false;
        } else {
            occupied = true;
        }
        return occupied;
    }

    /**
     * Method to unpark a car
     * Sets this.car to a null value
     */
    public void unParkCar()  {
        this.car = null;
    }

    /**
     * Method to park a car
     * Sets car with the values passed from the carpark class
     */
    public void parkCar(String regNumber, String carOwner, boolean isStaffMember) {
        this.car= new Car(regNumber, carOwner, isStaffMember);
    }

    /**
     * Method to get the reg number for the carpark class
     * @return the car reg number
     */
    public String regN() {
        if (this.car != null) {
            return this.car.getRegNumber();
        } else {
            return "";
        }
    }
    
    /**
     * Method to return the date and time the car was parked at
     * @return date
     */
    public Date getDate() {
        Date dateNow = null;
        if (this.car != null) {
            dateNow = this.car.getDate();
        } 
        return dateNow;
    }
    

}
