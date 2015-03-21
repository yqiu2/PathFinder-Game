/**
 * This class displays a single custom level and allows the user to
 * delete or rename the custom level. It contains 1 label and 2 buttons.
 * <p> 
 * Priscilla implemented this class on her own.
 * 
 * @author Priscilla Lee
 * @version December 25, 2014
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomLevelDisplayPanel extends JPanel {
  //private instance vars
  private JLabel levelName;
  private JButton delete, rename;
  private CustomPanel customPanel; 
  
  /**
   * Constructor that takes in a CustomPanel as input.
   * 
   * @param cp the CustomPanel that will contain this panel
   */
  public CustomLevelDisplayPanel(CustomPanel cp) {
    customPanel = cp;
    
    //setting background & layout
    setBackground(ColorScheme.background());
    setLayout(new GridLayout(3,1));
    
    //initialize levelName label, adjust design, add
    String s = "LEVEL NAME";
    levelName = new JLabel("<html><font color='white'>"+s+"</font><html>");
    levelName.setFont(new Font("Calibri", Font.BOLD, 20));
    levelName.setOpaque(true);
    levelName.setBackground(ColorScheme.labels());
    levelName.setBorder(BorderScheme.padding());
    levelName.setHorizontalAlignment(JLabel.CENTER);
    levelName.setPreferredSize(new Dimension(200,30));
    levelName.setMinimumSize(new Dimension(150,0));
    add(levelName);
    
    //initialize delete label, adjust design, add
    delete = new JButton("<html><font color='white'>DELETE</font></html>");
    delete.setFont(new Font("Calibri", Font.BOLD, 20));
    delete.setOpaque(true);
    delete.setBackground(ColorScheme.delete());
    //delete.addActionListener(new ButtonListener());
    delete.setBorder(BorderScheme.padding());
    add(delete);
    
    //initialize rename label, adjust design, add
    rename = new JButton("<html><font color='white'>RENAME</font></html>");
    rename.setFont(new Font("Calibri", Font.BOLD, 20));
    rename.setOpaque(true);
    rename.setBackground(ColorScheme.rename());
    //rename.addActionListener(new ButtonListener());
    rename.setBorder(BorderScheme.padding());
    add(rename);
  }
}