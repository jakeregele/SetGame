import java.util.ArrayList;

public class Game {

    private Deck deck;
    private Board board;
    private ArrayList<BoardSquare> selected;

    /**
     default constructor, creates new deck and uses it to create a board,
     also creates selected ArrayList
     */
    public Game(){
        deck = new Deck();
        board = new Board(deck);
        selected = new ArrayList<>();
    }

    /**
     Adds BoardSquare to the selected ArrayList
     @param row row of square being selected
     @param col column of square being selected
     */
    public void addToSelected(int row, int col){
        selected.add(board.getBoardSquare(row, col));
    }

    /**
     Removes specific BoardSquare from selected
     @param row row of square to be removed
     @param col column of square to be removed
     */
    public void removeSelected(int row, int col) {
        selected.remove(board.getBoardSquare(row, col));
    }

    /**
     returns a copy of the 'selected' ArrayList
     @return the 'selected' ArrayList
     */
    public ArrayList<BoardSquare> getSelected() {
        return selected;
    }

    /**
     Tests 'selected' ArrayList for a set, if there is a set it is cleared from
     the board and replaced either with cards from the deck, or cards at the end
     of their rows if the rows contain more than 4 cards
     */
    public void testSelected(){

    }

    /**
     loops through card combinations to find a set for the user
     combinations are of 2 cards, method then calculates the third
     card and searches a hash table of existing cards to find location
     */
    public void findSet(){

    }

    /**
     adds 3 cards from the deck to the board
     */
    public void add3(){ board.add3(deck); }

    /**
     returns number of cards in 'selected' ArrayList
     @return size of 'selected' ArrayList
     */
    public int numSelected() { return selected.size();}

    /**
     returns true if game's deck AND board have run out of cards
     @return true if deck and board are empty, false if not
     */
    public boolean outOfCards(){
        return deck.isEmpty() && board.numColumns() + board.numRows() == 0;
    }

    @Override
    public String toString(){
        return board.toString();
    }


}
