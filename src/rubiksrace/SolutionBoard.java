/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package rubiksrace;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JPanel;

/**
 *
 * @author kjhuggins
 */
class SolutionBoard extends JPanel {
    private final Integer DIMENSION = 5;
    
    GameTile[][] tileSolution = new GameTile[3][3];
    GameTile[][] tileFiver = new GameTile[5][5];
    static ArrayList<GameTile> tileDeck = null;
    
    private ArrayList<Color> backColors = new ArrayList<>(Arrays.asList(Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED, Color.RED,Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLACK));
    
    SolutionBoard() {
        setLayout(new GridLayout(DIMENSION, DIMENSION));
        initializeCards();
        // if tileSolution contains empty tile, redo solution
    }

    private void initializeCards() {
        for (int i = 0; i < ((DIMENSION*DIMENSION)); i++) {
            GameTile newCard = new GameTile("");
            newCard.setColor(backColors.get(i));
            tileDeck.add(newCard);
        }
        
         // Shuffles tiles and adds to ui
        Collections.shuffle(tileDeck);
        for (int i = 0; i < tileDeck.size(); i++) {
            add(tileDeck.get(i));
        }
        
        int q = 0;
        for (int i = 0; i < 5; i++) {
            for (int c = 0; c < 5; c++) {
                tileFiver[i][c] = tileDeck.get(q);
                tileDeck.get(q).setCoordinate(new Coordinate(i, c));
                q++;
            }
        }
        
        convertSolution();
    }
    
    private void containsEmpty() {
        // checks tileFiver center 3 for empty tile
    }
    
    private void convertSolution() {
        // translates the 5x5 into the 3x3
    }
    
    public GameTile[][] getSolution() {
        return tileSolution;
    }
    
    public void setSolution(GameTile[][] q) {
        tileSolution = q;
    }
    
    public void newSolution() {
        Collections.shuffle(tileDeck);

        int q = 0;
        for (int i = 0; i < 5; i++) {
            for (int c = 0; c < 5; c++) {
                tileFiver[i][c] = tileDeck.get(q);
                tileDeck.get(q).setCoordinate(new Coordinate(i, c));
                if (tileDeck.get(q).getColor() == Color.BLACK) {
                    tileDeck.get(q).setEmpty(true);
                }
                
                q++;
            }
        }
        
        // Parent should now call convertSolution
    }
}
