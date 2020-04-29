package fantasyBaseball;

public class LinkedList
{
	private Node head = null, tail = null;
	
	public void insert (Node n)
	{
		if (head == null)
		{
			head = n;
			tail = n;
		}
		else
		{
			tail.setNext(n);
			tail = n;
		}
	}
	
	public void printList()
	{
		Node rover = head;
		while (rover != null)
		{
			System.out.println(rover);
			rover = rover.getNext();
		}
	}
	
	public int size()
	{
		Node rover = head;
		int index = 0;
		while (rover != null)
		{
			rover = rover.getNext();
			index++;
		}
		return index;
	}
	
	public BaseBaller get(int index) {    // Access each element in LinkedList
		if (index < 0)            		  // The zeroth index is rank 1, and so on
			return null;
		
		Node current = null;
		if (head != null) {
			current = head;
			for (int i=0; i < index; i++) {
				if (current.getNext() == null) 
					return null;
				current = current.getNext();
			}
		}
		return current.getData();
	}
	
	public BaseBaller get (String name)
	{
		Node current = head;
		while (current != null)
		{
			String currentPlayerName = current.getData().getPlayername();
			if (currentPlayerName.equals(name))
				break;
			current = current.getNext();
		}
		if (current == null)
			return null;
		return current.getData();
	}
}
