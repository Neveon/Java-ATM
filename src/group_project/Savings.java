package group_project;

import java.util.Scanner;

public class Savings extends Account {
	
	// Constructor - When an object/instance of this class is created, this method runs automatically
	public Savings() {
		System.out.println("\n\n\n\n\n*********************************************************************");
		System.out.println("Promotion: All KEAN BANK Savings Accounts have a $2000 bonus.");
		// Savings balance starts at $2000
		setBalance(2000);
	}

	// Check pin is 4321
	public boolean checkPin() {
		Scanner input = new Scanner(System.in);
		boolean result = false;
		// attempts used in for loop and outside of for loop
		int attempts = 0;
		
		// User has 4 chances to guess the pin
		for(; attempts < 4; attempts++) {
			System.out.print("\nEnter Savings Account PIN #: ");
			int pin = input.nextInt();
			
			// If pin is not 4 digits (1000) or greater than 4 digits (9999)
			if(pin < 1000 || pin > 9999) {
				System.out.println("PIN # must be 4 digits");
				// attempt is not counted because pin must be 4 digits long
				attempts--;
				// use continue to immediately jump to start of for loop (attempts++ and attempts-- keeps attempts value the same)
				continue;
			}
			
			result = (pin == 4321) ? true : false;
			
			// If guess is true
			if(result) {
				System.out.println("Accessing Savings Account...");
				break;
			}
		}
		if(attempts == 4) {
			System.out.println("Number of PIN guesses Exceeded.");
		}
		return result;
	}

	// Check if money to transfer/withdraw is possible by checking their balance
	public boolean checkBalance(double MoneyToMove) {
		// Savings - Check that balance is above 0 when moving funds
		if(getBalance() - MoneyToMove > 0) {
			System.out.println("Balance Approved for Transaction");
			return true;
		} else if(MoneyToMove < 0.01) {
			System.out.println("Must be greater than $1 for transaction approval");
			return false;
		} else {
			System.out.println("WARNING: Savings cannot be 0.");
			System.out.println("Transaction Denied");
			return false;
		}
	}
	
	public void transfer(double amount, Account accountReceivingFunds) {
		if(checkBalance(amount)) {
			// 1000 transfer limit on savings
			if (amount > 1000) {
				System.out.println("ERROR: $1000 transfer limit on savings. Unable to transfer funds.");
			} else {
				System.out.println("Transferring Funds...");

				// set new balance for this account 
				setBalance(getBalance() - amount);

				// Then set balance for account receiving transferred funds
				accountReceivingFunds.setBalance(accountReceivingFunds.getBalance() + amount);
				System.out.printf("\nTransferred $%.2f\n to Checking Account", amount);
			}
		}
	}
	
	public double withdrawal(double cashToWithdraw) {
		if(checkBalance(cashToWithdraw)) {
			System.out.println("Withdrawing cash from ATM...");

			// Set new balance - removing funds for withdrawal
			setBalance(getBalance() - cashToWithdraw);
			
			// Return cash amount to add to user wallet
			System.out.printf("\nWithdrew $%.2f from ATM", cashToWithdraw);
			return cashToWithdraw;
		} else {
			// In case of transaction being denied - return 0, no cash added
			return 0;
		}
	}
}
