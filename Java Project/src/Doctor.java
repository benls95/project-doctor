/**
 * The doctor class is used to create doctors and display doctors.
 * @author Ben
 * Ben Longbottom-Smith
 *16054339
 *
 */


public class Doctor extends Person {
	
	private String specialism;
	private String startDate;
	private String email;
	private String ID;
	
	public Doctor(String name, String gender, String natInscNo, String dob, String address, String postcode, String specialism, String startDate, String email) {
		super(name, gender, natInscNo, dob, address, postcode);	// Person variables
		
		               
		this.specialism =specialism;
		this.startDate= startDate;
		this.email =email;
	}

	public String getSpecialism() {
		return specialism;
	}

	public void setSpecialism(String specialism) {
		this.specialism = specialism;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String toString() {   // displays text we want for each entry
		
		String d = "Doctor Name : " + super.getName() + " | Gender : " + super.getGender()
		+ " | NI : " + super.getNatInscNo() + " | D.O.B. : " + super.getDob()
		+ " | Address : " + super.getAddress() + " | Postcode : " + super.getPostcode()   // new doctor variables added
		+ " | Specialism : " + this.getSpecialism() + " | Start Date : " + this.getStartDate()
		+ " | Email : " + this.getEmail() + "\n" ;
		return d;
	}

	
	
}
