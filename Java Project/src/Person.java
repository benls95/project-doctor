/**
 * The person class is used for extension in the Doctor class.
 * @author Ben
 *Ben Longbottom-Smith
 *16054339
 */

public class Person {
	
	private String name;
	private String gender;
	private String natInscNo;    // NI number
	private String dob;        // date of birth
	private String address;
	private String postcode;
	
	public Person(String name, String gender, String natInscNo, String dob, String address, String postcode) {   // data constructor       // enter conditions of class
		
		this.name =name;
		this.gender = gender;      // sort out char string
		this.natInscNo =natInscNo;
		this.dob =dob;
		this.address =address;
		this.postcode =postcode;	          
		                  
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		
		this.gender = gender;
	}

	public String getNatInscNo() {
		return natInscNo;
	}

	public void setNatInscNo(String natInscNo) {
		this.natInscNo = natInscNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {  // added 'this'
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {  // added 'this'
		this.postcode = postcode;
	}
	
	public String toString() {   // displays text we want for each entry
		
		String p = "\nName : " + this.getName() + "\nGender : " + this.getGender()
				+ "\nNI : " + this.getNatInscNo() + "\nD.O.B. : " + this.getDob()
				+ "\nAddress : " + this.getAddress() + "\nPostcode : " + this.getPostcode() ;
		return p;
	}
	
}
