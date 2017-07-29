package lab3;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Compute shortest paths in a graph.
 *
 * Your constructor should compute the actual shortest paths and
 * maintain all the information needed to reconstruct them.  The
 * returnPath() function should use this information to return the
 * appropriate path of edge ID's from the start to the given end.
 *
 * Note that the start and end ID's should be mapped to vertices using
 * the graph's get() function.
 */
class ShortestPaths {
	int startId;
	Edge[] edges; // index of each edge represents the vertex it goes to

	/**
	 * implementation of Dijkstra's shortest paths
	 */
	public ShortestPaths(Multigraph G, int startId) {
		this.startId = startId;
		this.edges = new Edge[G.nVertices()];
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		Handle[] hs = new Handle[G.nVertices()];
		int[] ds = new int[G.nVertices()];

		//set all the distances to positive infinity
		for(int i=0;i<G.nVertices();++i){
			hs[i] = q.insert((int)Double.POSITIVE_INFINITY, G.get(i));
			ds[i] = (int)Double.POSITIVE_INFINITY;
		}
		//set the start distance equal to 0
		ds[startId]= 0;
		q.decreaseKey(hs[startId], 0);

		//relax everything
		while(!q.isEmpty()) {
			Vertex curr = q.extractMin();
			if(ds[curr.id()]==(int)Double.POSITIVE_INFINITY) {
				break;
			}
			Vertex.EdgeIterator ei = curr.adj();
			while(ei.hasNext()) {
				Edge e = ei.next();
				Vertex v = e.to();
				if(ds[v.id()]>(ds[curr.id()]+e.weight())) {
					ds[v.id()] = ds[curr.id()]+e.weight();
					q.decreaseKey(hs[v.id()],ds[v.id()]);
					edges[e.to().id()] = e; //save edge to find path later
				}
			}
		}
	}

	/**
	 * Calculates the list of edge ID's forming a shortest path from the start
	 * vertex to the specified end vertex.
	 *
	 * @return the array
	 */
	public int[] returnPath(int endId) { 
		//get IDs from end to beginning
		LinkedList<Integer> reverseIDs = new LinkedList<Integer>();
		for(int i=endId;i!=startId;i=edges[i].from().id()) {
			if(edges[i]==null) {
				break;
			}
			reverseIDs.add(edges[i].id());
		}
		//put in correct order and return
		int size = reverseIDs.size();
		int[] path = new int[size];
		for(int j=0;j<size;++j) {
			path[j]=reverseIDs.removeLast();
		}
		return path;
	}

}
