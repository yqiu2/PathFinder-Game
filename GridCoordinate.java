/**
 * This class represents objects with x and y coordinate values and 
 * directional (N,S,E,W) values. Booleans are used to store whether there 
 * is a block above, below, to the left, or to the right of the object.
 * GridCoordinate objects are used in the GridGraph class to represent 
 * the vertices of a graph. 
 * <p>
 * Amy and Michelle were equally responsible for the implementation of 
 * this class.
 * 
 * @author Amy Qiu, Michelle Gao
 * @version December 2 2014
 */

public class GridCoordinate {
  // instance variables 
  private int x; 
  private int y;
  private boolean n;
  private boolean w;
  private boolean s;
  private boolean e;
  private GridCoordinate previous; //used in GridGraph.bfs()
  
  /**
   * Constructorthat takes in an (x,y) coordinate pair.
   * The first coordinate has x = 0 and y = 0.
   * 
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public GridCoordinate(int x, int y){
    this.x = x;
    this.y = y;
    n = true;
    w = true;
    s = true;
    e = true;
  }
  
  /**
   * Sets n (northern neighbor).
   * 
   * @param value a boolean for whether this object has a northern neighbor
   */
  public void setN(boolean value){
    n = value;
  }
  
  /**
   * Sets e (eastern neighbor).
   * 
   * @param value a boolean for whether this object has a eastern neighbor
   */
  public void setE(boolean value){
    e = value;
  }
  
  /**
   * Sets s (southern neighbor).
   * 
   * @param value a boolean for whether this object has a southern neighbor
   */
  public void setS(boolean value){
    s = value;
  }
  
  /**
   * Sets w (western neighbor).
   * 
   * @param value a boolean for whether this object has a western neighbor
   */
  public void setW(boolean value){
    w = value;
  }
  
  /**
   * Returns n (northern neighbor).
   * 
   * @return   a boolean for whether this object has a northern neighbor
   */
  public boolean getN(){
    return n;
  }  
  
  /**
   * Returns e (eastern neighbor).
   * 
   * @return   a boolean for whether this object has a eastern neighbor
   */
  public boolean getE(){
    return e;
  }  
  
  /**
   * Returns s (southern neighbor).
   * 
   * @return   a boolean for whether this object has a southern neighbor
   */
  public boolean getS(){
    return s;
  }  
  
  /**
   * Returns w (western neighbor).
   * 
   * @return   a boolean for whether this object has a western neighbor
   */
  public boolean getW(){
    return w;
  }  
  
  /**
   * Returns a 4-digit (XXYY) int representation of a coordinate pair. 
   * The first 2 digits represent the x coor, last 2 digits the y coor.
   * 
   * @return   a 4-digit int representation of a coordinate pair
   */
  public int getCoordinate(){
    int result = 100*x + y;
    //x coor is XXYY/100
    //y coor is XXYY%100
    return result;
  }
  
    /**
   * Returns a backwards 4-digit (YYXX) int representation of a coordinate pair. 
   * The first 2 digits represent the y coor, last 2 digits the x coor.
   * 
   * @return   a 4-digit int representation of a coordinate pair
   */
  public int getBackwardsCoordinate(){
    return 100*y + x;
  }
  
  /**
   * Returns x (x-coordinate).
   * 
   * @return   the x-coordinate
   */
  public int getX() {
    return x;
  }
  
  /**
   * Returns y (y-coordinate).
   * 
   * @return   the y-coordinate
   */
  public int getY() {
    return y;
  }
  
  /**
   * Sets previous reference.
   * 
   * @param n the GridCoordinate to be stored in the previous reference.
   */
  public void setPrevious(GridCoordinate n) {
    previous = n; 
  }
  
  /**
   * Returns the GridCoordinate stored in the previous reference.
   * 
   * @return   the GridCoordinate stored in the previous reference.
   */
  public GridCoordinate getPrevious() {
    return previous; 
  }
  
  /**
   * Returns true if this GridCoordinate's previous reference isn't null.
   * 
   * @return   the boolean that indicates whether the previous refers to null
   */
  public boolean hasPrevious() {
    return (previous != null); 
  }
  
  /**
   * Returns true if this GridCoordinate shares the same x and y values 
   * as the given GridCoordinate.
   * 
   * @param other the GridCoordinate to be compared
   * @return      boolean that indicates equality
   */
  public boolean equals(GridCoordinate other) {
    return (this.x == other.x && this.y == other.y);
  }
  
  /**
   * Returns a String representation of a GridCoordinate object.
   * 
   * @return    a String that represents the coordinate and directional values
   *            of this object.
   */
  public String toString(){
    String result = "";
    result += "("+x+","+y+")";
    result += "N:"+ (n == true ? 1 : 0);
    result += " E:" + (e == true ? 1 : 0);
    result += " S:" + (s == true ? 1 : 0);
    result += " W:" + (w == true ? 1 : 0) + "  ";
    return result;
  }
}