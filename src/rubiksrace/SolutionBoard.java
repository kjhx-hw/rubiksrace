/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
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
class SolutionBoard extends JPanel {
    private final Integer DIMENSION = 5;
    
    GameTile[][] tileSolution = new GameTile[3][3];
    GameTile[][] tileFiver = new GameTile[5][5];
    static ArrayList<GameTile> tileSolutionDeck = null;
    
    private ArrayList<Color> backColors = new ArrayList<>(Arrays.asList(Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED, Color.RED,Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLACK));
    
    SolutionBoard() {
        setLayout(new GridLayout(DIMENSION, DIMENSION));
        initializeCards();
    }

    private void initializeCards() {
        for (int i = 0; i < ((DIMENSION*DIMENSION)); i++) {
            GameTile newCard = new GameTile("");
            newCard.setColor(backColors.get(i));
            tileSolutionDeck.add(newCard);
        }
        
         // Shuffles tiles and adds to deck
        Collections.shuffle(tileSolutionDeck);
        for (int i = 0; i < tileSolutionDeck.size(); i++) {
            add(tileSolutionDeck.get(i));
        }
        
        int q = 0;
        for (int i = 0; i < 5; i++) {
            for (int c = 0; c < 5; c++) {
                tileFiver[i][c] = tileSolutionDeck.get(q);
                tileSolutionDeck.get(q).setCoordinate(new Coordinate(i, c));
                q++;
            }
        }
    }
    
    public GameTile[][] getSolution() {
        // gets solution array
        return null;
    }
    
    public void setSolution(GameTile[][] q) {
        // sets new solution array
    }
    
    public void newSolution() {
        // shuffles solution array to get new solution
        // used upon new game
    }
}
