package shapesAndColour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import mainApplet.MainStart;

/**
 * Draw the win screen for the user
 */
public class FinishGameImage {

	int x;
	int y;

	public FinishGameImage(){}

	public FinishGameImage(Graphics g, int x,int y) {
		this.x = x;
		this.y = y;

		g.setColor(Color.GRAY);	

		drawFace(g);
		drawWinMessage(g);

	}


	/**
	 * Draw the face
	 */
	public void drawFace(Graphics g) {

		g.drawOval(x, y, 200, 200);//Draw head shape
		drawLeftEye(g);
		drawRightEye(g);
		drawMouth(g);
		drawNose(g);
	}
	
	
	/**
	 * Display the win message
	 */
	public void drawWinMessage(Graphics g) {
		g.drawString("You won the amazing prize, exciting I know! Congratulations", x, y+210);
		g.drawString("Keep playing and see far you can go" , x, y+225);
	}
	
	
	/**
	 * Draw the left Eye
	 */
	public void drawLeftEye(Graphics g) {
		int size = 30;
		
		for (int i=0; i<=3; i++) {
			g.drawOval(x+44, y+40, size, size);//Left eye
			size --;
		}
		
	}

	
	/**
	 * Draw the right eye
	 */
	public void drawRightEye(Graphics g) {
		int size = 30;
		
		for (int i=0; i<=3; i++) {
			g.drawOval(x+124, y+40, size, size);
			size --;
		}
		
	}
	
	
	/**
	 * Draw the mouth
	 */
	public void drawMouth(Graphics g) {
		int size = 100;
		
		for (int i=0; i<=3; i++) {
			g.drawArc(x+48, x+380, size, size, 180, 180);
			size --;
		}
		
	}
	
	
	/**
	 * Draw the nose
	 */
	public void drawNose(Graphics g) {
		int xPoints[] = {x+100, x+90, x+110};
		int yPoints[] = {y+60, y+120, y+120};
		
		g.drawPolygon(xPoints, yPoints, xPoints.length);
	}
	
	
	
	
	
	


}



