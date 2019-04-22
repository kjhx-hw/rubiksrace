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
    static ArrayList<GameTile> tiles = null;
    private ArrayList<Color> backColors = new ArrayList<>(Arrays.asList(Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED, Color.RED,Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLACK));
    static long startTime = 0;
    
    GameBoard() {
        setLayout(new GridLayout(DIMENSION, DIMENSION));
        initializeCards();
    }

    private void initializeCards() {
        // setColors();
        tiles = new ArrayList();
        for (int i = 0; i < ((DIMENSION*DIMENSION)); i++) {
            GameTile newCard = new GameTile("");
            newCard.setColor(backColors.get(i));
            tiles.add(newCard);
            newCard.addTileClickedListener(this);
        }
        
        Collections.shuffle(tiles);
        for(int i = 0; i < tiles.size(); i++) {
            add(tiles.get(i));
        }
        
        // startTime = getTime();
    }
    
    public void setColors() {
         backColors = new ArrayList();
         Random random = new Random();

        for(int i = 0; i < ((DIMENSION*DIMENSION)/2); i++) {
            
            float red = random.nextFloat();
            float green = random.nextFloat();
            float blue = random.nextFloat();
            
            Color newColor = new Color(red, green, blue);
            backColors.add(newColor);
        }
    }
    
    @Override
    public void tileClicked(GameTile tile) {
        // tile.setColor(Color.yellow);
    }
    
    public long getTime() {
        long result;
        result = System.currentTimeMillis()/1000;
        
        return result;
    }
}
