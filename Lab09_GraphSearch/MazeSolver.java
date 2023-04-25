import java.util.ArrayList;

public class MazeSolver {

    private static int count = 0;

    /**
     * Resets the count to 0.
     */
    public static void resetCount() {
        count = 0;
    }

    /**
     * Returns the number of nodes visited during the last search.
     */
    public static int count() {
        return count;
    }

    /**
     * Solves a maze using either bfs or dfs algorithm.
     *
     * @param root The root node of the maze.
     * @param q The type of traversal to use: "bfs" or "dfs".
     * @return An ArrayList of nodes representing the path from root to the exit.
     */
    public static ArrayList<MazeNode> solve(MazeNode root, String q) {
        resetCount();
        ArrayList<MazeNode> path = new ArrayList<>();
        QueueInterface<MazeNode> queue;
        if (q.equals("bfs")) {
            queue = new Queue<MazeNode>(1000);
        } else {
            queue = (QueueInterface<MazeNode>) new Stack<MazeNode>(1000);
        }
        queue.enqueue(root);
        root.previous(null);
        while (!queue.isEmpty()) {
            MazeNode node = queue.dequeue();
            if (node.isExit()) {
                // found exit
                while (node != null) {
                    path.add(0, node);
                    node = node.previous();
                }
                return path;
            }
            for (MazeNode child : node.children()) {
                if (child.previous() == null) {
                    queue.enqueue(child);
                    child.previous(node);
                    count++;
                }
            }
        }
        // no path found
        return null;
    }
}
