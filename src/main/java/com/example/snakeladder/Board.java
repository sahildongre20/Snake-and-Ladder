package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>> positionCoordinates;
    ArrayList<Integer> snakeLadderPosition ;

    public Board(){
        positionCoordinates = new ArrayList<>();
        setPositionCoordinates();
        populateSnakeLadder();
    }

    public  void setPositionCoordinates(){
        positionCoordinates.add(new Pair<>(0,0));

        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                int xCord=0;
                if (i%2==0){
                   xCord= j*SnakeLadder.tileSize+SnakeLadder.tileSize/2;
                }
                else{
                    xCord = SnakeLadder.width*SnakeLadder.tileSize-(j*SnakeLadder.tileSize)-SnakeLadder.tileSize/2;
                }
                int yCord = SnakeLadder.height*SnakeLadder.tileSize - (i*SnakeLadder.tileSize)-SnakeLadder.tileSize/2;

                positionCoordinates.add(new Pair<>(xCord,yCord));

            }

        }
    }

    public  int getNewPosition(int currentPosition){
        if(currentPosition>=1 && currentPosition<=100) {
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;

    }

    public int  getXCoordinates(int currentPosition){
        if(currentPosition>=1 && currentPosition<=100) {
            return positionCoordinates.get(currentPosition).getKey();
        }
        return -1;
    }
    public int  getYCoordinates(int currentPosition){
        if(currentPosition>=1 && currentPosition<=100) {
            return positionCoordinates.get(currentPosition).getValue();
        }
        return -1;
    }

    private  void populateSnakeLadder(){
        snakeLadderPosition= new ArrayList<>();
        for (int i = 0; i <101 ; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4,25);
        snakeLadderPosition.set(13,46);
        snakeLadderPosition.set(27,5);
        snakeLadderPosition.set(33,49);
        snakeLadderPosition.set(40,3);
        snakeLadderPosition.set(42,63);
        snakeLadderPosition.set(43,18);
        snakeLadderPosition.set(50,69);
        snakeLadderPosition.set(54,31);
        snakeLadderPosition.set(62, 81);
        snakeLadderPosition.set(66,45);
        snakeLadderPosition.set(74,92);
        snakeLadderPosition.set(76,58);
        snakeLadderPosition.set(89,53);
        snakeLadderPosition.set(99,41);
    }

//    public static void main(String[] args) {
//        Board board = new Board();
//
        // for (int i = 0; i <101 ; i++) {
        //     System.out.println(i+" X: "+ board.positionCoordinates.get(i).getKey() +" Y:" + board.positionCoordinates.get(i).getValue() );
        // }
//
//    }

}
