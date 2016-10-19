package FME;

import java.util.ArrayList;

/**
 * Class of File entity
 */
public class File extends Node {
    public File(String name, Node parent){
        super(name, parent);
    }

    public File(File copy) {
        super(copy);
    }

    /**
     * Implements Create function always return false because file can't contain other files or directories.
     * @return boolean false
     */
    public boolean create(String[] name, boolean isDirectory){
        return false;
    }

    /**
     * Implements Delete function always return false because file can't contain other files or directories.
     * @return boolean false
     */
    public boolean delete(String name){
        return false;
    }

    /**
     * Implements MOVE function.
     * @param pathToMove Destination absolute name splited into tokens.
     * @return True if succeed.
     */
    public boolean move(String[] source, String[] pathToMove){
        return false;
    }

    @Override
    boolean copy(String[] source, String[] pathToMove) {
        return false;
    }

    /**
     * Implements DeleteAll function always return false because file can't contain other files or directories.
     * @return boolean false
     */
    public boolean deleteAll(){
        return false;
    }

    @Override
    Node getChild(String name) {
        return null;
    }

    @Override
    ArrayList<Node> getContent() {
        return null;
    }

    @Override
    public void print(int depth) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            name.append("|");
        }
        name.append("_");
        name.append(this.getName());
        System.out.println(name.toString());
    }

    public boolean isEmpty() {
        return true;
    }
}
