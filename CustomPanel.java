/**
 * This class displays a panel that allows the user to view, rename,
 * reorder, and delete custom levels. It displays multiple 
 * CustomLevelDisplayPanels.
 * <p> 
 * Priscilla implemented this class on her own.
 * 
 * @author Priscilla Lee
 * @version December 25, 2014
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomPanel extends JPanel {
  //private instance vars
  private JLabel help;
  private JPanel displayPanel;
  private GamePanel gamePanel;
  
  /**
   * Constructor that takes in a GamePanel as input.
   * 
   * @param gp the GamePanel that will contain this panel
   */
  public CustomPanel(GamePanel gp) {
    gamePanel = gp;
    
    //setting background & size
    setPreferredSize(new Dimension(700,500));
    setMinimumSize(new Dimension(700,500));
    setBackground(ColorScheme.background());
    
    //initialize help label, adjust design, add
    String h = "INSTRUCTIONS WILL GO HERE";
    help = new JLabel("<html><font color='white'>"+h+"</font><html>");
    help.setFont(new Font("Calibri", Font.BOLD, 20));
    help.setOpaque(true);
    help.setBackground(ColorScheme.labels());
    help.setBorder(BorderScheme.padding());
    help.setHorizontalAlignment(JLabel.CENTER);
    add(help);
    
    //initialize display panel, set layout, adjust design, add
    displayPanel = new JPanel();
    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
    displayPanel.setBackground(ColorScheme.background());
    add(displayPanel);
  }
}