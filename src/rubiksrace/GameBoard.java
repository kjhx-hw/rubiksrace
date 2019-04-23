/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubiksrace;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author kjhuggins
 */
public class GameBoard extends JPanel implements IGameTileListener {
    private final int DIMENSION = 5;
    static ArrayList<GameTile> tileDeck = null;
    GameTile[][] allTiles = new GameTile[5][5];
    Coordinate emptyLocation = null;
    private ArrayList<Color> backColors = new ArrayList<>(Arrays.asList(Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED, Color.RED,Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLACK));
    static long startTime = 0;
    
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
        
        Integer direction = directionFind(tile, emptyLocation);
        tileMove(direction, tile, emptyLocation);
        
        System.out.println(tile.getCoordinate());
        System.out.println(tile.getColor());
        System.out.println(tile.getEmpty());
        System.out.println(emptyLocation);
        System.out.println();
    }
    
    public long getTime() {
        long result;
        result = System.currentTimeMillis()/1000;
        
        return result;
    }

    void resetGame() {
        removeAll();
        tileDeck = null;
        initializeCards();
        revalidate();
    }
    
    public Integer directionFind(GameTile tile, Coordinate emptyLocation) {
        Integer result = 0;
        
        Integer clickX = tile.getCoordinate().x;
        Integer clickY = tile.getCoordinate().y;
        Integer blankX = emptyLocation.x;
        Integer blankY = emptyLocation.y;
        
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
    
    public void tileMove(Integer direction, GameTile tile, Coordinate emptyLocation) {
        
        Integer tileX = tile.getCoordinate().x;
        Integer tileY = tile.getCoordinate().y;
        Integer emptyX = emptyLocation.x;
        Integer emptyY = emptyLocation.y;
           
        switch (direction) {
            
            case 1:
                
                //Set Empty to false for previous empty tile to move
                allTiles[emptyX][emptyY].setEmpty(false);
                
                //LEFT
                for(int i = emptyY; i < tileY; i++) {
                    
                    allTiles[tileX][i].setColor(allTiles[tileX][i+1].getColor());
                }
                
                allTiles[tileX][tileY].setEmpty(true);
                
                System.out.println("Case 1: LEFT");
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
        
        revalidate();
       
    }
}
