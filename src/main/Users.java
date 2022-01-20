package main;

import java.util.ArrayList;

//inheritance 
public class Users extends UserType{

	private String ID , name , pass , email;

	public Users(String iD, String name, String pass, String email, String userType) {
		super(userType);
		ID = iD;
		this.name = name;
		this.pass = pass;
		this.email = email;
	}

	public static boolean isUserExists(String text, ArrayList<Users> userslist) {

		for (Users users : userslist){
			if(users.getEmail().equals(text)){
				return true;
			}else continue;
		}

		return false;
	}

	public static void CreateUsers(String name, String email, String pass, String type, ArrayList<Users> userslist) {


		long id = (long)(Math.random() * 45435435);
		String ID = "ID"+id;
		userslist.add(new Users(ID,name,email,pass,type));


	}

	public String getID() {return ID;
	}

	public void setID(String iD) {ID = iD;
	}

	public String getName() {return name;
	}

	public void setName(String name){this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
