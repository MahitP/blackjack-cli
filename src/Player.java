import java.util.List;
import java.util.ArrayList;

public class Player
{
	private Deck deck;
	List<Hand> playerHands = new ArrayList<>();
	private int handIndex;

	private int total = 1000;
	private int bet;
	private final List<Integer> handBets = new ArrayList<>();

	public Player(Deck deck)
	{
		this.deck = deck;
		handIndex = 0;

		Hand startingHand = new Hand();
		playerHands.add(startingHand);
		handBets.add(0);

	}

	//Displays Cards for the Player
	public void display()
	{
		if(playerHands.size() == 1)
		{
			System.out.println("Your Hand: ");
			for (Card card : getCurrentHand().getCards())
			{
				System.out.print(card + " ");
			}
			System.out.println();
		}
		else
		{
			for(int i = 0; i < playerHands.size(); i++)
			{
				Hand hand = playerHands.get(i);

				if (hand == getCurrentHand())
				{
					System.out.println("Hand " + i + " [Current Hand]: ");
				}
				else
				{
					System.out.println("Hand " + i + ": ");

				}

				for (Card card : hand.getCards())
				{
					System.out.print(card + " ");
				}
				System.out.println();

			}

		}

		System.out.println();

	}

	public void addHand()
	{
		Hand hand = new Hand();
		playerHands.add(hand); //don't change index because what your current hand is.. not how many hands are there
		handBets.add(0);
	}


	public void addCards(Card... newCards)
	{
		for (Card card : newCards)
		{
			playerHands.get(handIndex).addCard(card);  // addCard handles storing the card
		}

	}

	public void hit()
	{
		Hand hand = getCurrentHand();
		hand.addCard(deck.draw());
		System.out.println("Your Total For Hand " + handIndex + ": " + hand.getSum());
		hand.display();

	}

	public int getHandBet(int index)
	{
		if (index < 0 || index >= handBets.size())
			return 0;
		return handBets.get(index);
	}

	public boolean canSplit()
	{
		Hand hand = getCurrentHand();
		return hand.size() == 2 && hand.getCard(0).getValue() == hand.getCard(1).getValue();
	}


	public void split()
	{
		Hand handToSplit = playerHands.get(handIndex);

		Hand hand1 = new Hand();
		Hand hand2 = new Hand();

		hand1.addCard(handToSplit.getCard(0));
		hand1.addCard(deck.draw());

		hand2.addCard(handToSplit.getCard(1));
		hand2.addCard(deck.draw());

		playerHands.set(handIndex, hand1); //replaces the handToSplit with hand1
		playerHands.add(handIndex + 1, hand2); //inserts hand2 right after

		int currentBet = getHandBet(handIndex);
		handBets.add(handIndex + 1, currentBet);
	}

	public int getHandIndex()
	{
		return handIndex;
	}

	public void setHandIndex(int value)
	{
		handIndex = value;
	}

	public int getSum()
	{
		return playerHands.get(handIndex).getSum();
	}

	public boolean hasBust()
	{
		return playerHands.get(handIndex).hasBust();
	}

	public boolean hasBlackJack()
	{
		return playerHands.get(handIndex).hasBlackJack();
	}

	public Hand getCurrentHand()
	{
		return playerHands.get(handIndex);
	}


	public boolean nextHand()
	{
		if(handIndex + 1 < playerHands.size()) {
			handIndex++;
			return true;
		}
		else
			return false;
	}

	public boolean hasSplit()
	{
		return playerHands.size() > 1;
	}

	//This getter is used for loops because for each loops don't work
	public List<Hand> getHands()
	{
		return playerHands; //hand is basically a ArrayList of cards
	}

	public int getTotal()
	{
		return total;
	}

	public boolean isBankrupt()
	{
		return total <= 0;
	}

	public int getBet()
	{
		return bet;
	}

	public void setBet(int value)
	{
		bet = value;
		handBets.clear();
		handBets.add(value);
	}

	public void adjustTotal(boolean youWon)
	{
		adjustTotal(youWon, 0);
	}

	public void adjustTotal(boolean youWon, int handIndex)
	{
		if (handIndex < 0 || handIndex >= handBets.size())
			return;

		int amount = handBets.get(handIndex);
		if (amount == 0)
			return;

		total += youWon ? amount : -amount;
		clearHandBet(handIndex);
		bet = 0;
	}

	public void clearHandBet(int handIndex)
	{
		if (handIndex < 0 || handIndex >= handBets.size())
			return;

		handBets.set(handIndex, 0);
	}


}
