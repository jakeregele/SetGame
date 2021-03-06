import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.geometry.Pos;
import javafx.scene.image.Image;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;


public class SetGui extends Application implements EventHandler<ActionEvent> {

    private Game game;

    private Button add3, newGame, exitGame, findSet;

    private BorderPane primaryPane;
    private HBox bottomContainer;
    private HBox titleContainer;
    private GridPane cardContainer;
    private Text title, cardsLeft;





    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        game = new Game();

        primaryStage.setTitle("Game of Set");

        add3 = new Button("Add 3");
        newGame = new Button("New Game");
        exitGame = new Button("Exit");
        findSet = new Button("Help!");

        /**
         adds three cards to the board
         */
        add3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!game.outOfCards()) {
                    game.add3();
                    drawBoard();
                }
            }
        });

        /**
         starts a new game instance in the window
         */
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.clearFound();
                start(primaryStage);
            }
        });

        /**
         exits instance of the game
         */
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        /**
         calls find set method
         */
        findSet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.clearFound();
                game.setFound(game.findSet());
                drawBoard();
            }
        });

        primaryPane = new BorderPane();
        bottomContainer = new HBox();
        titleContainer = new HBox();

        cardContainer = new GridPane();
        cardContainer.setHgap(15);
        cardContainer.setVgap(15);
        cardContainer.setAlignment(Pos.CENTER);

        title = new Text("Game of Set");
        title.setStyle("-fx-font: 35 arial");
        cardsLeft = new Text("Cards left: " + game.cardsLeft());

        drawBoard();
        bottomContainer.getChildren().add(newGame);
        bottomContainer.getChildren().add(add3);
        bottomContainer.getChildren().add(findSet);
        bottomContainer.getChildren().add(exitGame);
        bottomContainer.getChildren().add(cardsLeft);
        bottomContainer.setAlignment(Pos.CENTER);
        bottomContainer.setPadding(new Insets(10, 0, 10, 0));
        bottomContainer.setSpacing(10);

        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(10, 0, 10, 0));

        primaryPane.setBottom(bottomContainer);
        primaryPane.setCenter(cardContainer);
        primaryPane.setTop(titleContainer);

        Scene primaryScene = new Scene(primaryPane);

        primaryStage.setScene(primaryScene);

        primaryStage.show();
    }


    /**
     draws the board and all cards it contains
     adds listeners to cards
     */
    public void drawBoard(){
        cardContainer.getChildren().clear();
        cardsLeft.setText("Cards Left: " + game.cardsLeft());
        if (!game.isOver()) {
            int row = 0;
            int col;
            for (ArrayList<BoardSquare> list : game.getAllRows()) {
                col = 0;
                for (BoardSquare boardSquare : list) {
                    CardPane pane = new CardPane(boardSquare);
                    pane.setAlignment(Pos.CENTER);
                    pane.setPrefSize(200, 250);
                    pane.setSpacing(10);
                    pane.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                        @Override
                        public void handle(javafx.scene.input.MouseEvent event) {
                            CardPane c = (CardPane) (event.getSource());
                            BoardSquare b = c.getBoardSquare();
                            if (b.isSelected()) {
                                b.setSelect(false);
                                game.removeSelected(b.getRow(), b.getColumn());
                            } else {
                                b.setSelect(true);
                                game.addToSelected(b.getRow(), b.getColumn());
                            }
                            b.setFound(false);
                            if (game.numSelected() == 3) {
                                for (BoardSquare selected : game.getSelected())
                                    selected.setSelect(false);
                                game.testSelected();
                                game.clearFound();
                            }

                            drawBoard();
                        }
                    });
                    cardContainer.add(pane, col, row);
                    col++;
                }
                row++;
            }
        } else {
            Text end = new Text("You Win!");
            end.setStyle("-fx-font: 30 arial");
            cardContainer.getChildren().add(end);
        }
    }

    @Override
    public void handle(ActionEvent event) {

    }

    /**
     class to store and display BoardSquare objects
     */
    public class CardPane extends VBox {
        private BoardSquare boardSquare;


        /**
         constructor, takes BoardSquare object and uses attributes to draw shapes
         @param boardSquare square being drawn
         */
        public CardPane(BoardSquare boardSquare){
            if (boardSquare.hasCard()) {
                this.boardSquare = boardSquare;
                if (boardSquare.isSelected()) {
                    this.setBackground(new Background(new BackgroundFill(Paint.valueOf("LIGHTBLUE"),
                            new CornerRadii(0),
                            new Insets(0))));
                } else if (boardSquare.isFound()) {
                    this.setBackground(new Background(new BackgroundFill(Paint.valueOf("LIGHTCORAL"),
                            new CornerRadii(0),
                            new Insets(0))));
                } else {
                    this.setBackground(new Background(new BackgroundFill(Paint.valueOf("WHITE"),
                            new CornerRadii(0),
                            new Insets(0))));
                }
                drawCard();
            }

        }

        /**
         gives square from instance of a card pane
         @return BoardSquare in a given card pane
         */
        public BoardSquare getBoardSquare() { return boardSquare; }

        /**
         draws card as specified by it's attributes
         */
        public void drawCard() {
            if (boardSquare.hasCard()) {
                int color = boardSquare.getCard().getColor();
                int shape = boardSquare.getCard().getShape();
                int fill = boardSquare.getCard().getFill();
                int num = boardSquare.getCard().getNum();

                for (int i = 1; i <= num + 1; i++) {
                    switch (shape) {
                        case 0:
                            drawOval(color, fill);
                            break;
                        case 1:
                            drawSquare(color, fill);
                            break;
                        case 2:
                            drawSquiggle(color, fill);
                    }
                }
            }
        }

        /**
         Draws square objects of a given color and fill
         @param color color of square
         @param fill fill of square
         */
        public void drawSquare(int color, int fill){
            Rectangle r = new Rectangle(50, 50);
            switch (color) {
                case 0:
                    r.setStroke(Paint.valueOf("RED"));
                    break;
                case 1:
                    r.setStroke(Paint.valueOf("BLUE"));
                    break;
                case 2:
                    r.setStroke(Paint.valueOf("GREEN"));
            }
            switch (fill) {
                case 0:
                    r.setFill(r.getStroke());
                    break;
                case 1:
                    try {
                        Image img = new Image("hatch.png");
                        r.setFill(new ImagePattern(img));
                    } catch (IllegalArgumentException e) {
                        r.setFill(Paint.valueOf("GREY"));
                    }
                    break;
                case 2:
                    r.setFill(Paint.valueOf("WHITE"));
            }
            this.getChildren().add(r);
        }

        /**
         draws diamonds of a given color and fill
         @param color diamond color
         @param fill diamond fill
         */
        public void drawSquiggle(int color, int fill){
            Polygon p = new Polygon();

            p.getPoints().addAll(60.0, 10.0,
                                          90.0, 30.0,
                                          60.0, 50.0,
                                          30.0, 30.0);
            switch (color) {
                case 0:
                    p.setStroke(Paint.valueOf("RED"));
                    break;
                case 1:
                    p.setStroke(Paint.valueOf("BLUE"));
                    break;
                case 2:
                    p.setStroke(Paint.valueOf("GREEN"));
            }
            switch (fill) {
                case 0:
                    p.setFill(p.getStroke());
                    break;
                case 1:
                    try {
                        Image img = new Image("hatch.png");
                        p.setFill(new ImagePattern(img));
                    } catch (IllegalArgumentException e) {
                        p.setFill(Paint.valueOf("GREY"));
                    }
                    break;
                case 2:
                    p.setFill(Paint.valueOf("WHITE"));
            }
            this.getChildren().add(p);

        }

        /**
         draws circles of a given color and fill
         @param color circle color
         @param fill circle fill
         */
        public void drawOval(int color, int fill){
            Circle c = new Circle(25);
            switch (color) {
                case 0:
                    c.setStroke(Paint.valueOf("RED"));
                    break;
                case 1:
                    c.setStroke(Paint.valueOf("BLUE"));
                    break;
                case 2:
                    c.setStroke(Paint.valueOf("GREEN"));
            }
            switch (fill) {
                case 0:
                    c.setFill(c.getStroke());
                    break;
                case 1:
                    try {
                        Image img = new Image("hatch.png");
                        c.setFill(new ImagePattern(img));
                    } catch (IllegalArgumentException e) {
                        c.setFill(Paint.valueOf("GREY"));
                    }
                    break;
                case 2:
                    c.setFill(Paint.valueOf("WHITE"));
            }
            this.getChildren().add(c);
        }
    }

}




