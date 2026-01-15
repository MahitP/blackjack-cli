public class Deck
{
	private Card[] deck = new Card[52];
	private int deckPosition;

	String[] suits = {"♠", "♥", "♦", "♣"};
	//String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};

	public Deck()
	{
		//automatically create and shuffle a deck because im too lazy to do it manually
		create();
		shuffle();
	}

	public void create()
	{
		int index = 0;
		for(int s = 0; s < suits.length; s++)
		{
			for(int v = 0; v < 13; v++)
			{
				deck[index] = new Card(v+1, suits[s]); //Cards go from 1-13
				index++;

			}

		}
	}

	public void shuffle()
	{
		//Fisher-Yates shuffle: (A algorithm to shuffle the cards) I have no idea how this works it just shuffles it (copied from google)
		for (int i = deck.length - 1; i > 0; i--)
		{
			int j = (int)(Math.random() * (i + 1));
			Card temp = deck[i];
			deck[i] = deck[j];
			deck[j] = temp;
		}

	}

	public Card draw()
	{

		if(deckPosition >= deck.length)
		{
			throw new IllegalStateException("No more cards in the deck.");
		}

		Card card = deck[deckPosition];
		deckPosition++;

		return card;

	}

	//used for debugging
	public int getRemainingCards()
	{
		return deck.length-deckPosition;
	}


	public Card peek()
	{
		return deck[deckPosition];
	}

	//clears the deck and makes a new one
	public void clear()
	{
		deck = new Card[52];
		deckPosition = 0;

		create();
		shuffle();
	}

}