package text.game.engine;

public class Command {
    private String command;
    private Method method;
    
    public Command(String command, Method method){
        this.command= command;
        this.method = method;
    }
    public Command(String command){
        this.command= command;
        this.method = null;
    }
    public void setCommand(String command){this.command = command;}
    public void setTarget(Method method){this.method = method;}
    public String getCommand(){return command;}
    public Method getTarget(){return method;}
}
