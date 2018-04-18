import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

import java.util.ArrayList;

public class SetGui extends Application implements EventHandler<ActionEvent> {

    Game game;

    Button add3, newGame, exitGame, findSet;

    BorderPane primaryPane;
    HBox buttonContainer;
    HBox titleContainer;
    GridPane cardContainer;
    Text title;


    public class CardPane extends VBox {
        private BoardSquare boardSquare;

        public CardPane(BoardSquare boardSquare){
            this.boardSquare = boardSquare;
            drawCard();

        }

        public void drawCard() {
            int color = boardSquare.getCard().getColor();
            int shape = boardSquare.getCard().getShape();
            int fill = boardSquare.getCard().getFill();
            int num = boardSquare.getCard().getNum();

            for (int i = 1; i <= num + 1 ; i++) {
                switch (shape) {
                    case 0:
                        drawSquare(color, fill);
                        break;
                    case 1:
                        drawSquiggle(color, fill);
                        break;
                    case 2:
                        drawOval(color, fill);

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
                    r.setFill(r.getStroke());
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
                    e.setFill(e.getStroke());
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
                    c.setFill(c.getStroke());
                    break;
                case 2:
                    c.setFill(Paint.valueOf("WHITE"));
            }
            this.getChildren().add(c);
        }
    }


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

        primaryPane = new BorderPane();
        buttonContainer = new HBox();
        titleContainer = new HBox();
        cardContainer = new GridPane();
        title = new Text("Game of Set");

        drawBoard();
        buttonContainer.getChildren().add(newGame);
        buttonContainer.getChildren().add(add3);
        buttonContainer.getChildren().add(findSet);
        buttonContainer.getChildren().add(exitGame);

        titleContainer.getChildren().add(title);

        primaryPane.setBottom(buttonContainer);
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
        int row = 0;
        int col;
        for (ArrayList<BoardSquare> list : game.getAllRows()) {
            col = 0;
            for (BoardSquare boardSquare : list) {
                cardContainer.add(new CardPane(boardSquare),col, row );
                col++;
            }
            row++;
        }
    }
}


