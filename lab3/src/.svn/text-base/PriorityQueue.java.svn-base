package lab3;

import java.util.ArrayList;

/**
 * A MINIMUM priority queue (binary heap) class supporting operations needed for
 * Dijkstra's algorithm.
 */
class PriorityQueue<T> {
	ArrayList<PQNode<T>> q;

	/**
	 * Constructor
	 */
	public PriorityQueue() {
		q = new ArrayList<PQNode<T>>();
	}

	/**
	 * @return true iff the queue is empty.
	 */
	public boolean isEmpty() {
		return q.isEmpty();
	}

	/**
	 * Insert a (key, value) pair into the queue.
	 *
	 * @return a Handle to this pair so that we can find it later.
	 */
	Handle insert(int key, T value) {
		Handle h = new Handle(q.size());
		PQNode<T> pqn = new PQNode<T>(h,(int)Double.POSITIVE_INFINITY,value);
		q.add(pqn);
		decreaseKey(h,key);
		return h;
	}

	/**
	 * @return the min key in the queue.
	 */
	public int min() {
		return q.get(0).getKey();
	}

	/**
	 * Find and remove the smallest (key, value) pair in the queue.
	 *
	 * @return the value associated with the smallest key
	 */
	public T extractMin() {
		if(this.isEmpty()) {
			System.err.println("Cannot extract minimum because the Priority Queue is empty.");
		}
		PQNode<T> min = q.get(0);
		swap(0,q.size()-1);
		q.get(q.size()-1).getHandle().invalidate();
		q.remove(q.size()-1);
		heapify(0);
		return min.getValue();
	}


	/**
	 * Decrease the key to the newKey associated with the Handle.
	 *
	 * If the pair is no longer in the queue, or if its key <= newKey,
	 * do nothing and return false.  Otherwise, replace the key with newkey,
	 * fix the ordering of the queue, and return true.
	 *
	 * @return true if we decreased the key, false otherwise.
	 */
	public boolean decreaseKey(Handle h, int newKey) {
		if (!h.isValid()||handleGetKey(h)<=newKey){
			return false;
		}
		PQNode<T> pqn = q.get(h.getIndex());
		pqn.setKey(newKey);
		while(h.getIndex()>0 && q.get(parent(h.getIndex())).getKey()>q.get(h.getIndex()).getKey()){
			swap(h.getIndex(), parent(h.getIndex()));
		}
		return true;
	}

	/**
	 * @return the key associated with the Handle.
	 */
	public int handleGetKey(Handle h) {
		if(h.isValid()) {
			return q.get(h.getIndex()).getKey();
		}
		return (int)Double.NEGATIVE_INFINITY;
		
	}

	/**
	 * @return the value associated with the Handle.
	 */
	public T handleGetValue(Handle h) {
		if(h.isValid()) {
			return q.get(h.getIndex()).getValue();
		}
		return null;
	}

	/**
	 * the heapify helper method. fixes as most one node in the heap using a recursive call
	 * @param index
	 */
	private void heapify(int index) {
		int smallest;
		int l = this.lChild(index);
		int r = this.rChild(index);
		if(l>q.size()-1 || r>q.size()-1) {
			return;
		}
		if(q.get(l).getKey()<q.get(r).getKey()) {
			smallest=l;
		} else {
			smallest=r;
		}
		swap(index,smallest);
		heapify(smallest);
	}

	/**
	 * Print every element of the queue in the order in which it appears
	 * (i.e. the array representing the heap)
	 */
	public String toString() {
		return q.toString();
		//return treePrint(0,0);
	}

	//Convenience methods below
	
	/**
	 * prints the priority queue in tree form. Leftmost node is root. 
	 * @param curr
	 * @param level
	 * @return
	 */
	private String treePrint(int curr, int level) {
		if (curr>q.size()-1) return "";
		String tabber = "";
		for (int i=0;i<level; i++) tabber+="\t";
		return treePrint(rChild(curr),level+1)
				+tabber+q.get(curr).getKey()+","+q.get(curr).getValue()+"\n"
				+treePrint(lChild(curr), level+1);
	}

	/**
	 * swaps two nodes in the priority queue, fixing their handles appropriately
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		PQNode<T> n1 = q.get(i);
		PQNode<T> n2 = q.get(j);
		n1.getHandle().set(j);
		n2.getHandle().set(i);
		q.set(i,n2);
		q.set(j,n1);
	}

	/**
	 * clearly shows when I am calling for the left Child anywhere in this class
	 * @param i
	 * @return
	 */
	private int lChild(int i) {
		return  2*i+1;
	}

	/**
	 * clearly shows when I am calling for the right Child anywhere in this class
	 * @param i
	 * @return
	 */
	private int rChild(int i) {
		return 2*i+2;
	}

	/**
	 * clearly shows when I am calling for the parent node anywhere in this class
	 * @param i
	 * @return
	 */
	private int parent(int i) {
		return (i-1)/2;
	}

}

