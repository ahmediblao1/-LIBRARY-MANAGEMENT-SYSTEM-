package main;

public class Student {
	
	String id, bid, issuedate;

	//for storing the IDS fo borrowing books 
	public Student(String id, String bid, String issuedate) {
		super();
		this.id = id;
		this.bid = bid;
		this.issuedate = issuedate;
	}

	public String getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String toString() {
		String st = "";
		st += " STUDENT ID : "+this.getId() +" BOOK ID : "+this.getBid()+" BOOK ISSUE DATE : "+this.getIssuedate()+" - Status : Borrowed" 
				+"\n";
		return st;
	}


}
