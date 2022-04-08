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
		if(elem == null) {
			throw new NullPointerException();
		}else if(target == null) {
				throw new NullPointerException();
		}else if(contains(target)) {
			throw new NoSuchElementException();
		}else {
			DoubleNode<T> nuevo = new DoubleNode<T>(elem);
			DoubleNode<T> aux;
			aux = front;
			if(front.equals(target)) {
				addFirst(elem);
			}else {
				while(!aux.elem.equals(target) && aux.next != null) {
					aux = aux.next;
				}
				if(aux.next == null) {
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
	public T getElemPos(int position) {
		DoubleNode<T> aux;
		if(1>position || position>size()) {
			throw new IllegalArgumentException();
		}else {
			int vuelta = position-1;
			aux = front;
			while(aux.next != null && vuelta != 0) {
				aux = aux.next;
				vuelta--;
			}
		}
		return aux.elem;
	}


	@Override
	public int getPosFirst(T elem) {
		int pos = 1;
		DoubleNode<T> aux;
		if(elem == null) {
			throw new NullPointerException();
		}else {
			if(!contains(elem)) {
				throw new NoSuchElementException();
			}
			aux = front;
			while(!aux.elem.equals(elem)) {
				aux=aux.next;
				pos++;
			}
		}
		return pos;
	}


	@Override
	public int getPosLast(T elem) {
		int pos = size();
		DoubleNode<T> aux;
		if(elem == null) {
			throw new NullPointerException();
		}else {
			if(!contains(elem)) {
				throw new NoSuchElementException();
			}
			aux = last;
			while(!aux.elem.equals(elem)) {
				aux = aux.prev;
				pos--;
			}
		}
		return pos;
	}

	
	@Override
	public T removeLast()  throws EmptyCollectionException{
		DoubleNode <T> aux;
		if(isEmpty()) {
			throw new EmptyCollectionException("");
		}else if(size() == 1) {
			aux = last;
			front = null;
			last = null;
			return aux.elem;
		}else {
			aux = last;
			last.prev.next = null;
			last = last.prev;
			return aux.elem;
		}
	}
	

	@Override
	public T removePos(int pos)  throws EmptyCollectionException{
		DoubleNode <T> current,previous,result;
		if(isEmpty()) {
			throw new EmptyCollectionException("");
		}else if(1>pos || pos>size()) {
			throw new IllegalArgumentException();
		}else {
			if(pos == 1) {
				result = front;
				front.next.prev = null;
				front = front.next;
			}else if(pos == size()) {
				result = last;
				last.prev.next = null;
				last = last.prev;
			}else{
				previous = front;
				current = front.next;
				for(int i=1; i<pos ;i++) {
					previous = current;
					current = current.next;
				}
				result = current;
				current.next.prev = previous;
				previous.next = current.next;
				
			}
		}
		return result.elem;
	}


	@Override
	public int removeN(T elem, int times) throws EmptyCollectionException {
		DoubleNode <T> current,previous;
		int n = 0;
		int contador = times;
		if(elem == null) {
			throw new NullPointerException();
		}else if(times<1) {
			throw new IllegalArgumentException();
		}else if(isEmpty()) {
			throw new EmptyCollectionException("");
		}else if(!contains(elem)) {
			throw new NoSuchElementException();
		}else {
			if(front.elem.equals(elem)) {
				front.next.prev = null;
				front = front.next;
				contador--;
				n++;
			}else if(last.elem.equals(elem)) {
				last.prev.next = null;
				last = last.prev;
				contador--;
				n++;
			}else{
				previous = front;
				current = front.next;
				while(contador != 0 || current.next == null) {
					if(current.elem.equals(elem)) {
						current.next.prev = previous;
						previous.next = current.next;
						contador--;
						n++;
					}
					previous = current;
					current = current.next;
				}
				
			}
		}
		return n;
	}


	@Override
	public T removeSecond() throws EmptyCollectionException {
		DoubleNode<T> result;
		if(isEmpty()) {
			throw new EmptyCollectionException("");
		}else if(size()==1) {
			throw new NoSuchElementException();
		}else {
			result = front.next;
			front.next.next.prev = front;
			front.next = front.next.next;
		}
		return result.elem;
	}



	@Override
	public DoubleList<T> copy() {
		DoubleLinkedListImpl<T> copia = new DoubleLinkedListImpl<T>();
		DoubleNode<T> current,previous,currentC,previousC;
		if(isEmpty()) {
			return copia;
		}else if(size() == 1) {
			current = front;
			copia.front = current;
			copia.last = current;
			return copia;
		}else {
			current = front.next;
			previous = front;
			copia.front = previous;
			copia.front.next = current;
			copia.front.next.prev = previous;
			currentC = copia.front.next;
			previousC = copia.front;
			for(int i=1; i<size(); i++) {
				previous = current;
				current = current.next;
				previousC = currentC;
				currentC = current;
				copia.addLast(current.elem);
			}
		}
		return copia;
	}


	@Override
	public boolean contains(T elem) {
		DoubleNode<T> current;
		if(isEmpty()) {
			return false;
		}else if(elem == null) {
			throw new NullPointerException("");
		}else{
			if(size()==1) {
				if(front.elem.equals(elem)) {
					return true;
				}else {
					return false;
				}
			}else {
				for(int i=1; i<size(); i++) {
					if(elem.equals)
				}
			}
		}
	}


	@Override
	public int size() {
		int size = 1;
		DoubleNode <T> aux;
		aux = front;
		if(front == null) {
			return 0;
		}else {
			while(aux.next != null) {
				size++;
			}
		return size;
		}
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