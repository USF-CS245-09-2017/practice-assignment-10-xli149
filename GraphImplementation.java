import java.util.*;


public class GraphImplementation implements Graph {
	//Data members
	private int[][] adjmatrix;
	private int vertices;
	
	/**Constructs and returns a graph with the number of vertices passed as the
	 * argument. Vertices have IDs, numbered 0, 1, …, vertices-1. No edges exist
	 * between vertices at instantiation
	 * @param vertices the number of vertice.
	 */

	public GraphImplementation(int vertices) {
		this.vertices = vertices;
		adjmatrix = new int[vertices][vertices];
	}

	/**Function for adding a directed edge between two vertices from src to tar. Default weight is
	 * 1.
	 * @param v1: The index of the src in the adjcent matrix
	 * @param v1L the index of the tar int the adjcent matrix
	 */

	public void addEdge(int v1, int v2) {
		adjmatrix[v1][v2] = 1;
	}
	/** Function for sorting the vertices in topological way. It has
	 * and incident array to hold the number of directed edges by that
	 * points to that verticessumming up through colums . And it picks 
	 * the first index of that array with content "0" add it to and arrasylist 
	 * and find its neigbors and subtract 1 from them then update 
	 * the content in incident array. Then repeat the procedure above until finishing 
	 * all the vertices
	 */
	public List<Integer> topologicalSort(){
		List<Integer> schedule = new ArrayList<>();
		int[] incident = new int[vertices];
		int colwise = 0;
		for(int i = 0; i < vertices; i++){
			incident[i] = 0;
		}
		for(int i = 0; i < vertices; i++){
			for(int j = 0; j < vertices; j++){
				colwise += adjmatrix[j][i];
			}
			incident[i] = colwise;
			colwise = 0;
		}

		for(int i = 0; i < vertices; i++){
			for (int j = 0; j < vertices ; j++ ) {
				if(incident[j] ==0){
					int[] neighbors = neighbors(j);
					for (int k = 0; k < neighbors.length; k++) {
						incident[neighbors[k]] -= 1;
					}
					schedule.add(j);
					incident[j] = -1;
				}
			}
		}
		
		return schedule;
	}
/** Function for returning back the neighbors of a vertice, it 
 * it check through the row of adjmatrix identified as a specific 
 *vertix and add the the index of col which has "1" on that position
 * and return back the array.
 *@param vertex: the vertex to be used to get its neighbors
 */ 
	public int[] neighbors(int vertex){
		
		
		ArrayList<Integer> list = new ArrayList<>(); 
		for (int i = 0; i < vertices; i++) {
			
			if(adjmatrix[vertex][i] == 1){
				list.add(i);
			}
		}
		int size = list.size();
		int[] array = new int[size];
		for(int i = 0; i < size; i++){

			array[i] = list.get(i);
		}
		return array;

	}

}