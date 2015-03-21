/**
 * This class displays a panel that allows the user to name
 * his/her custom-made level and choose to either play the newly-made
 * level, return the main menu, or create another custom level. It 
 * displays 4 buttons, 1 text field, and 2 labels.
 * <p>
 * Priscilla implemented this class on her own.
 *
 * @author Priscilla Lee
 * @version December 19 2014
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class GeneratePanel extends JPanel {
  //private instance vars
  private GamePanel gamePanel; 
  private JPanel filePanel, buttonPanel;
  private JLabel nameFile, status;
  private JButton saveFile, mainMenu, createAgain, playLevel;
  private JTextField fileName;
  private int width, height;
  private GridCoordinate start;
  private ArrayList<GridCoordinate> targets;
  private ArrayList<GridCoordinate> blocks;
  private String finalName;
  
  /**
   * Constructor that takes in all the information of the customized grid.
   *
   * @param gp  the GamePanel that will contain this GeneratePanel
   * @param w   the width of the grid
   * @param h   the height of the grid
   * @param s   the starting GridCoordinate
   * @param t   the list of target GridCoordinates
   * @param b   the list of block GridCoordinates
   */
  public GeneratePanel(GamePanel gp, int w, int h, GridCoordinate s, ArrayList<GridCoordinate> ts, ArrayList<GridCoordinate> bs) {
    gamePanel = gp;
    width = w;
    height = h;
    start = s;
    targets = ts;
    blocks = bs;
    
    //setting background & size
    setPreferredSize(new Dimension(700,500));
    setMinimumSize(new Dimension(700,500));
    setBackground(ColorScheme.background());
    setLayout(new GridLayout(5,1));
    
    //initialize nameFile label, adjust design
    nameFile = new JLabel("<html><font color='white'>NAME YOUR FILE</font><html>");
    nameFile.setFont(new Font("Calibri", Font.BOLD, 20));
    nameFile.setOpaque(true);
    nameFile.setBackground(ColorScheme.labels());
    nameFile.setBorder(BorderScheme.padding());
    nameFile.setHorizontalAlignment(JLabel.CENTER);
    
    //initialize fileName text field, adjust design
    fileName = new JTextField("Enter a name for your level here", 17);
    fileName.setFont(new Font("Calibri", Font.BOLD, 20));
    fileName.setOpaque(true);
    fileName.setBackground(Color.WHITE);
    fileName.setBorder(BorderScheme.padding());
    
    //initialize saveFile button, adjust design
    saveFile = new JButton("<html><font color='white'>SAVE</font><html>");
    saveFile.setFont(new Font("Calibri", Font.BOLD, 20));
    saveFile.setOpaque(true);
    saveFile.setBackground(ColorScheme.save());
    saveFile.setBorder(BorderScheme.padding());
    saveFile.setHorizontalAlignment(JLabel.CENTER);
    saveFile.addActionListener(new ButtonListener());
    
    //initialize filePanel, contains label + text field + button
    filePanel = new JPanel();
    filePanel.setBackground(ColorScheme.background());
    filePanel.setLayout(new GridBagLayout());
    filePanel.setBorder(BorderScheme.padding());
    add(filePanel);
    
    //use grid bag constraints to add label + text field + button
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    
    //add nameFile label
    c.weightx = 0.3;
    c.gridx = 0;
    c.gridy = 0;
    c.ipady = 30;
    filePanel.add(nameFile,c);
    
    //add fileName text field
    c.weightx = 0.2;
    c.gridx = 1;
    filePanel.add(fileName,c);
    
    //add save button
    c.weightx = 0.8;
    c.gridx = 2;
    filePanel.add(saveFile,c);
    
    //initialize status label, adjust design, add to generate panel
    status = new JLabel();
    status.setFont(new Font("Calibri", Font.BOLD, 20));
    status.setOpaque(true);
    status.setBackground(ColorScheme.background());
    status.setBorder(BorderScheme.padding());
    status.setHorizontalAlignment(JLabel.CENTER);
    add(status);
    
    //create filler1 (blank) label and add to generate panel
    JLabel filler1 = new JLabel();
    filler1.setOpaque(true);
    filler1.setBackground(ColorScheme.background());
    add(filler1);
    
     //create filler2 (blank) label and add to generate panel
    JLabel filler2 = new JLabel();
    filler2.setOpaque(true);
    filler2.setBackground(ColorScheme.background());
    add(filler2); 
    
    //initialize main menu button, adjust design
    mainMenu = new JButton("<html><font color='white'>MAIN MENU</font></html>");
    mainMenu.setFont(new Font("Calibri", Font.BOLD, 20));
    mainMenu.setOpaque(true);
    mainMenu.setBackground(ColorScheme.main());
    mainMenu.addActionListener(new ButtonListener());
    mainMenu.setBorder(BorderScheme.padding());
    
    //initialize createAgain button, adjust design
    createAgain = new JButton("<html><font color='white'>CREATE AGAIN</font><html>");
    createAgain.setFont(new Font("Calibri", Font.BOLD, 20));
    createAgain.setOpaque(true);
    createAgain.setBackground(ColorScheme.createAgain());
    createAgain.setBorder(BorderScheme.padding());
    createAgain.addActionListener(new ButtonListener());
    
    //initialize playLevel button, adjust design
    playLevel = new JButton("<html><font color='white'>PLAY MY LEVEL</font><html>");
    playLevel.setFont(new Font("Calibri", Font.BOLD, 20));
    playLevel.setOpaque(true);
    playLevel.setBackground(ColorScheme.unselected());
    playLevel.setBorder(BorderScheme.padding());
    playLevel.addActionListener(new ButtonListener());
    
    //initialize button panel, add mainMenu + createAgain + playLevel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1,3));
    buttonPanel.add(mainMenu);
    buttonPanel.add(createAgain);
    buttonPanel.add(playLevel);  
    add(buttonPanel);
  }
  
  
  /**
   * Private helper method that updates the status label with a bad status 
   * message given the rejected file name.
   * 
   * @param name the file name to display on the bad status label
   */
  private void setBadStatus(String name) {
    String msg = "A level with the name \"" + name + "\" already exists. ";
    msg += "<br>Please use a different level name.";
    status.setText("<html><font color='white'>"+msg+"</font></html>");
    status.setBackground(ColorScheme.bad());
  }
  
  /**
   * Private helper method that updates the status label with a good status
   * message given the accepted file name.
   * 
   * @param name the file name to display on the good status label
   */
  private void setGoodStatus(String name) {
    String msg = "Congratulations! Your level \"" + name + "\" has been saved!";
    status.setText("<html><font color='white'>"+msg+"</font></html>");
    status.setBackground(ColorScheme.good());
  }
  
  /**
   * Private helper method that resets the status label to be blank.
   */
  private void resetStatus() {
    status.setText("");
    status.setBackground(ColorScheme.background());
  } 
  
  /**
   * Private helper method that determines whether the given file name
   * already exists as a text file.
   * 
   * @param name the name of the file to be checked
   * @return     a boolean indicating whether the file with the given 
   *             name already exists
   */
  private boolean fileExists(String name) {
    File f = new File(name + ".txt");
    try {
      Scanner s = new Scanner(f);
      return true; //file exists because Scanner was created successfully
    } catch (FileNotFoundException ex) {
      return false;
    }
  }
  
  /**
   * Private helper method that generates a text file and prints the grid's 
   * information onto the file.
   * 
   * @param name the name of the file to be generated
   */
  private void printTextFile(String name) {
    File f = new File(name + ".txt");
    try {
      PrintWriter pw = new PrintWriter(f);
      pw.println("width " + width);
      pw.println("height " + height);
      pw.println("start " + start.getBackwardsCoordinate());
      for (GridCoordinate t: targets)
        pw.println("end " + t.getBackwardsCoordinate());
      pw.println("*");
      for (GridCoordinate b: blocks)
        pw.println(b.getBackwardsCoordinate());
      pw.close();
    } catch (FileNotFoundException ex) {
      System.out.println(ex);
    }
  }
  
  /**
   * Private inner class that indicates the appropriate action when a
   * button is clicked.
   */
  private class ButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent event) {
      if (event.getSource() == saveFile) {
        String name = fileName.getText();
        if (fileExists(name)) {
          setBadStatus(name);
        } else {
          setGoodStatus(name);
          printTextFile(name);
          playLevel.setBackground(ColorScheme.playLevel());
          finalName = name + ".txt";
        }
      } else if (event.getSource() == mainMenu) {
        gamePanel.add(new MainMenuPanel(gamePanel));
        gamePanel.remove(GeneratePanel.this);
        gamePanel.revalidate();
        gamePanel.repaint();
        revalidate();
        repaint(); 
      } else if (event.getSource() == createAgain) {
        gamePanel.add(new CreatePanel(gamePanel, width, height));
        gamePanel.remove(GeneratePanel.this);
        gamePanel.revalidate();
        gamePanel.repaint();
        revalidate();
        repaint();
      } else { //play level
        if (finalName == null)
          return;
        try {
          GridGraph gg = new GridGraph(finalName);
          gamePanel.add(new LevelPanel(gamePanel, gg));
          gamePanel.remove(GeneratePanel.this);
          gamePanel.revalidate();
          gamePanel.repaint();
          revalidate();
          repaint();
        } catch (FileNotFoundException ex) {
          System.out.print(ex);
        }
      }
    } 
  }
}
