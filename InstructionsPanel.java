/**
 * This class displays the instructions for the game. It contains a .gif 
 * image on a label (which instructions). It also contains 3 buttons (main 
 * menu, next/previous, and skip/play)
 * <p>
 * Priscilla was primarily responsible for the implementation of this class
 * 
 * @author Priscilla Lee
 * @version December 12 2014
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class InstructionsPanel extends JPanel {
  //private instance vars
  private JLabel instructions;
  private JPanel buttonPanel;
  private JButton mainMenu, nextPrevious, skipPlay;
  private GamePanel gamePanel;
  
  /**
   * Constructor that takes GamePanel in as input.
   * 
   * @param gp the GamePanel that will contain this panel
   */
  public InstructionsPanel(GamePanel gp) {
    gamePanel = gp;
    
    //setting background & layout
    setBackground(ColorScheme.background());
    setLayout(new GridBagLayout());
    
    //instructions label
    ImageIcon icon = new ImageIcon("instructions.gif");
    instructions = new JLabel(icon);
    
    //main menu button
    mainMenu = new JButton("<html><font color='white'>MAIN MENU</font></html>");
    mainMenu.setFont(new Font("Calibri", Font.BOLD, 25));
    mainMenu.setOpaque(true);
    mainMenu.setBackground(ColorScheme.main());
    mainMenu.addActionListener(new ButtonListener());
    mainMenu.setBorder(BorderScheme.padding());
    
    //nextPrevious button
    nextPrevious = new JButton("<html><font color='white'>NEXT</font><html>");
    nextPrevious.setFont(new Font("Calibri", Font.BOLD, 25));
    nextPrevious.setOpaque(true);
    nextPrevious.setBackground(ColorScheme.nextPrevious());
    nextPrevious.setBorder(BorderScheme.padding());
    nextPrevious.addActionListener(new ButtonListener());
    
    //skipPlay button
    skipPlay = new JButton("<html><font color='white'>SKIP</font><html>");
    skipPlay.setFont(new Font("Calibri", Font.BOLD, 25));
    skipPlay.setOpaque(true);
    skipPlay.setBackground(ColorScheme.skipPlay());
    skipPlay.setBorder(BorderScheme.padding());
    skipPlay.addActionListener(new ButtonListener());
    
    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1,3));
    buttonPanel.add(mainMenu);
    buttonPanel.add(nextPrevious);
    buttonPanel.add(skipPlay);
    
    //add label + button panel with constraints
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1;
    c.gridx = 0;
    c.gridy = 0;
    add(instructions, c);
    c.weightx = 0.25;
    c.gridy = 1;
    c.ipady = 30;
    add(buttonPanel, c);
  }
  
  /**
   * Private inner class that indicates appropriate action button is clicked.
   */
  private class ButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent event) {
      if (event.getSource() == nextPrevious)  {
        if (nextPrevious.getText().equals("<html><font color='white'>NEXT</font><html>")) {
          //then switch to 2nd image, change next to previous & skip to play
          ImageIcon icon2 = new ImageIcon("instructions2.gif");
          instructions.setIcon(icon2);
          
          gamePanel.revalidate();
          gamePanel.repaint();
          revalidate();
          repaint();
          
          nextPrevious.setText("<html><font color='white'>PREVIOUS</font><html>");
          skipPlay.setText("<html><font color='white'>PLAY</font><html>");
        } else { //previous was clicked
          //then switch back to 1st image, change previous to next & play to skip
          ImageIcon icon2 = new ImageIcon("instructions.gif");
          instructions.setIcon(icon2);
          
          gamePanel.revalidate();
          gamePanel.repaint();
          revalidate();
          repaint();
          
          nextPrevious.setText("<html><font color='white'>NEXT</font><html>");
          skipPlay.setText("<html><font color='white'>SKIP</font><html>");
        }
      }
      else if (event.getSource() == skipPlay) {
        try { //then play first level (construct new LevelPanel)
          GridGraph gg = new GridGraph(1);
          LevelPanel levelPan = new LevelPanel(gamePanel, gg);
          gamePanel.add(levelPan);
          gamePanel.remove(InstructionsPanel.this);
          
          gamePanel.revalidate();
          gamePanel.repaint();
          revalidate();
          repaint();
        } catch (FileNotFoundException ex) {
          System.out.println(ex);
        }
      }  else { //mainMenu
        gamePanel.add(new MainMenuPanel(gamePanel));
        gamePanel.remove(InstructionsPanel.this);
        
        gamePanel.revalidate();
        gamePanel.repaint();
        revalidate();
        repaint();
      }
    }
  }
}