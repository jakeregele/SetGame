import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.geometry.Pos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

        add3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!game.outOfCards()) {
                    game.add3();
                    drawBoard();
                }
            }
        });

        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(primaryStage);
            }
        });

        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        findSet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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



    @Override
    public void handle(ActionEvent event) {


    }


    public void drawBoard(){
        cardContainer.getChildren().clear();
        cardsLeft.setText("Cards Left: " + game.cardsLeft());
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
                            CardPane c = (CardPane)(event.getSource());
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
                cardContainer.add(pane ,col, row );
                col++;
            }
            row++;
        }
    }

    public class CardPane extends VBox {
        private BoardSquare boardSquare;



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

        public BoardSquare getBoardSquare() {
            return boardSquare;
        }

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
                    r.setFill(Paint.valueOf("GREY"));
                    break;
                case 2:
                    r.setFill(Paint.valueOf("WHITE"));
            }
            this.getChildren().add(r);
        }

        public void drawSquiggle(int color, int fill){
            Ellipse e = new Ellipse(25, 15);
            switch (color) {
                case 0:
                    e.setStroke(Paint.valueOf("RED"));
                    break;
                case 1:
                    e.setStroke(Paint.valueOf("BLUE"));
                    break;
                case 2:
                    e.setStroke(Paint.valueOf("GREEN"));
            }
            switch (fill) {
                case 0:
                    e.setFill(e.getStroke());
                    break;
                case 1:
                    e.setFill(Paint.valueOf("GREY"));
                    break;
                case 2:
                    e.setFill(Paint.valueOf("WHITE"));
            }
            this.getChildren().add(e);

        }

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
                    c.setFill(Paint.valueOf("GREY"));
                    break;
                case 2:
                    c.setFill(Paint.valueOf("WHITE"));
            }
            this.getChildren().add(c);
        }
    }

}




