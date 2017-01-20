/**
 * The consultant class allows me to add, remove doctors from a 
 * consultant, but also display the teams and create and insert consultants 
 * to the database.
 * @author Ben
 * Ben Longbottom-Smith
 *16054339
 *
 */

import java.util.ArrayList;
//import java.util.Collections;
import java.util.Collection;

public class Consultant extends Doctor {
	                                        // no new variables or attributes
	
	public ArrayList<Doctor> cons = new ArrayList<Doctor>();

	public Consultant(String name, String gender, String natInscNo, String dob, String address, String postcode, 
			String specialism, String startDate, String email) {
			super(name, gender, natInscNo, dob, address, postcode, specialism, startDate, email);
	}

	public String toString() {   // displays text we want for each entry
		
		String d = "Consultant Name : " + super.getName() + " | Gender : " + super.getGender()
		+ " | NI : " + super.getNatInscNo() + " | D.O.B. : " + super.getDob()
		+ " | Address : " + super.getAddress() + " | Postcode : " + super.getPostcode()   // new doctor variables added
		+ " | Specialism : " + this.getSpecialism() + " | Start Date : " + this.getStartDate()
		+ " | Email : " + this.getEmail() + "\n" ;
		return d;
	}
	
	public boolean addDoctor(Doctor newDoctor) {   // adds doctor to team, using their name
		cons.add(newDoctor);
		int size = cons.size();
		
		System.out.println(super.getName() + "'s Consultancy Team: (after insert)");

		System.out.println("Added DR name: " + newDoctor.getName()); // CAN GET ELEMENTS FROM EACH OBJECT
		if (size == 0) {
			System.out.println("No Team Members :(\n");
		}
		for (int i=0; i<size; i++) {
			System.out.println("Doctor #" + (i+1) + "\t " + cons.get(i));
		}
		return true;
	}
	
	public boolean removeDoctor(Doctor oldDoctor) {   // removes doctor from team

		cons.remove(oldDoctor);     
		int size = cons.size();
		System.out.println(super.getName() + "'s Consultancy Team: (after delete)");
		//System.out.println(cons);
		//System.out.println("Size: " + size);
		if (size == 0) {
			System.out.println("No Team Members :(\n");
		}
		for (int i=0; i<size; i++) {
			System.out.println("Doctor #" + (i+1) + "\t " + cons.get(i));
		}
		return true;
	}
	
	public boolean displayTeam() { // displays consultant's team
		int size = cons.size();
		System.out.println(super.getName() + "'s Consultancy Team:");
		//System.out.println("Size: " + size);
		if (size == 0) {
			System.out.println("No Team Members :(\n");
		}
		for (int i=0; i<size; i++) {
			System.out.println("Doctor #" + (i+1) + "\t " + cons.get(i));
		}
		return true;
	}
		
}
