/**
 * This class displays the main menu of the game. It contains a 
 * levelSelect panel, a randomSelect panel, and an instructions button.
 * <p>
 * Priscilla was primarily responsible for the implementation of this class.
 * 
 * @author Priscilla Lee
 * @version December 11 2014
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainMenuPanel extends JPanel {
  //private final vars
  private final int numberOfLevels = 26;
  
  //private instance vars
  private JPanel levelSelect, randomSelect, createSelect, customPlay;
  private JLabel level, randomSize, customSize, custom, error;
  private JButton instructions, random, play, create, playCustom;
  private JTextField fileNameInput;
  private JComboBox numbers, randomSizes, customSizes;
  private GamePanel gamePanel;
  
  /**
   * Constructor that takes in a GamePanel as input.
   * 
   * @param gp the GamePanel that will contain this panel
   */
  public MainMenuPanel(GamePanel gp) {
    //store containing panel 
    gamePanel = gp;
    
    //setting background & layout & size
    setPreferredSize(new Dimension(500,300));
    setMaximumSize(new Dimension(500,300));
    setBackground(ColorScheme.background());
    setLayout(new GridLayout(6,1));
    
    //initialize instructions, design, add
    instructions = new JButton("<html><font color='white'>INSTRUCTIONS</font><html>");
    instructions.setFont(new Font("Calibri", Font.BOLD, 30));
    instructions.setOpaque(true);
    instructions.setBackground(ColorScheme.instructions());
    instructions.setBorder(BorderScheme.padding());
    instructions.addActionListener(new ButtonListener());
    add(instructions);
    
    //initialize level label, adjust design
    level = new JLabel("<html><font color='white'>LEVEL</font><html>");
    level.setFont(new Font("Calibri", Font.BOLD, 30));
    level.setHorizontalAlignment(JLabel.CENTER);
    level.setOpaque(true);
    level.setBackground(ColorScheme.labels());
    level.setBorder(BorderScheme.horzPadding());
    
    //initialize numbers, adjust design
    String[] choices = new String[numberOfLevels];
    for (int i = 0; i < choices.length; i++)
      choices[i] = "" + (i+1);
    numbers = new JComboBox(choices);
    numbers.setFont(new Font("Calibri", Font.BOLD, 30));
    numbers.setOpaque(true);
    numbers.setBackground(Color.WHITE);
    numbers.setBorder(BorderScheme.horzPadding());
    
    //initialize play button, adjust design
    play = new JButton("<html><font color='white'>PLAY</font><html>");
    play.setFont(new Font("Calibri", Font.BOLD, 30));
    play.setOpaque(true);
    play.setBackground(ColorScheme.play());
    play.setBorder(BorderScheme.horzPadding());
    play.addActionListener(new ButtonListener());
    
    //initialize levelSelect panel, which contains label + drop-down + button
    levelSelect = new JPanel();
    levelSelect.setBackground(ColorScheme.background());
    levelSelect.setLayout(new GridBagLayout());
    levelSelect.setBorder(BorderScheme.padding());
    add(levelSelect);
    
    //use grid bag constraints to add label+dropdown+button
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    
    //add level label
    c.weightx = 0.37;
    c.gridx = 0;
    c.gridy = 0;
    c.ipady = 10;
    levelSelect.add(level,c);
    
    //add numbers drop-down combo box
    c.weightx = 0.1;
    c.gridx = 1;
    levelSelect.add(numbers,c);
    
    //add play button
    c.weightx = 0.53;
    c.gridx = 2;
    levelSelect.add(play,c);
    
    //initialize randomSize label, adjust design
    randomSize = new JLabel("<html><font color='white'>SIZE</font><html>");
    randomSize.setFont(new Font("Calibri", Font.BOLD, 30));
    randomSize.setHorizontalAlignment(JLabel.CENTER);
    randomSize.setOpaque(true);
    randomSize.setBackground(ColorScheme.labels());
    randomSize.setBorder(BorderScheme.horzPadding());
    
    //initialize randomSizes combobox, adjust design
    String[] randomOptions = new String[11];
    for (int i = 0; i < randomOptions.length; i++)
      randomOptions[i] = "" + (i+10);
    randomSizes = new JComboBox(randomOptions);
    randomSizes.setFont(new Font("Calibri", Font.BOLD, 30));
    randomSizes.setOpaque(true);
    randomSizes.setBackground(Color.WHITE);
    randomSizes.setBorder(BorderScheme.horzPadding());
    
    //initialize random button, adjust design
    random = new JButton("<html><font color='white'>RANDOM</font><html>");
    random.setFont(new Font("Calibri", Font.BOLD, 30));
    random.setOpaque(true);
    random.setBackground(ColorScheme.random());
    random.setBorder(BorderScheme.horzPadding());
    random.addActionListener(new ButtonListener());
    
    //initialize randomSelect panel, which contains label + dropdown + button
    randomSelect = new JPanel();
    randomSelect.setBackground(ColorScheme.background());
    randomSelect.setLayout(new GridBagLayout());
    randomSelect.setBorder(BorderScheme.padding());
    add(randomSelect);
    
    //use grid bag constraints to add label + dropdown + button
    GridBagConstraints d = new GridBagConstraints();
    d.fill = GridBagConstraints.BOTH;
    
    //add size label
    d.weightx = 0.47;
    d.gridx = 0;
    d.gridy = 0;
    d.ipady = 10;
    randomSelect.add(randomSize,d);
    
    //add sizes drop-down combo box
    d.weightx = 0.12;
    d.gridx = 1;
    randomSelect.add(randomSizes,d);
    
    //add play button
    d.weightx = 0.41;
    d.gridx = 2;
    randomSelect.add(random,d);
    
        //initialize customSize label, adjust design
    customSize = new JLabel("<html><font color='white'>SIZE</font><html>");
    customSize.setFont(new Font("Calibri", Font.BOLD, 30));
    customSize.setHorizontalAlignment(JLabel.CENTER);
    customSize.setOpaque(true);
    customSize.setBackground(ColorScheme.labels());
    customSize.setBorder(BorderScheme.horzPadding());
    
    //initialize customSizes, adjust design
    String[] customOptions = new String[11];
    for (int i = 0; i < customOptions.length; i++)
      customOptions[i] = "" + (i+10);
    customSizes = new JComboBox(customOptions);
    customSizes.setFont(new Font("Calibri", Font.BOLD, 30));
    customSizes.setOpaque(true);
    customSizes.setBackground(Color.WHITE);
    customSizes.setBorder(BorderScheme.horzPadding());
    
    //initialize create button, adjust design
    create = new JButton("<html><font color='white'>CREATE</font><html>");
    create.setFont(new Font("Calibri", Font.BOLD, 30));
    create.setOpaque(true);
    create.setBackground(ColorScheme.create());
    create.setBorder(BorderScheme.horzPadding());
    create.addActionListener(new ButtonListener());
    
    //initialize createSelect panel, which contains label + drop-down + button
    createSelect = new JPanel();
    createSelect.setBackground(ColorScheme.background());
    createSelect.setLayout(new GridBagLayout());
    createSelect.setBorder(BorderScheme.padding());
    add(createSelect);
    
    //use grid bag constraints to add label+dropdown+button
    GridBagConstraints e = new GridBagConstraints();
    e.fill = GridBagConstraints.BOTH;
    
    //add level label
    e.weightx = 0.46;
    e.gridx = 0;
    e.gridy = 0;
    e.ipady = 10;
    createSelect.add(customSize,e);
    
    //add numbers drop-down combo box
    e.weightx = 0.12;
    e.gridx = 1;
    createSelect.add(customSizes,e);
    
    //add play button
    e.weightx = 0.46;
    e.gridx = 2;
    createSelect.add(create,e);
    
        //initialize custom label, adjust design
    custom = new JLabel("<html><font color='white'>CUSTOM LEVEL</font><html>");
    custom.setFont(new Font("Calibri", Font.BOLD, 30));
    custom.setHorizontalAlignment(JLabel.CENTER);
    custom.setOpaque(true);
    custom.setBackground(ColorScheme.labels());
    custom.setBorder(BorderScheme.horzPadding());
    
    //initialize fileNameInput, adjust design
    fileNameInput = new JTextField("Enter the level name", 13);
    fileNameInput.setFont(new Font("Calibri", Font.BOLD, 20));
    fileNameInput.setOpaque(true);
    fileNameInput.setBackground(Color.WHITE);
    fileNameInput.setBorder(BorderScheme.horzPadded());
    
    //initialize playCustom button, adjust design
    playCustom = new JButton("<html><font color='white'>PLAY</font><html>");
    playCustom.setFont(new Font("Calibri", Font.BOLD, 30));
    playCustom.setOpaque(true);
    playCustom.setBackground(ColorScheme.playCustom());
    playCustom.setBorder(BorderScheme.horzPadding());
    playCustom.addActionListener(new ButtonListener());
    
    //initialize customPlay panel, which contains label + text field + button
    customPlay = new JPanel();
    customPlay.setBackground(ColorScheme.background());
    customPlay.setLayout(new GridBagLayout());
    customPlay.setBorder(BorderScheme.padding());
    add(customPlay);
    
    //use grid bag constraints to add label+dropdown+button
    GridBagConstraints f = new GridBagConstraints();
    f.fill = GridBagConstraints.BOTH;
    
    //add level label
    f.weightx = 0.25;
    f.gridx = 0;
    f.gridy = 0;
    f.ipady = 15;
    customPlay.add(custom,f);
    
    //add numbers drop-down combo box
    f.weightx = 0.6;
    f.gridx = 1;
    customPlay.add(fileNameInput,f);
    
    //add play button
    f.weightx = 0.2;
    f.gridx = 2;
    customPlay.add(playCustom,f);
    
    //initialize error label, adjust design, add 
    error = new JLabel();
    error.setFont(new Font("Calibri", Font.BOLD, 20));
    error.setOpaque(true);
    error.setBackground(ColorScheme.background());
    error.setBorder(BorderScheme.padding());
    error.setHorizontalAlignment(JLabel.CENTER);
    add(error);  
  }
  
  /**
   * Private helper method that sets the text on the error label given
   * the error message as input.
   * 
   * @param msg  the message to be displayed on the error label
   */
  private void setError(String msg) {
    error.setText("<html><font color='white'>"+msg+"</font></html>");
    error.setBackground(ColorScheme.bad());
  }
  
  /**
   * Private inner class that indicates the appropriate action when a button
   * is clicked.
   */
  private class ButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent event) {
      if (event.getSource() == play) {
        try { //play button pressed --> construct new level panel (level 1)
          int level = numbers.getSelectedIndex() + 1;
          GridGraph gg = new GridGraph(level);
          LevelPanel levelPan = new LevelPanel(gamePanel, gg);
          gamePanel.add(levelPan);
          gamePanel.remove(MainMenuPanel.this);
          
          gamePanel.revalidate();
          gamePanel.repaint();
          revalidate();
          repaint();
        } catch (FileNotFoundException ex) {
          System.out.println(ex);
        }
      } else if (event.getSource() == random) { 
        //random button pressed, generate random levelpanel
        int size = randomSizes.getSelectedIndex() + 10;
        GridGraph gg = new GridGraph(size,size);
        LevelPanel levelPan = new LevelPanel(gamePanel, gg);
        gamePanel.add(levelPan);
        gamePanel.remove(MainMenuPanel.this);
        
        gamePanel.revalidate();
        gamePanel.repaint();
        revalidate();
        repaint();
        
      } else if (event.getSource() == instructions) {
        //instructions button is pressed, switch screens to instructions panel
        gamePanel.add(new InstructionsPanel(gamePanel));
        gamePanel.remove(MainMenuPanel.this);
        
        gamePanel.revalidate();
        gamePanel.repaint();
        revalidate();
        repaint();
        
      } else if (event.getSource() == create) {
        int dimension = customSizes.getSelectedIndex() + 10;
        gamePanel.add(new CreatePanel(gamePanel, dimension, dimension));
        gamePanel.remove(MainMenuPanel.this);
        
        gamePanel.revalidate();
        gamePanel.repaint();
        revalidate();
        repaint();
        
      } else { //customPlay
        String fileName = fileNameInput.getText();
        try { //try constructing grid with file name given
          GridGraph gg = new GridGraph(fileName + ".txt");
          LevelPanel levelPan = new LevelPanel(gamePanel, gg);
          gamePanel.add(levelPan);
          gamePanel.remove(MainMenuPanel.this);
          
          gamePanel.revalidate();
          gamePanel.repaint();
          revalidate();
          repaint();
        } catch (FileNotFoundException ex) {
          setError("The level \"" + fileName + "\" does not exist. " +
                   "<br> Please try another level name.");
        }
      }
    }
  }
}