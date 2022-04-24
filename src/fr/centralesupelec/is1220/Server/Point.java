package fr.centralesupelec.is1220.Server;

import java.io.Serializable;

/**
 * <h1>Point</h1>
 *This class represents
 *the coordinates of an Object
 *on the map.
 */
public class Point implements Serializable{
	/**
	 * serial number.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * X coordinate of the Object.
	 */
	private double x;
	/**
	 * Y coordinate of the Object.
	 */
	private double y;

	/**
	 * Create a new Point on the map.
	 * @param x X coordinate of the Object.
	 * @param y Y coordinate of the Object.
	 */
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Gives the X coordinate of the Object.
	 * @return X coordinate.
	 */
	public double getX() {
		return x;
	}

	/**
	 *Update the X coordinate of an Object. 
	 * @param x X coordinate. 
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Gives the Y coordinate of the Object.
	 * @return Y coordinate.
	 */
	public double getY() {
		return y;
	}

	/**
	 *Update the Y coordinate of an Object. 
	 * @param y Y coordinate. 
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Gives a representation of the position of the Object.
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}



}
