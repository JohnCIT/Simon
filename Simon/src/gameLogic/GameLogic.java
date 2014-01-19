package gameLogic;

import mainApplet.MainStart;


public class GameLogic implements Runnable{
	MainStart view;
	Thread runGame;

	public GameLogic() {}

	public GameLogic(MainStart view) {
		this.view = view;

		runGame = new Thread(this, "game loop");
		runGame.start();
	}


	public int getRandomNum() {
		return (int) (Math.random() * 4);
	}



	/**
	 * Implements the new thread
	 */
	public void run() {
		//Control the loop
		boolean loopController = true;

		//Number of times the loop has gone round
		int revolutions = 0;

		//A second in nano seconds
		long showLength = 1000000000;

		//The time at this point
		long execution = System.nanoTime();

		while (loopController) {
			//Done every second
			if ((System.nanoTime() - execution) >= showLength) {
				execution = System.nanoTime(); //Sets the time to the current time so it will be correct

				//Keeps track of how many times the if statement has been successful
				revolutions ++;
				
				//Sends in the random number to the view so it will show the shape
				MainStart.shapeToColour = getRandomNum();
				
				view.repaint();
			}

			//Check if the revolutions are the same as the level + 2 so it will flash twice
			if (MainStart.gameLevel + 2 == revolutions) {
				revolutions = 0;
				loopController = false;//Get out of the loop
				MainStart.shapeToColour = 5;
				view.repaint();
			}
		}
		
		//Turn the colour buttons back on so The user can input there guess
		view.enableColourButtons(true);
		
		//Enable the submit button
		view.enableInputButton(true);

	}
	
	
	/**
	 * Checks the guess to see if it is correct
	 */
	public static boolean isGuessCorrect() {
		
		boolean win = true;
		
		//Check if the length of the arrays are the same. If not then it is definitely wrong
		if (MainStart.gameInput.size() != MainStart.userInput.size()) {
			win = false;
			return win;
		}

		for (int i=0; i<MainStart.gameInput.size(); i++) {
			int gameCol   = MainStart.gameInput.get(i);
			int userGuess = MainStart.userInput.get(i);
			
			if (gameCol != userGuess) {
				win = false;						
			}

		}
		
		return win;		
	}

}
