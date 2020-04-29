package fantasyBaseball;

public class Node
{
	private BaseBaller data;
	Node next = null;
	
	public Node (BaseBaller data)
	{
		this.data = data;
	}
	
	public BaseBaller getData()
	{
		return data;
	}
	
	public void setNext(Node n)
	{
		this.next = n;
	}
	
	public Node getNext()
	{
		return next;
	}
	
	public boolean hasNext()
	{
		return next != null;
	}
	
	public String toString()
	{
		return data.toString();
	}
	
	
}
