package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.setNext(tail);
		tail.setPrev(head);
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> node = new LLNode<>(element);
		node.setPrev(tail.getPrev());
		tail.getPrev().setNext(node);
		tail.setPrev(node);;
		node.setNext(tail);
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		System.out.println(index + " " +size);
		if (size == 0 || index > size-1 || index < 0)
			throw new IndexOutOfBoundsException();
		LLNode<E> loop = head;
		for(int i = 0; i<=index; i++){
			loop = loop.getNext();
			}
		return loop.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
		LLNode<E> loop = head;
		for(int i = 0; i<= index; i++){
			loop = loop.getNext();
			}
		LLNode<E> node = new LLNode<>(element);
		node.setPrev(loop.getPrev());
		loop.getPrev().setNext(node);
		loop.setPrev(node);;
		node.setNext(loop);
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (size == 0 || index > size-1 || index < 0)
			throw new IndexOutOfBoundsException();
		LLNode<E> obj = head.getNext();
		for(int i = 0; i<index; i++){
			obj = obj.getNext();
			}
		obj.getNext().setPrev(obj.getPrev());
		obj.getPrev().setNext(obj.getNext());
		size--;
		return obj.getData();
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (size == 0 || index > size-1 || index < 0)
			throw new IndexOutOfBoundsException();
		LLNode<E> loop = head;
		for(int i = 0; i<= index; i++){
			loop = loop.getNext();
			}
		loop.setData(element);
		return element;
	}
	public static void main() {
		MyLinkedList<Integer> linkTest = new MyLinkedList<Integer>();
		linkTest.get(0);
	}
	
	public void print(){
		LLNode<E> loop = head;
		for (int i = 0; i<size;i++) {
			loop = loop.getNext();
			System.out.printf(loop.data.toString() + " ");
		}
		System.out.println();
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	public LLNode<E> getNext() {
		return next;
	}

	public void setNext(LLNode<E> next) {
		this.next = next;
	}
	public LLNode<E> getPrev() {
		return prev;
	}
	public void setPrev(LLNode<E> prev) {
		this.prev = prev;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}

}
