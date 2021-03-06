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
     returns an ArrayList of every BoardSquare for drawing the board
     @return nested ArrayLists of BoardSquares
     */
   public ArrayList<ArrayList<BoardSquare>> getAllRows(){
       return board;
   }
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
     returns BoardSquare given an array with coordinates
     @param arr array of coordinates
     @return BoardSquare at location
     */
   public BoardSquare getBoardSquare(int [] arr) { return board.get(arr[0]).get(arr[1]);}
   
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
    Adds three cards to the board (onto the right side) with a maximum of 21 cards
    @param deck deck to be used for new card objects, should be same deck as constructor
    */
   public void add3(Deck deck) {
       if (board.get(0).size() < 7) {
           int col = board.get(1).size();
           for (int i = 0; i < 3; i++)
               board.get(i).add(new BoardSquare(deck.getTopCard(), i, col));
       }
   }

    /**
     removes the last index of each row
     */
   public void sub3() {
      if (board.get(0).size() > 4) {
         board.get(0).remove(board.get(0).size() - 1);
         board.get(1).remove(board.get(1).size() - 1);
         board.get(2).remove(board.get(2).size() - 1);
      }
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