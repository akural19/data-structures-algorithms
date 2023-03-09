package crashtable;

import java.util.ArrayList;

public class CrashTable {

	int size = 8;
	EntryNode[] table = new EntryNode[size];
	final int MAX_CONFLICTS = 10;
	
	private static class EntryNode {
		String key;
		Object value; 
		EntryNode next;
		int numElements;
		
		EntryNode(String key, Object value) {
			this.key = key;
			this.value = value;
			this.next = null;
			this.numElements = 0;
		}
	}
	 
	private int hash(String key) {
		
		char[] charArray = key.toCharArray();
		int hash = 0; 

		for (int ii = 0; ii < charArray.length; ii++) {	
			hash += (charArray[ii] * Math.pow(89, (charArray.length - (1 + ii))) % size);
		}
		return hash % size;
	}
	
	public Object get(String key) {
		
		int index = hash(key);
		EntryNode node;
		if (index >= size || table[index] == null) {
			return null;
		}
		node = table[index];
		do {
			if (node.key.equals(key)) {
				break;
			}
			node = node.next;
		}
		while (node != null);
		return node;
	}
	
	public Object get(String key, Object def) {
		
		int index = hash(key);
		EntryNode node = table[index];
		
		while (node != null) {
			
			if (node.key.equals(key)) {
				break;
			}
			node = node.next;
		}

		if (node == null) {
			return def;
		}
		return node;
	}
	public Object put(String key, Object value) {
		
		EntryNode node = (EntryNode) get(key);
		
		if (node != null) {
			Object prevValue = node.value;
			node.value = value;
			return prevValue;
		}
		
		int index = hash(key);
		EntryNode newNode = new EntryNode(key, value);
		// insertFirst
		if (table[index] == null) {
			table[index] = newNode;
		}
		else {
			newNode.next = table[index];
			table[index] = newNode;	
			newNode.numElements = newNode.next.numElements;
		}
		
		newNode.numElements++;
		
		if (newNode.numElements == MAX_CONFLICTS) {
			size *= 2;
			resize(size);
		}
		
		return null;
	}
	
	public Object remove(String key) {
		
		int index = hash(key);
		EntryNode node = table[index];
		
		if (node.key.equals(key)) {
			if (node.numElements == 1) {
				table[index] = null;
			}
			else {
				table[index] = node.next;
				node.numElements--;
				node.next.numElements = node.numElements;
			}
			Object value = node.value;
			assignNull(node);
			return value;
		}
		else {
			while (node.next != null) {
				if (node.next.key.equals(key)) {
					EntryNode nextNode = node.next;
					node.next = nextNode.next;
					Object value = nextNode.value;
					table[index].numElements--;
					assignNull(nextNode);
					return value;
				}
				node = node.next;
			}
		}
		return null;
	}
	
	public String[] getKeys() {
		
		ArrayList<String> stringList = new ArrayList<>();
		
		for (int ii = 0; ii < size; ii++) {
			
			EntryNode node = table[ii];
			
			while (node != null) {
				stringList.add(node.key);
				node = node.next;
			}
		}
		
		String[] stringArray = new String[stringList.size()];
		stringArray = stringList.toArray(stringArray);
		
		return stringArray;
	}
	
	public void resize(int size) {
		
		EntryNode[] oldTable = table;
		table = new EntryNode[size];
		
		for (int ii = 0; ii < oldTable.length; ii++) {
			while (oldTable[ii] != null) {
				EntryNode node = oldTable[ii];
				put(node.key, node.value);
				oldTable[ii] = node.next;
			}
		}
	}
	
	public void assignNull(EntryNode node) {
		
		node.key = null;
		node.value = null;
		node.next = null;
		node.numElements = 0;
	}
	
	public void printTable() {
		
		for (int ii = 0; ii < size; ii++) {
			System.out.printf("LinkedList #%d\n", ii);
			EntryNode node = table[ii];
			while (node != null) {
				System.out.printf("-%-20s %s\n", node.key, node.value);
				node = node.next;
			}
		}
	}
}
