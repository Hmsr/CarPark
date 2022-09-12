import java.util.Date;
/**
 * Project 1 - Car Class
 * @author Hamish Sandys-Renton
 * @version JDK 1.8
 * @date Created on 5th September 2022
 */
public class Car
{
    // instance variables
    private String regNumber;
    private String carOwner; 
    private boolean isStaffMember;
    private Date date;

    /**
     * Constructor for objects of class Car
     */
    public Car(String regNumber, String carOwner, boolean isStaffMember){
        this.regNumber = regNumber;
        this.carOwner = carOwner;
        this.isStaffMember = isStaffMember;
        this.date = new Date();
    }

    
    /**
     * Getters and Setters
     */
    public String getRegNumber(){
        return regNumber;
    }
    
    
    public Date getDate() {
        return date;
    }
    
    
    public void setRegNumber(String regNumber){ 
        this.regNumber = regNumber;
    }
    
    
    public String getCarOwner(){
        return carOwner;
    }
    
    
    public void setCarOwner(String carOwner){ 
        this.carOwner = carOwner;
    }
    
    
    public boolean getIsStaffMember() {
        return isStaffMember;
    }
    
    
    public void setIsStaffMember(boolean isStaffMember){ 
        this.isStaffMember = isStaffMember;
    }
    
    
}
