package FME.command;
import FME.*;

/**
 * Created by lopatkindaniil on 18.10.16.
 */
public abstract class Command {
    FileSystem fs = null;
    public Command(FileSystem fs){
        this.fs = fs;
    }
    abstract public void execute();
}
