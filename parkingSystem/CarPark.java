import java.util.ArrayList;
import java.util.Date;
/** Project 1 - Car Park Class
 * @author Hamish Sandys-Renton
 * @version JDK 1.8
 * @date Created on 5th September 2022
 */
public class CarPark
{
    private ArrayList<ParkingSlot> parkingSlots;
    /**
     * Constructor for objects of class CarPark
     */
    public CarPark()
    {
        parkingSlots = new ArrayList<ParkingSlot>();
    }

    /** 
     * Method to return the number of parking slots in the parkingSlots array.
     * @return    Returns the size of parkingSlots Array
     */
    public int printNumParkingSlots() {
        return parkingSlots.size();
    }

    /**
     * Method to add a Staff parking slot
     * @param slot ID 
     */
    public void addStaffParkingSlot(String slotID) {
        parkingSlots.add(new ParkingSlot(slotID, true)); 
    }

    /**
     * Method to add a Visitor parking slot
     */
    public void addVisitorParkingSlot(String slotID) {
        parkingSlots.add(new ParkingSlot(slotID, false)); 
    }

    /**
     * Method to check if a slot already exists
     * @param parking slot ID
     * @return boolean exists
     */
    public boolean slotExists(String slotID){
        //System.out.println(parkingSlots);
        boolean exists= false;
        ParkingSlot slot = findSlotByID(slotID);
        if (slot != null) {
            exists = true;
        }
        return exists;
    }

    /**
     * Method to check if a Car already exists
     * @param car registration number
     * @return boolean exists
     */
    public boolean carExists(String reg){
        //System.out.println(parkingSlots);
        boolean exists= false;
        ParkingSlot slot = findSlotByReg(reg);
        if (slot != null) {
            exists = true;
        }
        return exists;
    }

    /**
     * Method to print spots and their information
     */
    public void listSpots() {
        Date currentTime = new Date();
        for (ParkingSlot slot: parkingSlots) {
            boolean occupied = slot.isOccupied();
            String owner = "";
            String reg = "";
            String info = "";
            if (occupied == true) {
                owner = slot.getOwner();
                reg = slot.regN();
                info += (", Registration: " + reg + ", Owner: " + owner);
            }
            boolean isStaffSlot = slot.getIsStaffSlot();
            System.out.println("Slot ID: "+ slot.getSlotID() + ", Staff Slot: "+isStaffSlot + ", Occupied: " + occupied + info);
            if (slot.getDate() != null) {
                long diffTime = currentTime.getTime() - slot.getDate().getTime();
                long diffHours= (diffTime / (1000 * 60 * 60))% 24;
                long diffMins= (diffTime / (1000 * 60))% 60;
                long diffSecs= (diffTime / 1000) % 60;
                System.out.println("Car has been parked for " + diffHours + " hours " + diffMins + " minutes " + diffSecs + " seconds ");
            }
        }
    }

    /**
     * Method to Park a car in a slot
     * checks if the car already exists in a slot
     * checks if the space is occupied
     * checks if visitor or staff is parking in a visitor or staff space
     * adds car to the slot or prints error string
     * 
     * @param parking slot ID
     * @param Car registration ID
     * @param car owner name
     * @param is staff member? true/false
     */
    public void park(String slotNum, String reg, String owner, boolean staffMem) {
        //check if the reg already exists
        boolean alreadyExists = false;
        ParkingSlot slot = findSlotByID(slotNum);
        if (carExists(reg) ==true) {
            alreadyExists=true;
            System.out.println("ERROR: This Car is Already Parked in a Spot!");
        }
        if (alreadyExists==false) { 
            if(slot.isOccupied() == false) {
                if (slot.getIsStaffSlot() == true && staffMem == true) {
                    slot.parkCar(reg, owner, staffMem);
                    System.out.println("\nCar Parked in Staff Slot:" +slot.getSlotID() + " on " + slot.getDate());
                } else if (slot.getIsStaffSlot() == true && staffMem == false) {
                    System.out.println("\nERROR: You Must Park in a Visitor Slot\n");
                } else if (slot.getIsStaffSlot() == false && staffMem == false) {
                    slot.parkCar(reg, owner, staffMem);
                    System.out.println("\nCar Parked in Visitor Slot:" +slot.getSlotID() + " on " + slot.getDate());
                } else if (slot.getIsStaffSlot() == false && staffMem == true) {
                    System.out.println("\nERROR: You Must Park in a Staff Slot\n");
                }                    
            } else {
                System.out.println("ERROR: Slot is already occupied. Try a different space.");
            }
        }      
    } 

    /**
     * Method to remove car from slot
     * @param Car registration number
     */
    public void unPark(String regNumber) {
        ParkingSlot slot = findSlotByReg(regNumber);
        if (slot != null) {
            if (slot.regN().equals(regNumber)) {
                slot.unParkCar();
                System.out.println("Unparked "+ regNumber);
            }
        } else {
            System.out.println("Error: Car Not Found");
        }

    }

    /**
     * Method to find a car
     * @return string with car and slot info
     * @param Car registration number
     */
    public String findCarSlot(String reg) {
        String returnString= "";
        ParkingSlot slot = findSlotByReg(reg);
        if (slot !=null) {
            if (slot.isOccupied() == true) {
                returnString += ("Car ID " + reg + " is owned by " + (slot.getStaff() ? " Staff Member " : " Visitor ") + slot.getOwner() +" and is in slot number " + slot.getSlotID());
            } else {
                returnString += ("ERROR: Car ID : " + reg + " is not in the car park:");
            }
        }

        return returnString;
    } 

    /**
     * Method to find a car by registration
     * @return slot object
     * @param Car registration number
     */
    public ParkingSlot findSlotByReg(String reg) {
        ParkingSlot sl = null;
        for (ParkingSlot slot: parkingSlots) {
            String s = slot.regN();
            if (s.equals(reg)) {
                sl = slot;
            }
        } 
        return sl;
    }

    /**
     * Method to find a car by the Slot ID
     * @return slot object
     * @param parking slot ID
     */
    public ParkingSlot findSlotByID(String slotID) {
        ParkingSlot sl = null;
        for (ParkingSlot slot: parkingSlots) {
            String s = slot.getSlotID();
            if (s.equals(slotID)) {
                sl = slot;
            }
        } 
        return sl;
    }

    /**
     * Method to delete a parking slot
     * checks if it is occupied before deletion
     * @param parking slot ID
     */
    public void deleteParkingSlot(String slotNumber){
        ParkingSlot slot = findSlotByID(slotNumber);
        if (slot != null) {
            if (slot.isOccupied() == false) {
                parkingSlots.remove(slot);
                System.out.println("Slot removed");
            } else {
                System.out.println("ERROR: Cannot remove - Slot is occupied");
            }
        } else {
            System.out.println("Error: Slot does not exist");
        }
    }

    /**
     * Method to find the date object when a car was created
     *@param car registration number
     *@return date
     */
    public Date getDate(String reg) {
        Date date= null;
        ParkingSlot slot = findSlotByReg(reg);
        if (slot != null) {
            if (slot.regN().equals(reg)) {
                date = slot.getDate();
            }
        }
        return date;
    }

}    

