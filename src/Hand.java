import java.util.ArrayList;

public class Hand
{
	//hand is private but can be used in sub-classes
	protected ArrayList<Card> hand = new ArrayList<>();
	private boolean hasSplit = false;


	public Hand()
	{

	}

	//int... means you can add any number of arguments (behaves like an array of ints)
	//changed that to Card... hope it works the same
	public void addCards(Card... newCards)
	{
		for (Card card : newCards)
		{
			addCard(card);  // addCard handles storing the card
		}

	}


	public void addCard(Card card)
	{

		hand.add(card); //adds card at the end of the List

	}

	private void removeCard(int index)
	{
		if(index < 0 || index >= hand.size())
		{
			throw new IndexOutOfBoundsException("Invalid index: " + index);
	    }

		hand.remove(index);

	}


	//setter methods

	//Not for normal gameflow
	private void setCard(int index, Card card)
	{
		if(index < 0 || index >= hand.size())
		{
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		if(card.getValue() < 1 || card.getValue() > 13)
		{
			throw new IllegalArgumentException("Invalid card value: " + card);
		}

			hand.set(index, card); //replaces card with another card
	}


	//getter methods

	public Card getCard(int index)
	{
	    if(index < 0 || index >= hand.size())
	    {
	    	throw new IndexOutOfBoundsException("Invalid index: " + index);
	    }
	    	return hand.get(index);
	}




	public int getHandSize()
	{
		return hand.size();
	}


	public int getSum()
	{
		int sum = 0;
		int aceCount = 0;

		for(Card card : hand)
		{

			int value = card.getValue();

			if(value == 1)
			{
				aceCount++;
				sum +=1;
			}
			else if(value >= 11 && value <= 13)
				sum += 10;
			else
				sum += value;

		}

		while(aceCount > 0 && sum+10 <= 21)
		{
			sum += 10;
			aceCount--;
		}

		return sum;
	}

	//check if im soft or hard
	public boolean isSoft()
	{
		int sum = 0;
		int aceCount = 0;

		for (Card card : hand)
		{
			int value = card.getValue();
			if (value == 1)
				aceCount++;

			sum += (value >= 11) ? 10 : value;
		}
	return (aceCount > 0 && sum + 10 <= 21);
	}

	//check if BlackJack
	public boolean hasBlackJack()
	{
		return (getSum() == 21 && hand.size() == 2);
	}


	//check if Bust
	public boolean hasBust()
	{
    	return (getSum() > 21);
	}

	//clears cards for next round
	public void clear()
	{
		hand.clear();
	}




}