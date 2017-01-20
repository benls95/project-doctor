import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.*;

/**
 * The controller controls the whole project with the database running through it and the HTTP
 * server being enabled to allow info to pass onto a localhost.
 * @author Ben
 * Ben Longbottom-Smith
 *16054339
 *
 */
// test via controller
//CREATES ONLY NULLS ATM DUE TO INABILITY TO ENTER DATA FOR NEW OBJECTS
//need to test CRUD (CREATE, RETRIEVE, UPDATE, DELETE) for db. (e.g. using SELECT, INSERT, DELETE)

public class Controller {
	public static String name;
	public static String gender;
	public static String natInscNo;    // NI number
	public static String dob;        // date of birth
	public static String address;
	public static String postcode;
	public static String specialism;
	public static String startDate;
	public static String email;
	
	public static void main(String[] args) throws SQLException{

		final DoctorDAO dao = new DoctorDAO();
		ArrayList<Doctor> doctorsList = new ArrayList<Doctor>();
		
		
		//Print all contacts using DAO SelectAllContacts
		if(doctorsList != null)
			  doctorsList = dao.selectAllEmployees();	// SELECTS all doctors
		
		for (Doctor x : doctorsList){		 // BEFORE EDITS PRINT ALL DOCTORS     
		    System.out.println(x);
		}
		/* !!TEST CRITERIA FOR 'CRUD' OPERATIONS ON DAO!!
		
		Doctor bob = new Doctor("Bob", 'M', "ZX6662111", "05-05-1970", "10 Java Way", "SK8 1UP", "Toes",  "03-07-2011", "bob@gmail.com") ;
		
		dao.insertDoctor(bob);   // INSERTS a doctor
		
		Consultant maggie = new Consultant("Maggie", "F", "AS4348966", "20-12-1987", "3 Candy Lane", "M87 7QW", "Toes", "30-07-2016", "mags@gmail.com") ;
		
		dao.insertConsultants(maggie);   // INSERTS a consultant
		
		maggie.addDoctor(bob);  // adds a doctor to a consultant
		
		// maggie.removeDoctor(bob);  removes bob from maggie's team
		 
		
		maggie.displayTeam();  // displays all doctors in Maggie's team (e.g. bob)

		Ronald.setSpecialism("Heart");      // UPDATE a doctor
		Ronald.setEmail("newronald@gmail.com");
		
		// dao.deleteDoctorById(5);     deletes doctor with ID=5
		
		*/
		
		for (Doctor x : doctorsList){		// AFTER EDITS PRINT ALL DOCTORS     
		    System.out.println(x);
		}
		
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
			
			//localhost:8000 (home)
			server.createContext("/", new HttpHandler() {     // home page that displays all doctors and consultants

				@Override
				public void handle(HttpExchange he) throws IOException {
					final String head = "<html><head></head><body><h1 align='center'>Welcome</h1><h2 align='right'>Doctor Insertion<br><a href='post'>Click Here</a></h2><h1>Doctor Table</h1>"
							+ "<table border='1'><tr>"
							+ "<th>ID</th>"
							+ "<th>Name</th>"
							+ "<th>Specialism</th>"
							+ "<th>Action</th></tr>"; // this is the headers of web page
					final String foot = "</table></body></html>";
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
					
					ArrayList<Doctor> doctors = dao.selectAllEmployees();
					
					he.sendResponseHeaders(200,0); //Must send Http response, other will not work
					out.write(head);					
					for(Doctor c: doctors) {
						out.write(
							 "<tr><td>" + c.getID()
							+ "</td><td>" + c.getName()         
							+ "</td><td>" + c.getSpecialism()
							+ "</td><td>" + "<a href='all'>View</a>" 
							+ "</tr>"   // links to expanded records
						);
					}													
					out.write(foot);
					
					out.write("<html><head></head><body><h1>Consultants Table</h1>"
							+ "<table border='1'><tr>"
							+ "<th>ID</th>"
							+ "<th>Name</th>"
							+ "<th>Specialism</th>"
							+ "<th>Action</th></tr>"); // this is the headers of web page");
					
					ArrayList<Consultant> consultants = dao.selectAllCons();//doctor
					
					for(Consultant c: consultants) {
						out.write(
							 "<tr><td>" + c.getID()
							+ "</td><td>" + c.getName()         // only puts name and email on page
							+ "</td><td>" + c.getSpecialism()
							+ "</td><td>" + "<a href='all'>View</a>"     // links to expanded records
							+ "</tr>" 
						);
				}
					out.write("</table></body></html>");
					
					out.write("<html><head></head><body><br><form method=\"POST\" action=\"/select\"><fieldset><legend style=color:blue>Select Doctor By Specialism:</legend>");
					out.write("Specialism:<input name=\"specialism\"><br>");
					out.write("<input type=\"submit\" value=\"Submit\">");
					out.write("</fieldset></form></body></html>");
					
					out.write("<html><head></head><body><br><form method=\"POST\" action=\"/select_name\"><fieldset><legend style=color:blue>Select Doctor By Name:</legend>");
					out.write("Name:<input name=\"name\"><br>");
					out.write("<input type=\"submit\" value=\"Submit\">");
					out.write("</fieldset></form></body></html>");
					
					out.close();
				}
			});
//------------------
			server.createContext("/all", new HttpHandler() {     // home page that displays all attributes of all doctors and consultants
				@Override
				public void handle(HttpExchange he) throws IOException {
					final String head = "<html><head></head><body><h1 align='center'>All Records Expanded</h1><h2 align='right'>Home<br><a href='/'>Click Here</a></h2><br><h1>Doctor Table</h1>"
							+ "<table border='1'><tr>"
							+ "<th>ID</th>"
							+ "<th>Name</th>"
							+ "<th>Gender</th>"
							+ "<th>NI</th>"
							+ "<th>DOB</th>"
							+ "<th>Address</th>"
							+ "<th>Postcode</th>"
							+ "<th>Specialism</th>"
							+ "<th>Start Date</th>"
							+ "<th>Email</th>";
					final String foot = "</table></body></html>";
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
					
					ArrayList<Doctor> doctors = dao.selectAllEmployees(); //doctors
					
					he.sendResponseHeaders(200,0); //Must send Http response, other will not work
					out.write(head);					
					for(Doctor c: doctors) {
						out.write(
							 "<tr><td>" + c.getID()
							+ "</td><td>" + c.getName()         
							+ "</td><td>" + c.getGender() 	
							+ "</td><td>" + c.getNatInscNo()
							+ "</td><td>" + c.getDob() 	
							+ "</td><td>" + c.getAddress()
							+ "</td><td>" + c.getPostcode()
							+ "</td><td>" + c.getSpecialism()
							+ "</td><td>" + c.getStartDate()
							+ "</td><td>" + c.getEmail() 
							+ "</tr>" 
						);
					}													
					out.write(foot);
					
					out.write("<html><head></head><body><h1>Consultants Table</h1>"
							+ "<table border='1'><tr>"
							+ "<th>ID</th>"
							+ "<th>Name</th>"
							+ "<th>Gender</th>"
							+ "<th>NI</th>"
							+ "<th>DOB</th>"
							+ "<th>Address</th>"
							+ "<th>Postcode</th>"
							+ "<th>Specialism</th>"
							+ "<th>Start Date</th>"
							+ "<th>Email</th>"
							+ "</tr>"); // this is the headers of web page");
					
					ArrayList<Consultant> consultants = dao.selectAllCons();//consultants
					
					for(Consultant c: consultants) {
						out.write(
							 "<tr><td>" + c.getID()
							+ "</td><td>" + c.getName()         // only puts name and email on page
							+ "</td><td>" + c.getGender() 	
							+ "</td><td>" + c.getNatInscNo()
							+ "</td><td>" + c.getDob() 	
							+ "</td><td>" + c.getAddress()
							+ "</td><td>" + c.getPostcode()
							+ "</td><td>" + c.getSpecialism()
							+ "</td><td>" + c.getStartDate()
							+ "</td><td>" + c.getEmail() 
							+ "</tr>"  
						);
				}
					out.write("</table></body></html>");			
					out.close();
				}
			});
		
			server.createContext("/select",new HttpHandler() {       // displays all doctors from searched specialism
						//process data from /add form
						@Override
						public void handle(HttpExchange he) throws IOException {
							HashMap<String,String> post = new HashMap<String,String>();
							//read the request body
							BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
							String line = "";
							String request = "";
							while((line = in.readLine()) != null) {
								request = request + line;
							}
							//individual key=value pairs are delimited by ampersands. Tokenize.
							String[] pairs = request.split("&");					
							for(int i=0;i<pairs.length;i++) {
								//each key=value pair is separated by an equals, and both halves require URL decoding.
								String pair = pairs[i];
								post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=")[1],"UTF-8"));
							}					
							//Should have a HashMap of posted data in our "post" variable. Now to add a contact
							//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^				
							String spec = post.get("specialism");

							BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));	
							
							final String head = "<html><head></head><body><h1 align='center'>Specialism: " + spec + "</h1><h2 align='right'>Home<br><a href='/'>Click Here</a></h2><br><h1>Doctor Table</h1>"
									+ "<table border='1'><tr>"
									+ "<th>ID</th>"
									+ "<th>Name</th>"
									+ "<th>Gender</th>"
									+ "<th>NI</th>"
									+ "<th>DOB</th>"
									+ "<th>Address</th>"
									+ "<th>Postcode</th>"
									+ "<th>Specialism</th>"
									+ "<th>Start Date</th>"
									+ "<th>Email</th>"
									+ "</tr>"; // this is the headers of web page
							final String foot = "</table></body></html>";
							
							
							ArrayList<Doctor> doctors = dao.doctorBySpec(spec);
							
							he.sendResponseHeaders(200,0); //Must send Http response, other will not work
							out.write(head);					
							for(Doctor p: doctors) {
								out.write(
									 "<tr><td>" + p.getID()
									+ "</td><td>" + p.getName()         // only puts name and email on page
									+ "</td><td>" + p.getGender() 	
									+ "</td><td>" + p.getNatInscNo()
									+ "</td><td>" + p.getDob() 	
									+ "</td><td>" + p.getAddress()
									+ "</td><td>" + p.getPostcode()
									+ "</td><td>" + p.getSpecialism()
									+ "</td><td>" + p.getStartDate()
									+ "</td><td>" + p.getEmail() 
									+ "</tr>"  
								);
							}
							out.write(foot);
							
							out.write("<html><head></head><body><h1>Consultants Table</h1>"
									+ "<table border='1'><tr>"
									+ "<th>ID</th>"
									+ "<th>Name</th>"
									+ "<th>Gender</th>"
									+ "<th>NI</th>"
									+ "<th>DOB</th>"
									+ "<th>Address</th>"
									+ "<th>Postcode</th>"
									+ "<th>Specialism</th>"
									+ "<th>Start Date</th>"
									+ "<th>Email</th>"
									+ "<</tr>"); 
							
							ArrayList<Consultant> consultants = dao.consBySpec(spec);//doctor
							
							for(Consultant c: consultants) {
								out.write(
									 "<tr><td>" + c.getID()
									+ "</td><td>" + c.getName()         // only puts name and email on page
									+ "</td><td>" + c.getGender() 	
									+ "</td><td>" + c.getNatInscNo()
									+ "</td><td>" + c.getDob() 	
									+ "</td><td>" + c.getAddress()
									+ "</td><td>" + c.getPostcode()
									+ "</td><td>" + c.getSpecialism()
									+ "</td><td>" + c.getStartDate()
									+ "</td><td>" + c.getEmail() 
									+ "</tr>" 
								);
						}
							out.write("</table></body></html>");
							out.write(foot);
							out.close();
						}
					});
			
			server.createContext("/select_name",new HttpHandler() {       // displays all doctors from searched specialism
				//process data from /add form
				@Override
				public void handle(HttpExchange he) throws IOException {
					HashMap<String,String> post = new HashMap<String,String>();
					//read the request body
					BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
					String line = "";
					String request = "";
					while((line = in.readLine()) != null) {
						request = request + line;
					}
					//individual key=value pairs are delimited by ampersands. Tokenize.
					String[] pairs = request.split("&");					
					for(int i=0;i<pairs.length;i++) {
						//each key=value pair is separated by an equals, and both halves require URL decoding.
						String pair = pairs[i];
						post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=")[1],"UTF-8"));
					}					
					//Should have a HashMap of posted data in our "post" variable. Now to add a contact
					String nm = post.get("name");

					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));	
					
					final String head = "<html><head></head><body><h1 align='center'>Doctor Name: " + nm + "</h1><h2 align='right'>Home<br><a href='/'>Click Here</a></h2><br><h1>Doctor Table</h1>"
							+ "<table border='1'><tr>"
							+ "<th>ID</th>"
							+ "<th>Name</th>"
							+ "<th>Gender</th>"
							+ "<th>NI</th>"
							+ "<th>DOB</th>"
							+ "<th>Address</th>"
							+ "<th>Postcode</th>"
							+ "<th>Specialism</th>"
							+ "<th>Start Date</th>"
							+ "<th>Email</th>"
							+ "</tr>"; // this is the headers of web page
					final String foot = "</table></body></html>";
					
					
					ArrayList<Doctor> doctors = dao.doctorByName(nm);
					
					he.sendResponseHeaders(200,0); //Must send Http response, other will not work
					out.write(head);					
					for(Doctor p: doctors) {
						out.write(
							 "<tr><td>" + p.getID()
							+ "</td><td>" + p.getName()        
							+ "</td><td>" + p.getGender() 	
							+ "</td><td>" + p.getNatInscNo()
							+ "</td><td>" + p.getDob() 	
							+ "</td><td>" + p.getAddress()
							+ "</td><td>" + p.getPostcode()
							+ "</td><td>" + p.getSpecialism()
							+ "</td><td>" + p.getStartDate()
							+ "</td><td>" + p.getEmail() 
							+ "</tr>" 
						);
					}
					out.write(foot);
					out.close();
				}

			});

			server.createContext("/post",new HttpHandler() {       //displays a form to enter a new doctor's details to add to database
				@Override
				public void handle(HttpExchange he) throws IOException {
					//output HTML form--- ^^^^^^^ creates new doctors?
					he.sendResponseHeaders(200, 0);
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
					int spacing = 2000;
					out.write("<html><head></head><body><h2 align='right'>Home<br><a href='/'>Click Here</a></h2><form method=\"POST\" action=\"/add_write\"><fieldset><legend style=color:blue>Doctor Insertion:</legend>");
					out.write("Name:<input name=\"name\"><br>");
					out.write("Gender:<input name=\"gender\"><br>");
					out.write("NI:<input name=\"natInscNo\"><br>");
					out.write("DOB:<input name=\"dob\"><br>");
					out.write("Address:<input name=\"address\"><br>");
					out.write("Postcode:<input name=\"postcode\"><br>");
					out.write("Specialism:<input name=\"specialism\"><br>");
					out.write("Start Date:<input name=\"startDate\"><br>");
					out.write("Email:<input name=\"email\"><br>");
					out.write("<input type=\"submit\" value=\"Submit\">");
					out.write("</fieldset></form></body></html>");
					out.close();			
				}				
			});
			
			server.createContext("/add_write",new HttpHandler() {       //processes request of a new doctor, doctor is added to database. Now you must click back or go 'Home' to see result.
				//process data from /add form
				@Override
				public void handle(HttpExchange he) throws IOException {
					HashMap<String,String> post = new HashMap<String,String>();
					//read the request body
					BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
					String line = "";
					String request = "";
					while((line = in.readLine()) != null) {
						request = request + line;
					}
					//individual key=value pairs are delimited by ampersands. Tokenize.
					String[] pairs = request.split("&");					
					for(int i=0;i<pairs.length;i++) {
						//each key=value pair is separated by an equals, and both halves require URL decoding.
						String pair = pairs[i];
						post.put(URLDecoder.decode(pair.split("=")[0],"UTF-8"),URLDecoder.decode(pair.split("=")[1],"UTF-8"));
					}					
					//Should have a HashMap of posted data in our "post" variable. Now to add a contact
					Doctor c = new Doctor(name, gender, natInscNo, dob, address, postcode, specialism, startDate, email);  // changed all attributes to 'static'
				c.setName(post.get("name"));
					c.setGender(post.get("gender")); 	
					c.setNatInscNo(post.get("natInscNo"));
					c.setDob(post.get("dob")); 	
					c.setAddress(post.get("address"));
					c.setPostcode(post.get("postcode"));
					c.setSpecialism(post.get("specialism"));
					c.setStartDate(post.get("startDate"));
					c.setEmail(post.get("email")); 

					BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));					
					try { 
						dao.insertEmployee(c);                           // doctor has been added to server
						he.sendResponseHeaders(200, 0); //HTTP 200 (OK)
						out.write("Success!");
					}
					catch(SQLException se) {
						 he.sendResponseHeaders(500, 0); //HTTP 500 (Internal Server Error)
						 out.write("Error Adding Doctor");
					}
				}
				
			});
			
			
			
			
			//actually start the server
			server.start();
		}
		catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage() + "  " + ioe.getStackTrace());
		}
		
	}//main

}//Controller class
