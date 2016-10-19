package FME;

import java.util.ArrayList;

/**
 * Base class for node of our file system.
 * It has some virtual methods which will be implemented in subclasses.
 */
public abstract class Node {
    /**
     * Name of file or directory.
     */
    private String name;

    /**
     * Parent directory node.
     */
    private Node parent;

    /**
     * Constructor of class.
     * @param name Name of new file(directory).
     */
    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }

    public Node(Node copy) {
        this.name = copy.getName();
        this.parent = copy.getParent();
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }
    /**
     * Method which will tell us node name.
     * @return String which contains name of file or directory, represented by this node.
     */
    public String getName(){
        return name;
    }

    /**
     * Method which will tell us node absolute name.
     * @return String which contains absolute name of file or directory, represented by this node.
     */
    public String getFullName() {
        String fullName = null;
        if (parent != null) {
            fullName = parent.getFullName() + "/" + name;
        } else {
            fullName = name;
        }
        return fullName;
    }

    /**
     * Virtual method.
     */
    abstract boolean create(String[] name, boolean isDirectory);

    /**
     * Virtual method.
     */
    abstract boolean delete(String name);

    /**
     * Virtual method.
     */
    abstract boolean move(String[] source, String[] pathToMove);

    /**
     * Virtual method.
     */
    abstract boolean copy(String[] source, String[] pathToMove);

    /**
     * Virtual method.
     */
    abstract boolean deleteAll();

    abstract Node getChild(String name);

    abstract ArrayList<Node> getContent();

    abstract void print(int depth);

    abstract boolean isEmpty();
}
