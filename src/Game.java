import java.util.ArrayList;

public class Game {

    private Deck deck;
    private Board board;
    private ArrayList<BoardSquare> selected;
    private ArrayList<BoardSquare> found;

    /**
     default constructor, creates new deck and uses it to create a board,
     also creates selected ArrayList
     */
    public Game(){
        deck = new Deck();
        deck.shuffle();
        board = new Board(deck);
        selected = new ArrayList<>();
        found = new ArrayList<>();
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
     returns an ArrayList of every BoardSquare for drawing the board
     @return nested ArrayLists of BoardSquares
     */
    public ArrayList<ArrayList<BoardSquare>> getAllRows(){
        return board.getAllRows();
    }

    /**
     Tests 'selected' ArrayList for a set, if there is a set it is cleared from
     the board and replaced either with cards from the deck, or cards at the end
     of their rows if the rows contain more than 4 cards
     */
    public void testSelected(){
        if (Card.isSet(selected)) {
            selected.get(0).removeCard();
            selected.get(1).removeCard();
            selected.get(2).removeCard();
            if (board.numColumns() > 4) {
                ArrayList<BoardSquare> endElements = new ArrayList<>();
                ArrayList<BoardSquare> selectedReplacer = new ArrayList<>();
                endElements.add(board.getBoardSquare(0, board.numColumns()-1));
                endElements.add(board.getBoardSquare(1, board.numColumns()-1));
                endElements.add(board.getBoardSquare(2, board.numColumns()-1));
                for (BoardSquare square : selected) {
                    if (endElements.contains(square)) {
                        endElements.remove(square);
                    } else {
                        selectedReplacer.add(square);
                    }
                }
                selected.clear();
                clearFound();
                int i = endElements.size() - 1;
                for (BoardSquare square : selectedReplacer) {
                    square.setCard(endElements.get(i--).removeCard());
                }
                board.sub3();
            } else {
                selected.get(0).setCard(deck.getTopCard());
                selected.get(1).setCard(deck.getTopCard());
                selected.get(2).setCard(deck.getTopCard());
                selected.clear();
                clearFound();
            }
        } else {
            selected.clear();
        }
    }

    /**
     loops through card combinations to find a set for the user
     combinations are of 2 cards, method then calculates the third
     card and searches a hash table of existing cards to find location
     */
    public ArrayList<BoardSquare> findSet(){
        ArrayList<Card> foundSet = new ArrayList<>();
        ArrayList<BoardSquare> outSet = new ArrayList<>();
        for (int a = 0 ; a < board.numRows() ; a++){
            for (int b = 0; b < board.numColumns() ; b++){
                for (int c = 0; c < board.numRows() ; c++){
                    for (int d = 1 ; d < board.numColumns() ; d++){
                        if (board.getCard(a, b) != board.getCard(c, d)) {
                            String thirdCard =
                                    Card.cardEncoder(Card.thirdCard(board.getCard(a, b), board.getCard(c, d)));
                            if (BoardSquare.containsKey(thirdCard)) {
                                foundSet.add(board.getCard(a, b));
                                foundSet.add(board.getCard(c, d));
                                int[] thirdCardLocation = BoardSquare.getCardLocation(thirdCard);
                                foundSet.add(board.getCard(thirdCardLocation[0], thirdCardLocation[1]));
                                for (Card card : foundSet)
                                    outSet.add(board.getBoardSquare(BoardSquare.getCardLocation(Card.cardEncoder(card))));
                                return outSet;
                            }
                        }
                    }
                }
            }
        }
    return outSet;
    }

    /**
     add list of 'found' cards to list and change their found indicators
     @param list list of found cards
     */
    public void setFound(ArrayList<BoardSquare> list) {
        if (list.size() > 0) {
            for (BoardSquare b : list) {
                b.setFound(true);
                found.add(b);
            }
        }
    }

    /**
     clear list of found cards
     */
    public void clearFound() {
        for (BoardSquare b : found)
            b.setFound(false);
        found.clear();
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

    /**
     returns BoardSquare given an array with coordinates
     @param arr array of coordinates
     @return BoardSquare at location
     */
    public BoardSquare getBoardSquare(int [] arr) { return board.getBoardSquare(arr[0], arr[1]);}

    /**
     returns amount of cards in deck
     @return number of cards left in the deck
     */
    public int cardsLeft() { return deck.cardsLeft(); }

    @Override
    public String toString(){
        return board.toString();
    }


}
