
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		String[] mazes = {"maze1.txt","maze2.txt","maze3.txt"};

		for (String fname: mazes) {
			System.out.println("============= "+fname+" ==================");

			System.out.println("__________________________________");
			try {
				testSolve(fname,"bfs");
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("__________________________________");
			try {
				testSolve(fname,"dfs");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void testSolve(String fname, String queue) {

		System.out.println("Solving "+fname+" using "+queue);

		MazeNode mazeStart = Mazes.loadMaze(fname);

		ArrayList<MazeNode> path = MazeSolver.solve(mazeStart,queue);
		String pathString = "";
		for (MazeNode n: path) {
			pathString = n.value() + " "+pathString;
		}
		System.out.println("PATH "+pathString);
		System.out.println("COUNT "+MazeSolver.count());		
	} // end main
} // end class Main
