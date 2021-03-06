package FME.command;
import FME.*;

/**
 * Created by lopatkindaniil on 18.10.16.
 */
public class ComMove extends Command {
    String[] argument1 = null;
    String[] argument2 = null;

    public ComMove(FileSystem fs, String[] arg1, String[] arg2) {
        super(fs);
        argument1 = arg1;
        argument2 = arg2;
    }

    @Override
    public void execute() {
        fs.move(argument1, argument2);
    }
}
