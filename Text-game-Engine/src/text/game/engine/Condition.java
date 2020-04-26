package text.game.engine;

public class Condition {
	Object first;
	Object second;
	
	public Condition(Object first, Object second) {
		this.first = first;
		this.second = second;
	}
	
	public boolean and(boolean c1, boolean  c2) {
		if(c1&& c2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean or(boolean c1, boolean c2) {
		if(c1||c2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean equals(Object thing1, Object thing2) {
		if(thing1==thing2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean playerHasSkill(int skill) {
		return CentralDB.player.getSkills().contains(skill);
	}
	
	public boolean playerHasItem(int item) {
		return CentralDB.player.inventory.contains(item);
	}
	public boolean playerLocation(Location location) {
		if(PlatformPanel.here == location) {
			return true;
		}
		else {
			return false;
		}
	}
}
