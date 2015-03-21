/**
 * This class displays one level of the game. Each LevelPanel displays
 * a different level. It displays 2 labels, 4 buttons, and a GridPanel. 
 * Its undo, reset, next level, and main menu buttons allow the user
 * multiple options of action. 
 * <p> 
 * Priscilla was primarily responsible for the implementation of this class.
 * 
 * @author Priscilla Lee
 * @version December 2 2014
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LevelPanel extends JPanel{
  //private instance vars
  private GridPanel gridPanel;
  private GamePanel gamePanel;
  private GridGraph gridGraph;
  private JPanel sidePanel;
  private JButton undo, reset, nextLevel, mainMenu, hint;
  private JLabel level, stepCount;
  
  /**
   * Constructor that takes in a GamePanel and GridGraph as input.
   * 
   * @param gp the GamePanel that will contain this panel
   * @param gg the GridGraph that corresponds to this GridPanel
   */
  public LevelPanel(GamePanel gp, GridGraph gg) {
    gamePanel = gp;
    gridGraph = gg;
    
    //setting background & size
    setPreferredSize(new Dimension(700,500));
    setMinimumSize(new Dimension(700,500));
    setBackground(ColorScheme.background());
    
    //initialize panels (side and grid)
    sidePanel = new JPanel();
    gridPanel = new GridPanel(this, gridGraph);
    
    //adjust layout/design for the different panels
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    sidePanel.setLayout(new GridLayout(8,1));
    gridPanel.setBorder(BorderScheme.padded());
    
    //initialize level # label, adjust design, add to sidePanel
    level = new JLabel("<html><font color='white'>LEVEL " 
                         + gridGraph.getLevel() + "</font><html>");
    level.setFont(new Font("Calibri", Font.BOLD, 20));
    level.setOpaque(true);
    level.setBackground(ColorScheme.labels());
    level.setBorder(BorderScheme.padding());
    level.setHorizontalAlignment(JLabel.CENTER);
    level.setPreferredSize(new Dimension(200,30));
    level.setMinimumSize(new Dimension(150,0));
    sidePanel.add(level);
    
    //initialize stepCount label, adjust design, add to sidePanel
    int shortest = gridGraph.getShortestPath();
    stepCount = new JLabel("<html><font color='white'>STEPS: 0 / " 
                             + shortest + "</font><html>");
    stepCount.setFont(new Font("Calibri", Font.BOLD, 20));
    stepCount.setOpaque(true);
    stepCount.setBackground(ColorScheme.labels());
    stepCount.setBorder(BorderScheme.padding());
    stepCount.setHorizontalAlignment(JLabel.CENTER);
    sidePanel.add(stepCount);
    
    //add filler space (blank label) to sidePanel
    JLabel filler = new JLabel();
    filler.setOpaque(true);
    filler.setBackground(ColorScheme.background());
    sidePanel.add(filler);    
    
    //initialize nextlevel label, adjust design, add to sidePanel
    nextLevel = new JButton("<html><font color='white'>NEXT LEVEL</font></html>");
    nextLevel.setFont(new Font("Calibri", Font.BOLD, 20));
    nextLevel.setOpaque(true);
    nextLevel.setBackground(ColorScheme.labels());
    nextLevel.addActionListener(new ButtonListener());
    nextLevel.addKeyListener(new ArrowListener());
    nextLevel.setBorder(BorderScheme.padding());
    sidePanel.add(nextLevel);
    
    //initialize mainMenu label, adjust design, add to sidePanel
    mainMenu = new JButton("<html><font color='white'>MAIN MENU</font></html>");
    mainMenu.setFont(new Font("Calibri", Font.BOLD, 20));
    mainMenu.setOpaque(true);
    mainMenu.setBackground(ColorScheme.main());
    mainMenu.addActionListener(new ButtonListener());
    mainMenu.setBorder(BorderScheme.padding());
    sidePanel.add(mainMenu);
    
    //initialize undo button, adjust design, add listener, add to sidePanel
    undo = new JButton("<html><font color='white'>UNDO (U)</font></html>");
    undo.setFont(new Font("Calibri", Font.BOLD, 20));
    undo.setOpaque(true);
    undo.setBackground(ColorScheme.undo());
    undo.addActionListener(new ButtonListener());
    undo.addKeyListener(new ArrowListener());
    undo.setBorder(BorderScheme.padding());
    sidePanel.add(undo);
    
    //initialize reset button, adjust design, add listener, add to sidePanel
    reset = new JButton("<html><font color='white'>RESET (R)</font></html>");
    reset.setFont(new Font("Calibri", Font.BOLD, 20));
    reset.setOpaque(true);
    reset.setBackground(ColorScheme.reset());
    reset.addActionListener(new ButtonListener());
    reset.addKeyListener(new ArrowListener());
    reset.setBorder(BorderScheme.padding());
    sidePanel.add(reset);
    
     //initialize hint button, adjust design, add listener, add to sidePanel
    hint = new JButton("<html><font color='white'>HINT (H)</font></html>");
    hint.setFont(new Font("Calibri", Font.BOLD, 20));
    hint.setOpaque(true);
    hint.setBackground(ColorScheme.hint());
    hint.addActionListener(new ButtonListener());
    hint.addKeyListener(new ArrowListener());
    hint.setBorder(BorderScheme.padding());
    sidePanel.add(hint);
    
    //add panels to this LevelPanel
    add(sidePanel);
    add(gridPanel);
  }
  
  /**
   * Updates the step count label.
   */
  public void updateStepsLabel(){
    stepCount.setText("<html><font color='white'>STEPS: " 
                        + gridPanel.getStepCount() 
                        + " / " + gridGraph.getShortestPath() 
                        + "</font><html>");
  }
  
  /**
   * Updates the next level label by changing it's color to indicate 
   * that clicking has been enabled.
   */
  public void updateNextLabel() {
    if (gridPanel.levelComplete())
      nextLevel.setBackground(ColorScheme.next());
    else
      nextLevel.setBackground(ColorScheme.labels());
  }
  
  /**
   * "Loads" the next level onto the screen. It does this by creating a 
   * new instance of a LevelPanel object, adding it to the GamePanel, and 
   * then removing itself from the GamePanel.
   */
  public void loadNextLevel() {
    if (gridPanel.levelComplete()) {
      try {
        int currentLevel = gridGraph.getLevel();
        GridGraph gg = new GridGraph(currentLevel+1);
        LevelPanel levelPanel = new LevelPanel(gamePanel, gg);
        gamePanel.add(levelPanel);
        gamePanel.remove(LevelPanel.this);
        
        gamePanel.revalidate();
        gamePanel.repaint();
        revalidate();
        repaint();
      } catch (FileNotFoundException ex) { //next level txt file doesn't exist
        //so switch screen to EndGamePanel with game credits
        gamePanel.add(new EndGamePanel(gamePanel));
        gamePanel.remove(LevelPanel.this);
        
        gamePanel.revalidate();
        gamePanel.repaint();
        revalidate();
        repaint();
      }
    } 
  }
  
  /**
   * "Loads" a new random level by creating a new instance of a LevelPanel 
   * object, adding it to the GamePanel, and then removing itself from the 
   * GamePanel.
   * 
   * @param w the width of the level grid to be randomly generated
   * @param h the height of the level grid to be randomly generated
   */
  public void loadRandomLevel(int w, int h) {
    if (gridPanel.levelComplete()) {
      GridGraph gg = new GridGraph(w,h);
      LevelPanel levelPan = new LevelPanel(gamePanel, gg);
      gamePanel.add(levelPan);
      gamePanel.remove(LevelPanel.this);
      
      gamePanel.revalidate();
      gamePanel.repaint();
      revalidate();
      repaint();
    } 
  }
  
  /**
   * Private inner classes that indicates the appropriate action when a
   * button is clicked.
   */
  private class ButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent event) {
      if (event.getSource() == undo) 
        gridPanel.undo();
      else if (event.getSource() == reset)
        gridPanel.reset();
      else if (event.getSource() == hint)
        gridPanel.hint();
      else if (event.getSource() == nextLevel) {
        if (gridGraph.getLevel() == 0)
          loadRandomLevel(gridGraph.getWidth(), gridGraph.getHeight());
        else
          loadNextLevel();
      } else { //mainMenu
        gamePanel.add(new MainMenuPanel(gamePanel));
        gamePanel.remove(LevelPanel.this);
        
        gamePanel.revalidate();
        gamePanel.repaint();
        revalidate();
        repaint();
      }
    }
  }
  
  /**
   * Private inner classes that implements KeyListener so that the user can still
   * use arrow keys to move the piece even if he/she clicks off the GridPanel.
   */
  private class ArrowListener implements KeyListener {
    //keyPressed method required for KeyListener interface
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_LEFT) 
        gridPanel.move(GridPanel.LEFT);
      else if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
        gridPanel.move(GridPanel.RIGHT);
      else if (e.getKeyCode() == KeyEvent.VK_UP) 
        gridPanel.move(GridPanel.UP);
      else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
        gridPanel.move(GridPanel.DOWN);
      else if (e.getKeyCode() == KeyEvent.VK_U) 
        gridPanel.undo();
      else if (e.getKeyCode() == KeyEvent.VK_R) 
        gridPanel.reset(); 
      else if (e.getKeyCode() == KeyEvent.VK_H)
        gridPanel.hint();
      else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        if (gridGraph.getLevel() == 0)
          loadRandomLevel(gridGraph.getWidth(), gridGraph.getHeight());
        else
          loadNextLevel();
      }
    }
    
    //keyReleased method required for interface
    public void keyReleased(KeyEvent e){}
    
    //keyTyped method required for interface
    public void keyTyped(KeyEvent e){}
  }
}
