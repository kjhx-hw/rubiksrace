/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubiksrace;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kjhuggins
 */
public class GameBoard extends JPanel implements IGameTileListener {
    private final int DIMENSION = 5;
    
    Coordinate emptyLocation = null;
    static ArrayList<GameTile> tileDeck = null;
    GameTile[][] allTiles = new GameTile[5][5];
    private ArrayList<Color> backColors = new ArrayList<>(Arrays.asList(Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED, Color.RED,Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLACK));
    private ArrayList<Long> highscores = new ArrayList();
    
    static long startTime = 0;
    static long endTime = 0;
    private long timePlayed = 0;
    
    private Solution solution = new Solution();
    
    GameBoard() {
        setLayout(new GridLayout(DIMENSION, DIMENSION));
        initializeCards();
    }

    private void initializeCards() {
        tileDeck = new ArrayList();
        for (int i = 0; i < ((DIMENSION*DIMENSION)); i++) {
            GameTile newCard = new GameTile("");
            newCard.setColor(backColors.get(i));
            tileDeck.add(newCard);
            newCard.addTileClickedListener(this);
        }
        
        // Shuffles tiles and adds to deck
        Collections.shuffle(tileDeck);
        for (int i = 0; i < tileDeck.size(); i++) {
            add(tileDeck.get(i));
        }
        
        
        int q = 0;
        for (int i = 0; i < 5; i++) {
            for (int c = 0; c < 5; c++) {
                allTiles[i][c] = tileDeck.get(q);
                tileDeck.get(q).setCoordinate(new Coordinate(i, c));
                if (tileDeck.get(q).getColor() == Color.BLACK) {
                    tileDeck.get(q).setEmpty(true);
                    emptyLocation = new Coordinate(tileDeck.get(q).getCoordinate());
                }
                
                q++;
            }
        }
        
        // startTime = getTime();
    }
    
    @Override
    public void tileClicked(GameTile tile) {
        
        Integer direction = directionFind(tile);
        
        if (direction != 0) {
            tileMove(direction, tile);
        }
        
        System.out.println("Clicked tile " + tile.getCoordinate());
    }
    
    private long getTime() {
        long result;
        result = System.currentTimeMillis()/1000;
        
        return result;
    }

    private void endGame() {
        endTime = getTime();
        timePlayed = endTime - startTime;
        highscores.add(timePlayed);
        Collections.sort(highscores);
    }
    
    public void resetGame() {
        removeAll();
        tileDeck = null;
        initializeCards();
        revalidate();
    }

    public void showHighScores() {
        for (int i = 0; i < tileDeck.size(); i++) {
            File f = new File("highscores.ser");
            if (f.exists()) {
                // https://www.tutorialspoint.com/java/java_serialization.htm
                try {
                    FileInputStream fileIn = new FileInputStream("highscores.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    highscores = (ArrayList<Long>) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        String scoreOutput = "";

        if (!highscores.isEmpty()) { 
            for (int c = 0; c < highscores.size(); c++) {
                scoreOutput = scoreOutput.concat((c + 1) + ". " + highscores.get(c) + "\n");
            }
        } else {
            scoreOutput = "No high scores found.";
        }
        
        JOptionPane.showMessageDialog(null, scoreOutput, "Highscores", JOptionPane.PLAIN_MESSAGE);
    }
    
    private Integer directionFind(GameTile tile) {
        Integer result = 0;
        
        Integer clickX = tile.getCoordinate().x;
        Integer clickY = tile.getCoordinate().y;
        Integer blankX = this.emptyLocation.x;
        Integer blankY = this.emptyLocation.y;
        
        System.out.print("Direction ");
        
        if (Objects.equals(clickX, blankX)) {
            
            //RIGHT
            if (blankY > clickY) {
                result = 2;
                System.out.println("2");
            }
            
            //LEFT
            else {
                result = 1;
                System.out.println("1");
            }
        }
        
        else if (Objects.equals(clickY, blankY)) {
            
            //DOWN
            if (blankX > clickX) {
                result = 4;
                System.out.println("4");
            }
            
            //UP
            else {
                result = 3;
                System.out.println("3");
            }
        }
        
        else {
            System.out.println("wrong tile, my DUDE!");
            result = 0;
        }
        
        return result;
    }
    
    private void tileMove(Integer direction, GameTile tile) {
        
        Integer tileX = tile.getCoordinate().x;
        Integer tileY = tile.getCoordinate().y;
        Integer emptyX = this.emptyLocation.x;
        Integer emptyY = this.emptyLocation.y;
        
        //Set Empty to false for previous empty tile to move
        allTiles[emptyX][emptyY].setEmpty(false);
           
        switch (direction) {
            
            case 1:
  
                //LEFT
                for(int i = emptyY; i < tileY; i++) {
                    
                    allTiles[tileX][i].setColor(allTiles[tileX][i+1].getColor());
                    
                    //Saving for later but do not unleash; this code weird
                    //allTiles[tileX][i].setCoordinate(allTiles[tileX][i+1].getCoordinate());
                }
  
                //Saving for later but do not unleash; this code weird
                //allTiles[tileX][tileY].setCoordinate(allTiles[tileX][emptyY].getCoordinate());
                
                //System.out.println("Case 1: LEFT");
            break;
            
            case 2:
                //RIGHT
                for(int i = emptyY; i > tileY; i--) {
                    
                    allTiles[tileX][i].setColor(allTiles[tileX][i-1].getColor());
                    
                }
                
                //System.out.println("Case 2: RIGHT");
            break;
        
            case 3:
                //UP
                for(int i = emptyX; i < tileX; i++) {
                    
                    allTiles[i][tileY].setColor(allTiles[i+1][tileY].getColor());
                    
                }
                
                //System.out.println("Case 3: UP");
            break;
        
            case 4:
                //DOWN
                for(int i = emptyX; i > tileX; i--) {
                    
                    allTiles[i][tileY].setColor(allTiles[i-1][tileY].getColor());
                    
                }
                
                //System.out.println("Case 4: DOWN");
            break;
                
            default:
                //Do nothing
        }

        //This sets bool for empty true
        allTiles[tileX][tileY].setEmpty(true);

        //This sets the coordinate for empty
        this.emptyLocation.x = tileX;
        this.emptyLocation.y = tileY;
        
        //revalidate();
        checkVictory();
    }

    private void checkVictory() {
        // translates the 5x5 into a 3x3
        // creates array of colors
        // rotates and checks again
        GameTile[][] tinyBoard = new GameTile[3][3];
        ArrayList<GameTile> solutionBoard = solution.board.getSolution();
        
        for (int i = 1; i < 3; i++) {
            for (int c = 1; c < 3; c++) {
                tinyBoard[i-1][c-1] = allTiles[i][c];
            }
        }
        
        for (int i = 0; i < 4; i++) {
            // rotate 4 times
            // compareArray
        }
        
        
    }
    
    private boolean compareArray(ArrayList<GameTile> a, ArrayList<GameTile> b) {
        boolean arrayMatch = true;
        Integer iterator = 0;
        
        while (arrayMatch != false && iterator < a.size()) {
            if (a.get(iterator).getColor() != b.get(iterator).getColor()) {
                arrayMatch = false;
            }
            
            iterator++;
        }
        
        return arrayMatch;
    }
}
