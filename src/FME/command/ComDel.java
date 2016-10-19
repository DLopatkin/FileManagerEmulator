package FME.command;
import FME.*;

/**
 * Created by lopatkindaniil on 18.10.16.
 */
public class ComDel extends Command {
    String[] argument = null;

    public ComDel(FileSystem fs, String[] arg) {
        super(fs);
        argument = arg;
    }

    @Override
    public void execute() {
        fs.deleteFile(argument);
    }
}
