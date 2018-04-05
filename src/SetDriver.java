public class SetDriver {
    public static void main(String[] args) {
        Deck d = new Deck();
        Board b = new Board(d);
        System.out.println(b);
        Card c1 = d.getTopCard();
        Card c2 = d.getTopCard();
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(Card.thirdCard(c1, c2));
        System.out.println(Card.cardEncoder(Card.thirdCard(c1, c2)));
        System.out.println(Card.cardEncoder(c1));
        System.out.println(Card.cardEncoder(c2));
        Card c = b.getCard(2, 3);
        System.out.println(BoardSquare.getCardLocation(Card.cardEncoder(c))[0]);
        System.out.println(BoardSquare.getCardLocation(Card.cardEncoder(c))[1]);
    }
}