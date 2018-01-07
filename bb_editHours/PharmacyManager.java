//Honor Pledge: I pledge that I have neither
//given nor received any undue help on this assignment.
//
//strangni
//
//CS 240 - Assignment 5 - A Healthy Dose of Inheritance - BlackBelt: Variable Hours
//Created by: Nicholas Strange

public class PharmacyManager extends StaffPharmacist {  //inheritance
	
	private boolean isLeader;
	
	public PharmacyManager(){  //default constructor
		this.hourlyRate = 50.0;
	}
	
	public PharmacyManager(int ID, String fName, String lName) {  //overloaded constructor
		this.ID = ID;
		this.fName = fName;
		this.lName = lName;
		this.hourlyRate = 50.0;
		this.isLeader = true;
		this.isLicensed = true;
	}
	
	public void printEmployee() {
		String fullName = this.fName+" "+this.lName;
		System.out.printf("\nID: %-12d Name: %-19s Rate: %.1f     Licensed: %-10s Has Leadership: %s", this.ID, fullName, this.hourlyRate, this.isLicensed?"true":"false", this.isLeader?"true":"false");
	}

	public void printPaycheck() {
		double paycheck = this.hoursWorked*this.hourlyRate;
		System.out.printf("\nID: %d     Check Amount: $%.2f", this.ID, paycheck);
	}
}
