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
			node = aux;
		}

		@Override
		public boolean hasNext() {
			return (node.next!=null);
		}
	

		@Override
		public T next() {
			 if (! hasNext())
					throw new NoSuchElementException();
				 	T result = node.elem;
					node = node.next;
					return result; 
		}
	}

	////// FIN ITERATOR



	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIteratorReverse(DoubleNode<T> aux) {
			node = aux;	
			}

		@Override
		public boolean hasNext() {
			return(node.prev != null);
			}

		@Override
		public T next() {
			if (! hasNext())
				throw new NoSuchElementException();
			 	T result = node.elem;
				node = node.prev;
				return result; 
		}
	}
	
	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorOddPositions<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIteratorOddPositions(DoubleNode<T> aux) {
			node = aux;
		}

		@Override
		public boolean hasNext() {
			return(node.next.next !=null);
		}

		@Override
		public T next() {
			if (! hasNext())
				throw new NoSuchElementException();
				T result = node.elem;
				node = node.next.next;
				return result; 
			}		
	}
	
	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorProgressReverse<T> implements Iterator<T> {
		DoubleNode<T> node , naux;
		private int progress = 1;
	// Anadir si hace falta mas atributos
		public DoubleLinkedListIteratorProgressReverse(DoubleNode<T> aux) {
			node = aux;
		}

		@Override
		public boolean hasNext() {
			naux = node;
			for(int i = 0; i <= progress; i++) {
				naux=naux.next;
			}
			return (naux !=null);
		}

		@Override
		public T next() {
			if (! hasNext())
				throw new NoSuchElementException();
				T result = node.elem;
				for(int i = 0; i <= progress; i++) {
					node=node.next;
				}
				progress++;
				return result;			
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
		return (front == null);
		}


	@Override
	public void clear() {
		DoubleNode<T> aux;
		aux = null;
		front = aux;
	}

	@Override
	public void addFirst(T elem) {
		if(elem == null) {
			throw new NullPointerException("");
		}
		else if(isEmpty()) {
			DoubleNode<T> node = new DoubleNode<T>(elem);
			front = node;
			}else {
				DoubleNode<T> w = new DoubleNode<T>(elem);
				DoubleNode<T> aux;
				aux = front.next;
				front = w;
				w.next = aux;
			}
		}	


	@Override
	public void addLast(T elem) {
		if(elem == null) {
			throw new NullPointerException("");
		}
		else if(isEmpty()) {
			DoubleNode<T> node = new DoubleNode<T>(elem);
			last = node;
			}else {
				DoubleNode<T> w = new DoubleNode<T>(elem);
				DoubleNode<T> aux;
				aux = last.prev;
				last = w;
				w.prev = aux;
			}		
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