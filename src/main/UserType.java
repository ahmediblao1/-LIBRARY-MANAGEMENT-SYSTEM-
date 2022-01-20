package main;

//abstract
public abstract class UserType {
	
	private String userType;
	
	
	public UserType(String userType) {
		super();
		this.userType = userType;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType (String userType) {
		this.userType = userType;
	}
	
	
	

}
