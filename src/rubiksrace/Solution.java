/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package rubiksrace;

import java.awt.Dimension;
import java.awt.Menu;
import javax.swing.JFrame;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kjhuggins
 */
public class Solution extends JFrame {
    private SolutionBoard solutionBoard = new SolutionBoard();
    
    public Solution() {
        //Technique used from http://www.java2s.com
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        
        int windowLength = 300;
        int windowWidth = 300;
        setSize(windowWidth,windowLength);
        setLocation(screenWidth, -screenHeight);
        //
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(solutionBoard);
        setResizable(false);
        setVisible(true);
    }
}
