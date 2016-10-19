package FME;

/**
 * <h1>File Manager Emulator</h1>
 * File Manager Emulator (FME) emulates the details of creating, removing,
 * copying and moving files and directories. It also handles commands
 * related to these file management tasks. After the batch file execution it will
 * generate and print out formatted directory structure or
 * an error message if something went wrong to standard output.
 *
 * @author  Lopatkin Daniil
 * @version 0.01
 * @since   2016-10-01
 */

public class FileSystem {
    private Directory current;
    private Directory root;

    public FileSystem() {
        current = new Directory("C:", null);
        root = current;
    }

    /**
     * Creates a directory. Implements command MD.
     * @param dirName Name of new directory.
     * @return True if succeed.
     */
    public boolean makeDir(String[] dirName) {
        if(dirName[0].equals("C:")) {
            return root.create(dirName, true);
        }
        return current.create(dirName, true);
    } //checked!!

    /**
     * Changes the current directory. Implements command CD.
     * @param dirName Name of destination directory.
     * @return True if succeed.
     */
    public boolean changeDir(String[] dirName) {
        Node tmp = current;
        int count = 0;
        if (dirName[0].equals("C:")){
            tmp = root;
            count = 1;
        }
        for (; count < dirName.length; count++){
            tmp = tmp.getChild(dirName[count]);
            if (tmp == null) {
                return false;
            }
        }
        if(tmp instanceof File) {
            return false;
        }
        current = (Directory) tmp;
        return true;
    } //checked!!

    /**
     * Removes a directory if it is empty (doesn’t contain any files or subdirectories). Implements command RD.
     * @param dirName Name of directory we want to remove.
     * @return True if succeed.
     */
    public boolean removeDir(String[] dirName) {
        int count = 1;
        Node tmp = current;
        if (dirName[0].equals("C:") && dirName.length > 1 ) {
            tmp = root;
            count = 1;
        }
        for (; count < dirName.length - 1; count++){
            tmp = tmp.getChild(dirName[count]);
            if (tmp == null) {
                return false;
            }
        }
        if(tmp instanceof Directory) {
            tmp.delete(dirName[dirName.length - 1]);
            return true;
        }
        return false;
    } //checked!!

    /**
     * Removes a directory with all its subdirectories. Implements command DELTREE.
     * @param dirName Name of initial directory we want to remove.
     * @return True if succeed.
     */
    public boolean removeTreeDir(String[] dirName) {
        Node tmp = current;
        int count = 0;
        if (dirName[0].equals("C:")) {
            tmp = root;
            count = 1;
        }
        for (; count < dirName.length - 1; count++){
            tmp = tmp.getChild(dirName[count]);
            if (tmp == null) {
                return false;
            }
        }
        if(tmp instanceof Directory) {
            tmp.getContent().clear();
            return true;
        }
        return false;
    } //checked!!

    /**
     * Creates a file. Implements command MF.
     * @param dirName Name of new file.
     * @return True if succeed.
     */
    public boolean createFile(String[] dirName) {
        if(dirName[0].equals("C:")) {
            return root.create(dirName, true);
        }
        return current.create(dirName, false);
    } //checked!!

    /**
     * Removes a file. Implements command DEL.
     * @param dirName Name of file we want to remove.
     * @return True if succeed.
     */
    public boolean deleteFile(String[] dirName) {
        Node tmp = current;
        int count = 0;
        if (dirName[0].equals("C:")) {
            tmp = root;
            count = 1;
        }
        for (; count < dirName.length; count++){
            tmp = tmp.getChild(dirName[count]);
            if (tmp == null) {
                return false;
            }
        }
        if(tmp instanceof File) {
            tmp.getParent().delete(tmp.getName());
            return true;
        }
        return false;
    } //checked!!

    /**
     * Copy an existed directory/file/link to another location. Implements command COPY.
     * @param dirName Name of directory we want to copy.
     * @param destName Name of destination directory.
     * @return True if succeed.
     */
    public boolean copy(String[] dirName, String[] destName) {
        return current.copy(dirName,destName);
    }

    /**
     * Move an existing directory/file/link to another location. Implements command MOVE.
     * @param dirName Name of directory we want to move.
     * @param destName Name of destination directory.
     * @return True if succeed.
     */
    public boolean move(String[] dirName, String[] destName) {
        return current.move(dirName, destName);
    }

    public void print(){
        root.print(0);
    } //cheked!!
}







