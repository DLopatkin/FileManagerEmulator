package FME.command;
import FME.*;

/**
 * Created by lopatkindaniil on 18.10.16.
 */
public class ComCd extends Command {
    String[] argument = null;
    public ComCd(FileSystem fs, String[] arg) {
        super(fs);
        argument = arg;
    }

    @Override
    public void execute() {
        fs.changeDir(argument);
    }
}
