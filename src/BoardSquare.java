import java.util.HashMap;

public class BoardSquare {
   private Card card;
   private int column;
   private int row;
   private boolean selected;
   private boolean found;
   private static HashMap<Integer, int[]> dict = new HashMap<>();
   
   /**
    Default constructor
    @param card card to be contained in boardSquare
    @param column column number of boardSquare
    @param row row number of boardSquare
    */
   public BoardSquare(Card card, int row, int column) {
      this.card = card;
      this.column = column;
      this.row = row;
      selected = false;
      found = false;
      dict.put(Card.cardEncoder(card), new int[] {this.row, this.column} );
   }
   
   /**
    Returns card object in boardSquare
    @return card object contained in board object
    */
   public Card getCard(){ return card; }
   
   /**
    Returns row index of square
    @return row index of square
    */
   public int getRow() { return row; }
   
   /**
    Returns column index of square
    @return column index of square
    */
   public int getColumn() { return column; }
   
   /**
    is the current square selected
    @return current selection state of square
    */
   public boolean isSelected() { return selected; }

    /**
     is the current square found
     @return if card is found
     */
   public boolean isFound() {return found;}
   
   /**
    sets card object in square, replaces dict key to correspond with new card
    @param c card to go into square
    */
   public void setCard(Card c){
       dict.remove(Card.cardEncoder(c));
       dict.put(Card.cardEncoder(c), new int [] {column, row});
       card = c;
   }

    /**
     removes card at a given index, and removes the card's key from dictionary,
     returns card object that's been removed
     @return card stored in boardSquare object before removal
     */
   public Card removeCard() {
       Card c = card;
       dict.remove(Card.cardEncoder(card));
       card = null;
       return c;
   }

    /**
     returns true if the board has a card object
     @return wether or not the square has a card
     */
   public boolean hasCard() {
       return (card != null);
   }
   
   /**
    sets row index of boardSquare, replaces dict value to correspond with new row
    @param r row index to set
    */
   public void setRow(int r){
       dict.replace(Card.cardEncoder(card), new int [] {column, r});
       row = r;
   }
   
   /**
    sets column index of boardSquare, replaces dict value to correspond with new column
    @param c column index to be set
    */
   public void setColumn(int c){
       dict.replace(Card.cardEncoder(card), new int [] {c, row});
       column = c;
   }
   
   /**
    sets selection state of square
    @param s value whether or not board is selected
    */
   public void setSelect(boolean s){ selected = s; }

    /**
     sets found variable for highlighting cards with find set
     @param f whether or not a card is found
     */
   public void setFound(boolean f) {found = f;}
   
   @Override
   public String toString(){ return card.toString(); }

   /**
    Gets location of a card given the hashed value of the card (returns null if card is not present
    @param cardHash Hash of the card being looked for
    @return location on board of the card, null if card is not on board
    */
   public static int [] getCardLocation(int cardHash) { return dict.get(cardHash); }

}