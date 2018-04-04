public class BoardSquare {
   private Card card;
   private int column;
   private int row;
   private boolean selected;
   
   /**
   Default constructor
   @param card card to be contained in boardSquare
   @param column column number of boardSquare
   @param row row number of boardSquare
   */
   public BoardSquare(Card card, int column, int row) {
      this.card = card;
      this.column = column;
      this.row = row;
      selected = false;
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
   sets card object in square
   @param c card to go into square
   */
   public void setCard(Card c){ card = c; }
   
   /**
   sets row index of boardSquare
   @param r row index to set
   */
   public void setRow(int r){ row = r; }
   
   /**
   sets column index of boardsquare
   @param c column index to be set
   */
   public void setColumn(int c){ column = c; }
   
   /**
   sets selection state of square
   @param s value whether or not board is selected
   */
   public void setSelect(boolean s){ selected = s; }
   
   @Override
   public String toString(){ return card.toString(); }

}