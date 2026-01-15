public class Player extends Hand
{
	private Deck deck;
	private boolean hasSplit = false;

	public Player(Deck deck)
	{
		this.deck = deck;
	}

	//Displays Cards for the Player
	public void display()
	{

		System.out.println("Your Cards:");

		for (Card card : hand)
		{
			System.out.printf("%-2s ", card);
		}

		System.out.println();
		System.out.println();

	}

	public void hit()
	{
		addCard(deck.draw());
		System.out.println("Your Total: " + getSum());
		display();
	}

	public boolean canSplit()
	{
		return hand.size() == 2 && hand.get(0).getValue() == hand.get(1).getValue();

	}

	public boolean hasSplit()
	{
		return hasSplit;
	}

	public void setHasSplit(boolean split)
	{
		hasSplit = split;
	}

}