/**
 * This class represents a graph of GridCoordinate objects and can be used
 * to perform algorithms on or retrieve information from the graph. The basic 
 * information, such as start, targets, and blocks are stored in separate
 * .txt files that are read into the constructor. The shortest path using a 
 * modified bfs algorithm is calculated. GridGraph is the 'backend' version of
 * the grid displayed in the GUI. Note: Our graph assumes that coordinate (0,0) 
 * is at the top left corner. 
 * <p>
 * Amy and Michelle were equally responsible for the implementation of 
 * this class.
 * 
 * @author Amy Qiu, Michelle Gao
 * @version December 2 2014
 */

import java.util.*;
import java.io.*;
import javafoundations.*;

public class GridGraph {
  //private instance variables
  GridCoordinate begin;
  ArrayList<GridCoordinate> targets;
  int level, width, height, shortestSteps; 
  boolean[] visitedVertices;
  GridCoordinate[] allVertices;
  ArrayList<GridCoordinate> blocks;
  ArrayList<GridCoordinate> pathAnswer;
  
   /**
   * Constructor that creates the graph of the indicated level. It takes 
   * the level number as input and constructs an array of GridCoordinates 
   * by reading it in from the corresponding file.
   *
   * @param levelNum a value that indicates the level of this grid
   */
  public GridGraph(int levelNum) throws FileNotFoundException {
    //constructs a filename from the given int level
    level = levelNum;
    String fileName = "level_" + level + ".txt";
    //calls this private helper method to finish constructing the grid
    constructFromFile(fileName); 
  }
  
  /**
   * Constructor that creates the graph from the inputted file. It takes 
   * the file name as input and constructs an array of GridCoordinates 
   * by reading it in from that file.
   *
   * @param fileName the name of the file with the grid's information
   */
  public GridGraph(String fileName) throws FileNotFoundException {
    level = -1;
    //calls this private helper method to construct the grid
    constructFromFile(fileName); 
  }
  
  /**
   * Private helper method that creates the graph from the given file. It takes 
   * the file name as input and constructs an array of GridCoordinates 
   * by reading it in.
   *
   * @param fileName a String that indicates the name fo the file to read
   *                 in the grid's information from
   */
  private void constructFromFile(String fileName) throws FileNotFoundException {
    Scanner scan = new Scanner(new File(fileName));
    scan.next();
    width = scan.nextInt();
    scan.next();
    height = scan.nextInt();
    
    //constructing "blank" graph
    allVertices = new GridCoordinate[width*height];
    int n = 0;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        allVertices[n] = new GridCoordinate(x,y);
        n++;
      }
    }
    visitedVertices = new boolean[width*height];
    
    //reading in and storing start & end vertices
    scan.next();
    int start = scan.nextInt();
    begin = getVertex(start/100, start%100);
    
    //for multiple targets keep on reading in ending points until you hit the '*'
    targets = new ArrayList<GridCoordinate>();
    while (scan.next().equals("end")) {
      int ending = scan.nextInt();
      GridCoordinate end = getVertex(ending/100, ending%100);
      targets.add(end);
    }
    
    //fill in the blocks & populate blocks arrayList 
    blocks = new ArrayList<GridCoordinate>();
    while (scan.hasNext()) {
      int b = scan.nextInt();
      GridCoordinate tempBlock = new GridCoordinate(b/100,b%100);
      blocks.add(tempBlock);
      this.removeVertex(b/100,b%100);
    }
    
    //fill in top and bottom edges of grid
    for (int i = 0; i < width; i++) { 
      findGridCoordinate(i,0).setN(false);
      findGridCoordinate(i,height-1).setS(false);
    }
    
    //fill in left and right edges of grid
    for (int i = 0; i < height; i++) {
      findGridCoordinate(0,i).setW(false);
      findGridCoordinate(width-1,i).setE(false);
    }
    
        //pathAnswer will be created as the shortest path algorithm is performed
    pathAnswer = new ArrayList<GridCoordinate>();
    //calculate and store length of shortest path
    shortestSteps = shortestPath(begin, targets);

    
  }
  
    /**
   * Test constructor that creates the graph that represents a created,
   * custom-made grid level by the user. It takes in all the grid information
   * as its input and constructs a GridGraph object to determine whether
   * a valid path exists.
   * 
   * @param w   the width of the grid
   * @param h   the height of the grid
   * @param s   the starting GridCoordinate
   * @param t   the list of target GridCoordinates
   * @param b   the list of block GridCoordinates
   */
  public GridGraph(int w, int h, GridCoordinate s, ArrayList<GridCoordinate> ts, ArrayList<GridCoordinate> bs) {
    level = -1;
    width = w;
    height = h;
    
    //constructing "blank" graph
    allVertices = new GridCoordinate[width*height];
    int n = 0;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        allVertices[n] = new GridCoordinate(x,y);
        n++;
      }
    }
    visitedVertices = new boolean[width*height];
    
    //store start
    begin = getVertex(s.getX(), s.getY());

    //store targets
    targets = new ArrayList<GridCoordinate>();
    for (GridCoordinate t: ts) {
      GridCoordinate target = getVertex(t.getX(), t.getY());
      targets.add(target);
    }
    
    //store blocks and remove their vertices
    blocks = new ArrayList<GridCoordinate>();
    for (GridCoordinate b: bs) {
     GridCoordinate block = getVertex(b.getX(), b.getY());
     blocks.add(block);
     this.removeVertex(block.getX(), block.getY());
    }
    
    //fill in top and bottom edges of grid
    for (int i = 0; i < width; i++) { 
      findGridCoordinate(i,0).setN(false);
      findGridCoordinate(i,height-1).setS(false);
    }
    
    //fill in left and right edges of grid
    for (int i = 0; i < height; i++) {
      findGridCoordinate(0,i).setW(false);
      findGridCoordinate(width-1,i).setE(false);
    }
    
          //pathAnswer will be created as the shortest path algorithm is performed
    pathAnswer = new ArrayList<GridCoordinate>();
    //calculate and store length of shortest path
    shortestSteps = shortestPath(begin, targets);
  }
  
  
  /**
   * Constructor that randomly constructs a grid using the width and height
   * specified. It constructs a random array of GridCoordinates.
   * 
   * @param w the width of the random grid
   * @param h the height of the random grid
   */
  public GridGraph(int w, int h) {
    boolean success = false;
    do {
      try {
        constructRandomGridGraph(w, h);
        success = true;
      } catch (PathNotFoundException ex) {
        //failed, retry
      }
    } while (!success);
  }
  
  /**
   * Private helper method that randomly constructs a GridGraph object 
   * (to be used by our random constructor).
   * 
   * @param w the width of the random grid
   * @param h the height of the random grid
   */
  private void constructRandomGridGraph(int w, int h) {
    level = 0;
    width = w;
    height = h;
    
    //constructing "blank" graph
    allVertices = new GridCoordinate[width*height];
    int n = 0;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        allVertices[n] = new GridCoordinate(x,y);
        n++;
      }
    }
    visitedVertices = new boolean[width*height];
    
    begin = randomGridCoordinate(width, height);
    targets = new ArrayList<GridCoordinate>();
    blocks = new ArrayList<GridCoordinate>();
    
    //randomize targets
    int numTargets = (int)Math.sqrt(width*height)/5;
    for (int i = 0; i < numTargets; i++) {
      GridCoordinate random;
      do {
        random = randomGridCoordinate(width, height); 
      } while (isTaken(random));
      targets.add(random);
    }
    
    //randomize blocks 
    int numBlocks = 2*(width + height);
    for (int i = 0; i < numBlocks; i++) {
      GridCoordinate random;
      do {
        random = randomGridCoordinate(width, height); 
      } while (isTaken(random));
      blocks.add(random);
      int x = random.getX();
      int y = random.getY();
      this.removeVertex(x,y);
    }
    
    //fill in top and bottom edges of grid
    for (int i = 0; i < width; i++) { 
      findGridCoordinate(i,0).setN(false);
      findGridCoordinate(i,height-1).setS(false);
    }
    
    //fill in left and right edges of grid
    for (int i = 0; i < height; i++) {
      findGridCoordinate(0,i).setW(false);
      findGridCoordinate(width-1,i).setE(false);
    }
    
          //pathAnswer will be created as the shortest path algorithm is performed
    pathAnswer = new ArrayList<GridCoordinate>();
    //calculate and store length of shortest path
    shortestSteps = shortestPath(begin, targets);
  }
  
  /**
   * Private helper method that creates and returns a random GridCoordinate.
   * 
   * @param width  the width of the random grid
   * @param height the height of the random grid
   * @return       a randomly generated GridCoordinate
   */
  private GridCoordinate randomGridCoordinate(int width, int height) {
    Random gen = new Random();
    int randomWidth = gen.nextInt(width-1);
    int randomHeight = gen.nextInt(height-1);
    GridCoordinate randomVertex = getVertex(randomWidth, randomHeight);
    return randomVertex;
  }  
  
  /**
   * Private helper method that returns true if a coordinate pair position 
   * is taken (already occupied by a specific block/target/begin vertex). It
   * is used by the random constructor to avoid the overlapping of blocks 
   * and targets.
   * 
   * @param gc the GridCoordinate to be checked
   * @return   a boolean indicating whether the inputted GridCoordinate is 
   *           already occupied by a block or target
   */
  private boolean isTaken(GridCoordinate gc) {
    if (begin.equals(gc))
      return true; //if taken by begin
    for (GridCoordinate target: targets) {
      if (target.equals(gc))
        return true; //if taken by a target
    } for (GridCoordinate block: blocks) {
      if (block.equals(gc))
        return true; //if taken by a block
    } return false;
  }
  
  /**
   * Returns the level value.
   * 
   * @return the level number
   */
  public int getLevel() {
    return level;
  }
  
  /**
   * Returns the width of this graph.
   * 
   * @return the width of this graph
   */
  public int getWidth() {
    return width; 
  }
  
  /**
   * Returns the height of this graph.
   * 
   * @return the height of this graph
   */
  public int getHeight() {
    return height; 
  }
  
  /** 
   * Returns the GridCoordinate that marks the starting position.
   * 
   * @return the GridCoordinate that marks the starting position
   */
  public GridCoordinate getBegin() {
    return begin;
  }
  
  /**
   * Returns a list of all the targets in the grid.
   * 
   * @return a list of all the targets in the grid
   */
  public ArrayList<GridCoordinate> getTargets() { 
    return targets; 
  }
  
  /**
   * Returns a list of all the blocks in the grid.
   * 
   * @return a list of all the blocks in the grid
   */
  public ArrayList<GridCoordinate> getBlocks() {
    return blocks; 
  }
  
  /**
   * Returns the length of the shortest path determined through the grid.
   * 
   * @return the length of the shortest path determined through the grid.
   */
  public int getShortestPath() {
    return shortestSteps;
  }
  
  /**
   * Computes and returns the length of the shortest path determined through
   * the grid. The algorithm for a grid with one target is simple, but it
   * requires recursion when used with multiple targets.
   * 
   * @param start   the GridCoordinate that represents the starting position
   * @param targets a list of the GridCoordinates that represent all the
   *                required ending destinations
   * @return        the length of the shortest path determined
   */
  private int shortestPath(GridCoordinate start, 
                           ArrayList<GridCoordinate> targets) {
    if (targets.size() == 1) { 
      //if simply 1 target, just determine path length of bfs
      LinkedList<GridCoordinate> bfs = bfs(start, targets.get(0));
      return pathLength(bfs);
    } //else we must use recursion
    int minimum = width*height;
    GridCoordinate minTarget = new GridCoordinate(0,0); //will be replaced in loop
    for (GridCoordinate target: targets) {
      //the setup: we must clone so we don't destroy our list
      ArrayList<GridCoordinate> clone = (ArrayList<GridCoordinate>)targets.clone(); 
      clone.remove(target);
      ArrayList<GridCoordinate> oneTarget = new ArrayList<GridCoordinate>();
      oneTarget.add(target);
      
      //calc path(start --> target) + path(target --> all remaining targets)
      int path = shortestPath(start, oneTarget) + shortestPath(target, clone);
      if (path < minimum) {
        minimum = path; //store minimum
        minTarget = target;
      }
    }
    return minimum;
  }
  
  /** 
   * Private helper method that determines the length of a path given
   * the LinkedList that results from its bfs traversal.
   *
   * @param bfs a list of GridCoordinate objects that remembers the order
   *            in which they were visited
   * @return    the length of the resulting path
   */
  private int pathLength(LinkedList<GridCoordinate> bfs) {
    if (bfs.size() == 0)
      return 0;
    int counter = 0; //will count number of steps we trace back 
    //before reaching our original position
    GridCoordinate current = bfs.getLast();
    while (current.hasPrevious()) { //traces back through shortest path
      current = current.getPrevious();
      counter++; 
      pathAnswer.add(current);
    }
    return counter;
  }
  
  /**
   * Private helper method that implements the bfs algorithm.
   * Extra note: each time a new GridCoordinate is enqueued, the current
   * GridCoordinate is stored into its previous reference (which enables the 
   * shortest path to be traced later).
   * 
   * @param start  the GridCoordinate that represents the starting position 
   *               of the search algorithm
   * @param target the GridCoordinate that represents the ending position 
   *               of the search algorithm
   * @return       a LinkedList of GridCoordinates in the order in which they were
   *               visited
   */
  private LinkedList<GridCoordinate> bfs(GridCoordinate start, 
                                         GridCoordinate target) {
    //reset all previous to null and all visited vertices to false
    for(int i = 0; i < allVertices.length; i++) {
      allVertices[i].setPrevious(null);
      visitedVertices[i] = false;
    }
    
    //setting up for our while loop
    GridCoordinate currentVertex;
    LinkedQueue<GridCoordinate> traversalQueue = new LinkedQueue<GridCoordinate>();
    LinkedList<GridCoordinate> bfsPath = new LinkedList<GridCoordinate>();
    traversalQueue.enqueue(start);
    this.setVisited(start);
    
    //while loop for bfs algorithm
    while (!traversalQueue.isEmpty()) {
      currentVertex = traversalQueue.dequeue();
      bfsPath.add(currentVertex);
      if (currentVertex.equals(target)) { 
        return bfsPath;
      } else {
        int currentX = currentVertex.getX();
        int currentY = currentVertex.getY();
        GridCoordinate northernNeighbor = this.getVertex(currentX, currentY-1);
        GridCoordinate easternNeighbor = this.getVertex(currentX+1, currentY);
        GridCoordinate southernNeighbor = this.getVertex(currentX, currentY+1);
        GridCoordinate westernNeighbor = this.getVertex(currentX-1, currentY);
        
        //visit neighbors & set previous (if they are unvisited)
        if (currentVertex.getN() && !this.isVisited(northernNeighbor)) {
          traversalQueue.enqueue(northernNeighbor);
          northernNeighbor.setPrevious(currentVertex);
          this.setVisited(northernNeighbor);
        } if (currentVertex.getE() && !this.isVisited(easternNeighbor)){
          traversalQueue.enqueue(easternNeighbor);
          easternNeighbor.setPrevious(currentVertex);
          this.setVisited(easternNeighbor);
        } if (currentVertex.getS() && !this.isVisited(southernNeighbor)) {
          traversalQueue.enqueue(southernNeighbor);
          southernNeighbor.setPrevious(currentVertex);
          this.setVisited(southernNeighbor);
        } if (currentVertex.getW() && !this.isVisited(westernNeighbor)){
          traversalQueue.enqueue(westernNeighbor);
          westernNeighbor.setPrevious(currentVertex);
          this.setVisited(westernNeighbor);
        }
      }
    }
    if (bfsPath.size() > 0 && bfsPath.getLast().equals(target)) 
      return bfsPath; //if we found our target
    else //we couldn't find a valid path to our target, so return an empty list
      throw new PathNotFoundException("A valid path does not exist!");
  }
  
  /**
   * Returns a list of GridCoordinates that must be visited in order to 
   * reach the target destination in the least number of steps possible.
   * 
   * @return an ArrayList of GridCoordinates that represents the shortest path
   */
  public ArrayList<GridCoordinate> getPathAnswer() {
   return pathAnswer; 
  }
  
  /**
   * Private helper method that returns the appropriate GridCoordinate object 
   * given its x and y values.
   * 
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @return  the appropriate GridCoordinate with the given x and y values 
   */
  private GridCoordinate findGridCoordinate(int x, int y) {
    int target = 100*x + y;
    for (int i = 0; i < allVertices.length; i++) {
      if (allVertices[i].getCoordinate() == target) {
        return allVertices[i]; 
      }
    }
    return null;
  }
  
  /**
   * Private helper method that creates a block that the user cannot navigate to
   * by removing all the 'edges' connecting it neighboring GridCoordinates.
   * 
   * @param x the x-coordinate of the GridCoordinate to be removed/blocked off
   * @param y the y-coordinate of the GridCoordinate to be removed/blocked off
   */
  private void removeVertex(int x, int y) {
    GridCoordinate target = findGridCoordinate(x,y);
    //set target's direction values to be false 
    target.setN(false);
    target.setS(false);
    target.setW(false);
    target.setE(false);
    //set the neighbors of target's direction values 
    //such that they don't point to target 
    if (x != 0)
      findGridCoordinate(x-1,y).setE(false);
    if (x != width-1)
      findGridCoordinate(x+1,y).setW(false);
    if (y != 0)
      findGridCoordinate(x,y-1).setS(false);
    if (y != height-1) 
      findGridCoordinate(x,y+1).setN(false);
  }
  
  /**
   * Private helper method that returns the GridCoordinate that corresponds to
   * the given x,y parameters.
   * 
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @return  the GridCoordinate with the corresponding x and y values
   */
  private GridCoordinate getVertex(int x, int y) {
    if (x < 0 || y < 0 || x > width-1 || y > height -1) 
      return null;
    int index = y*width + x;
    return allVertices[index];
  }
  
  /**
   * Private helper method that determines whether the GridCoordinate 
   * has been visited.
   * 
   * @param v the GridCoordinate to be checked
   * @return  a boolean indicating whether the GridCoordinate has been visited
   */
  private boolean isVisited(GridCoordinate v) {
    int index = v.getY()*width + v.getX();
    return visitedVertices[index];
  }
  
  /**
   * Private helper method that marks a GridCoordinate as visited.
   * 
   * @param v the GridCoordinate to be marked.
   */
  private void setVisited(GridCoordinate v) {
    int index = v.getY()*width + v.getX();
    visitedVertices[index] = true;
  }
  
  /**
   * Returns a String representation of a GridGraph object.
   * 
   * @return a String containing the information of all the GridCoordinates
   *         in this graph.
   */
  public String toString() {
    String s = "";
    for (int i = 0; i < allVertices.length; i++) {
      s += allVertices[i] + " ";
      if ((i+1) % width == 0)
        s += "\n";
    }
    return s;
  }
}