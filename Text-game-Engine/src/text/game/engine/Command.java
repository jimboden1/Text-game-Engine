package text.game.engine;

public class Command {
    private String command, target;
    
    public Command(String command, String target){
        this.command= command;
        this.target = target;
    }
    public Command(String command){
        this.command= command;
        this.target = null;
    }
    public void setCommand(String command){this.command = command;}
    public void setTarget(String target){this.target = target;}
    public String getCommand(){return command;}
    public String getTarget(){return target;}
}
