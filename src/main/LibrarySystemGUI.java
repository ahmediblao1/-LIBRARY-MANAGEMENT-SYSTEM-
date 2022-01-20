package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import Includes.Header;
import components.InputBox;
import components.MyButton;
import components.PasswordBox;
import components.TextLabel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibrarySystemGUI extends Application{

	//titles
	public static String TITLE = LibrarySystemManage.TITLE;

    public static String checkEMAIL = "";
    MyButton add , display , search;
    TextArea areaForAdmin , areaForCustomer , areaForAdmins;

	//width and height of screen
	public static int WIDTH = 730, HEIGHT = 570;
	Stage primaryStage;
	
	//layout
	static BorderPane root;
	static Scene sc;

    //--module-path "C:\Users\DELL\Music\javafx-sdk-17.0.1\lib" --add-modules javafx.controls,javafx.fxml

	//login components
	MyButton loginbutton, signupButtonPage;
	InputBox email;
	PasswordBox password;
	static TextLabel appTitle, sTitle, loginNote;

    //SIGNUP
    InputBox name,surname,semail;
    PasswordBox spassword,confirmpassword;
    MyButton signupbutton;
    //dropdown box for usertype
    ComboBox<String> usertype;


	public void init() throws ClassNotFoundException, SQLException, FileNotFoundException {

		//for login
		email = new InputBox("Enter Email", "#f1f1f1", "#000");
		email.setText("");
		password = new PasswordBox("Enter Password", "#f1f1f1", "#000");
		password.setText("");
		loginbutton = new MyButton("LOGIN", "#6699ff", 15);
        signupButtonPage = new MyButton("SIGN UP", "#6699ff", 15);

        signupButtonPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    root.setRight(signupPage());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

	}

	public VBox signupPage() throws FileNotFoundException {

        VBox signupage = new VBox();
        signupage.setStyle("-fx-background-color:#fff;-fx-border-color: grey;-fx-border-width:1px;");
        signupage.setPadding(new Insets(10));
        signupage.setAlignment(Pos.BASELINE_CENTER);
        signupage.setSpacing(10);

        String[] list = {"student","staff"};
        usertype = new ComboBox<>(FXCollections.observableArrayList(list));
        usertype.setPrefWidth(290);
        usertype.getSelectionModel().select(0);

        //for sign up
        sTitle = new TextLabel("SIGN UP","#6699ff", "#fff",15);
        signupbutton = new MyButton("SIGNUP", "#4CAF50", 15);
        name = new InputBox("Enter Name", "#f1f1f1", "#000");name.setPrefWidth(300);
        surname = new InputBox("Enter Surname", "#f1f1f1", "#000");
        semail = new InputBox("Enter Email", "#f1f1f1", "#000");
        spassword = new PasswordBox("Enter Password", "#f1f1f1", "#000");
        confirmpassword = new PasswordBox("Enter Confirm Password", "#f1f1f1", "#000");

        Image img = new Image(new FileInputStream("bg.jpg"));
        ImageView bg = new ImageView(img);
        bg.setFitWidth(400);
        bg.setFitHeight(495);
       // signupage.getChildren().add(bg);

        signupage.getChildren().add(sTitle);
        signupage.getChildren().add(name);
        signupage.getChildren().add(semail);
        signupage.getChildren().add(spassword);
        signupage.getChildren().add(confirmpassword);
        signupage.getChildren().add(usertype);
        signupage.getChildren().add(signupbutton);


        signupbutton.setOnAction(e ->{

            try {
                    if(!name.getText().isEmpty() && !semail.getText().isEmpty() &&
                            !spassword.getText().isEmpty() && !confirmpassword.getText().isEmpty()) {

                        if(!spassword.getText().equalsIgnoreCase(confirmpassword.getText())) {
                            new Alert(AlertType.ERROR,"Password must be same in both fields!").show();
                        }else {


                            if(Users.isUserExists(semail.getText() ,LibrarySystemManage.userslist)) {

                                new Alert(AlertType.ERROR,"Email already exists, \n Please try another email address...!").show();

                            }else {

                                Users.CreateUsers(name.getText(), semail.getText(), spassword.getText() , usertype.getSelectionModel().getSelectedItem(), LibrarySystemManage.userslist);
                                LibrarySystemManage.writeUsers();//writing to file...
                                new Alert(AlertType.INFORMATION,"Account created success !").show();

                                //making it clear after creation of account
                                name.setText("");
                                semail.setText("");
                                spassword.setText("");
                                confirmpassword.setText("");
                            }
                        }

                    }else {

                        new Alert(AlertType.ERROR,"All fields are required to signup!").show();
                    }

            }catch (Exception e1) {
                // TODO: handle exception
                new Alert(AlertType.ERROR,""+e1.getMessage()).show();
            }

        });



        return signupage;
    }

    @Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		primaryStage.setTitle(TITLE);
		this.primaryStage = primaryStage;
		//primaryStage.setResizable(false);
		
        //reading the users , books and Students details of borrowed books
        try{
            
         // to read the data from files...
         LibrarySystemManage.readBooks();
         LibrarySystemManage.readUsers();
         LibrarySystemManage.readStudents();

         System.out.println("Total users : "+ LibrarySystemManage.userslist.size());

        //calling the init 
		init();
                 
        }catch(FileNotFoundException | ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

		
		//adding the layouts on the borderpane...
		root = new BorderPane();
		root.setCenter(LoginPage());
		root.setTop(new Header());
		//root.setRight(signupPage());

		
		sc = new Scene(root,WIDTH,HEIGHT);
		primaryStage.setScene(sc);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	

	
	VBox LoginPage () {
		
		VBox loginpage = new VBox();
		loginpage.setStyle("-fx-background-color:#fff;-fx-border-color: #f1f1f1;-fx-border-width:1px;");
		loginpage.setPadding(new Insets(10));
		loginpage.setAlignment(Pos.BASELINE_CENTER);
		loginpage.setSpacing(10);
		
        TextLabel txt2 = new TextLabel(" Note: Enter valid Credentials to login ! ","#fff", "#000",12);
        txt2.setMinWidth(300);
        txt2.setAlignment(Pos.CENTER);

		//loginNote.setMaxWidth(300);
		
		loginpage.getChildren().add(email);
		loginpage.getChildren().add(password);

		loginpage.getChildren().add(txt2);
		loginpage.getChildren().add(loginbutton);
		loginpage.getChildren().add(signupButtonPage);

        loginbutton.setOnAction(e ->{

            String em = email.getText();
            String pass = password.getText();

            for (int i = 0; i < LibrarySystemManage.userslist.size(); i++) {

                // if user logged in
                if(LibrarySystemManage.userslist.get(i).getEmail().equalsIgnoreCase(em) &&
                        LibrarySystemManage.userslist.get(i).getPass().equalsIgnoreCase(pass) &&
                        LibrarySystemManage.userslist.get(i).getUserType().equals("student")) {
                        LibrarySystemManage.index = i;
                        LibrarySystemManage.IsStudentLoggedIn = true;
                        System.out.println("LOGIN SUCCESS AS : " + LibrarySystemManage.userslist.get(i).getUserType());

                }else if(LibrarySystemManage.userslist.get(i).getEmail().equalsIgnoreCase(em) &&
                        LibrarySystemManage.userslist.get(i).getPass().equalsIgnoreCase(pass) &&
                        LibrarySystemManage.userslist.get(i).getUserType().equals("staff")) {
                    LibrarySystemManage.index = i;
                    LibrarySystemManage.IsStudentLoggedIn = true;
                    System.out.println("LOGIN SUCCESS AS : " + LibrarySystemManage.userslist.get(i).getUserType());

                }
            }

            if(LibrarySystemManage.IsStudentLoggedIn){

                gotToHomePage(LibrarySystemManage.userslist.get(LibrarySystemManage.index).getUserType());

            }else if(LibrarySystemManage.IsStaffLoggedIn){

                gotToHomePage(LibrarySystemManage.userslist.get(LibrarySystemManage.index).getUserType());

            }else {
                new Alert(AlertType.ERROR,"Please enter valid info to login !").show();
            }



        });
                

        TextLabel txt = new TextLabel("Info: This is basically a Library Management System . \r\n"
				+ " through books can be borrowed or returned ....! \n","#f1f1f1", "#000",12);
		txt.setPrefSize(1000, 300);
        txt.setTranslateY(-40);
        txt.setPadding(new Insets(5));
        txt.setAlignment(Pos.CENTER);
		loginpage.getChildren().add(txt);

		
		return loginpage;
	}
	
        //for customer
        VBox HomeCustomerPage () {
		
		VBox homepage = new VBox();
		homepage.setStyle("-fx-background-color:#fff;-fx-border-color: #f1f1f1;-fx-border-width:1px;");
		homepage.setPadding(new Insets(10));
		homepage.setAlignment(Pos.BASELINE_CENTER);
		homepage.setSpacing(10);
		
        TextLabel txt2 = new TextLabel(" View Books Info ! ","#fff", "#000",18);
        txt2.setMinWidth(300);
        txt2.setAlignment(Pos.CENTER);
		homepage.getChildren().add(txt2);

		//to display books in Info Area...
        //areaForCustomer.setText(""+Main.list.toString());
        MyButton display2 = new MyButton("DISPLAY BOOKS", "#6699ff", 15);
        //on display button click
        display2.setOnAction(e ->{

        areaForCustomer.setText(""+ LibrarySystemManage.list.toString());

        });
                
        InputBox searchBx = new InputBox("SEARCH BOOK BY TITLE", "#f1f1f1", "#000");

        search = new MyButton("SEARCH BOOK", "#6699ff", 15);
        search.setOnAction(e ->{


         String searchText = searchBx.getText().toString();

         if(searchBx.getText().isEmpty()){
              new Alert(AlertType.INFORMATION,"Please enter book title to search!").show();
         }else{

             boolean isfound = false;


                  for (Book m : LibrarySystemManage.list) { if(m.getTitle().equalsIgnoreCase(searchText) ||
                  m.getCategory().equalsIgnoreCase(searchText)){

                  areaForCustomer.setText(""+m.toString());

                  isfound = true; } }


             if(!isfound){
                 new Alert(AlertType.ERROR," book doesn't exist with this title...!").show();
             }

         }



        });

        InputBox borrowID = new InputBox("ENTER BOOK ID TO BORROW", "#f1f1f1", "#000");
        homepage.getChildren().add(borrowID);

        MyButton borrow = new MyButton("BORROW BOOK", "#FFA511", 15);
        homepage.getChildren().add(borrow);

        MyButton Dborrow = new MyButton("DISPLAY BORROWED BOOKS", "#FFA500", 15);
        homepage.getChildren().add(Dborrow);

        Dborrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                boolean isF = false;
                areaForCustomer.appendText("<<< YOUR ISSUED BOOKS >>>\n");

                //current user ID
                String ID = LibrarySystemManage.userslist.get(LibrarySystemManage.index).getID();
                if(LibrarySystemManage.borrowlist.size() > 0) {
                    for (int i = 0; i < LibrarySystemManage.borrowlist.size(); i++) {

                        //math ID
                        if(LibrarySystemManage.borrowlist.get(i).getId().equalsIgnoreCase(ID)){

                            //get books ID
                            String booksID = LibrarySystemManage.borrowlist.get(i).getBid();
                            System.out.println("BOOK ID : "+booksID);

                            for (int j = 0; j < LibrarySystemManage.list.size(); j++) {

                                //match books id
                                if(booksID.equalsIgnoreCase(LibrarySystemManage.list.get(j).getId())){

                                 areaForCustomer.appendText(""+ LibrarySystemManage.list.get(j).toString());

                                 isF = true;

                                }else continue;


                            }


                        }else continue;

                    }
                }else{
                    areaForCustomer.setText(" YOU HAVE NOT ISSUED ANY BOOK !");
                }

                //if no issued book found!
                if(!isF){
                    areaForCustomer.setText(" YOU HAVE NOT ISSUED ANY BOOK !");
                }

            }
        });

        borrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            try{

                String BID = borrowID.getText().toString();
                if(BID.isEmpty()){
                    new Alert(AlertType.INFORMATION, "Enter valid ID TO BORROW THE BOOK!").show();
                }else{


                    //getting current date...
                    Calendar calendar = Calendar.getInstance();
                    long t = System.currentTimeMillis();
                    calendar.setTimeInMillis(t);

                    int month  = (calendar.get(Calendar.MONTH) + 1);

                    //ISSUE DATE
                    String issueDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                            month + "/" + calendar.get(Calendar.YEAR);

                    //if book is found in the list...
                    if(LibrarySystemManage.isBookIDFound(BID)) {

                        //studentID , bookID , date
                        LibrarySystemManage.borrowlist.add(new Student(LibrarySystemManage.userslist.get(LibrarySystemManage.index).getID(), BID, issueDate));
                        LibrarySystemManage.writeBorrowedBooks();

                        new Alert(AlertType.INFORMATION, "BOOK IS BORROWED SUCCESS!").show();

                        borrowID.setText("");

                    }else{
                        new Alert(AlertType.INFORMATION, "BOOK NOT FOUND WITH THIS ID!").show();
                    }

                }

            }catch (Exception exception){
                new Alert(AlertType.INFORMATION, "Enter valid ID!").show();
                System.out.println(exception.getMessage());
            }


            }
        });

        homepage.getChildren().addAll(searchBx,search,display2);

		return homepage;
	}
        
        
         //for admin
        VBox HomeAdminPage() {
		
		VBox homepage = new VBox();
		homepage.setStyle("-fx-background-color:#fff;-fx-border-color: #f1f1f1;-fx-border-width:1px;");
		homepage.setPadding(new Insets(10));
		homepage.setAlignment(Pos.BASELINE_CENTER);
		homepage.setSpacing(5);
        homepage.setPrefSize(400, 500);

                
        TextLabel txt2221 = new TextLabel(" ADD BOOK  ","#fff", "#000",14);
        txt2221.setPrefWidth(1000);
        txt2221.setAlignment(Pos.CENTER);
        homepage.getChildren().add(txt2221);

        TextLabel txt222 = new TextLabel(" BOOK TITLE :  ","#fff", "#000",14);
        txt222.setPrefWidth(1000);
        txt222.setAlignment(Pos.CENTER);
        homepage.getChildren().add(txt222);

        InputBox bookTitle = new InputBox("e.g abc..", "#f1f1f1", "#000");
        bookTitle.setPrefWidth(1000);
        bookTitle.setAlignment(Pos.CENTER);
        homepage.getChildren().add(bookTitle);

        TextLabel txt22 = new TextLabel(" BOOK AUTHOR ","#fff", "#000",14);
        txt22.setMinWidth(300);
        txt22.setAlignment(Pos.CENTER);
        homepage.getChildren().add(txt22);

        InputBox bookAuthor = new InputBox("e.g john...", "#f1f1f1", "#000");
        bookAuthor.setPrefWidth(1000);
        bookAuthor.setAlignment(Pos.CENTER);
        homepage.getChildren().add(bookAuthor);

        TextLabel txt2223 = new TextLabel(" BOOK Category :  ","#fff", "#000",14);
        txt2223.setPrefWidth(1000);
        txt2223.setAlignment(Pos.CENTER);
        homepage.getChildren().add(txt2223);

        String[] list2 = {"Literary Fiction","story","Horror","Classics", "Fantasy", "Other"};
        ComboBox<String> category = new ComboBox<>(FXCollections.observableArrayList(list2));
        category.setPrefWidth(1000);
        category.getSelectionModel().select(0); homepage.getChildren().add(category);


        TextLabel txt224 = new TextLabel(" BOOK SYNOPSIS ","#fff", "#000",14);
        txt224.setMinWidth(300);
        txt224.setAlignment(Pos.CENTER);
        homepage.getChildren().add(txt224);

        InputBox bookSynopsis = new InputBox("this book is about...", "#f1f1f1", "#000");
        bookSynopsis.setPrefWidth(1000);
        bookSynopsis.setAlignment(Pos.CENTER);
        homepage.getChildren().add(bookSynopsis);

        add = new MyButton("ADD BOOK", "#6699ff", 15);

        display = new MyButton("DISPLAY", "#4CAF50", 15);
        homepage.getChildren().addAll(add,display);

        MyButton display2 = new MyButton("DISPLAY BORROWED BOOKS", "#4CAF52", 15);
        homepage.getChildren().addAll(display2);

        display2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                areaForAdmin.setText(""+ LibrarySystemManage.borrowlist.toString());

            }
        });

        //on add button click
        add.setOnAction(e ->{

            //getting the data from fields
            String title = bookTitle.getText();
            String author = bookAuthor.getText();
            String synopsis = bookSynopsis.getText();
            String cate = category.getSelectionModel().getSelectedItem();

            //if fields are not empty...
            if(!bookTitle.getText().isEmpty() &&
                    !bookAuthor.getText().isEmpty()&&
                    !bookSynopsis.getText().isEmpty()&&
                    !cate.isEmpty()){

                int iid = (int)(Math.random() * 550000);
                LibrarySystemManage.list.add(new Book("CS"+iid,title,author,cate,synopsis));
                new Alert(AlertType.INFORMATION, "Book added success!").show();

                bookTitle.setText("");
                bookAuthor.setText("");
                bookSynopsis.setText("");


                try {
                    LibrarySystemManage.writeBooks(); // calling method to store the data info files...
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }else{
                new Alert(AlertType.INFORMATION, "Enter valid data to add!!").show();
            }
        });

        //on display button click
        display.setOnAction(e ->{
        areaForAdmin.setText(""+ LibrarySystemManage.list.toString());
        });
               
		return homepage;
	}
        
        
             //for admin
        VBox sidePageAfterAdminLogin(){
            
            //rightSide
            VBox sidePage = new VBox();
            sidePage.setStyle("-fx-background-color:#fff;-fx-border-color: #f1f1f1;-fx-border-width:1px;");
            sidePage.setPadding(new Insets(10));
            sidePage.setAlignment(Pos.BASELINE_CENTER);
            sidePage.setPrefWidth(200);
            sidePage.setSpacing(10);

            TextLabel text = new TextLabel(" STAFF - DISPLAY ! ","#fff", "#000",18);
            text.setMinWidth(300);
            text.setAlignment(Pos.CENTER);

            MyButton lgoout = new MyButton("LOGOUT", "tomato", 15);
            
            //gogint to loginpage
            lgoout.setOnAction( e -> {
                try {
                    gotToLoginPage();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            });
            
            areaForAdmin = new TextArea();
            areaForAdmin.setPrefSize(300,200);
            sidePage.getChildren().addAll(text,new ScrollPane(areaForAdmin),lgoout);


            return sidePage;
        }
        

            //for customer
        VBox sidePageAfterLogin(){
            
            //rightSide
            VBox sidePage = new VBox();
            sidePage.setStyle("-fx-background-color:#fff;-fx-border-color: #f1f1f1;-fx-border-width:1px;");
            sidePage.setPadding(new Insets(10));
            sidePage.setAlignment(Pos.BASELINE_CENTER);
            sidePage.setSpacing(10);

            TextLabel text = new TextLabel(" INFO BOX ! ","#fff", "#000",18);
            text.setMinWidth(300);
            text.setAlignment(Pos.CENTER);

            MyButton lgoout = new MyButton("LOGOUT", "tomato", 15);
            
            //gogint to loginpage
            lgoout.setOnAction( e -> {
                try {
                    gotToLoginPage();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            });
            
            areaForCustomer = new TextArea();
            areaForCustomer.setPrefSize(300,300);
            sidePage.getChildren().addAll(text,new ScrollPane(areaForCustomer),lgoout);


            return sidePage;
        }
        
	
	//main method to run the program...
	public static void main(String[] args) {
		launch(args);
	}

        private void gotToLoginPage() throws FileNotFoundException {

            root.setCenter(LoginPage());
            root.setRight(signupPage());

        }
        //method to go to GUI screens based on user
        //@user Student/Staff
        private void gotToHomePage(String type) {

	    //If staff / Student
        if(type.equalsIgnoreCase("student")){

        root.setRight(sidePageAfterLogin());
        root.setCenter(HomeCustomerPage());
        root.setLeft(null);

        }else if(type.equalsIgnoreCase("staff")){

        root.setRight(sidePageAfterAdminLogin());
        ScrollPane pane =  new ScrollPane(HomeAdminPage());
        root.setCenter(pane);

        }

        }

}

