public class Dealer extends Hand
{
	private Deck deck;

	public Dealer(Deck deck)
	{
		this.deck = deck;

	}

	//Displays Cards for the Dealer (Can Hide the First Card)
	public void display(boolean hideFirstCard)
	{

		System.out.println("Dealer's Cards:");
		for (int x = 0; x < hand.size(); x++)
		{
			if(x == 0 && hideFirstCard)
				System.out.printf("[%-2s]", "?");
			else
			{
					System.out.printf("%-2s ", hand.get(x));
			}
			if (x % 12 == 11)
				System.out.println();
		}
		System.out.println("\n");


	}

	public int getVisibleCard()
	{
		if (getHandSize() < 2)
			return 0;


		if (hand.get(1).getValue() == 1)
			return 11;
		if (hand.get(1).getValue() >= 11)
			return 10;

		return hand.get(1).getValue();
	}

	public void playTurn()
	{

			while(true)
			{
				if(hasBust() || getSum()>=17)
					break;

				addCard(deck.draw());
			}

	}

}