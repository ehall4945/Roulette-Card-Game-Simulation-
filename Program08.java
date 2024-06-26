import java.util.Scanner;
public class Program08 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		//Declare and initialize starting chip count at 100 chips
		int chipsNow = 100;

		//Call welcome method
		welcome();
		
		//While true loop so you keep going until user inputs 3 to cash out, then break the loop
		while(true) {
			
			//Declare spin number with math.random
			int spinNum = (int)(Math.random() * (36 - 0 + 1)) + 0;
			
			//First thing is to check to see if chipsNow <= 0, if so then 
			//break the loop because they can't bet anymore
			if(chipsNow <= 0) {
				System.out.println("Sorry, you're all out of chips. Looks like that's a wrap for today.");
				break;
			}
			
			//Declare int answer to getMenuChoice and call the method
			int answer = getMenuChoice(stdIn);
			
			//If loop to see what method to call
			//If answer == 1 call getNumber method and then getBet method
			if(answer == 1) {
				int userNumGuess = getNumber(stdIn);
				int betAmount = getBet(stdIn, chipsNow);
				//Print spinning the wheel to get what number
				System.out.println();
				System.out.println("-----------------------------------------------------");
				System.out.println("Spinng the wheel.....");
				System.out.println("The number was: " + spinNum + "!");
				System.out.println("And the color was: " + determineColor(spinNum));
				System.out.println();
				if(userNumGuess == spinNum) {
					System.out.println("Congrats, you won!");
					chipsNow = chipsNow + (betAmount * 35);
					report(chipsNow);
					System.out.println("-----------------------------------------------------");
				} else {
					System.out.println("Sorry, you lost!");
					chipsNow -= betAmount;
					report(chipsNow);
					System.out.println("-----------------------------------------------------");
				}
			} 
			//Else if answer == 2 call getColor method, then call getBet Method, then call determineColor method
			else if(answer == 2) {
				//Set inputAnswer = to return value of getColor method
				String inputAnswer = getColor(stdIn);
				//Call getBet method with stdIn and chipsNow as parameters and store return val in betAmount
				int betAmount = getBet(stdIn, chipsNow);
				//Set selectedColor string equal to return value of determineColor method
				String colorOutcome = determineColor(spinNum);
				//Print empty line to separate chunk of text, then display what color was selected
				System.out.println();
				System.out.println("-----------------------------------------------------");
				System.out.println("Spinng the wheel.....");
				System.out.println("The color was: " + colorOutcome + "!");
				System.out.println("And the number was: " + spinNum);
				System.out.println();
				//If selected color is the same as color user bet on, print that they win
				if(inputAnswer.equalsIgnoreCase(colorOutcome)) {
					System.out.println("Congrats, you won!");
					chipsNow += betAmount;
					report(chipsNow);
					System.out.println("-----------------------------------------------------");
				} 
				//Otherwise print to user that they lost
				else {
					System.out.println("Sorry, you lost!");
					chipsNow -= betAmount;
					report(chipsNow);
					System.out.println("-----------------------------------------------------");
				}
			} 
			//If user selects 3 then thank them for playing and include chip count of total won or lost
			else {
				System.out.println("-----------------------------------------------------");
				System.out.println("Thanks for playing! Your total chip count after all rounds is " + chipsNow);
				break;
			}
		}
		//Close scanner
		stdIn.close();
	}

	//Given the following method heading, you must write the corresponding definition for a void return
	//method that displays a welcome message containing the bet types and payouts to the screen 
	public static void welcome() {		
		System.out.println("############################\r\n"
				+ "# WELCOME TO ROULETTE #\r\n"
				+ "############################\r\n"
				+ "# NUMBER BETS PAYOUT: 35:1 #\r\n"
				+ "# COLOR BETS PAYOUT: 1:1 #\r\n"
				+ "############################");
		System.out.println();
		System.out.println("You have 100 chips");
		System.out.println();
	}

	//Given the following method heading, you must write the corresponding definition for a int return
	//method that displays a menu and reads, validates, and returns the user’s choice to place a specific
	//bet type or cash out.
	public static int getMenuChoice(Scanner stdIn) {
		int answer;
		do {
			System.out.println("Option 1: Bet on a number [1, 36]");
			System.out.println("Option 2: Bet on Red or Black");
			System.out.println("Option 3: Cash out");
			answer = stdIn.nextInt();
		} while(!(answer == 1 || answer == 2 || answer == 3));
		return answer;
	}

	//Given the following method heading, you must write the corresponding definition for a int return
	//method that reads, validates, and returns a number to bet on, which must be between 1 and 36.
	public static int getNumber(Scanner stdIn) {
		int numberToBet;
		do {
			System.out.println("What number would you like to bet on? [1, 36]");
			numberToBet = stdIn.nextInt();
		} while(numberToBet < 1 || numberToBet > 36);	
		return numberToBet;
	}

	//Given the following method heading, you must write the corresponding definition for a String return
	//method that reads, validates, and returns a color to bet on, which must be "Red" or "Black"
	public static String getColor(Scanner stdIn) {
		String inputAnswer;
		//while true loop to accommodate user not entering red or black
		//and because I couldn't get this to work with a do loop for some reason
		while(true) {
			System.out.println("What color would you like to bet on? [Red, Black]");
			inputAnswer = stdIn.next();
			//If else loop to see if user input was red, black, or other. Non case sensitive.
			if(inputAnswer.equalsIgnoreCase("red")) {
				break;
			} else if(inputAnswer.equalsIgnoreCase("black")) {
				break;
			} else {
				System.out.println("Error, you must choose Red or Black.");
			}	
		}
		return inputAnswer;
	}

	//Given the following method heading, you must write the corresponding definition for a int return
	//method that takes the number of chips the user currently has: chipsNow as its only input. Then
	//reads, validates, and returns a bet amount, which must be between 1 and chipsNow.
	public static int getBet(Scanner stdIn, int chipsNow) {
		int chipAmount;
		do {
			System.out.println("And how many chips would you like to bet? [1, " + chipsNow + "]");
			chipAmount = stdIn.nextInt();
		} while(chipAmount < 1 || chipAmount > chipsNow);
		return chipAmount;
	}

	//Given the following method heading, you must write the corresponding definition for a String return
	//method that takes the number just spun on the wheel : spinNum as its only input. Then determines
	//and returns the spin color based on spinNum. i.e. "Red", "Black", or "Green"
	public static String determineColor(int spinNum) {
		if(spinNum == 0) {
			return "Green";
		} else if(spinNum % 2 == 0) {
			return "Red";
		} else {
			return "Black";
		}
	}

	//Given the following method heading, you must write the corresponding definition for a void return
	//method that takes the number of chips the user currently has: chipsNow as its only input. Then
	//displays the user’s winnings or loses (relative to their starting with 100 chips) to the screen
	public static void report(int chipsNow) {
		if(chipsNow > 100) {
			System.out.println("So far you have a net gain of: " + (chipsNow - 100) + " chips!");
			System.out.println("Current chip count: " + chipsNow);
		} else {
			System.out.println("So far you have a net loss of: " + (100 - chipsNow) + " chips.");
			System.out.println("Current chip count: " + chipsNow);
		}
	}
}