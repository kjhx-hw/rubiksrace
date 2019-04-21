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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author kjhuggins
 */
public class Game extends JFrame {
    private MenuBar menuBar = new MenuBar();
    
    public Game() {
        //JFrame frame = new JFrame("Rubik's Race");
        //frame.setSize(500, 500);
        setSize(500, 500);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeMenu();
        //frame.setVisible(true);
        setVisible(true);
    }

    private void initializeMenu() {
        
        Menu Game = new Menu("Game");
        menuBar.add(Game);
        
        MenuItem startOver = new MenuItem("Start Over");
        Game.add(startOver); 
        MenuItem showSolution = new MenuItem("Show Solution");
        Game.add(showSolution);
        MenuItem viewHighScores = new MenuItem("View High Scores");
        Game.add(viewHighScores);
        
        /*
        startOver.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               for(int i = 0; i < GameBoard.cards.size(); i++)
               {
                   GameBoard.cards.get(i).flipBack();
                   GameBoard.cards.get(i).makeUnMatched();
                   GameBoard.startTime = gameboard.getTime();
               }
            }
        });
        
        showSolution.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               for(int i = 0; i < GameBoard.cards.size(); i++)
               {
                   GameBoard.cards.get(i).flip();
                   GameBoard.cards.get(i).makeMatched();
                   
               }
            }
        });
        
        viewHighScores.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               gameboard.showHighScores();
            }
        });
        */
                
        setMenuBar(menuBar);
    }
}
