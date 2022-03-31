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
		DoubleNode<T> node , paux;
		private int progress = 1;
	// Anadir si hace falta mas atributos
		public DoubleLinkedListIteratorProgressReverse(DoubleNode<T> aux) {
			node = aux;
		}

		@Override
		public boolean hasNext() {
			paux = node;
			for(int i = 0; i <= progress; i++) {
				paux=paux.prev;
			}
			return (paux !=null);
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
		return (front == null && last == null);
		}


	@Override
	public void clear() {
		DoubleNode<T> aux;
		aux = null;
		front = aux;
		last = aux;
	}

	@Override
	public void addFirst(T elem) {
		if(elem == null) {
			throw new NullPointerException("");
		}
		else if(isEmpty()) {
			DoubleNode<T> node = new DoubleNode<T>(elem);
			front = node;
			last = node;
			}else {
				DoubleNode<T> w = new DoubleNode<T>(elem);
				w.next = front;
				front.prev = w;
				front = w;
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
			front = node;
			}else {
				DoubleNode<T> w = new DoubleNode<T>(elem);
				w.prev = last;
				last.next = w;
				last = w;
			}		
	}


	@Override
	public void addPos(T elem, int position) {
		int vuelta = position-1;
		if(elem == null) {
			throw new NullPointerException();
		}else if(position <= 0) {
			throw new IllegalArgumentException();
		}else {
			DoubleNode<T> nuevo = new DoubleNode<T>(elem);
			DoubleNode<T> aux;
			if(position == 1) {
				addFirst(elem);
			}else {
				aux = front;
				while(aux.next != null && vuelta != 0) {
					aux = aux.next;
					vuelta--;
				}if(aux.next == null && vuelta !=0) {
					addLast(elem);
				}else {
					aux.prev.next = nuevo;
					nuevo.prev = aux.prev;
					nuevo.next = aux;
					aux.prev = nuevo;
				}	
			}
		}
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
		StringBuffer salida = new StringBuffer("");
		salida.append("(");
		DoubleNode<T> aux;
		aux = front;
		while(aux.next != null) {
			salida.append(aux.elem + " ");
			aux = aux.next;
		}
		salida.append(aux.elem + " )");
		return salida.toString();
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