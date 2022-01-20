package main;

//using inheritance here - extends
public class Book implements Book_Interface{

	//attribute
	private String id, title, author , category, synopsis;
	//constructor
	public Book() {
		
	}

	//constructor
	public Book(String id, String title, String author, String category, String synopsis) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.synopsis = synopsis;
	}

	@Override
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "BOOK ID :"+ this.getId()+ "  BOOK TITLE : "+ this.getTitle()+ "  BOOK AUTHOR : "+ this.getAuthor()
				+ "  BOOK CATEGORY : "+ this.getCategory() + "  BOOK SYNOPSIS : "+ this.getSynopsis()  + "\n";
	}

}
