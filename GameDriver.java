/**
 * This class acts as the driver for the shortest path finder 
 * game. It instantiates one instance of GamePanel class, which in turn
 * creates all the subpanels and then allows the user to play the game.
 * <p>
 * Michelle was primarily responsible for the implementation of this class.
 * 
 * @author Michelle Gao
 * @version December 2 2014
 */

import javax.swing.*;

public class GameDriver {
  
  /**
   * Creates and displays the Path Finder game, then allows the user to play.
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame("Path Finder");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //creates an instance of a GamePanel class and adds it to the frame
    frame.getContentPane().add(new GamePanel());
    
    frame.pack();
    frame.setVisible(true);
  }
}