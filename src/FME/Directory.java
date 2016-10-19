package FME;

import java.util.ArrayList;

/**
 * Implements Directory entity.
 */
public class Directory extends Node {
    private ArrayList<Node> content = null;

    public Directory(String name, Node parent){
        super(name, parent);
        content = new ArrayList<Node>();
    }

    public Directory(Directory copy) {
        super(copy);
        content = copy.getContent();
    }

    @Override
    public Node getChild(String name) {
        for (Node child : content) {
            if(child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Implements create function. Creates new directory of file.
     * @param name Name of new file or directory.
     * @return True if succeed.
     */
    @Override
    public boolean create(String[] name, boolean isDirectory){
        if (name.length != 1) {
            String[] subname = null;
            int index = 0;
            Node start = this;
            if (name[0].equals("C:")) {
                subname = new String[name.length - 2];
                System.arraycopy(name, 2, subname, 0, subname.length);
                while (start.getParent() != null ) {
                    start = start.getParent();
                }
                index = 1;
            } else {
                subname = new String[name.length - 1];
                System.arraycopy(name, 1, subname, 0, subname.length);
            }
            return start.getChild(name[index]).create(subname, isDirectory);
        }
        for (Node child : content) {
            if (child.getName().equals(name[0])) {
                return false;
            }
        }
        if (isDirectory) {
            content.add(new Directory(name[0], this));
        } else {
            content.add((new File(name[0], this)));
        }
        return false;
    }

    /**
     * Implements Delete function. Delete selected directory(if it's empty) of file.
     * @param name Name of file or directory we want to remove.
     * @return True if succeed.
     */
    @Override
    public boolean delete(String name){
        Node child = this.getChild(name);
        if (child == null) {
            return false;
        }
        if (child.isEmpty() != true) {
            return false;
        }
        this.getContent().remove(child);
        return true;
    }

    /**
     * Implements MOVE function.
     * @param pathToMove Destination absolute name splited into tokens.
     * @return True if succeed.
     */
    @Override
    public boolean move(String[] source, String[] pathToMove){
        Node nodeSource = this;
        Node destination = this;
        int counter = 0;
        if (source[0].equals("C:")) {
            while (nodeSource.getParent() != null){
                nodeSource = nodeSource.getParent();
            }
            counter = 1;
        }
        for (; counter < source.length; counter++) {
            nodeSource = nodeSource.getChild(source[counter]);
            if (nodeSource == null) {
                return false;
            }
        }
        if (pathToMove[0].equals("C:")) {
            while (destination.getParent() != null){
                destination = destination.getParent();
            }
            counter = 1;
        }
        for (; counter < pathToMove.length; counter++) {
            destination = destination.getChild(pathToMove[counter]);
            if (destination == null) {
                return false;
            }
        }
        if (destination.getChild(nodeSource.getName()) != null) {
            return false;
        }
        ((Directory) destination).addChild(nodeSource);
        nodeSource.getParent().getContent().remove(nodeSource);
        nodeSource.setParent(destination);
        return true;
    }

    @Override
    public boolean copy(String[] source, String[] pathToMove){
        Node nodeSource = this;
        Node destination = this;
        int counter = 0;
        if (source[0].equals("C:")) {
            while (nodeSource.getParent() != null){
                nodeSource = nodeSource.getParent();
            }
            counter = 1;
        }
        for (; counter < source.length; counter++) {
            nodeSource = nodeSource.getChild(source[counter]);
            if (nodeSource == null) {
                return false;
            }
        }
        if (pathToMove[0].equals("C:")) {
            while (destination.getParent() != null){
                destination = destination.getParent();
            }
            counter = 1;
        }
        for (; counter < pathToMove.length; counter++) {
            destination = destination.getChild(pathToMove[counter]);
            if (destination == null) {
                return false;
            }
        }
        if (destination.getChild(nodeSource.getName()) != null) {
            return false;
        }
        Node copied = null;
        if (nodeSource instanceof File) {
            copied = new File((File) nodeSource);
        } else {
            copied = new Directory((Directory) nodeSource);
        }
        ((Directory) destination).addChild(copied);
        copied.setParent(destination);
        return true;
    }

    /**
     * Implements DeleteAll function. Removing all nested files and directories.
     * @return True if succeed.
     */
    @Override
    public boolean deleteAll(){
        content = null;
        return true;
    }

    public void addChild(Node child) {
        content.add(child);
    }

    @Override
    public ArrayList<Node> getContent() {
        return content;
    }

    @Override
    void print(int depth) {
        if (depth == 0){
            System.out.println(this.getName());
        } else {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                name.append("|");
            }
            name.append("_");
            name.append(this.getName());
            System.out.println(name.toString());
        }
        for (Node child : content) {
            child.print(depth + 1);
        }
    }

    @Override
    boolean isEmpty() {
        if (content == null || content.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
