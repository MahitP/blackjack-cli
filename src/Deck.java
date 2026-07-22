public class Deck
{
	private static final String[] SUITS = {"♠", "♥", "♦", "♣"};

	private static final int CARDS_PER_SUIT = 13;
	private static final int NUMBER_OF_SUITS = SUITS.length;
	private static final int DECK_SIZE = CARDS_PER_SUIT * NUMBER_OF_SUITS;

	private Card[] deck = new Card[DECK_SIZE];
	private int deckPosition;

	//String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};

	public Deck()
	{
		//automatically create and shuffle a deck because im too lazy to do it manually
		create();
		shuffle();
	}

	public void create()
	{
		deckPosition = 0;
		int index = 0;

		for(int s = 0; s < NUMBER_OF_SUITS; s++)
		{
			for(int v = 0; v < CARDS_PER_SUIT; v++)
			{
				deck[index] = new Card(v+1, SUITS[s]); //Cards go from 1-13
				index++;

			}

		}
	}

	public void shuffle()
	{
		//Fisher-Yates shuffle: (A algorithm to shuffle the cards)
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

		Card card = deck[deckPosition++];

		return card;

	}

	//used for debugging
	public int getRemainingCards()
	{
		return deck.length - deckPosition;
	}


	public Card peek()
	{
		if(deckPosition >= deck.length)
		{
			throw new IllegalStateException("No more cards in the deck.");
		}
		return deck[deckPosition];
	}


	//clears the deck and makes a new one
	public void clear()
	{
		create();
		shuffle();
	}

}