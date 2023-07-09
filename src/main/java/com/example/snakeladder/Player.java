package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Player  {
    private Circle coin;
    private String name;
    private int currentPosition;

    private Board gameboard = new Board();

    public Player(int tileSize, Color coinColor, String playerName){

        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPosition=0;
        movePlayer(1);
        name = playerName;
    }

    boolean playerWon(){
        if(currentPosition==100){
            return true;
        }
        return false;
    }
    public void resetPlayerPosition(){
        currentPosition=0;
        movePlayer(1);
    }

    public  void movePlayer(int diceValue){
        if(diceValue+currentPosition<=100){
            currentPosition+=diceValue;

            TranslateTransition firstMove = translateAnimation(diceValue), secondMove=null;


            int newPosition = gameboard.getNewPosition(currentPosition);
            if(newPosition!=currentPosition && newPosition!=-1){
                currentPosition= newPosition;
                secondMove =translateAnimation(6);
            }
            if(secondMove==null){
                firstMove.play();
            }
            else{
                SequentialTransition sequentialTransition= new SequentialTransition(firstMove, new PauseTransition(Duration.millis(500)), secondMove);
                sequentialTransition.play();
            }
        }
//        int x = gameboard.getXCoordinates(currentPosition);
//        int y = gameboard.getYCoordinates(currentPosition);
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);

    }

    //Dice animation
    public   TranslateTransition translateAnimation(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(diceValue*150),coin);
        animate.setToX(gameboard.getXCoordinates(currentPosition));
        animate.setToY(gameboard.getYCoordinates(currentPosition));
        animate.setAutoReverse(false);
       return animate;

    }

    public Circle getCoin() {
        return coin;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
