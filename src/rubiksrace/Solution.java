/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
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
public class Solution extends JFrame {
    private SolutionBoard solutionBoard = new SolutionBoard();
    
    public Solution() {
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(solutionBoard);
        setResizable(false);
        setVisible(true);
    }
}
