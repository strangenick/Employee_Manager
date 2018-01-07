//Honor Pledge: I pledge that I have neither
//given nor received any undue help on this assignment.
//
//strangni
//
//CS 240 - Assignment 5 - A Healthy Dose of Inheritance - BlackBelt: Any Number of Employees
//Created by: Nicholas Strange

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Driver{
	//ArrayList used to store employees:
	static ArrayList<Employee> employeeAL = new ArrayList<Employee>();
	
	public static void main (String[] args){
		//variables used to store user's menu selections:
		int selection1 = 0; 
		int selection2 = 0;
		
		double hoursWorked = -1; //stores number of hours worked by all employees (will be edited by user)
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
				input.next();  //consumes erroneous entry
			}
		}
		
		if (selection1 == 1) {
			try{
				//strategy for reading text file data:
				InputStream inputStream = new FileInputStream("employees.txt");
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader buffer = new BufferedReader(reader);
				String line = buffer.readLine();  //returns the next line in the text file
				line = buffer.readLine();

				while (line != null){
					makeEmployee(line); //call to method that creates an instance of the appropriate employee class from a line in the text file and stores it in the respective array (method defined below)
					line = buffer.readLine();
				}

				buffer.close();
				System.out.print("\nEmployees successfully loaded!");
			}catch(FileNotFoundException fnfe){
					System.out.println("The employee file cannot be found! Make sure it exists and is in the right folder, and rerun the program.");
			}catch(IOException e){
				System.err.println(e);
			}

			
			
			while (selection2 != 4){
				System.out.print("\n\n1. Print Employee Information\n2. Enter Hours Worked\n3. Calculate Paychecks\n4. Exit Program\nEnter your selection: ");  //second menu
				try{
					selection2 = input.nextInt();
				}catch(InputMismatchException imme){
					input.next(); //consumes erroneous entry
				}
				
				switch(selection2) {
					case 1:
						for(int i = 0; i < employeeAL.size(); i++){  
							employeeAL.get(i).printEmployee();  //print all employees
						}
						break;
				
					case 2:  //collect hours worked from user
						System.out.print("\nPlease enter the hours worked: ");
						try{
							hoursWorked = input.nextDouble();
						}catch(InputMismatchException im){
							input.next();
						}
						break;
					
					case 3:
						if (hoursWorked == -1) {  //user must enter hours worked before printing paychecks
							System.out.print("\nPlease enter the hours worked (Option #2) before trying to calculate the paycheck amounts!");
						}else{
							for(int j = 0; j < employeeAL.size(); j++){
								employeeAL.get(j).printPaycheck(hoursWorked);	 //print all employee paychecks
							}
						}
						break;

					case 4:
						break;
					
					default:
						System.out.print("\nInvalid choice! Please select option 1, 2, 3, or 4.");
				}
			}
		}
		
		System.out.println("\nGoodbye!\n");
	
			
	}



	private static void makeEmployee(String line) {
	
		String[] parsed = line.split(",");  //delimits the line by commas and stores string 'tokens' in an array
		
		//assign the new 'tokens' created by line.split to their respective variables:
		String empType = parsed[0];
		int employeeType = 0;
		try{
			employeeType = Integer.parseInt(empType);
		}catch(NumberFormatException nf){
			System.err.println(nf);
		}
		String id = parsed[1];
		int ID = 0;
		try{
			ID = Integer.parseInt(id);
		}catch (NumberFormatException nfe){
			System.err.println(nfe);
		}					
		String fName = parsed[2];
		String lName = parsed[3];
		
		//use above variables to call constructors and store created employee in the ArrayList:
		switch(employeeType) {
			case 1:
				PharmacyManager pharmManager = new PharmacyManager(ID, fName, lName);
				employeeAL.add(pharmManager);
				break;
			case 2:
				StaffPharmacist staffPharm = new StaffPharmacist(ID, fName, lName);
				employeeAL.add(staffPharm);
				break;
			case 3:
				StaffTechnician staffTech = new StaffTechnician(ID, fName, lName);
				employeeAL.add(staffTech);
				break;
			case 4:
				SeniorTechnician seniorTech = new SeniorTechnician(ID, fName, lName);
				employeeAL.add(seniorTech);
				break;
			default:
				System.out.println("\nThe employee has an invalid type; please change it!");
		}
	}
}	

