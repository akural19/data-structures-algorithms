class Playlist {
    
	Node head = null;
    Node tail = null;
    int numElements = 0; 

    class Node {
        
    	Song data;
        Node next;
        Node prev;
           
        Node(Song d) {
        	
        	data = d;
            next = null;
            prev = null;
        }
    }

    public void insertToEnd(Song new_song) {
    	
    	try {
	    	if (new_song == null) {
	    		throw new NullPointerException("Null song entry");
	    	}
	    	
	    	Node newNode = new Node(new_song);
	    	numElements++; 
	    	
	    	if (head == null) {
	    		head = newNode;
	    		tail = newNode; 
	    		newNode.next = newNode;
	    		newNode.prev = newNode;
	    	}
	    	else {
	    		newNode.next = head; 
	    		newNode.prev = tail; 
	    		tail.next = newNode; 
	    		tail = newNode; 
	    		head.prev = tail; 
	    	}
    	}
    	catch (Exception exception) {
    	}
    }

    public void insertToIndex(Song new_song, int index) {
       
    	try {
    		
	    	if (new_song == null) {
	    		throw new NullPointerException("Null song entry");
	    	}
	    	else if (index > numElements + 1 || index < 1) {
	    		throw new ArrayIndexOutOfBoundsException("No valid input to insertToIndex method");
	    	}
	    	
	    	Node newNode = new Node(new_song);
	    	
	    	if (head == null) {
	    		head = newNode; 
	    		tail = newNode;
	    		newNode.next = newNode;
	    		newNode.prev = newNode;
	    	}
	    	else {
	    		Node current = head; 
	    
	    		for (int ii = 1; ii < ((index % numElements == 1) ? 1 : index); ii++) {
	    			current = current.next;
	    		}
	    		newNode.prev = current.prev;
	    		newNode.next = current; 
	    		current.prev = newNode; 
	    		newNode.prev.next = newNode;
	    	}
	    	numElements++;
	    	
	    	if (index == 1) {
	    		head = newNode;
	    	}
	    	else if (index == numElements + 1) {
	    		tail = newNode;
	    	}
    	}
    	catch (Exception exception){
    	}
    }

	public void removeSong(String song_name){
        
		try {
	    	if (song_name == null) {
	    		throw new NullPointerException("Null song name entry");
	    	}
	    	
	    	Node current = head;    	
	    	boolean flag = false;
	    	int index; 
	    	for (index = 1; index <= numElements; index++) {
	    		if (current.data.getName().equals(song_name)) {
	    			flag = true;
	    			break;
	    		}
	    		current = current.next;
	    	}
	    
	    	if (flag == true) {
	    		
	    		if (numElements == 1) {
	    			head = null;
	    			tail = null;
	    		}
	    		else {
		    		current.prev.next = current.next;
		    		current.next.prev = current.prev;
		    		if (index == 1) {
		        		head = current.next;
		        	}
		        	else if (index == numElements) {
		        		tail = current.prev;
		        	}
	    		}
	    		numElements--;
	    		current.prev = null;
	        	current.next = null;
	        	current.data = null;
	        }
		}
		catch (Exception exception) {
		}
   	}
    	
    public void move(String song_name, int move_num ) {
    	
    	try {
	    	if (song_name == null) {
	    		throw new NullPointerException("Null song name entry");
	    	}
	    	else if (move_num < 0) {
	    		throw new IllegalArgumentException("No valid input to move method");
	    	}
	  
	    	Node current = head; 
	    	boolean flag = false;
	    	int index;
	    	
	    	for (index = 1; index <= numElements; index++) {
	    		if (current.data.getName().equals(song_name)) {
	    			flag = true;
	    			break;
	    		}
	    		current = current.next;
	    	}
	    	
	    	if (flag == true) {
	    		if (move_num > index - 1) {
	    			throw new IllegalArgumentException("No valid input to move method");
	    		}
	    		for (int ii = 0; ii < move_num; ii++) {
	    			swapPrev(current);
	    		}
 	    	}
    	}
    	catch (Exception exception) {
    	}
    }

    public void reverseSequence(int first_ind, int second_ind){
    	
    	try {
	    	if (numElements == 0) {
	    		throw new ArrayIndexOutOfBoundsException("Operation on a null linked list");
	    	}
	    	
	    	if (first_ind < 1 || second_ind > numElements) {
	    		throw new ArrayIndexOutOfBoundsException("No valid input to reverseSequence method");
	    	}
	    	recursiveSwap(first_ind, second_ind);
    	}
    	catch (Exception exception) {
    	}
    }

    public void swapPrev(Node node) {
   
    	Node prevNode = node.prev;
    	Node nextNode = node.next; 
    	
    	node.prev = prevNode.prev;
    	prevNode.prev.next = node;
    	node.next = prevNode;
    	prevNode.prev = node;
    	nextNode.prev = prevNode;
    	prevNode.next = nextNode;
    	
    	if (head == prevNode) {
    		head = node;
    	}
    	if (tail == node) {
    		tail = prevNode;
    	}
    }
    
    public void recursiveSwap(int index1, int index2) {
    
    	if (index2 <= index1) {
    		return;
    	}
    	else {
    		Node node2 = findNode(index2);
    		int numSwap = index2 - index1;
    		for (int ii = 0; ii < numSwap; ii++) {
    			swapPrev(node2);
    		}
    		recursiveSwap(index1+1, index2);
    	}  	
    }
    
    public Node findNode(int index) {
    	
    	Node current = head; 
    	for (int ii = 1; ii < index; ii++) {
    		current = current.next;
    	}
    	return current;
    }
    
    public void displayList() {
        Node temp = head;

        if (temp == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        while (temp.next != head) {
            temp.data.displaySong();
            temp = temp.next;
        }
        temp.data.displaySong();
    }
}
