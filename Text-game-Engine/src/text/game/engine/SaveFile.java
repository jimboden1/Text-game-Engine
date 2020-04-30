package text.game.engine;

public class SaveFile implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Player player;
	private Location location;
	
	public SaveFile(Player p, Location l) {
		player=p;
		location=l;
	}
	
	public Player getPlayer() {return player;}
	public Location getLocation() {return location;}

}
