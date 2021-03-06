import java.util.ArrayList;

public class Card {
   // Instance variables
   private final COLOR color;
   private final SHAPE shape;
   private final NUMBER number;
   private final FILL fill;
   
   /**
    Default constructor, takes color, shape,
    number, and fill and creates a new card object
    @param c color of card
    @param s shape of card
    @param n number on card
    @param f fill on card
    */
   public Card(COLOR c, SHAPE s, NUMBER n, FILL f) {
      this.color = c;
      this.shape = s;
      this.number = n;
      this.fill = f;  
   }
   
   /**
    Enum for color
    */
   public enum COLOR{
      RED,
      BLUE,
      GREEN      
      }

   /**
    Enum for shape
    */
   public enum SHAPE{
      CIRCLE,
      SQUARE,
      DIAMOND,
      }
   
   /**
    Enum for number
    */
   public enum NUMBER{
      ONE,
      TWO,
      THREE
      }
   
   /**
    Enum for fill
    */
   public enum FILL{
      FILLED,
      HASHED,
      EMPTY
      }

   /**
    returns ordinal for number of shapes
     @return number of shapes (ordinal)
    */
   public int getNum(){
      return number.ordinal();
   }

   /**
    returns ordinal for shape
    @return shape ordinal value
    */
   public int getShape(){
      return shape.ordinal();
   }

   /**
    returns ordinal for color value
    @return color ordinal value
    */
   public int getColor(){
      return color.ordinal();
   }

   /**
    return ordinal for fill value
    @return fill ordinal value
    */
   public int getFill(){
      return fill.ordinal();
   }

   @Override   
   public String toString(){
      return color.name() + "_" + 
             shape.name() + "_" +
             number.name() + "_" + 
             fill.name();
   }

   /**
    Given three cards, determines if cards are a set
    if the sum of the indexes of a groups properties is divisible by three they are a set
    @param list ArrayList containing BoardSquare objects (each containing cards)
    @return whether or not the cards are a set
    */
   public static boolean isSet(ArrayList<BoardSquare> list) {
      return isSet(list.get(0).getCard(), list.get(1).getCard(), list.get(2).getCard());
   }

   /**
    Given three cards, determines if cards are a set
    if the sum of the indexes of a groups properties is divisible by three they are a set
    @param c1 First card for checking
    @param c2 Second card for checking
    @param c3 Third card for checking
    @return whether or not the cards form a set
    */
   public static boolean isSet(Card c1, Card c2, Card c3) {
      int colorCheck;
      int shapeCheck;
      int numberCheck;
      int fillCheck;
      
      colorCheck = c1.color.ordinal() + c2.color.ordinal() + c3.color.ordinal();
      shapeCheck = c1.shape.ordinal() + c2.shape.ordinal() + c3.shape.ordinal();
      numberCheck = c1.number.ordinal() + c2.number.ordinal() + c3.number.ordinal();
      fillCheck = c1.fill.ordinal() + c2.fill.ordinal() + c3.fill.ordinal();
      
      return( colorCheck % 3 == 0 &&
          shapeCheck % 3 == 0 &&
          numberCheck % 3 == 0 &&
          fillCheck % 3 == 0 );
   }

    /**
     Looks at two cards and determines what the third card in the set is
     @param c1 first card in set
     @param c2 second card in set
     @return ordinal enum values for the missing card in the set
     */
   public static ArrayList<Integer> thirdCard(Card c1, Card c2){
      ArrayList<Integer> outCard = new ArrayList<>();
      outCard.add(Math.floorMod((-1 * c1.color.ordinal() +  -1 * c2.color.ordinal()),3));
      outCard.add(Math.floorMod((-1 * c1.shape.ordinal() + -1 * c2.shape.ordinal()),3));
      outCard.add(Math.floorMod((-1 * c1.number.ordinal() + -1 * c2.number.ordinal()),3));
      outCard.add(Math.floorMod((-1 * c1.fill.ordinal() + - 1 *c2.fill.ordinal()),3));

      return outCard;
   }

    /**
     Encodes card data into and then out of binary for easier searching in hash table
     @param c card to be encoded
     @return ordinals of card values converted to binary, concatenated, and then converted back to base 10
     */
   public static String cardEncoder(Card c){
       if (c != null) {
           String out = Integer.toString(c.color.ordinal()) +
                   Integer.toString(c.shape.ordinal()) +
                   Integer.toString(c.number.ordinal()) +
                   Integer.toString(c.fill.ordinal());

           return out;
       }
       return null;
   }

    /**
     Finds encoded value of a 'fake' card represented by an array of int values
     @param list list meant to represent card
     @return encoded value of card
     */
   public static String cardEncoder(ArrayList<Integer> list){
       String out = Integer.toString(list.get(0)) +
                    Integer.toString(list.get(1)) +
                    Integer.toString(list.get(2)) +
                    Integer.toString(list.get(3));

       return out;
   }

    /**
     overload .equals for card objects
     @param c card being checked
     @return whether or not cards are the same
     */
    public boolean equals(Card c) {
        return this.getColor() == c.getColor() &&
                this.getFill() == c.getFill() &&
                this.getNum() == c.getNum() &&
                this.getShape() == c.getShape();
    }
}