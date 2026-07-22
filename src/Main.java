import java.util.Scanner;

 public class Main
 {

	Deck deck;

	Player player;
	Dealer dealer;

	String earlyGameOverMessage = "";

	boolean gameOn = true;
	boolean hideFirstCard = true;
	boolean earlyGameOver = false;

	private final Scanner scanner = new Scanner(System.in);

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

		gameOn = true;
		hideFirstCard = true;
		earlyGameOver = false;

		earlyGameOverMessage = "";

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
		int playerBet = promptBetAmount(player.getTotal());
		player.setBet(playerBet);

		if(earlyGameOver)
		{
			System.out.println(earlyGameOverMessage);
			if (player.hasBlackJack() && !dealer.hasBlackJack())
				player.adjustTotal(true, 0);
			else if (!player.hasBlackJack() && dealer.hasBlackJack())
				player.adjustTotal(false, 0);
			System.out.println();
			return;
		}

		playPlayerHands();
	}

	private int promptBetAmount(int max)
	{
		int playerBet;
		System.out.println("Total Money: " + player.getTotal());

		while (true)
		{
			System.out.print("How much would you like to bet? (1 - " + max + "): ");
			String line = scanner.nextLine().trim();

			try
			{
				playerBet = Integer.parseInt(line);
			}
			catch (NumberFormatException e)
			{
				System.out.println("Invalid input. Please enter a whole number.");
				continue;
			}

			if (playerBet < 1)
			{
				System.out.println("Bet is too low. Please enter a number higher than 0.");
			}
			else if (playerBet > max)
			{
				System.out.println("Bet is too high. Please enter a number lower than or equal to " + max + ".");
			}
			else
			{
				return playerBet;
			}
		}
	}

	private String promptPlayerAction(boolean canSplit)
	{
		while (true)
		{
			if (canSplit)
			{
				System.out.print("You have the option to split the bets, Would you like to hit, stand or split?: ");
			}
			else
			{
				System.out.print("Would you like to hit or stand?: ");
			}

			String action = scanner.nextLine().trim().toLowerCase();

			if (action.equals("hit") || action.equals("h") || action.equals("stand") || action.equals("s") || (canSplit && action.equals("split")))
			{
				return action;
			}

			if (canSplit)
			{
				System.out.println("Invalid input. Please type 'hit', 'stand' or 'split'.");
			}
			else
			{
				System.out.println("Invalid input. Please type 'hit' or 'stand'.");
			}
		}
	}

	private void playPlayerHands()
	{
		player.setHandIndex(0);

		do
		{
			playCurrentHand();
		} while(player.nextHand());

		dealerTurn();
		evaluateHands();
	}

	private void playCurrentHand()
	{
		Hand hand = player.getCurrentHand();

		while (!hand.hasBust())
		{
			player.display();

			boolean canSplit = hand.canSplit();
			String action = promptPlayerAction(canSplit);

			if (action.equals("hit") || action.equals("h"))
			{
				System.out.println("You chose to hit");
				System.out.println();
				hand.addCard(deck.draw());
				System.out.println("Your Total For " + handLabel(player.getHandIndex()) + ": " + hand.getSum());
				player.display();
				System.out.println();

				if(hand.hasBust())
				{
					System.out.println(handLabel(player.getHandIndex()) + " Busted!");
					System.out.println();
					player.display();
					break;
				}
			}
			else if (action.equals("stand") || action.equals("s"))
			{
				System.out.println("You chose to stand for " + handLabel(player.getHandIndex()));
				System.out.println();
				break;
			}
			else if (action.equals("split") && canSplit)
			{
				player.split();
				hand = player.getCurrentHand();
				System.out.println("You chose to split!");
				System.out.println();
			}
		}
	}

	private void evaluateHands()
	{
		int dealerSum = dealer.getSum();

		for(int i = 0; i < player.getHands().size(); i++)
		{
			Hand hand = player.getHands().get(i);

			if (hand.hasBust())
			{
				System.out.println("Result for " + handLabel(i) + ":");
				System.out.println("Busted!");
				player.adjustTotal(false, i);
				System.out.println();
				continue;
			}

			evaluateResult(i, hand.getSum(), dealerSum);
		}
	}

	public void evaluateResult(int handIndex, int playerSum, int dealerSum)
	{
		System.out.println("Result for " + handLabel(handIndex) + ":");

		if (dealerSum > 21)
		{
			System.out.println("The Dealer went over 21 'Busted', You Win!");
			player.adjustTotal(true, handIndex);
		}
		else
		{
			if (dealerSum > playerSum)
			{
				System.out.println("The dealer has higher sum than you, Dealer Wins!");
				player.adjustTotal(false, handIndex);
			}
			else if (dealerSum < playerSum)
			{
				System.out.println("You have a higher sum than the dealer, You Win!");
				player.adjustTotal(true, handIndex);
			}
			else
			{
				System.out.println("You both have the same sum, It's a tie!");
				player.clearHandBet(handIndex);
			}
		}

		System.out.println();
	}

	private String handLabel(int index)
	{
		return player.getHands().size() > 1 ? "Hand " + index : "Your hand";
	}





	//this adds the cards at the starting of the game (2 for each)
	 public void addCards()
	 {

			player.addCards(deck.draw(), deck.draw());

			dealer.addCards(deck.draw(), deck.draw());

	}


	 public void dealerTurn()
	 {
		 dealer.playTurn();
		 hideFirstCard = false;

		 System.out.println("Dealer's Total: " + dealer.getSum());
		 dealer.display(false);

	 }
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		boolean Playing = true;

		while(Playing)
		{
			Main app = new Main();
			System.out.print("Press 'r' to play again! Press any other key to quit!: ");
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
