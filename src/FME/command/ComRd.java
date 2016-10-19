package FME.command;
import FME.*;

/**
 * Created by lopatkindaniil on 18.10.16.
 */
public class ComRd extends Command {
    String[] argument = null;
    public ComRd(FileSystem fs, String[] arg) {
        super(fs);
        argument = arg;
    }

    @Override
    public void execute() {
        fs.removeDir(argument);
    }
}
