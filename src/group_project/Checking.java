package group_project;

import java.util.Scanner;

public class Checking extends Account {

	// Check pin is 1234
	public boolean checkPin() {
		Scanner input = new Scanner(System.in);
		boolean result = false;
		// attempts used in for loop and outside of for loop
		int attempts = 0;
		
		// User has 4 chances to guess the pin
		for(; attempts < 4; attempts++) {
			System.out.print("\nEnter Checking Account PIN #: ");
			int pin = input.nextInt();
			
			// If pin is not 4 digits (1000) or greater than 4 digits (9999)
			if(pin < 1000 || pin > 9999) {
				System.out.println("PIN # must be 4 digits");
				// attempt is not counted because pin must be 4 digits long
				attempts--;
				// use continue to immediately jump to start of for loop (attempts++ and attempts-- keeps attempts value the same)
				continue;
			}
			
			result = (pin == 1234) ? true : false;
			
			// If guess is true
			if(result) {
				System.out.println("Accessing Checking Account...");
				break;
			}
		}
		// Outside of for loop
		if(attempts == 4) {
			System.out.println("Number of PIN guesses Exceeded.");
		}
		return result;
	}
	
	// Checking balance must be minimum $250 at all times
	public boolean checkBalance(double MoneyToMove) {
		// Savings - Check that balance is above 0 when moving funds
		if(getBalance() - MoneyToMove > 250) {
			System.out.println("Balance Approved for Transaction");
			return true;
		} else {
			System.out.println("WARNING: Checking cannot be less than $250.");
			System.out.println("Transaction Denied");
			return false;
		}
	}
	
	public void transfer(double amount, Account accountReceivingFunds) {
		if(checkBalance(amount)) {
			// No transfer limit on checking
			System.out.println("Transferring Funds...");

			// set new balance for this account 
			setBalance(getBalance() - amount);

			// Then set balance for account receiving transferred funds
			accountReceivingFunds.setBalance(accountReceivingFunds.getBalance() + amount);
			System.out.printf("\nTransferred $%.2f\n to Savings Account", amount);
		}
	}
	
	public double withdrawal(double cashToWithdraw) {
		if(checkBalance(cashToWithdraw)) {
			System.out.println("Withdrawing cash from ATM...");

			// Set new balance - removing funds for withdrawal
			setBalance(getBalance() - cashToWithdraw);
			
			// Return cash amount to add to user wallet
			System.out.printf("\n$%.2f received from ATM", cashToWithdraw);
			return cashToWithdraw;
		} else {
			// In case of transaction being denied - return 0, no cash added
			return 0;
		}
	}
		

}
