package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystemManage {

	//title for the app
	public static final String TITLE = "   							LIBRARY MANAGEMENT SYSTEM							";
	
	//filenames	of .txt file 
	//to store / retrive the data 
	private final static String BOOKS_FILENAME  = "Books.txt";
	private final static String STUDENTS_FILENAME  = "StudentsDetails.txt";
	private final static String USERS_FILENAME  = "Users.txt";

	//variables to use if user / staff logged in
	public static boolean IsStudentLoggedIn = false, IsStaffLoggedIn = false;
	public static int index = 0;

	//for taking user input
	static Scanner sc = new Scanner(System.in);
	
	//for adding books
	static ArrayList<Book> list = new ArrayList<Book>();

	//to store the users
	static ArrayList<Users> userslist = new ArrayList<Users>();

	//for adding student and book id...
	static ArrayList<Student> borrowlist = new ArrayList<Student>();

	//to write the books into file
	public static void writeBooks() throws IOException {

		File f  = new File(BOOKS_FILENAME);
		if(!f.exists()) {
			f.createNewFile();
		}

		FileWriter  fw = new FileWriter(BOOKS_FILENAME);

		for (int i = 0; i < list.size(); i++) {
			fw.append(""+list.get(i).getId()+","+list.get(i).getTitle()+","+list.get(i).getAuthor()+","+list.get(i).getCategory()
					+","+list.get(i).getSynopsis()+"\n");
		}

		fw.close();

	}

	//to write the borrowed books into file
	public static void writeBorrowedBooks() throws IOException {
		
		
		File f  = new File(STUDENTS_FILENAME);
		if(!f.exists()) {
			f.createNewFile();
		}
		
		FileWriter  fw = new FileWriter(STUDENTS_FILENAME);
		
		for (int i = 0; i < borrowlist.size(); i++) {
			fw.append(""+borrowlist.get(i).getId()+","+borrowlist.get(i).getBid()+","+borrowlist.get(i).getIssuedate()+"\n");
		}
		
		
		fw.close();
			
			
		}

	//to write users
	public static void writeUsers() throws IOException {


		File f  = new File(USERS_FILENAME);
		if(!f.exists()) {
			f.createNewFile();
		}

		FileWriter  fw = new FileWriter(USERS_FILENAME);

		for (int i = 0; i < userslist.size(); i++) {
			fw.append(""+userslist.get(i).getID()+","+userslist.get(i).getName()+","+userslist.get(i).getEmail()
					+","+userslist.get(i).getPass()+","+userslist.get(i).getUserType()
					+"\n");
		}


		fw.close();


	}

	public static void readBooks() throws FileNotFoundException {
		
		//reads object for every line
		
		File f1 = new File(BOOKS_FILENAME);
		
		if(f1.exists()) {
			try {
				f1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Scanner s1 = new Scanner(new File(BOOKS_FILENAME));
		
		while (s1.hasNextLine()) {
			
		    String[] split = s1.nextLine().split(","); 
		    
		  try {
			  
			  
		  //adding objects to list... 
		  list.add(new Book(split[0], split[1],split[2],split[3],split[4]));
		  
		  
		  }catch (Exception e) {
			// TODO: handle exception
			  System.out.println("Message: "+ e.getMessage());
		}
		    
		}
			
	}

	//to read the students
	public static void readStudents() throws FileNotFoundException {
			
			
			//reads object for every line
			
			File f1 = new File(STUDENTS_FILENAME);
			
			if(f1.exists()) {
				try {
					f1.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			Scanner s1 = new Scanner(new File(STUDENTS_FILENAME));
			
			while (s1.hasNextLine()) {
				
			    String[] split = s1.nextLine().split(","); 
			    
			  try {
				  
				  
			  //adding objects to list... 
			  borrowlist.add(new Student(split[0], split[1], split[2]));
			  
			  
			  }catch (Exception e) {
				// TODO: handle exception
				//  System.out.println(e.getMessage());
			  }
			    
			}
		}

	//to read the users data from list
	public static void readUsers() throws FileNotFoundException {
			
			
				//reads object for every line
				
				File f1 = new File(USERS_FILENAME);
				
				if(f1.exists()) {
					try {
						f1.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				Scanner s1 = new Scanner(new File(USERS_FILENAME));
				
				while (s1.hasNextLine()) {
					
				    String[] split = s1.nextLine().split(","); 
				    
				  try {
					  
					  
				  //adding objects to list... 
				 userslist.add(new Users(split[0], split[1],split[2],split[3],split[4]));
				  
				  
				  }catch (Exception e) {
					// TODO: handle exception
					  System.out.println("Message: "+ e.getMessage());
				}
				    
				}
				
		
		}

	 //check i...
	public static boolean isBookIDFound(String id) {
		
		//if id found return true
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equalsIgnoreCase(id)) {
				return true;
			}else continue;
		}
		
		return false;
	}

}
