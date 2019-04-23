/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package rubiksrace;

import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;

/**
 *
 * @author kjhuggins
 */
public class GameTile extends Button {
    // Default button color is BLACK
    private Color tileColor = Color.BLACK;
    
    Coordinate location = null;
    
    private boolean hasMatch = false;
    private boolean isEmpty = false;
    
    private ArrayList<IGameTileListener> clickListeners = new ArrayList<>();
    //<IGameTileListener>
    
    public GameTile(String label) {
        super(label);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!hasMatch) {
                        for (IGameTileListener clickListener : clickListeners) {
                            clickListener.tileClicked((GameTile)e.getSource());
                        }
                    }
                }
            });
            
            setColor(tileColor);
    }
    
    public void setColor(java.awt.Color newColor) {
        tileColor = newColor;
        setBackground(newColor);
        setForeground(newColor);
    }
    
    public Color getColor() {
        return tileColor;
    }
    
    public void setEmpty(boolean q) {
        isEmpty = q;
        setColor(Color.BLACK);
    }
    
    public boolean getEmpty() {
        return isEmpty;
    }
    
    public void addTileClickedListener(IGameTileListener listener) {
        clickListeners.add(listener);
    }

    void setCoordinate(Coordinate q) {
        location = q;
    }
    
    public Coordinate getCoordinate() {
        return location;
    }
}
