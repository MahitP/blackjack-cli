import java.util.Scanner;

 public class Main
 {

	Deck deck;

	Player player;
	Dealer dealer;

	Hand splitHand1;
	Hand splitHand2;

	String earlyGameOverMessage = "";

	boolean gameOn = true;
	boolean hideFirstCard = true;
	boolean earlyGameOver = false;
	boolean canSplit = false;
	boolean hasSplit = false;


	 public Main()
	 {
		 	setupGame();

			IntroMessage();

			addCards();


			gamePlay();

			playerChoice();

	}

	//resets every single variable value
	public void setupGame()
	{
		deck = new Deck();

		player = new Player(deck);
		dealer = new Dealer(deck);

		splitHand1 = new Hand();
	 	splitHand2 = new Hand();

		gameOn = true;
		hideFirstCard = true;
		earlyGameOver = false;

		earlyGameOverMessage = "";

		canSplit = false;
		hasSplit = false;


	}

	//this is like the welcome screen
	public void IntroMessage()
	{
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("BlackJack Game by Mahit P");

		System.out.println("Welcome to BlackJack!");
		System.out.println();

		System.out.println("Rules:");
		System.out.println("1. The goal is to get cards totaling as close to 21 as possible without going over.");
		System.out.println("2. Number cards are worth their face value.");
		System.out.println("3. Face cards (J, Q, K) are worth 10.");
		System.out.println("4. Aces (A) can count as 1 or 11, whichever helps you more.");
		System.out.println("5. You start with 2 cards and can choose to 'hit' (get another card) or 'stand' (keep your cards).");
		System.out.println("6. If your total goes over 21, you bust and lose.");
		System.out.println("7. After you stand, the dealer plays and tries to beat your total without busting.");
		System.out.println("Good luck and have fun!");
		System.out.println();
	}

	//game logic and display here
	public void gamePlay()
	{

		if (player.hasBlackJack() && dealer.hasBlackJack())
		{
			earlyGameOver = true;
			earlyGameOverMessage = "You and the Dealer both got a 21, It's a tie!";
			gameOn = false;
		}
		else if (player.hasBlackJack())
		{
			earlyGameOver = true;
			earlyGameOverMessage = "You got a 21, Congrats you Won!";
			gameOn = false;
		}
		else if (dealer.hasBlackJack())
		{
			earlyGameOver = true;
			earlyGameOverMessage = "The Dealer got a 21, Game Over!";
			gameOn = false;
		}

			canSplit = player.canSplit();

		System.out.println("Your Total: "+player.getSum());
		player.display();


		if (!hideFirstCard || earlyGameOver)
		{
			System.out.println("Dealer's Total: " + dealer.getSum());

			dealer.display(false);
		}
		else
		{

			System.out.println("Dealer's Total: [Hidden] + " + dealer.getVisibleCard());

			dealer.display(true);
		}
	}

	//asks players for inputs and chooses what to do based on input
	public void playerChoice()
	{
		Scanner scanner = new Scanner(System.in);

		if(earlyGameOver)
		{
			System.out.println(earlyGameOverMessage);
			System.out.println();
		}


		while(gameOn)
		{
			String choice = "";

			while (true)
			{
				if(!canSplit)
				{
					System.out.print("Would you like to hit or stand?: ");
					choice = scanner.nextLine().trim().toLowerCase();
					if (choice.equals("hit") || choice.equals("h") || choice.equals("stand") || choice.equals("s")) break;
					System.out.println("Invalid input. Please type 'hit' or 'stand'.");
				}
				else
				{
					System.out.print("You have the option to split the bets, Would you like to hit, stand or split?: ");
					choice = scanner.nextLine().trim().toLowerCase();
					if (choice.equals("hit") || choice.equals("h") || choice.equals("stand") || choice.equals("s") || choice.equals("split")) break;
					System.out.println("Invalid input. Please type 'hit', 'stand' or 'split'.");
				}

			}

			if(choice.equals("hit") || choice.equals("h"))
			{
				System.out.println();
				System.out.println("You chose to hit");
				player.hit();
				System.out.println();
				if(player.hasBust())
				{
					System.out.println("You went over 21 'Busted', Game Over!");
					System.out.println();
					gameOn = false;
				}
			}

			else if(choice.equals("stand") || choice.equals("s"))
			{
				//Made a method for hit but not for stand (probably should copy this all into a method but i think but it's probably not needed??)
				System.out.println();
				System.out.println("You chose to stand");

				dealerTurn(false);

			}

			else if(choice.equals("split"))
			{
				System.out.println();
				System.out.println("You chose to split: [Playing Hand 1]");
				System.out.println();
				//Split(cards);
			}

			else
				System.out.println("Invalid choice, please enter 'hit' or 'stand'");

		}

	}


	/*

	Kinda complicated to explain

	also there no two hands for dealer (only two hands for player)
	player can win on both/one or none


	how i want it to work:
	two cards make two arrays
	player plays first array
	then play second array
	repeat until both games are over
	then print out the results for both

	how it actually is working (or atleast currently its supposed to):
	plays hand 1
	get the result for hand 1 (It shows the dealers cards so basically u alr know dealers cards for hand 2 :/  )
	plays hand 2
	gets the results for hand 2 (dealers cards alr shown for hand 1 so yk the results before hand)




	public void Split()
	{
		//says that u split
		hasSplit = true;

		//makes both the cards
		splitHand1[0] = cards[0];
		splitHand1[1] = deck[deckPosition++];

		splitHand2[0] = cards[1];
		splitHand2[1] = deck[deckPosition++];

		//plays hand1
		GamePlay(splitHand1);
		playerChoice(splitHand1);
		int hand1Sum = sum;
		boolean hand1Bust = playerBust;

		//changes the variables for hand2
		GameOn = true;
		playerBust = false;

		System.out.println("You chose to split: [Playing Hand 2]");
		System.out.println();

		//plays hand2
		GamePlay(splitHand2);
		playerChoice(splitHand2);
		int hand2Sum = sum;
		boolean hand2Bust = playerBust;

    	System.out.println("\n[Dealer's Turn]");
		dealerTurn(true);


		//Should print out the results after BOTH hands are finished cuz same dealer hand
		System.out.println("\n[Results]");
		if(hand1Bust)
		{
			System.out.println("Hand 1: Busted");
		}
		else
		{
			System.out.println("Hand 1: " + hand1Sum + " vs Dealer: " + dealerSum + " → " + evaluateResult(hand1Sum, dealerSum));
		}

		if(hand2Bust)
		{
			System.out.println("Hand 2: Busted");
		}
		else
		{
			System.out.println("Hand 2: " + hand2Sum + " vs Dealer: " + dealerSum + " → " + evaluateResult(hand2Sum, dealerSum));
		}

		//so you don't split again?
		canSplit = false;


	}

	*/


	//this adds the cards at the starting of the game (2 for each)
	 public void addCards()
	 {

			player.addCards(deck.draw(), deck.draw());

			dealer.addCards(deck.draw(), deck.draw());

	}




	public void dealerTurn(boolean playerHit)
	{


			//If you haven't split because you if i split it has different system.out.println() statements that display instead of this
			if(!hasSplit)
			{

				dealer.playTurn();

				if(!playerHit) //It shows this if player choose to stand and the dealersTurn() method is being called from that
				{
					System.out.println("Dealer's Total: "+ dealer.getSum());
					dealer.display(false);
					evaluateResult();


				}
				else  //It shows this if player choose to hit and the dealersTurn() method is being called from Hit()
				{
					System.out.println("Dealer's Total: [Hidden] + " + dealer.getVisibleCard());
					dealer.display(true);
				}

			}

	}

	public void evaluateResult()
	{

		if(dealer.hasBust())
		{
			System.out.println("The Dealer went over 21 'Busted', You Win!");
				gameOn = false;
		}
		else
		{
			if(dealer.getSum()>player.getSum())
			{
				System.out.println("The dealer has higher sum than you, Dealer Wins!");
				gameOn = false;
			}
			else if(dealer.getSum()<player.getSum())
			{
				System.out.println("You have a higher sum than the dealer, You Win!");
				gameOn = false;
			}
			else
			{
				System.out.println("You both have the same sum, It's a tie!");
				gameOn = false;
			}
		}

				System.out.println();
	}


	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		boolean Playing = true;

		while(Playing)
		{
			Main app = new Main();
			System.out.println("Press 'r' to play again! Press any other key to quit!");
			String playing = scanner.nextLine().toLowerCase();

			if (!playing.equals("r"))
			{
				Playing = false;
				System.out.println();
				System.out.println("Thanks for Playing!");
				System.out.println();
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.println();
			}
			else
				System.out.println();
		}
	}
}
