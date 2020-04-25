package group_project;

// Parent class - Checking.java and Savings.java inherit these methods and instance variables
public class Account {
	
	// Every account will have a balance variable
	private double balance = 0;

	// Getters and Setters for balance avoid directly changing balance and causing bugs/errors
	public double getBalance() {
		return balance;
	}

	public void setBalance(double newBalance) {
		balance = newBalance;
	}
	
	// Every account can deposit the same
	public double deposit(double userCash, double cashDepositing) {
		// Check if user has sufficient funds to make deposit
		if(userCash - cashDepositing >= 0 && cashDepositing > 0) {
			System.out.println("Depositing...");
			// Set new balance with added cash deposit
			setBalance(getBalance() + cashDepositing);

			// Returning cash amount to subtract from user's wallet
			return cashDepositing;
		} else {
			System.out.println("Insufficient amount of cash to make this deposit");
			System.out.println("Transaction Denied\n");
			// Return 0 - Adds nothing to user's wallet
			return 0;
		}
		
	}
	
}
