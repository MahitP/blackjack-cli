public class Card
{
	private int value;
	private String suit;

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
		if(value == 1)
			return "[" + suit + "A" + "]";
		if(value == 11)
			return "[" + suit + "J" + "]";
		if(value == 12)
			return "[" + suit + "Q" + "]";
		if(value == 13)
			return "[" + suit + "K" + "]";

		return "[" + suit+value + "]";
	}




}



