//Honor Pledge: I pledge that I have neither
//given nor received any undue help on this assignment.
//
//strangni
//
//CS 240 - Assignment 5 - A Healthy Dose of Inheritance - BlackBelt: Variable Hours
//Created by: Nicholas Strange

public class StaffTechnician extends Employee {  //inheritance

	protected boolean hasDegree;
	
	public StaffTechnician() {  //default constructor
		this.hourlyRate = 20.0;
	}
	
	public StaffTechnician(int ID, String fName, String lName) {  //overloaded constructor
		this.ID = ID;
		this.fName = fName;
		this.lName = lName;
		this.hourlyRate = 20.0;
		this.hasDegree = true;
	}
	
	public void printEmployee() {
		String fullName = this.fName+" "+this.lName;
		System.out.printf("\nID: %-12d Name: %-19s Rate: %.1f     Has Degree: %s", this.ID, fullName, this.hourlyRate, this.hasDegree?"true":"false");
	}

	public void printPaycheck() {
		double paycheck = this.hoursWorked*this.hourlyRate;
		System.out.printf("\nID: %d     Check Amount: $%.2f", this.ID, paycheck);
	}

}
