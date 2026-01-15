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

	public void hit(int index)
	{
		playerHands.get(index).addCard(deck.draw());
		System.out.println("Your Total: " + playerHands.get(index).getSum());
		playerHands.get(index).display(false);

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
		playerHands.set(handIndex, hand1); //inserts hand2 right after

	}



}