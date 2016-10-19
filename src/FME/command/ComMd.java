package FME.command;
import FME.*;

/**
 * Created by lopatkindaniil on 18.10.16.
 */
public class ComMd extends Command {
    String[] argument = null;
    public ComMd(FileSystem fs, String[] arg) {
        super(fs);
        argument = arg;
    }

    @Override
    public void execute() {
        fs.makeDir(argument);
    }
}
