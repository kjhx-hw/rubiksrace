/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubiksrace;

import java.awt.Menu;
import javax.swing.JFrame;
import java.awt.MenuBar;
import java.awt.MenuItem;

/**
 *
 * @author kjhuggins
 */
public class Game extends JFrame {
    private MenuBar menuBar = new MenuBar();
    
    public Game() {
        JFrame frame = new JFrame("Rubik's Race");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeMenu();
        frame.setVisible(true);
    }

    private void initializeMenu() {
        Menu menuGo = new Menu("Game");
        menuBar.add(menuGo);
        
        MenuItem startOver = new MenuItem("Start Over");
        menuGo.add(startOver); 
        MenuItem showSolution = new MenuItem("Show Solution");
        menuGo.add(showSolution);
        MenuItem viewHighScores = new MenuItem("View High Scores");
        menuGo.add(viewHighScores);
                
        setMenuBar(menuBar);
    }
}
