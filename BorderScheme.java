/**
 * This class stores Border objects to be recalled for later use when 
 * designing the GUI. BorderScheme uses static methods that return Border
 * objects. 
 * <p>
 * Priscilla was primarily responsible for the implementation of this class. 
 * 
 * @author Priscilla
 * @version December 4 2014
 */

import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;

public class BorderScheme {
  
  /**
   * Returns a "standard" Border object: a dark outline.
   * 
   * @return    a dark outlined border
   */
  public static Border standard(){
    return BorderFactory.createMatteBorder(1,1,1,1,ColorScheme.dark());
  }
  
  /**
   * Returns a "padded" Border object: a dark outline with extra 
   * background-colored padding.
   * 
   * @return    a dark outlined border with extra padding
   */
  public static Border padded(){
    Border border = BorderFactory.createMatteBorder(6,6,6,6,ColorScheme.dark());
    Border padding = BorderFactory.createMatteBorder(20,20,20,20,ColorScheme.background());
    return BorderFactory.createCompoundBorder(padding, border);
  }
  
    /**
   * Returns a horizontal padded Border object: dark outline with extra
   * background-colored padding only on the left and right sides.
   * 
   * @return    dark outline with padding on left and right sides only
   */
  public static Border horzPadded() {
    Border border = BorderFactory.createMatteBorder(1,1,1,1,ColorScheme.dark());
    Border horzPadding = BorderFactory.createMatteBorder(0,14,0,14,ColorScheme.background());
    return BorderFactory.createCompoundBorder(horzPadding, border);
  }
  
  /**
   * Returns a "padding" Border object: extra background-colored
   * padding with no dark outline.
   * 
   * @return    padding with no dark outline
   */
  public static Border padding(){
    return BorderFactory.createMatteBorder(10,15,10,15,ColorScheme.background());
  }
  
  /**
   * Returns horizontal padding: adds extra background-colored padding
   * to the left and right sides only.
   * 
   * @return    padding on left and right sides only
   */
  public static Border horzPadding() {
    return BorderFactory.createMatteBorder(0,15,0,15,ColorScheme.background());
  }
  
  /**
   * Returns extra "padding": extra background-colored padding all around.
   * 
   * @return    extra padding
   */
  public static Border extraPadding() {
    return BorderFactory.createMatteBorder(20,60,20,60,ColorScheme.background());
  }
  
  /**
   * Returns a "target" Border object: a target-colored compound border.
   * 
   * @return    target border
   */
  public static Border target(int width, int height) {
    int n;
    if (width < 5 || height < 5) n = 15; //big target
    else if (width < 10 || height < 10) n = 7; //medium target
    else if (width < 15 || height < 15) n = 4; //small target
    else n = 2; //tiny target
    
    Border in = BorderFactory.createMatteBorder(n,n,n,n,ColorScheme.target());
    Border white = BorderFactory.createMatteBorder(n,n,n,n,Color.WHITE);
    Border out = BorderFactory.createMatteBorder(n,n,n,n,ColorScheme.target());
    Border inside = BorderFactory.createCompoundBorder(white,in);
    return BorderFactory.createCompoundBorder(out,inside);
  }
}

