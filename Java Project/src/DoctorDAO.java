/**
 * The database is created to run entries and allow CRUD operations.
 * @author Ben
 * Ben Longbottom-Smith
 *16054339
 *
 */
// successful week 6 exercise 1; can now add new records into table using DoctorDAO
import java.sql.Connection;
import java.util.Set;
import java.util.HashSet;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DoctorDAO {
	static Connection c = null;
	static Statement s = null;
	ResultSet r = null;
	
	public String ID;
	public String name;
	public String gender;
	public String natInscNo;    // NI number
	public String dob;        // date of birth
	public String address;
	public String postcode;
	public String specialism;
	public String startDate;
	public String email;
	
	/**Get Database Connection
	 *  
	 * @return Statement Object
	 */
	  public static Statement getConnection() {

			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:doctordb.sqlite");
				s = c.createStatement();				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException c) {
				c.printStackTrace();
			}
			
			return s;
	  }
		
	   /**Close any open database connection
	    * 
	    */
	  public static void closeConnection() {
		   try {
			   if(s!=null) {
				   s.close();
			   }
			   if(c!=null) {
				   c.close();
			   }
		   }
		   catch (SQLException e) {
				e.printStackTrace();
		   }
	   }
	  
	  /**Retrieve all Contacts
		 * 
		 * @return
		 */
	  public ArrayList<Doctor> selectAllEmployees() { // selects all doctors
			
		 ArrayList<Doctor> doctorsArray = new ArrayList<Doctor>();
			try {
				
				String sql = "SELECT * FROM  doctors";
				ResultSet rs = getConnection().executeQuery(sql);
				System.out.println(sql);
				
				if(rs != null) {
					while(rs.next()) {
						
						Doctor doctor = new Doctor(this.name, this.gender, this.natInscNo, this.dob, this.address, this.postcode, this.specialism, this.startDate, this.email);
						
						try {
							doctor.setID(rs.getString("ID"));
							doctor.setName(rs.getString("Name"));
							doctor.setGender(rs.getString("Gender"));
							doctor.setNatInscNo(rs.getString("NIN")) ;
							doctor.setDob(rs.getString("DOB")) ;
							doctor.setAddress(rs.getString("Address")) ;
							doctor.setPostcode(rs.getString("Postcode")) ;
							doctor.setSpecialism(rs.getString("Specialism")) ;
							doctor.setStartDate(rs.getString("StartDate")) ;
							doctor.setEmail(rs.getString("Email"));
							//System.out.println("Email equals = " + rs.getString("Email")); // uses an element of an element (needed for error checking- just prints email
						   }
						   catch(SQLException s) {
							   s.printStackTrace();
						   }					
						
						doctorsArray.add(doctor);
						//System.out.println("Doctor has been added to web array");
						//System.out.println("the first array is: " + doctorsArray);
					}
					
					rs.close();
				}
			}
			
			catch (SQLException s) {
			System.out.println("Doctor has not been added to web array");
			}
			for (int i = 0; i < doctorsArray.size(); i++) { // checks each record in sql
		          System.out.println(doctorsArray.get(i) );
			}
			closeConnection();
			return doctorsArray;
		 }
	  
	  public ArrayList<Consultant> selectAllCons() { // selects all consultants
			
			 ArrayList<Consultant> consArray = new ArrayList<Consultant>();
				try {
					
					String sql = "SELECT * FROM  consultants";
					ResultSet rs = getConnection().executeQuery(sql);
					System.out.println(sql);
					
					if(rs != null) {
						while(rs.next()) {
							
							Consultant consultant = new Consultant(this.name, this.gender, this.natInscNo, this.dob, this.address, this.postcode, this.specialism, this.startDate, this.email);
							
							try {
								consultant.setID(rs.getString("ID"));
								consultant.setName(rs.getString("Name"));
								consultant.setGender(rs.getString("Gender"));
								consultant.setNatInscNo(rs.getString("NIN")) ;
								consultant.setDob(rs.getString("DOB")) ;
								consultant.setAddress(rs.getString("Address")) ;
								consultant.setPostcode(rs.getString("Postcode")) ;
								consultant.setSpecialism(rs.getString("Specialism")) ;
								consultant.setStartDate(rs.getString("StartDate")) ;
								consultant.setEmail(rs.getString("Email"));
								//System.out.println("Email equals = " + rs.getString("Email")); // uses an element of an element (needed for error checking- just prints email
							   }
							   catch(SQLException s) {
								   s.printStackTrace();
							   }					
							
							consArray.add(consultant);
							//System.out.println("Doctor has been added to web array");
							//System.out.println("the first array is: " + doctorsArray);
						}
						
						rs.close();
					}
				}
				
				catch (SQLException s) {
				System.out.println("Doctor has not been added to web array");
				}
				for (int i = 0; i < consArray.size(); i++) { // checks each record in sql
			          System.out.println(consArray.get(i) );
				}
				closeConnection();
				return consArray;
			 }
	  
	public static void selectEmployeeByName(String docName) throws SQLException { // selects a doctor by name
			
		try {
				
				String query = "\nSELECT * FROM doctors WHERE NAME = '" + docName + "'"; // shows up 'Ronald' which is correct!!
				ResultSet rs = getConnection().executeQuery(query);
				System.out.println(query);
				
				while (rs.next()) {
					System.out.println(rs.getString("ID") + " "
					+ rs.getString("Name") + " "
					+ rs.getString("Gender") + " "
					+ rs.getString("DOB") + " "
					+ rs.getString("Address") + " "
					+ rs.getString("Postcode") + " "
					+ rs.getString("NIN") + " "
					+ rs.getString("Specialism") + " "
					+ rs.getString("StartDate") + " "
					+ rs.getString("Email"));
				}
			}
				
			catch (SQLException s) {
				System.out.println("Doctor has not been selected");
			}

					closeConnection();

		}
	  
	  public ArrayList<Doctor> doctorBySpec(String spec) {// allows a search for doctors by specialism on server
			
			 ArrayList<Doctor> doctorsArray = new ArrayList<Doctor>();
				try {
					
					String sql = "SELECT * FROM  doctors WHERE Specialism  = '" + spec + "'";
					ResultSet rs = getConnection().executeQuery(sql);
					System.out.println(sql);
					
					if(rs != null) {
						while(rs.next()) {
							
							Doctor doctor = new Doctor(this.name, this.gender, this.natInscNo, this.dob, this.address, this.postcode, this.specialism, this.startDate, this.email);
							
							try {
								doctor.setID(rs.getString("ID"));
								doctor.setName(rs.getString("Name"));
								doctor.setGender(rs.getString("Gender"));
								doctor.setNatInscNo(rs.getString("NIN")) ;
								doctor.setDob(rs.getString("DOB")) ;
								doctor.setAddress(rs.getString("Address")) ;
								doctor.setPostcode(rs.getString("Postcode")) ;
								doctor.setSpecialism(rs.getString("Specialism")) ;
								doctor.setStartDate(rs.getString("StartDate")) ;
								doctor.setEmail(rs.getString("Email"));
								//System.out.println("Email equals = " + rs.getString("Email")); // uses an element of an element (needed for error checking- just prints email
							   }
							   catch(SQLException s) {
								   s.printStackTrace();
							   }					
							
							doctorsArray.add(doctor);
							//System.out.println("Doctor has been added to web array");
							//System.out.println("the first array is: " + doctorsArray);
						}
						
						rs.close();
					}
				}
				
				catch (SQLException s) {
				System.out.println("Doctor has not been added to web array");
				}
				for (int i = 0; i < doctorsArray.size(); i++) { // checks each record in sql
			          System.out.println(doctorsArray.get(i) );
				}
				closeConnection();
				return doctorsArray;
			 }
	  
	  public ArrayList<Consultant> consBySpec(String spec) { // allows a search for consultants by specialism

			
			 ArrayList<Consultant> consArray = new ArrayList<Consultant>();
				try {
					
					String sql = "SELECT * FROM  consultants WHERE SPECIALISM  = '" + spec + "'";
					ResultSet rs = getConnection().executeQuery(sql);
					System.out.println(sql);
					
					if(rs != null) {
						while(rs.next()) {
							
							Consultant consultant = new Consultant(this.name, this.gender, this.natInscNo, this.dob, this.address, this.postcode, this.specialism, this.startDate, this.email);
							
							try {
								consultant.setID(rs.getString("ID"));
								consultant.setName(rs.getString("Name"));
								consultant.setGender(rs.getString("Gender"));
								consultant.setNatInscNo(rs.getString("NIN")) ;
								consultant.setDob(rs.getString("DOB")) ;
								consultant.setAddress(rs.getString("Address")) ;
								consultant.setPostcode(rs.getString("Postcode")) ;
								consultant.setSpecialism(rs.getString("Specialism")) ;
								consultant.setStartDate(rs.getString("StartDate")) ;
								consultant.setEmail(rs.getString("Email"));
								//System.out.println("Email equals = " + rs.getString("Email")); // uses an element of an element (needed for error checking- just prints email
							   }
							   catch(SQLException s) {
								   s.printStackTrace();
							   }					
							
							consArray.add(consultant);
							//System.out.println("Doctor has been added to web array");
							//System.out.println("the first array is: " + doctorsArray);
						}
						
						rs.close();
					}
				}
				
				catch (SQLException s) {
				System.out.println("Doctor has not been added to web array");
				}
				for (int i = 0; i < consArray.size(); i++) { // checks each record in sql
			          System.out.println(consArray.get(i) );
				}
				closeConnection();
				return consArray;
			 }
	
	  public ArrayList<Doctor> doctorByName(String nm) {  // allows a search of doctors by name on server
			
			 ArrayList<Doctor> doctorsArray = new ArrayList<Doctor>();
				try {
					
					String sql = "SELECT * FROM  doctors WHERE Name  = '" + nm + "'";
					ResultSet rs = getConnection().executeQuery(sql);
					System.out.println(sql);
					
					if(rs != null) {
						while(rs.next()) {
							
							Doctor doctor = new Doctor(this.name, this.gender, this.natInscNo, this.dob, this.address, this.postcode, this.specialism, this.startDate, this.email);
							
							try {
								doctor.setID(rs.getString("ID"));
								doctor.setName(rs.getString("Name"));
								doctor.setGender(rs.getString("Gender"));
								doctor.setNatInscNo(rs.getString("NIN")) ;
								doctor.setDob(rs.getString("DOB")) ;
								doctor.setAddress(rs.getString("Address")) ;
								doctor.setPostcode(rs.getString("Postcode")) ;
								doctor.setSpecialism(rs.getString("Specialism")) ;
								doctor.setStartDate(rs.getString("StartDate")) ;
								doctor.setEmail(rs.getString("Email"));
							 }
							   catch(SQLException s) {
								   s.printStackTrace();
							   }					
							
							doctorsArray.add(doctor);
						}
						
						rs.close();
					}
				}
				
				catch (SQLException s) {
				System.out.println("Doctor has not been added to web array");
				}
				for (int i = 0; i < doctorsArray.size(); i++) { // checks each record in sql
			          System.out.println(doctorsArray.get(i) );
				}
				closeConnection();
				return doctorsArray;
			 }

	  /**
		 * Insert Contact into database
		 * 
		 * @param c            		Contact Object
		 * @return				 	True if inserted
		 * @throws SQLException  	Any error message thrown
		 */
		public static boolean insertEmployee(Doctor c) throws SQLException {  // inserts doctor into database
			boolean b = false;
			try {
				String sql = "insert into doctors VALUES (null, '" + c.getName() 
						+ "' , '" + c.getGender() + "' , '" + c.getNatInscNo() 
						+ "' , '" + c.getDob() + "' ,  '" + c.getAddress() 
						+ "' , '" + c.getPostcode() + "' , '" + c.getSpecialism() 
						+ "' , '" + c.getStartDate() + "' , '" + c.getEmail() + "') \n"; 
				
				b = getConnection().execute(sql);
				System.out.println(sql);
				System.out.println("Doctor " + c.getName() + " has been added into db");
				closeConnection();
			}
			catch (SQLException s) {
				throw new SQLException("Doctor Not Added");
			}	  
			return b;	 }
		
		
		public static boolean deleteDoctorById(int docId) throws SQLException { //deletes a doctor by it's ID
			boolean b = false;
			try {
			String   query = "\nDELETE FROM doctors WHERE ID = '" + docId + "' \n"; 
			
			b = getConnection().execute(query);
			System.out.println(query);
			System.out.println("Record is deleted from doctors table");
			closeConnection();
		} 
			catch (SQLException e) {
				throw new SQLException("Doctor Not Added");
			}
			return b; 
		
		}
		
		public static boolean insertConsultants(Consultant c) throws SQLException { // inserts consultant to database
			boolean b = false;
			try {
				String sql = "insert into consultants VALUES (null, '" + c.getName() 
						+ "' , '" + c.getGender() + "' , '" + c.getNatInscNo() 
						+ "' , '" + c.getDob() + "' ,  '" + c.getAddress() 
						+ "' , '" + c.getPostcode() + "' , '" + c.getSpecialism() 
						+ "' , '" + c.getStartDate() + "' , '" + c.getEmail() + "') \n"; 
				
				b = getConnection().execute(sql);
				System.out.println(sql);
				System.out.println("Consultant " + c.getName() + " has been added into db");
				closeConnection();
			}
			catch (SQLException s) {
				throw new SQLException("Consultant Not Added");
			}	  
			return b;	 }
			
}

	