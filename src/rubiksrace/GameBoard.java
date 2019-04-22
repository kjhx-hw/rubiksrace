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
                q++;
            }
        }
        
        // startTime = getTime();
    }
    
    @Override
    public void tileClicked(GameTile tile) {
        System.out.println(tile.getCoordinate());
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
}
