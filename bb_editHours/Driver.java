//Honor Pledge: I pledge that I have neither
//given nor received any undue help on this assignment.
//
//strangni
//
//CS 240 - Assignment 5 - A Healthy Dose of Inheritance - BlackBelt: Variable Hours
//Created by: Nicholas Strange

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Driver{
	//ArrayList to store all created employees:
	static ArrayList<Employee> employeeAL = new ArrayList<Employee>();
	
	public static void main (String[] args){
		//variable used to store user's menu selection:
		int selection1 = 0;  
		int selection2 = 0;
		boolean hoursHaveBeenEntered = false; //variable to check whether the user has entered the number of hours
		Scanner input = new Scanner(System.in);  //used for getting user input
		
		System.out.println("\nWelcome to the CSCI 240 Healthy Pharmacy!");
		System.out.print("\n1. Load Employees (From File)\n2. Exit Program\nEnter your selection: "); //first menu
		try{
			selection1 = input.nextInt();
		}catch(InputMismatchException ime){
			input.next(); //consumes erroneous entry
		}
		while (selection1 != 1 && selection1 != 2){
			System.out.print("\nInvalid choice! Please select option 1 or 2: ");
			try{
				selection1 = input.nextInt();
			}catch(InputMismatchException x){
				input.next(); //consumes erroneous entry
			}
		}
		
		if (selection1 == 1) {
			try{
				//strategy for reading text file data:
				InputStream inputStream = new FileInputStream("employees.txt");
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader buffer = new BufferedReader(reader);
				String line = buffer.readLine();
				line = buffer.readLine();//returns the next line in the text file

				while (line != null){
					makeEmployee(line);  //call to method that creates an employee from a line in the text file and stores it in the ArrayList
					line = buffer.readLine();
				}

				buffer.close();
			}catch(FileNotFoundException fnfe){
					System.out.println("The employee file cannot be found. Make sure it exists and is in the right folder, and rerun the program."); //second menu
			}catch(IOException e){
				System.err.println(e);
			}

			System.out.print("\nEmployees successfully loaded!");
			
			while (selection2 != 4){
				System.out.print("\n\n1. Print Employee Information\n2. Enter Hours Worked\n3. Calculate Paychecks\n4. Exit Program\nEnter your selection: ");
				try{
					selection2 = input.nextInt();
				}catch(InputMismatchException im){
					input.next(); //consumes erroneous entry
				}
				
				switch(selection2) {
					case 1:
						for(int i = 0; i < employeeAL.size(); i++){
							employeeAL.get(i).printEmployee();  //prints all employees' info
						}
						break;
				
					case 2:
						System.out.print("\nEnter the ID# of the employee for whom you wish to enter hours: ");
						int empToEdit = input.nextInt();
						
						for(int k = 0; k < employeeAL.size(); k++) {	  //finds the employee for whom the user wishes to enter hours
							if(employeeAL.get(k).ID == empToEdit){
								System.out.print("\nPlease enter the hours worked for employee "+employeeAL.get(k).ID+": ");
								try{
									employeeAL.get(k).hoursWorked = input.nextDouble();  //sets hoursWorked attribute for this employee
								}catch(InputMismatchException imme){
									input.next();  //consumes erroneous entry
								}
							}
						}
						hoursHaveBeenEntered = true;  //user has now entered hours
						break;
							
					case 3:
						if (!hoursHaveBeenEntered) { //print error message if the user hasn't entered hours first
							System.out.print("\nPlease enter the hours worked (Option #2) before trying to calculate the paycheck amounts!");
						}else{
							for(int j = 0; j < employeeAL.size(); j++){
								employeeAL.get(j).printPaycheck();	//prints all employees' paychecks
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
	
		String[] parsed = line.split(",");  //delimits the line by commas and stores 'tokens' in an array
		//assign the new 'tokens' created by line.split to their respective variables
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
		
		//use above variables to call constructors and store each created employee in the ArrayList:
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

