public class Card
{
	private final int value;
	private final String suit;

	public Card(int value, String suit)
	{
		this.value = value;
		this.suit = suit;

	}

	public int getValue()
	{
		return value;
	}

	public String getSuit()
	{
		return suit;
	}

	public String toString()
	{
		switch(value)
		{
			case 1:
				return "[" + suit + "A" + "]";
			case 11:
				return "[" + suit + "J" + "]";
			case 12:
				return "[" + suit + "Q" + "]";
			case 13:
				return "[" + suit + "K" + "]";
			default:
				return "[" + suit + value + "]";
		}
	}

}



