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
        System.out.println(tile.getCoordinate());
        System.out.println(tile.getColor());
        System.out.println(tile.getEmpty());
        System.out.println(emptyLocation);
        System.out.println();
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
    
    public Integer directionFind(GameTile tile, Coordinate emptylocation) {
        Integer result = 0;
        
        Integer clickX = tile.getCoordinate().x;
        Integer clickY = tile.getCoordinate().y;
        Integer blankX = emptylocation.x;
        Integer blankY = emptylocation.y;
        
        if (Objects.equals(clickX, blankX)) {
            
            //RIGHT
            if (blankY > clickY) {
                result = 2;
            }
            
            //LEFT
            else {
                result = 1;
            }
        }
        
        else if (Objects.equals(clickY, blankY)) {
            
            //DOWN
            if (blankX > clickX) {
                result = 4;
            }
            
            //UP
            else {
                result = 3;
            }
        }
        
        else {
            System.out.println("Wrong tile, my DUDE!");
        }
        
        return result;
    }
    
    public void tileMove(Integer direction, GameTile tile, Coordinate emptylocation) {
        
        Integer tileX = tile.getCoordinate().x;
        Integer tileY = tile.getCoordinate().y;
        Integer emptyX = emptylocation.x;
        Integer emptyY = emptylocation.y;
           
        switch (direction) {
            
            case 1:
                
                //Set Empty to false for previous empty tile to move
                allTiles[emptyX][emptyY].setEmpty(false);
                
                //LEFT
                for(int i = emptyY; i < tileY; i++) {
                    
                    allTiles[tileX][i].setColor(allTiles[tileX][i+1].getColor());
                }
                
                allTiles[tileX][tileY].setEmpty(true);
            break;
            
            case 2:
                //RIGHT
                for(int i = emptyY; i > tileY; i--) {
            
                }
            break;
        
            case 3:
                //UP
                for(int i = emptyX; i < tileX; i++) {
                
                }   
            break;
        
            case 4:
                //DOWN
                for(int i = emptyX; i > tileX; i--) {

                }
            break;
                
            default:
                //Do nothing
        }
    }
}
