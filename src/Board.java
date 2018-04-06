import java.util.ArrayList;

public class Board {

   private ArrayList<ArrayList<BoardSquare>> board = new ArrayList<>();

   /**
    Default constructor creates a standard 3 x 4 set
    board and draws cards into board squares
    @param deck deck of cards used for construction
    */
   public Board(Deck deck){
      for (int r = 0; r < 3; r++){
         board.add(new ArrayList<>());
         for (int c = 0; c < 4; c++){
            board.get(r).add(new BoardSquare(deck.getTopCard(),r,c));
         }
      }
   }
   
   /**
    Replaces card at specified coordinates
    @param c the card that will replace a chosen card
    @param row row of card to be replaced
    @param column column of card to be replaced
    */
   public void replaceCard(Card c, int row, int column){ board.get(row).get(column).setCard(c); }
   
   /**
    returns boardSquare object at given coordinates
    @param row row of desired boardSquare
    @param column column of desired boardSquare
    @return boardSquare at given coordinates
    */
   public BoardSquare getBoardSquare(int row, int column){ return board.get(row).get(column); }
   
   /**
    returns card at given coordinates
    @param row row of desired card
    @param column column of desired card
    @return card at given coordinates
    */
   public Card getCard(int row, int column){ return board.get(row).get(column).getCard(); }
   
   /**
    Gives number of rows in current board
    @return current number of board rows
    */
   public int numRows(){ return board.size(); }
   
   /**
    Gives number of columns in the board
    @return number of columns in current board
    */
   public int numColumns(){ return board.get(1).size(); }
   
   /**
    Adds three cards to the board (onto the right side)
    @param deck deck to be used for new card objects, should be same deck as constructor
    */
   public void add3(Deck deck){
      for (int i = 0; i < 3; i++)
         board.get(i).add(new BoardSquare(deck.getTopCard(), i, board.get(1).size()));
   }
   
   @Override
   public String toString(){
      StringBuilder out = new StringBuilder();
      for (ArrayList list : board){
         for (Object card: list){
            out = out.append("  ").append(card.toString());
         }
         out = out.append("\n");
      }
      return out.toString();
   }

}