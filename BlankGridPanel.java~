/**
 * This class displays a blank grid to allow the user to create 
 * his/her own level by placing the start tile, target tiles, and block
 * tiles in a grid of buttons. It uses a 2d-array of JButtons, and 
 * indicates their type with colors/borders. 
 * <p>
 * Priscilla implemented this class on her own.
 * 
 * @author Priscilla Lee
 * @version December 16 2014
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.io.File.*;

public class BlankGridPanel extends JPanel{
  //private instance vars
  private GamePanel gamePanel;
  private JButton[][] buttonGrid;
  private GridCoordinate[][] coordinates;
  private int width, height;
  private GridCoordinate start;
  private ArrayList<GridCoordinate> targets;
  private ArrayList<GridCoordinate> blocks;
  private CreatePanel createPanel;
  
  /**
   * Constructor that constructs a 2d array of JButtons given the grid's
   * width and height. 
   * 
   * @param cp the CreatePanel that will contain this GridPanel
   * @param w  the width of the desired grid
   * @param h  the height of the desired grid
   */
  public BlankGridPanel(GamePanel gp, CreatePanel cp, int w, int h) {
    gamePanel = gp;
    createPanel = cp;
    width = w;
    height = h;
    targets = new ArrayList<GridCoordinate>();
    blocks = new ArrayList<GridCoordinate>();
    
    //setting layout & background & size
    setLayout(new GridLayout(height, width));
    setBackground(ColorScheme.background());
    setPreferredSize(new Dimension(500,500));
    setMinimumSize(new Dimension(500,500));
    setMaximumSize(new Dimension(500,500));
    
    //create button grid of all WHITE JButtons
    buttonGrid = new JButton[height][width];
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        JButton tile = new JButton();   
        tile.addActionListener(new ButtonListener());
        tile.setOpaque(true);
        tile.setBackground(Color.WHITE);
        tile.setBorder(BorderScheme.standard());
        buttonGrid[r][c] = tile;
        add(tile);
      }
    }
    
    //create 2d array of all GridCoordinates
    coordinates = new GridCoordinate[height][width];
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        coordinates[r][c] = new GridCoordinate(c,r);
      }
    }
  }
  
  /**
   * Private helper method that sets the start GridCoordinate and colors 
   * the respective tile on the grid.
   * 
   * @param gc the GridCoordinate that should be set as the start
   */
  private void setStart(GridCoordinate gc) {
    if (targets.contains(gc) || blocks.contains(gc))
      return; //don't replace a target/block
    if (start == gc) {
      //if start is clicked, remove it
      color(start, Color.WHITE);
      start = null;
    } else {
      if (start != null) //if start already set, replace it
        color(start, Color.WHITE); //change old start back to white color    
      start = gc;
      color(gc, ColorScheme.character()); //set color of start tile
    }
  }
  
  /**
   * Private helper method that either adds/removes the given GridCoordinate 
   * to/from the list of targets and colors/uncolors the respective tile on 
   * the grid.
   * 
   * @param gc the GridCoordinate that should be added/removed as a target
   */
  private void setTarget(GridCoordinate gc) {
    if (start == gc || blocks.contains(gc))
      return; //don't replace a start/block
    if (targets.contains(gc)) {
      //if already a target, then remove it 
      border(gc, BorderScheme.standard());
      targets.remove(gc);
    } else { //add target
      border(gc, BorderScheme.target(width, height));
      targets.add(gc);
    }
  }
  
  /**
   * Private helper method that either adds/remove the given GridCoordinate 
   * to/from the list of blocks and colors/uncolors the respective tile on 
   * the grid.
   * 
   * @param gc the GridCoordinate that should be added/removed as a block
   */
  private void setBlock(GridCoordinate gc) {
    if (start == gc ||targets.contains(gc))
      return; //don't replace a start/target
    if (blocks.contains(gc)) {
      //if already a block, then remove it
      color(gc, Color.WHITE);
      blocks.remove(gc);
    } else { //add block
      color(gc, ColorScheme.block());
      blocks.add(gc);
    }
  }
  
  /**
   * Private helper method that determines whether or not the GridCoordinate
   * selected is empty (doesn't already contain a start tile, target
   * tile, or block tile)
   *
   * @param b the GridCoordinate to be checked
   * @return  a boolean indicating whether the button is empty
   */
  private boolean isEmpty(GridCoordinate gc) {
    if (start == gc)
      return false;
    if (targets.contains(gc))
      return false;
    if (blocks.contains(gc))
      return false;
    return true;
  }
 
  /**
   * Private helper method that sets the background of the tile that 
   * represents the given GridCoordinate to the given Color.
   * 
   * @param gc    the GridCoordinate whose background color is being changed
   * @param color the Color for the background
   */
  private void color(GridCoordinate gc, Color color) {
    int x = gc.getX();
    int y = gc.getY();
    buttonGrid[y][x].setBackground(color);
  }
  
  /**
   * Private helper method that sets the border of the tile that 
   * represents the given GridCoordinate to the given Border.
   * 
   * @param gc     the GridCoordinate whose border is being changed
   * @param border the Border
   */
  private void border(GridCoordinate gc, Border border) {
    int x = gc.getX();
    int y = gc.getY();
    buttonGrid[y][x].setBorder(border);
  }
  
    /**
   * Determines if the created level is valid and solvable by creating a
   * GridGraph and performing the shortest path algorithm as a test.
   * 
   * @return  a boolean indicating whether the created level is valid
   */
  public boolean isValidLevel() {
    if (start == null) {
      createPanel.setWarning("START NEEDED");
      return false;
    } if (targets.size() == 0) {
      createPanel.setWarning("TARGET NEEDED");
      return false;
    } if (targets.size() > 5) {
      createPanel.setWarning("5 TARGETS MAX");
      return false;
    } try {
      GridGraph test = new GridGraph(width, height, start, targets, blocks);
      return true;
    } catch (PathNotFoundException ex) {
      createPanel.setWarning("NO VALID PATH");
      return false;
    }
  }
  
  /**
   * If the created custom-made level is valid, the screen progresses to a panel
   * in which the user may enter a level name and save the file. 
   */
  public void generate() {
    if (!isValidLevel()) {
      return;
    } GeneratePanel gp = new GeneratePanel(gamePanel, width, height, start, targets, blocks);
    gamePanel.add(gp);
    gamePanel.remove(createPanel);
    
    gamePanel.revalidate();
    gamePanel.repaint();
    revalidate();
    repaint();
  } 
  
  /**
   * Private inner class that indicates the appropriate action when
   * a button is clicked.
   */
  private class ButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent event) {
     createPanel.resetWarning(); 
      for (int r = 0; r < buttonGrid.length; r++) {
        for (int c = 0; c < buttonGrid[r].length; c++) {
          if (event.getSource() == buttonGrid[r][c]) {
            GridCoordinate gc = coordinates[r][c];
            String selected = createPanel.getSelected();
            if (selected == "start")
              setStart(gc);
            else if (selected == "target")
              setTarget(gc);
            else //block
              setBlock(gc); 
          }
        }
      } 
    }
  }
}
