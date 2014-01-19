package mainApplet;

/** 
 * Where the applet begins
 * @author John
 * Version 1
 * john.murphy7@mycit.ie
 *
 */



import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import net.miginfocom.swing.MigLayout;
import shapesAndColour.DrawSquareWithColour;
import shapesAndColour.FinishGameImage;



public class MainStart extends Applet {
	//High score array
	public static HighScoreArrayList highScore;
	
	//The points the user has
	public static int points = 0;

	//The Level the user is on
	public static int gameLevel = 1;

	//The deciding colour
	public static int shapeToColour = 5; //Five so nothing is filled 
	
	//The array to store what colours were shown
	public static ArrayList<Integer> gameInput;
	
	//Stores what colour the user entered
	public static ArrayList<Integer> userInput;

	//Set up gui components
	private Button inputAnswer;
	private Button begin;
	private Button blueButt;
	private Button redButt;
	private Button greenButt;
	private Button yellowButt;
	private Button clear;
	private TextArea guessArea;
	private Panel  topPan;
	private Label  instructLab;
	private Button reset;
	private Label pointsLab;
	
	//Win boolean and level
	public static boolean win = false;
	final public static int FINISHGAMELEVEL = 5;
	
	//Where the applet begins
	public void init(){
		//Makes the view
		setUpView();

		//Make the controller. Links the action listeners to the view
		new Controller(this);
		
		//Initilize the array list
		gameInput = new ArrayList<Integer>();
		userInput = new ArrayList<Integer>();
		highScore = new HighScoreArrayList();
	}



	//Set up the view
	public void setUpView() {


		//Make the components
		inputAnswer = new Button("Submit");
		begin		= new Button("Begin level " + gameLevel);
		blueButt	= new Button();
		redButt     = new Button();
		greenButt   = new Button();
		yellowButt  = new Button();
		clear       = new Button("Clear");
		guessArea   = new TextArea();
		topPan      = new Panel();
		instructLab = new Label("Welcome to the great guessing game. Try and finish it, its fun honest!");
		reset       = new Button("Start again?");
		pointsLab   = new Label("Points: " + points);
		
		//Change the font
		Font font = new Font("Verdana", Font.BOLD, 18);
		instructLab.setFont(font);
		
		//Add the label to the top panel
		topPan.add(instructLab);
		
		//Set layout
		setLayout(new MigLayout());
	
		//Set the size of the components
		blueButt.setPreferredSize  (new Dimension(60, 50));
		redButt.setPreferredSize   (new Dimension(60, 50));
		greenButt.setPreferredSize (new Dimension(60, 50));
		yellowButt.setPreferredSize(new Dimension(60, 50));
		guessArea.setPreferredSize (new Dimension(50, 10));
		pointsLab.setPreferredSize (new Dimension(100, 30));
		
		//Turn the submit button of until there is something to submit
		inputAnswer.setEnabled(false);

		//Set the button colour
		blueButt.setBackground  (Color.blue);
		redButt.setBackground   (Color.red);
		greenButt.setBackground (Color.green);
		yellowButt.setBackground(Color.yellow);
		
		//Hide the reset button until it is needed
		reset.setVisible(false);

		//Add the components to the applet
		add(topPan,     "wrap");
		add(redButt,    "cell 0 1");
		add(blueButt,   "cell 0 1");		
		add(greenButt,  "cell 0 1");
		add(yellowButt, "cell 0 1");
		add(begin,      "wrap");
		add(guessArea,  "wrap");
		add(inputAnswer,"split 2");	
		add(clear,      "gap left 340, cell 0 2, wrap");
		add(reset, 		"wrap");
		add(pointsLab);



		//If the status bar is present show the user there level
		showStatus("You are on level: " + gameLevel);


		//Set the size of the applet
		setSize(new Dimension(1000, 1000));

	}



	//Override the paint method to draw the shapes
	public void paint(Graphics g) {
		super.paint(g);
		
		//Make the square objects
		DrawSquareWithColour redSquare      = new DrawSquareWithColour();
		DrawSquareWithColour blueSquare     = new DrawSquareWithColour();
		DrawSquareWithColour greenSquare    = new DrawSquareWithColour();
		DrawSquareWithColour yellowSquare   = new DrawSquareWithColour();

		//Draw the squares
		redSquare.DrawSquare   (g, false, Color.red,    450, 100);
		blueSquare.DrawSquare  (g, false, Color.blue,   700, 100);
		greenSquare.DrawSquare (g, false, Color.green,  450, 350);
		yellowSquare.DrawSquare(g, false, Color.yellow, 700, 350);
		
		//Stops the thread long enough so if the same colour is shown twice there is a noticeable gap
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		switch (shapeToColour) {
		case 0:			
			redSquare.DrawSquare(g, true, Color.red, 450, 100);
			gameInput.add(0);//Add the number corresponding to the colour to the array
			break;

		case 1:
			blueSquare.DrawSquare(g, true, Color.blue, 700, 100);
			gameInput.add(1);
			break;
			
		case 2:
			greenSquare.DrawSquare(g, true, Color.green, 450, 350);
			gameInput.add(2);
			break;
			
		case 3:
			yellowSquare.DrawSquare(g, true, Color.yellow, 700, 350);
			gameInput.add(3);
			break;
		}
		
		if (win) {
			new FinishGameImage(g, 50, 350);
		}
		
		
				
	}
	
	
	



	//Action events and general things to happen in the main applet


	/**
	 * Append text to the text area
	 * @param msg	The message you want displayed
	 */
	public void addTextToTheTA(String msg) {
		guessArea.append(msg);
	}
	
	/**
	 * Sets the text area string
	 * @param text	The text to place in the text area
	 */
	public void setGuessAreaText(String text) {
		guessArea.setText(text);
	}



	//Add buttons event
	public void blueButtEvent(ActionListener e) {
		blueButt.addActionListener(e);
	}


	public void redButtEvent(ActionListener e) {
		redButt.addActionListener(e);
	}


	public void greenButtEvent(ActionListener e) {
		greenButt.addActionListener(e);
	}


	public void yellowButtEvent(ActionListener e) {
		yellowButt.addActionListener(e);
	}
	
	public void startGameButt(ActionListener e) {
		begin.addActionListener(e);
	}
	
	public void clearButtonAction(ActionListener e) {
		clear.addActionListener(e);
	}
	
	public void submitButtonAction(ActionListener e) {
		inputAnswer.addActionListener(e);
	}
	
	public void resetAction(ActionListener e) {
		reset.addActionListener(e);
	}
	
	
	
	//Refresh the points label
	public void refreshPointsLabel(){
		pointsLab.setText("Points: " + points);
	}
	
	//Set the text of the begin button
	public void setBeginText(String msg) {
		begin.setLabel(msg);
	}
	
	//Set the reset button visible or not
	public void showResetButton(boolean bool) {
		reset.setVisible(bool);
	}
	
	//Turn buttons on and off
	public void enableColourButtons(boolean bool) {
		redButt.setEnabled(bool);
		blueButt.setEnabled(bool);
		greenButt.setEnabled(bool);
		yellowButt.setEnabled(bool);
	}
	
	//Set the begin Button on or off
	public void enableBeginButton(boolean bool) {
		begin.setEnabled(bool);
	}
	
	//Turn the submit button on and off
	public void enableInputButton(boolean bool) {
		inputAnswer.setEnabled(bool);
	}




}
