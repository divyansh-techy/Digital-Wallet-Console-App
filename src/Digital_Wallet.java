package digital_wallet_mini_project;

import java.util.ArrayList;
import java.util.Scanner;

//User class defined
class User{
	private String Username;
	private String Password;
	private double Balance;
	private ArrayList<String> transicationHistory;
	
	//Constructor
	
	public User(String Username, String Password){
		this.Username=Username;
		this.Password=Password;
		this.Balance=0;
		
		transicationHistory = new ArrayList();
	}
	
	//get username
	public String getUsername() {
		return Username;
	}
	
	//check password
	public boolean checkPassword(String Password) {
		return this.Password.equals(Password);
	}
	
	//get balance
	public double getBalance() {
		return Balance;
	}
	
	//deposit money
	public void deposit(double amount) {
		if(amount>0) {
			Balance = Balance+amount;
			transicationHistory.add("Diposited: $"+amount);
			System.out.println("Money Deposited Succfully.");
			System.out.println("Current Balance: $"+Balance);
		}else {
			System.out.println("Invalid amount.");
		}
	}
	
	//spend money
	public void spend(double amount) {
		if(amount<=0) {
			System.out.println("Invalid amount.");
		}else if(amount>Balance) {
			System.out.println("Insufficient Balance.");
		}else {
			Balance = Balance-amount;
			transicationHistory.add("Spent: $"+amount);
			System.out.println("Money Spent Successfully.");
			System.out.println("Current Balance: $"+Balance);
		}
	}
	
	//Display transication history
	public void showTransicationHistory() {
		System.out.println("\n========== TRANSACTION HISTORY ==========");
		if(transicationHistory.isEmpty()) {
			System.out.println("No transication found");
		}else {
			for(String Transications : transicationHistory) {
				System.out.println(Transications);
			}
		}
	}
	
	
}



public class Digital_Wallet {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<User> users = new ArrayList<>();
		int choice;
		
		do{
			System.out.println("\n========== DIGITAL WALLET ==========");
			System.out.println("1. Register");
			System.out.println("2. Login");
	        System.out.println("3. Exit");
	        System.out.print("Enter your choice: ");
	        choice = sc.nextInt();
	        sc.nextLine();
	        
	        switch(choice) {
	        case 1: 
	        	System.out.println("\n========== REGISTRATION ==========");
	        	System.out.print("Create a Username: ");
	        	String Username = sc.nextLine();
	        	
	        	 boolean UsernameExists = false;
	        	 for(User user : users ) {
	        		 if(user.getUsername().equals(Username)) {
	        			 UsernameExists=true;
	        			 break;
	        		 }
	        	 }
	        	 if(UsernameExists) {
	        		 System.out.println("Username already exists.");
	        	 }else {
	        		 System.out.print("Enter Password: ");
	        		 String Password = sc.nextLine();
	        		 
	        		 User newUser = new User(Username, Password);
	        		 users.add(newUser);
	        		 
	        		 System.out.println("Account Created Succfully.");
	        	 }
	        	 break;
	        	
	        case 2:
	        	System.out.println("\n========== LOGIN ==========");
	        	
	        	System.out.print("Enter UserName: ");
	        	String LoginUsername = sc.nextLine();
	        	
	        	System.out.print("Enter Passsword: ");
	        	String LoginPass = sc.nextLine();
	        	
	        	User loggedInUser = null;
	        	
	        	for(User user : users) {
	        		if(user.getUsername().equals(LoginUsername) && user.checkPassword(LoginPass)) {
	        			loggedInUser = user;
	        			break;
	        		}
	        	}
	        	if(loggedInUser==null) {
	        		System.out.println("Invalid username or password.");
	        	}else {
	        		System.out.println("Login successful!");
	        		System.out.println("Welcome, "+loggedInUser.getUsername());
	        		
	        		int walletchoice;
	        		
	        		do {
	        			 System.out.println("\n========== WALLET MENU ==========");
                         System.out.println("1. Check Balance");
                         System.out.println("2. Deposit Money");
                         System.out.println("3. Spend Money");
                         System.out.println("4. Transaction History");
                         System.out.println("5. Logout");
                         System.out.print("Enter your choice: ");
                         
                         walletchoice = sc.nextInt();
                         
                         switch(walletchoice) {
                         
                         case 1: 
                        	 System.out.println("Current Balace: $"+loggedInUser.getBalance());
                        	 break;
                         case 2: 
                        	 System.out.print("Enter Amount to deposit: ");
                        	 double depositamount = sc.nextInt();
                        	 loggedInUser.deposit(depositamount);
                        	 break;
                         case 3:
                        	 System.out.print("Enter amount to Spend: ");
                        	 double spendmoney = sc.nextDouble();
                        	 loggedInUser.spend(spendmoney);
                        	 break;
                         case 4:
                        	 loggedInUser.showTransicationHistory();
                        	 break;
                         case 5:
                        	 System.out.println("Logged out successfully!");
                        	 break;
                         default:
                        	 System.out.println("Invalid Choice.");
                         }   
	        		}
	        		while(walletchoice !=5 );
	        	}
	        	break;
	        	
	        case 3:
	        	System.out.println("Thank you for using Digital Wallet!");
	        	break;
	        	default:
	        		System.out.println("Invalid input.");
	        }
	            
		}
		while(choice!=3);
		sc.close();

	}

}
