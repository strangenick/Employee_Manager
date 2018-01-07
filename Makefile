#Honor Pledge: I pledge that I have neither
#given nor received any undue help on this assignment.
#
#strangni
#
#CS 240 - Assignment 5 - A Healthy Dose of Inheritance
#Created by: Nicholas Strange

Driver.class: Driver.java Employee.class PharmacyManager.class StaffPharmacist.class StaffTechnician.class SeniorTechnician.class
	javac Driver.java

Employee.class: Employee.java
	javac Employee.java

PharmacyManager.class: PharmacyManager.java
	javac PharmacyManager.java

StaffPharmacist.class: StaffPharmacist.java
	javac StaffPharmacist.java

StaffTechnician.class: StaffTechnician.java
	javac StaffTechnician.java

SeniorTechnician.class: SeniorTechnician.java
	javac SeniorTechnician.java

clean:
	-rm *.class

run: Driver.class
	java Driver
