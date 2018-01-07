//Honor Pledge: I pledge that I have neither
//given nor received any undue help on this assignment.
//
//strangni
//
//CS 240 - Assignment 5 - A Healthy Dose of Inheritance - BlackBelt: Any Number of Employees
//Created by: Nicholas Strange

public class SeniorTechnician extends StaffTechnician {  //inheritance
	
	private boolean hasServiceAward;

	public SeniorTechnician() {  //default constructor
		this.hourlyRate = 25.0;
	}
	
	public SeniorTechnician(int ID, String fName, String lName) {  //overloaded constructor
		this.ID = ID;
		this.fName = fName;
		this.lName = lName;
		this.hourlyRate = 25.0;
		this.hasServiceAward = true;
		this.hasDegree = true;
	}
	
	public void printEmployee() {
		String fullName = this.fName+" "+this.lName;
		System.out.printf("\nID: %-12d Name: %-19s Rate: %.1f     Has Degree: %-8s Has Service Award: %s", this.ID, fullName, this.hourlyRate, this.hasDegree?"true":"false", this.hasServiceAward?"true":"false");
	}

	public void printPaycheck(double hoursWorked) {
		double paycheck = hoursWorked*this.hourlyRate;
		System.out.printf("\nID: %d     Check Amount: $%.2f", this.ID, paycheck);
	}


}
