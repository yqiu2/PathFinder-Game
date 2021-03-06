/**
 * This class is meant to serve as the ultimate containing panel to display
 * the full game. When the GameDriver is first run, a GamePanel
 * is constructed with a title label and MainMenuPanel. As the game is played, 
 * any inner panels (LevelPanel, MainMenuPanel, InstructionsPanel) may 
 * simultaneously add new panels and remove themselves from their master 
 * GamePanel (to produce the illusion of "switching screens" to a new panel).
 * <p>
 * Priscilla was primarily responsible for the implementation of this class.
 * 
 * @author Priscilla Lee
 * @version December 2 2014
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel{
  //private instance vars
  private JLabel title;
  
  /**
   * Default constructor that constructs a title label and a MainMenuPanel.
   */
  public GamePanel() {
    //set background & layout & size
    setBackground(ColorScheme.background());
    setLayout(new GridBagLayout());
    setPreferredSize(new Dimension(700,650));
    setMinimumSize(new Dimension(700,650));
    
    //title label
    ImageIcon icon = new ImageIcon("path-finder.gif");
    title = new JLabel(icon);
    title.setBorder(BorderScheme.padding());
    
    //MainMenuPanel 
    MainMenuPanel mmp = new MainMenuPanel(this);
    
    //add components to this GamePanel (with constraints)
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    
    //add title
    c.weighty = 0.5;
    c.gridx = 0;
    c.gridy = 0;
    add(title,c);
    
    //add mmp
    add(mmp);
  }
  
  /**
   * Overrides the add method specifically for GamePanel with 
   * GridBagConstraints automatically included.
   * 
   * @param comp the component to be added to this panel
   * @return     the component that was added to this panel
   */
  public Component add(Component comp) {
    //add components to this game panel (with constraints)
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.weighty = 2;
    c.gridx = 0;
    c.gridy = 1;
    add(comp,c);
    return comp;
  }
}
