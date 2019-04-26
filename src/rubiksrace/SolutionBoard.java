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
import static rubiksrace.GameBoard.tileDeck;

/**
 *
 * @author kjhuggins
 */
class SolutionBoard extends JPanel {
    private final Integer DIMENSION = 3;
    //There are 24 colors used
    private final Integer ALLCOLORS = 24;

    private GameTile[][] tileSolutionOutput = new GameTile[3][3];
    //GameTile[][] tileFiver = new GameTile[5][5];

    static ArrayList<GameTile> tileSolutionDeck = null;

    private ArrayList<Color> backColors = new ArrayList<>(Arrays.asList(Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED, Color.RED,Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW));

    SolutionBoard() {
        setLayout(new GridLayout(DIMENSION, DIMENSION));
        initializeCards(tileSolutionOutput);
        // if tileSolution contains empty tile, redo solution
    }

    private void initializeCards(GameTile[][] tileSolutionInput) {
        tileSolutionDeck = new ArrayList();

        for (int i = 0; i < (ALLCOLORS); i++) {
            GameTile newCard = new GameTile("");
            newCard.setColor(backColors.get(i));
            tileSolutionDeck.add(newCard);
        }

         // Shuffles tiles and adds to ui
        Collections.shuffle(tileSolutionDeck);
        for (int i = 0; i < (DIMENSION * DIMENSION); i++) {
            add(tileSolutionDeck.get(i));
        }

        int q = 0;
        for (int i = 0; i < DIMENSION; i++) {
            for (int c = 0; c < DIMENSION; c++) {
                //tileFiver[i][c] = tileSolutionDeck.get(q);
                tileSolutionInput[i][c] = tileSolutionDeck.get(q);
                tileSolutionDeck.get(q).setCoordinate(new Coordinate(i, c));
                q++;
            }
        }
        
        //prepare for getSolution()
        tileSolutionOutput = tileSolutionInput;

        //convertSolution();
    }
    
    public void resetGame() {
        removeAll();
        tileSolutionDeck = null;
        initializeCards(tileSolutionOutput);
        revalidate();
    }
    
    //Instead of converting the already 3 by 3 tileFiver to a 3 by 3
    //tileSolution, what if we just used tileFiver for the getSolution function.
    //It will cut out an extra step.
    /*
    private void convertSolution() {
        // translates the 5x5 into the 3x3
        for (int i = 1; i < 3; i++) {
            for (int c = 1; c < 3; c++) {
                tileSolution[i-1][c-1] = tileFiver[i][c];
            }
        }
    }
    */
    
    /*
    public ArrayList<GameTile> getSolution() {
        ArrayList<GameTile> temp = null;

        for (int i = 0; i < 3; i++) {
            for (int c = 0; c < 3; c++) {
                //temp.add(tileFiver[i][c]);
                if (tileSolutionOutput[i][c] != null) {
                    temp.add(tileSolutionOutput[i][c]);
                }
            }
        }

        return temp;
    }
    */
   
    
    public GameTile[][] getSolution(GameTile[][] temp) {

        temp = tileSolutionOutput;

        return temp;
    }

    /*
    public void setSolution(GameTile[][] q) {
        tileSolution = q;
    }

    public void newSolution() {
        initializeCards();
        //Since initializeCards() already makes a new random solution 3 by 3 board,
        //what if we used this method to make a new solution for newSolution();
        /*
        Collections.shuffle(tileSolutionDeck);

        int q = 0;
        for (int i = 0; i < DIMENSION; i++) {
            for (int c = 0; c < DIMENSION; c++) {
                tileFiver[i][c] = tileSolutionDeck.get(q);
                tileSolutionDeck.get(q).setCoordinate(new Coordinate(i, c));
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
        Collections.shuffle(tileSolutionDeck);

        int q = 0;
        for (int i = 0; i < 5; i++) {
            for (int c = 0; c < 5; c++) {
                tileFiver[i][c] = tileSolutionDeck.get(q);
                tileSolutionDeck.get(q).setCoordinate(new Coordinate(i, c));
                if (tileSolutionDeck.get(q).getColor() == Color.BLACK) {
                    tileSolutionDeck.get(q).setEmpty(true);
                }
                
                q++;
            }
        }

        // Parent should now call convertSolution
    }
    */
}
