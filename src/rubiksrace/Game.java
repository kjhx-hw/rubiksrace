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
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeMenu();
        setVisible(true);
    }

    private void initializeMenu() {
        
        Menu menuView = new Menu("View");
        menuBar.add(menuView);
        
        MenuItem startOver = new MenuItem("Start Over");
        menuView.add(startOver); 
        MenuItem showSolution = new MenuItem("Solution");
        menuView.add(showSolution);
        MenuItem viewHighScores = new MenuItem("High Scores");
        menuView.add(viewHighScores);
        
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
