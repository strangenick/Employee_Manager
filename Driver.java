//Honor Pledge: I pledge that I have neither
//given nor received any undue help on this assignment.
//
//strangni
//
//CS 240 - Assignment 5 - A Healthy Dose of Inheritance
//Created by: Nicholas Strange

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Driver{
	//arrays used to store each created employee:
	static PharmacyManager[] pharmManagerArray = new PharmacyManager[1];
	static StaffPharmacist[] staffPharmArray = new StaffPharmacist[1];
	static StaffTechnician[] staffTechArray = new StaffTechnician[1];
	static SeniorTechnician[] seniorTechArray = new SeniorTechnician[1];

	public static void main (String[] args){
		//variables used to store user's menu selections:
		int selection1 = 0;         
		int selection2 = 0; 
		
		double hoursWorked = -1;  //stores number of hours worked by all employees (will be edited by user)
		Scanner input = new Scanner(System.in);  //used for getting user input
		
		System.out.println("\nWelcome to the CSCI 240 Healthy Pharmacy!");
		System.out.print("\n1. Load Employees (From File)\n2. Exit Program\nEnter your selection: ");  //first menu
		try{
			selection1 = input.nextInt();
		}catch(InputMismatchException ime){ 
			input.next(); //consumes erroneous entry
		}
		while (selection1 != 1 && selection1 != 2){  //forces user to enter a valid option
			System.out.print("\nInvalid choice! Please select option 1 or 2: ");
			try{
				selection1 = input.nextInt();
			}catch(InputMismatchException x){
				input.next(); //consumes erroneous entry
			}
		}
		
		if (selection1 == 1) {  //user has selected to load employees from text file
			try{
				//strategy for reading text file data:
				InputStream inputStream = new FileInputStream("employees.txt");
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader buffer = new BufferedReader(reader);
				String line = buffer.readLine();  //returns the next line in the text file
				line = buffer.readLine();
				
				while (line != null){
					makeEmployee(line);  //call to method that creates an instance of the appropriate employee class from a line in the text file and stores it in the respective array (method defined below)
					line = buffer.readLine();
				}
				
				buffer.close();
				System.out.println("\nEmployees successfully loaded!");
			}catch(FileNotFoundException fnfe){
				System.out.println("The employee file cannot be found. Make sure it exists and is in the right folder, and rerun the program.");
			}catch(IOException e){
				System.err.println(e);
			}
			
			
			
			while (selection2 != 4){  //while the user hasn't selected to end the program
				System.out.print("\n1. Print Employee Information\n2. Enter Hours Worked\n3. Calculate Paychecks\n4. Exit Program\nEnter your selection: "); //second menu
				try{
					selection2 = input.nextInt();
				}catch(InputMismatchException imex){
					input.next();  //consumes erroneous entry
				}
				
				switch(selection2) {
					case 1:  //User has selected to print all employees' info; call each employee class' print method:
						pharmManagerArray[0].printPM();
						staffPharmArray[0].printSP();
						seniorTechArray[0].printSeniorTech();
						staffTechArray[0].printStaffTech();
						break;
				
					case 2:  //User has selected to enter the hours worked (all employees work the same number of hours):
						System.out.print("\nPlease enter the hours worked: ");
						try{
							hoursWorked = input.nextDouble();
						}catch(InputMismatchException imme) {
							input.next(); //consumes erroneous entry
						}
						break;
					
					case 3:  //User has selected to print paychecks for each employee:
						if (hoursWorked == -1) {  //check to see if user has entered the number of hours first
							System.out.println("\nPlease enter the hours worked (Option #2) before trying to calculate the paycheck amounts!");
						}else{
							//if so, call each employee's printPaycheck method, passing the hours worked entered from case 2:
							pharmManagerArray[0].printPaycheck(hoursWorked);
							staffPharmArray[0].printPaycheck(hoursWorked);
							seniorTechArray[0].printPaycheck(hoursWorked);
							staffTechArray[0].printPaycheck(hoursWorked);
						}
						break;

					case 4:
						break;
					
					default:
						System.out.println("\nInvalid choice! Please select option 1, 2, 3, or 4.");
				}
			}
		}
		
		System.out.println("\nGoodbye!\n");
	
	}
	
	//method that takes in a line from the text file and creates an employee from it:
	private static void makeEmployee(String line) {
	
		String[] parsed = line.split(",");  //delimits the line by commas and stores string 'tokens' in an array
		
		//assign the 'tokens' created by line.split to their respective variables (each line contains an employee type, ID#, first name, and last name):
		String empType = parsed[0];
		//make employeeType an integer:
		int employeeType = 0;
		try{
			employeeType = Integer.parseInt(empType);  
		}catch(NumberFormatException nf){
			System.err.println(nf);
		}
		
		String id = parsed[1];
		//make the ID# and integer:
		int ID = 0;
		try{
			ID = Integer.parseInt(id);  //make the ID# an integer
		}catch (NumberFormatException nfe){
			System.err.println(nfe);
		}					
		
		String fName = parsed[2];
		String lName = parsed[3];
		
		//use the above variables to call constructors and store new employee in its appropriate array: 
		switch(employeeType) {
			case 1:
				PharmacyManager pharmManager = new PharmacyManager(ID, fName, lName);
				pharmManagerArray[0] = pharmManager;
				break;
			case 2:
				StaffPharmacist staffPharm = new StaffPharmacist(ID, fName, lName);
				staffPharmArray[0] = staffPharm;
				break;
			case 3:
				StaffTechnician staffTech = new StaffTechnician(ID, fName, lName);
				staffTechArray[0] = staffTech;
				break;
			case 4:
				SeniorTechnician seniorTech = new SeniorTechnician(ID, fName, lName);
				seniorTechArray[0] = seniorTech;
				break;
			default:
				System.out.println("\nThe employee has an invalid type; please change it!");
		}
	}
}
