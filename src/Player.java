import java.util.List;
import java.util.ArrayList;

public class Player
{
	private Deck deck;
	List<Hand> playerHands = new ArrayList<>();;
	int handIndex;

	public Player(Deck deck)
	{
		this.deck = deck;

		Hand startingHand = new Hand();
		startingHand.addCards(deck.draw(), deck.draw());
		playerHands.add(startingHand);

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
		hand.display(false);

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
		playerHands.add(handIndex++, hand2); //inserts hand2 right after

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



}