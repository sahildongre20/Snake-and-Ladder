package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public static  final  int tileSize=60, height=10, width=10;
    public  static  final  int buttonLine = height*tileSize+50, infoLine=buttonLine-30;

    public  static  Dice gameDice = new Dice();
    public  Player playerOne,playerTwo;

    boolean gameStarted=false, playerOneTurn=false, playerTwoTurn=false;

    // new pane (for application window )
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);

        //creating 100 tiles and adding to the pane
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(i*tileSize);
                tile.setTranslateY(j*tileSize);
                root.getChildren().add(tile);

            }

        }

        // setting image as a background for the board
        Image img = new Image("C:\\Users\\sahil\\OneDrive\\Desktop\\amt\\accio-minor-projects\\snake-ladder\\src\\main\\board.png");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);


        //Buttons
        Button playerOneButton = new Button("Player One");
        playerOneButton.setDisable(true);
        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setDisable(true);
        Button startButton = new Button("Start");

        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(30);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(280);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(490);



        //labels

        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label dice = new Label("Start The Game!");

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(10);
        dice.setTranslateY(infoLine);
        dice.setTranslateX(260);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(470);

        //players
        playerOne = new Player(tileSize, Color.BLACK,"Sahil");
        playerTwo = new Player(tileSize-5, Color.WHITE,"Pratik");

        //player Actions

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn) {
                        int diceValue = gameDice.getDiceValue();
                        playerOne.movePlayer(diceValue);
                        dice.setText("Dice Value : " + diceValue);
                        if (playerOne.playerWon()) {
                            dice.setText("Winnwe is " + playerOne.getName());
                            playerOneTurn=false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoTurn=false;
                            playerTwoLabel.setText("");
                            playerTwoButton.setDisable(true);

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted=false;
                            playerOne.resetPlayerPosition();

                        } else {

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn " + playerTwo.getName());

                        }
                    }
                }


            }
        });

        //player two button

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = gameDice.getDiceValue();
                        playerTwo.movePlayer(diceValue);
                        dice.setText("Dice Value : "+diceValue);
                        if (playerTwo.playerWon()) {
                            dice.setText("Winnwe is " + playerTwo.getName());
                            playerOneTurn=false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoTurn=false;
                            playerTwoLabel.setText("");
                            playerTwoButton.setDisable(true);

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted=false;
                            playerTwo.resetPlayerPosition();

                        } else {

                        playerOneTurn = true;
                        playerOneButton.setDisable(false);
                        playerOneLabel.setText("Your Turn "+ playerOne.getName());

                        playerTwoTurn = false;
                        playerTwoButton.setDisable(true);
                        playerTwoLabel.setText("");

                    }
                    }
                }
            }
        });

        //Start button actions
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted=true;
                dice.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn=true;
                playerOneButton.setDisable(false);
                playerOneLabel.setText("Your Turn "+ playerOne.getName());
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);


            }
        });




        root.getChildren().addAll(
                board,playerOneButton,startButton,playerTwoButton,playerOneLabel,playerTwoLabel,dice,
                playerOne.getCoin(),playerTwo.getCoin()
                );




        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake And Ladder!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}