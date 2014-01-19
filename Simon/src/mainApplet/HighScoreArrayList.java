package mainApplet;

import java.util.ArrayList;

public class HighScoreArrayList extends ArrayList <Integer>{
	
	
	public String toString() {
		
		String score = "High scores: \n";
		
		for (int i=0; i<super.size(); i++) {
			score += i+1 + ": " + super.get(i) + "\n";
		}
		
		
		return score;
	}
	
	
	public String print(){
		return toString();
	}
}
