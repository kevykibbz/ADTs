import java.util.ArrayList;

public class MazeNode {

	/** Value of any Object type*/
	private String value;

	/** Can have any number of children */
	private ArrayList<MazeNode> children = new ArrayList<>();

	/** Previous Node (passed through to get to this) */
	private MazeNode previous = null;

	/** Exit point for the maze */
	private boolean isExit = false;

	public MazeNode(String v) {
		value = v;
	}

	/** Constructor
	  * @param v Initial value of Node.
	*/
	public MazeNode(String v, boolean exit) {
		value = v;
		isExit = exit;
	}

	public String toString() {
		return "["+value+","+previous+"]";
	}

	/** Add a Node that is a child of "this"
	* @param n Node with <T> type value.
	*/
	public void addChild(MazeNode n) {
		children.add(n);
	}

	/** Getter for children. */
	public ArrayList<MazeNode> children() {
		return children;
	}

	// Getter and setter for value
	public String value() {
		return value;
	}
	public void value(String v) {
		value = v;
	}
	public void setExit(boolean e) {
		isExit = e;
	}
	public boolean isExit() {
		return isExit;
	}
	public void previous(MazeNode p) {
		previous = p;
	}
	public MazeNode previous() {
		return previous;
	}
}
