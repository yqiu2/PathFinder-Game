/*
 * PathNotFoundException.java
 * CS230 Final Project
 * Author: Priscilla Lee
 * Date: 14 December 2014
 * 
 * Represents the situation in which a path doesn't exist from the
 * start to the targets.
 */

public class PathNotFoundException extends RuntimeException
{
  /**
   * Default constructor.
   * 
   * @param message a String containing the exception message
   */
  public PathNotFoundException (String message)
  {
    super (message);
  }
}
