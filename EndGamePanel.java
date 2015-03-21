/**
 * This class displays the end game congratulations and credits image.
 * It contains only a button with a credit.gif image. When the button
 * is clicked or any key is pressed on the keyboard, the user is returned
 * to the main menu. 
 * <p>
 * Priscilla was primarily responsible for the implementation of this class.
 * 
 * @author Priscilla Lee
 * @version December 14 2014
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EndGamePanel extends JPanel {
  //private instance vars
  private GamePanel gamePanel;
  private JButton credits;
  
  /**
   * Constructor that takes in a GamePanel object as input.
   * 
   * @param gp the GamePanel that will contain this panel
   */
  public EndGamePanel(GamePanel gp) {
    gamePanel = gp;
    
    //setting background & layout
    setBackground(ColorScheme.background());
    
    //initializing end game credits button & adjusting design
    ImageIcon icon = new ImageIcon("credits.gif");
    credits = new JButton(icon);
    credits.setOpaque(true);
    credits.setBackground(ColorScheme.background());
    credits.setBorder(BorderScheme.padding());
    credits.addActionListener(new ButtonListener());
    credits.addKeyListener(new ArrowListener());
    add(credits);
  }
  
  /**
   * Private inner class that indicates appropriate action when button is clicked.
   */
  private class ButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent event) {
      //simply return to main  menu if button is clicked
      gamePanel.add(new MainMenuPanel(gamePanel));
      gamePanel.remove(EndGamePanel.this);
      
      gamePanel.revalidate();
      gamePanel.repaint();
      revalidate();
      repaint();
    }
  }
  
  /**
   * Private inner class that indicates appropriate action when a key is pressed.
   */
  private class ArrowListener implements KeyListener {
    //keyPressed method required for KeyListener interface
    public void keyPressed(KeyEvent e) {
      //simply return to main  menu if any key is pressed
      gamePanel.add(new MainMenuPanel(gamePanel));
      gamePanel.remove(EndGamePanel.this);
      
      gamePanel.revalidate();
      gamePanel.repaint();
      revalidate();
      repaint();
    }
    
    //keyReleased method required for interface
    public void keyReleased(KeyEvent e){}
    
    //keyTyped method required for interface
    public void keyTyped(KeyEvent e){}
  }
}
