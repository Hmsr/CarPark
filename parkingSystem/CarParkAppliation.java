import java.util.Scanner; 
import java.util.ArrayList;
/**
 * Project 1 - Parking Spot System - Main Class
 * @author Hamish Sandys-Renton
 * @version JDK 1.8
 * @date Created on 5th September 2022
 */
public class CarParkAppliation{

    /**
     * Constructor for objects of class Appliation
     * @param
     */
    public static void main(String[] args) {
        boolean isRunning = true;
        String carRegex = "([A-Z][0-9][0-9][0-9][0-9])";
        String slotRegex = "([A-Z][0-9][0-9])";
        CarPark carPark = new CarPark();
        Scanner input = new Scanner(System.in);
        System.out.println("--------\nWelcome to the Car Park System\n");
        while (isRunning ==true) {
            printMenu();
            switch (input.next()) {
                case "1" :System.out.println("Find Car\n");
                    System.out.println("Enter Car Registration Number\n");
                    String carReg = input.next();
                    if (!(carReg.matches(carRegex))) {
                        System.out.println("Incorrect Car Registration Format. Try Again.");
                        break;
                    }
                    String carOwner = "";
                    carOwner = carPark.findCarSlot(carReg);
                    if (carOwner != "") {
                        System.out.println(carOwner);
                    } else {
                        System.out.println("ERROR: Car Not Found!");
                    }
                    //System.out.println("Car owner is "+slotNumber.carOwner());
                    //System.out.println("Car is parked in "+slotNumber.getSlotID());
                    break;
                    
                case "2" :System.out.println("\nEnter Slot ID");  // add a parking slot case
                    System.out.println("Parking Slot ID must be 1 Capital Letter and 4 Single Numbers e.g. 'R3497'\n");
                    String userSlotID = input.next();
                    if (carPark.slotExists(userSlotID) == true) {
                        System.out.println("\nERROR: Slot ID Already Exists!\n");
                        break;
                    }
                    if (!(userSlotID.matches(slotRegex))) {
                        System.out.println("\nERROR: Incorrect Slot ID format\n");
                        break;
                    }
                    System.out.println("Is This a Staff Parking Slot?\n");
                    System.out.println("Enter: '1' for YES or '2' for NO\n");
                    String staffSlot = input.next();
                    if (!(staffSlot.equals("1") || staffSlot.equals("2"))){
                        System.out.println("ERROR: You Must Enter a '1' or a '2'\n");
                        break;
                    }
                    if (staffSlot.equals("1")) {
                        carPark.addStaffParkingSlot(userSlotID); 
                        System.out.println("Staff Slot Added");
                    } else {
                        carPark.addVisitorParkingSlot(userSlotID);
                        System.out.println("Visitor Slot Added");
                    }
                    break;
                  
                case "3" :System.out.println("Delete Parking Slot");
                    System.out.println("Enter Slot Number to Delete.");
                    String sNum = input.next();
                    if (!(sNum.matches(slotRegex))) {
                        System.out.println("ERROR: Incorrect Slot ID format");
                        break;
                    }
                    carPark.deleteParkingSlot(sNum);
                    break;
                    
                case "4" :System.out.println("\nList All Parking Slots:");
                    carPark.listSpots();
                    break;
                    
                case "5" : //Park a Car
                    System.out.println("Enter Parking Slot ID\n");
                    String slotNum = input.next();
                    if (carPark.slotExists(slotNum) == false) {
                        System.out.println("\nERROR: Slot ID Does Not Exist.\n");
                        break;
                    }
                    System.out.println("Enter Car Registration\n");
                    System.out.println("Car Registration Must be 1 Capital Letter and 4 Single Numbers e.g. 'R3447'");
                    String reg = input.next();
                    if (!(reg.matches(carRegex))) {
                        System.out.println("Incorrect Car Registration Format. Try Again.");
                        break;
                    }
                    System.out.println("Enter Car Owner Full Name\n");
                    String owner = input.next();
                    System.out.println("Is the Car Owner a Staff Member?\n");
                    System.out.println("Enter '1' For YES or 'Any Key' for NO\n");
                    boolean staffMem = input.next().equals("1") ? true : false;
                    carPark.park(slotNum, reg, owner, staffMem);
                    if (carPark.getDate(reg) != null && carPark.findCarSlot(reg) == slotNum) {
                        System.out.println("Car Parked Time: "+carPark.getDate(reg)+ "\n");
                    }
                    break;  
                    
                case "6" : System.out.println("Un-Park a car\n");
                    System.out.println("Enter Reg Number to Unpark?\n");
                    String unparkReg = input.next();
                    if (!(unparkReg.matches(carRegex))) {
                        System.out.println("Incorrect Car Registration Format. Try Again.");
                        break;
                    }
                    carPark.unPark(unparkReg);
                    break;
                    
                case "7" : isRunning = false;
                    System.out.println("--Goodbye--");
                    break;
                    
                default :System.out.println("Enter a Number Between '1' and '7'");
            }
        }

    }

    /**
     * Method to print menu to console
     */
    public static void printMenu() {
        System.out.print("--------\n--Menu--\n-------- \n1.Find a Car \n2.Add a parking slot \n3.Delete a parking slot \n4.List all parking slots\n5.Park a car\n6.Un-Park a car \n7.EXIT\n--------\n--------");
        System.out.println("\nenter a number to choose an option");
    }
    


}
