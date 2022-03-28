package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {


	//	referencia al primer aux de la lista
	private DoubleNode<T> front;

	//	referencia al Último aux de la lista
	private DoubleNode<T> last;


	private class DoubleNode<T> {

		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}

		T elem;

		DoubleNode<T> next;
		DoubleNode<T> prev;
	}

	///// ITERADOR normal //////////

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIterator<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIterator(DoubleNode<T> aux) {
		}

		@Override
		public boolean hasNext() {
			// TODO
			return false;
		}
	

		@Override
		public T next() {
		// TODO
			return null;
		}
	}

	////// FIN ITERATOR



	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIteratorReverse(DoubleNode<T> aux) {
			// TODO	
			}

		@Override
		public boolean hasNext() {
			// TODO	
			return false;
			}

		@Override
		public T next() {
			// TODO
			return null;
		}
	}
	
	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorOddPositions<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIteratorOddPositions(DoubleNode<T> aux) {
			//TODO
		}

		@Override
		public boolean hasNext() {
			// TODO
			return false;
		}

		@Override
		public T next() {
			// TODO
			return null;
		}		
	}
	
	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorProgressReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
	// Anadir si hace falta mas atributos
		public DoubleLinkedListIteratorProgressReverse(DoubleNode<T> aux) {
			node = aux;
		}

		@Override
		public boolean hasNext() {
			//TODO
			return false;
		}

		@Override
		public T next() {
			//TODO
			
			return null;
		}
	}

	/////

	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		for (T elem:v) {
			this.addLast(elem);
		}
	}


	@Override
	public boolean isEmpty() {
		//TODO
		return false;
		}


	@Override
	public void clear() {
		//TODO
		
	}

	@Override
	public void addFirst(T elem) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addLast(T elem) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addPos(T elem, int position) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addBefore(T elem, T target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getElemPos(int position) {
		
		//TODO
		return null;
	}


	@Override
	public int getPosFirst(T elem) {
		//TODO

		return 0;
	}


	@Override
	public int getPosLast(T elem) {
		//TODO

		return 0;
	}

	
	@Override
	public T removeLast()  throws EmptyCollectionException{
		//TODO
		return null;
	}
	

	@Override
	public T removePos(int pos)  throws EmptyCollectionException{
		// TODO
		return null;
	

	}


	@Override
	public int removeN(T elem, int times) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public T removeSecond() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DoubleList<T> copy() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean contains(T elem) {
		//TODO
		return false;
	}


	@Override
	public int size() {
		//TODO
		return 0;
	}


	@Override
	public String toStringReverse() {
		// TODO
		return "";
	}


	@Override
	public int maxRepeated() {
	// TODO
		return 0;
	}


	@Override
	public boolean sameContent(DoubleList<T> other) {
		// TODO 
		return false;
	}



	@Override
	public String toStringFromUntil(int from, int until) {
		// TODO
				
		return null;
	}

	@Override
	public String toString() {
		// TODO
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return new DoubleLinkedListIterator<>(front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DoubleLinkedListIteratorReverse<>(last);
	}
	

	@Override
	public Iterator<T> oddPositionsIterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterator<T> progressReverseIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	

	



	@Override
	public String toStringFromUntilReverse(int from, int until) {
		// TODO Auto-generated method stub
		return null;
	}


	




}