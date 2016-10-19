package FME;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import FME.command.*;
import java.util.ArrayList;

/**
 * Created by lopatkindaniil on 16.10.16.
 */
public class Commander {

    private static boolean isCommand(String line) {
        line = line.toLowerCase();
        return (line.equals("cd")       ||
                line.equals("md")       ||
                line.equals("rd")       ||
                line.equals("deltree")  ||
                line.equals("mf")       ||
                line.equals("del")      ||
                line.equals("copy")     ||
                line.equals("move"));
    }

    /**
     * Main Method which would contain logic of our programme.
     * @param args Name of input file.
     */
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/input.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file with this name " + path);
            System.exit(0);
        }
        StringBuilder  readedString = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            readedString.append(line);
            readedString.append(" ");
        }

        FileSystem fs = new FileSystem();
        String[] notFiltered = readedString.toString().split(" ");
        ArrayList<Command> commands = new ArrayList<>();
        for (int i = 0; i < notFiltered.length;  i++) {
            switch (notFiltered[i]) {
                case "CD" : {
                    if (isCommand(notFiltered[i + 1]) == false) {
                        commands.add(new ComCd(fs, notFiltered[i+1].split("/")));
                    }
                    i++;
                    break;
                }
                case "MD" : {
                    if (isCommand(notFiltered[i+1]) == false) {
                        commands.add(new ComMd(fs, notFiltered[i+1].split("/")));
                    }
                    i++;
                    break;
                }
                case "RD" : {
                    if (isCommand(notFiltered[i+1]) == false) {
                        commands.add(new ComRd(fs, notFiltered[i+1].split("/")));
                    }
                    i++;
                    break;
                }
                case "DELTREE" : {
                    if (isCommand(notFiltered[i+1]) == false) {
                        commands.add(new ComDT(fs, notFiltered[i+1].split("/")));
                    }
                    i++;
                    break;
                }
                case "MF" : {
                    if (isCommand(notFiltered[i+1]) == false) {
                        commands.add(new ComMf(fs, notFiltered[i+1].split("/")));
                    }
                    i++;
                    break;
                }
                case "DEL" : {
                    if (isCommand(notFiltered[i+1]) == false) {
                        commands.add(new ComDel(fs, notFiltered[i+1].split("/")));
                    }
                    i++;
                    break;
                }
                case "COPY" : {
                    if (isCommand(notFiltered[i+1]) == false) {
                        commands.add(new ComCopy(fs, notFiltered[i+1].split("/"), notFiltered[i+2].split("/")));
                    }
                    i+=2;
                    break;
                }
                case "MOVE" : {
                    if (isCommand(notFiltered[i+1]) == false) {
                        commands.add(new ComMove(fs, notFiltered[i+1].split("/"), notFiltered[i+2].split("/")));
                    }
                    i+=2;
                    break;
                }

                default : break;
            }
        }
        for (Command command : commands) {
            command.execute();
        }
        fs.print();
    }
}
