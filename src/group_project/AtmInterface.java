package group_project;

import java.util.Scanner;

// This is where it all starts, at the main() method
public class AtmInterface {
	
	public static void quitATM() {
		System.out.println("\n\n\n\nThank you for using KEAN BANK.");
		System.out.println("Exiting KEAN BANK...");
		System.exit(1);
	}

	public static void main(String[] args) {
		// Welcome to Kean Bank - Fancy printf maybe?
		System.out.println("Welcome to KEAN BANK");
		
		// Get amount of cash user has before accessing ATM
		System.out.print("How much cash do you currently have?\n$");
		Scanner input = new Scanner(System.in);
		double cash = input.nextInt();
		
		// Create Savings Account object
		Savings savingsAccount = new Savings();
		// Create Checking Account object
		Checking checkingAccount = new Checking();
		
		boolean quit = false;

		while(!quit) {
			System.out.printf("\n%-32s%s\n%s\n", "Enter 1 for Savings", "Enter 2 to Quit", "Enter 3 for Checking");
			int option = input.nextInt();

			if(option == 1) {
				// Check user savings account PIN - max 4 guesses - true/false returned
				if(savingsAccount.checkPin()) {
					// If user chooses to back, exits while loop, goes back to main loop
					boolean goBack = false;

					while(!goBack) {
						// Give current savings account balance and cash amount
						System.out.printf("\nYou currently have $%.2f in cash", cash);
						System.out.printf("\nYour current Savings Account balance is: $%.2f\n", savingsAccount.getBalance());
						System.out.printf("\n%-38s%s\n%-38s%s\n%s\n", "Enter 1 to Deposit", "Enter 2 to Quit", "Enter 3 to Withdrawal", "Enter 4 to Transfer", "Enter 5 to go to previous menu");
						option = input.nextInt();
						switch(option) {

						// Deposit
						case 1:
							System.out.print("Enter amount you would like to deposit: $");
							double cashToDeposit = input.nextDouble();
							
							// Deposit function checks if user has sufficient amount of cash to make this transaction
							// deposit function returns how much will be deposited into account. Is 0 if transaction denied, no change in cash
							cash -= savingsAccount.deposit(cash, cashToDeposit);
							break;

						// Quit the ATM
						case 2:
							quitATM();
							break;

						// Withdraw
						case 3:
							System.out.print("\nEnter amount you would like to withdraw: $");
							double cashToTake = input.nextDouble();
							
							// withdrawal function checks balance then withdrawals
							// returns amount of cash to add to user's wallet, is 0 if transaction is denied then no change in cash amount
							cash += savingsAccount.withdrawal(cashToTake);
							break;

						// Transfer
						case 4:
							System.out.print("Enter amount to transfer from Savings Account: $");
							double fundsToTransfer = input.nextDouble();
							
							// Transfer function is a void function, directly changes balance between savings and checking account 
							savingsAccount.transfer(fundsToTransfer, checkingAccount);
							break;

						// Go back to previous menu
						case 5:
							goBack = true;
							System.out.println("\n\nGoing back..\n");
							break;
						}
					}
				}
			} else if(option == 2) {
				quit = true;
			} else if (option == 3) {
				// Check user checking account PIN - max 4 guesses - true/false returned
				if(checkingAccount.checkPin()) {
					// If user chooses to back, exits while loop, goes back to main loop
					boolean goBack = false;

					while(!goBack) {
						// Give current checking account balance and cash amount
						System.out.printf("\nYou currently have $%.2f in cash", cash);
						System.out.printf("\nYour current Checking Account balance is: $%.2f\n", checkingAccount.getBalance());
						System.out.printf("\n%-38s%s\n%-38s%s\n%s\n", "Enter 1 to Deposit", "Enter 2 to Quit", "Enter 3 to Withdrawal", "Enter 4 to Transfer", "Enter 5 to go to previous menu");
						option = input.nextInt();
						switch(option) {

						// Deposit
						case 1:
							System.out.print("Enter amount you would like to deposit: $");
							double cashToDeposit = input.nextDouble();
							
							// Deposit function checks if user has sufficient amount of cash to make this transaction
							// deposit function returns how much will be deposited into account. Is 0 if transaction denied, no change in cash
							cash -= checkingAccount.deposit(cash, cashToDeposit);
							break;

						// Quit the ATM
						case 2:
							quitATM();
							break;

						// Withdraw
						case 3:
							System.out.print("\nEnter amount you would like to withdraw: $");
							double cashToTake = input.nextDouble();
							
							// withdrawal function checks balance then withdrawals
							// returns amount of cash to add to user's wallet, is 0 if transaction is denied then no change in cash amount
							cash += checkingAccount.withdrawal(cashToTake);
							break;

						// Transfer
						case 4:
							System.out.print("Enter amount to transfer from Savings Account: $");
							double fundsToTransfer = input.nextDouble();
							
							// Transfer function is a void function, directly changes balance between savings and checking account 
							checkingAccount.transfer(fundsToTransfer, checkingAccount);
							break;

						// Go back to previous menu
						case 5:
							goBack = true;
							System.out.println("\n\nGoing back..\n");
							break;
						}
					}
				}
			} 
		}
		// outside while loop only when quit is true - exit ATM program
		quitATM();
	}

}