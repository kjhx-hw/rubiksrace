/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package rubiksrace;

import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author kjhuggins
 */
public class GameTile extends Button {
    private Color colorBackground = Color.WHITE;
    private Color colorForeground = null;
    
    private boolean hasMatch = false;
    private ArrayList<IGameTileListener> clickListeners = new ArrayList<IGameTileListener>();
    
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
    }
}
