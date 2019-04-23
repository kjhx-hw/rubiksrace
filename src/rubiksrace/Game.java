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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author kjhuggins
 */
public class Game extends JFrame {
    private GameBoard gameBoard = new GameBoard();
    private MenuBar menuBar = new MenuBar();
    
    public Game() {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeMenu();
        add(gameBoard);
        setResizable(false);
        setVisible(true);
    }
    
    private void initializeMenu() {
        Menu menuView = new Menu("View");
        menuBar.add(menuView);
        
        MenuItem showSolution = new MenuItem("Solution");
        menuView.add(showSolution);
        MenuItem viewHighScores = new MenuItem("High Scores");
        menuView.add(viewHighScores);
        
        Menu menuHelp = new Menu("Help");
        menuBar.add(menuHelp);
        
        MenuItem startOver = new MenuItem("Start Over");
        menuHelp.add(startOver);
        MenuItem onlineInstructions = new MenuItem("Online Instructions...");
        menuHelp.add(onlineInstructions);
        menuHelp.addSeparator();
        MenuItem reportIssue = new MenuItem("Report Issue");
        menuHelp.add(reportIssue);
        
        viewHighScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBoard.showHighScores();
            }
        });
        
        startOver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBoard.resetGame();
            }
        });
        
        onlineInstructions.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               String url = "https://github.com/kjhx/rubiksrace/wiki";
               openUrl(url);
           }
        });
        
        reportIssue.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               String url = "https://github.com/kjhx/rubiksrace/issues/new/choose";
               openUrl(url);
           }
        });
                
        setMenuBar(menuBar);
    }
    
    private void openUrl(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
