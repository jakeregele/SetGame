import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Deck {

   private ArrayList<Card> deck = new ArrayList<>();
   private Random r = new Random();
   
   /**
    Default constructor, creates deck with all possible cards
    */
   public Deck() {
      for (Card.COLOR c : Card.COLOR.values())
         for (Card.SHAPE s : Card.SHAPE.values())
            for (Card.NUMBER n : Card.NUMBER.values())
               for (Card.FILL f : Card.FILL.values())
                  deck.add(new Card(c, s, n ,f));
   }
   
   /**
    Shuffles deck by swaping every index with a random other index
    */
   public void shuffle(){
      for (int i = 0; i < deck.size(); i++){
         Collections.swap(deck, i, r.nextInt(deck.size()-1));
      }
   }
   
   /**
    Returns true if deck is empty
    @return true if deck is empty, false if it is not
    */
   public boolean isEmpty(){ return (deck.size() < 1); }
   
   /**
    Removes card from the 'top' of the deck and gives it
    to the caller
    @return card from the top of the deck
    */
   public Card getTopCard(){
      if (!deck.isEmpty())
        return deck.remove(deck.size()-1);
      return null;
   }

    /**
     retuns cards left in deck
     @return amount of cards in deck
     */
   public int cardsLeft() { return  deck.size(); }
   
   @Override
   public String toString(){
      StringBuilder out = new StringBuilder();
      for (Card c : deck)
         out.append(c.toString()).append("\n");
      return out.toString();
   }
}