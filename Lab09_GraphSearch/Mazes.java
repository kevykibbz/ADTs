import java.util.Scanner;
import java.io.File;
import java.util.Hashtable;

/** Create a graph representing a maze. */
public class Mazes {

	/** Create the graph and return the node representing the maze entrance */
	public static MazeNode loadMaze(String fname) {
		// Create all nodes, placing in hash tables
		Hashtable<String,MazeNode> nodes = new Hashtable<>();
		MazeNode entrance = null;

		try {
			Scanner myReader = new Scanner(new File(fname));

			// First line has Start, Exit, Max Node Value (a char)
			String line = myReader.nextLine();
			String[] values = line.split(" ");

			Integer nodeCount = Integer.valueOf(values[2]);

			for (int i=0; i<nodeCount; i++) {
				int c;
				String val;
				int idx = i%26;
				c = 'A' + idx;
				val = String.valueOf((char)c);
				if (i>=26) {
					val = "A" + val;
				}
				nodes.put(val, new MazeNode(String.valueOf(val)));
			}
			System.out.println();
			// Print them if you like
			/* 
        	for(String key: nodes.keySet()){
				System.out.print(key+",");
			}
			System.out.println();
			*/
			

			// refer back to first line of text file to set the entrance and exit nodes
			entrance = nodes.get(values[0]);
			nodes.get(values[1]).setExit(true);

			// Build the maze from the remaining lines in the file
			while (myReader.hasNextLine()) {
				line = myReader.nextLine();
				values = line.split(" ");
				//System.out.println("Adding "+line+" to the maze");
				MazeNode current = nodes.get(values[0]);
				//System.out.print("add "+current.value()+" children ");
				for (int i=1; i<values.length; i++) {
					//System.out.print(nodes.get(values[i]).value()+" ");
					current.addChild(nodes.get(values[i]));
				}
				//System.out.println();
			} // end while
			myReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entrance;
	}

} // end class Mazes
