package shapesAndColour;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Draw a square with the colour and whether is is filled in passed into the method. 
 * @author John
 *
 */
public class DrawSquareWithColour {
	
	/**
	 * Draw a square
	 * @param g
	 * @param isFilled	Is the square to be filled with colour
	 * @param colour	The Colour of the square
	 * @param x			The x location of the Square
	 * @param y			The y location of the Square
	 */
	public void DrawSquare(Graphics g, boolean isFilled, Color colour,  int x, int y){
		int dimension = 200;//The dimension off the square 
		
		
		//Check if the square is to be filled
		if (isFilled){
			//Draw the square
			g.setColor(colour);	
			g.fillRect(x, y, dimension, dimension);					
		}
		else{
			g.setColor(colour);
			g.drawRect(x, y, dimension, dimension);
		}
	}

}
