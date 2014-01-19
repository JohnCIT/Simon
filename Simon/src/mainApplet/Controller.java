package mainApplet;

import gameLogic.GameLogic;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Action;

public class Controller {
	MainStart view;
	GameLogic logic;

	public Controller() {}



	public Controller(MainStart view) {
		this.view  = view;
		this.logic = new GameLogic(); 

		//Link the action listeners
		view.blueButtEvent  (new BlueButtAction());
		view.redButtEvent   (new RedButtAction());
		view.greenButtEvent (new GreenButtAction());
		view.yellowButtEvent(new YellowButtAction());
		view.startGameButt  (new BeginGameAction());
		view.clearButtonAction(new clearUserGuess());
		view.submitButtonAction(new GuessSubmit());
		view.resetAction(new Reset());
	}




	/**
	 * The action the yellow button causes
	 *
	 */
	public class RedButtAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.addTextToTheTA("Red\n");
			MainStart.userInput.add(0);
		}

	}



	/**
	 * The action the Blue button causes
	 *
	 */
	public class BlueButtAction implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
			view.addTextToTheTA("Blue\n");
			MainStart.userInput.add(1);
		}

	}



	/**
	 * The action the green button causes
	 *
	 */
	public class GreenButtAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.addTextToTheTA("Green\n");
			MainStart.userInput.add(2);
		}

	}




	/**
	 * The action the yellow button causes
	 *
	 */
	public class YellowButtAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.addTextToTheTA("Yellow\n");
			MainStart.userInput.add(3);
		}

	}




	/**
	 * Begin the game
	 *
	 */
	public class BeginGameAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Turn the Begin button off so it wont be clicked numerous times and turn the colour buttons off until the game loop is done
			view.enableBeginButton(false);
			view.enableColourButtons(false);
			
			new GameLogic(view);
			
		}

	}




	/**
	 * Clear the user guess array and the guess area
	 *
	 */
	public class clearUserGuess implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.setGuessAreaText("");
			MainStart.userInput.clear();
		}

	}



	/**
	 * What happens when the user submits there guess
	 *
	 */
	public class GuessSubmit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Disable the submit button
			view.enableInputButton(false);
			
			boolean isWin = GameLogic.isGuessCorrect();

			//If a winner increment the level
			if (isWin) {

				if (MainStart.gameLevel == MainStart.FINISHGAMELEVEL) {
					MainStart.win = true;
					view.repaint();
				}

				MainStart.gameLevel ++;

				//Clear the colour arrays
				MainStart.gameInput.clear();
				MainStart.userInput.clear();

				//Change begin text
				view.setBeginText("Begin level " + MainStart.gameLevel);

				//Turn the button back on so the user can start the level
				view.enableBeginButton(true);

				//Reset the text area
				view.setGuessAreaText("");

				//Add some points and refresh the label to reflect the new score
				MainStart.points += 10 * MainStart.gameLevel;
				view.refreshPointsLabel();

			}
			else {
				//Show a message and disable all buttons except restart
				view.setBeginText("Game over");
				view.enableBeginButton(false);
				view.enableColourButtons(false);
				view.showResetButton(true);
				view.setGuessAreaText("Try again");
				
				//Add the points to the high score
				MainStart.highScore.add(MainStart.points);
				
				MainStart.points = 0;//reset the points
				view.refreshPointsLabel(); //Up date the label
				view.setGuessAreaText(MainStart.highScore.toString());
				
				view.enableInputButton(false);
			}
		}

	}





	/**
	 * What occurs when the reset button is clicked. Rest all arrays and level
	 */
	public class Reset implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Reset arrays
			MainStart.gameInput.clear();
			MainStart.userInput.clear();
			MainStart.gameLevel = 1;

			view.showResetButton(false);
			view.enableColourButtons(true);
			view.enableBeginButton(true);
			view.setGuessAreaText("");
			view.setBeginText("Begin level " + MainStart.gameLevel);

			//If the user unlocked the image take it away. Nasty I know
			MainStart.win = false;

			view.enableInputButton(false);
		}

	}

}


























