package text.game.engine;

public class Benefit {
    public String attribute;
    public int modifier, attributePlace;
    
    public void makeAttribute(int place) {
    	if(place == 0) {
    		attribute = "Strength";
    	}
    	else if(place == 1) {
    		attribute = "Dexterity";
    	}
    	else if(place == 2) {
    		attribute = "IQ";
    	}
    	else if(place == 3) {
    		attribute = "Health Points";
    	}
    	else if(place == 4) {
    		attribute = "Perception";
    	}
    	else {
    		attribute = "Will";
    	}
    }
}