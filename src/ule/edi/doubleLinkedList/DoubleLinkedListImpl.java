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
			return (node!=null);
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
			return(node != null);
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
		boolean previous = true;
		public DoubleLinkedListIteratorOddPositions(DoubleNode<T> aux) {
			node = aux;
		}

		@Override
		public boolean hasNext() {
			if(size()==1) {
				if(node == null) {
					return false;
				}else {
					return true;
				}
			}else {
				if(node == null) {
					return false;
				}else if(node != last && node.next == null) {
					return false;
				}else if( previous == false && node.next.next == null) {
					return false;
				}else {
					return true;
				}
			}
		}

		@Override
		public T next() {
			if (! hasNext())
				throw new NoSuchElementException();
				T result = node.elem;
				if(size()==1) {
					node = null;
				}else {
					if(node == last) {
						node = null;
					}else {
					node = node.next.next;
					}
				}
				return result; 
			}		
	}
	
	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorProgressReverse<T> implements Iterator<T> {
		DoubleNode<T> node , paux;
		private int progress = 0;
	// Anadir si hace falta mas atributos
		public DoubleLinkedListIteratorProgressReverse(DoubleNode<T> aux) {
			node = aux;
		}

		@Override
		public boolean hasNext() {
			boolean hasnext = false;
			paux = node;
			if(isEmpty()) {
				
			}else {
				for(int i = 0; i < progress; i++) {
					if(paux != null && paux.prev != null) {
						paux = paux.prev;
					}
				}
				if(paux.prev != null) {
					hasnext = true;
				}
			}
			return hasnext;
		}

		@Override
		public T next() {
			if (! hasNext())
				throw new NoSuchElementException();
				T result = null;
				if(progress == 0) {
					progress++;
					result = node.elem;
				}else {
				for(int i = 0; i < progress; i++) {
					node=node.prev;
				}
				result = node.elem;
				progress++;
				}
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
		}else if(contains(target) == false) {
			throw new NoSuchElementException();
		}else {
			DoubleNode<T> nuevo = new DoubleNode<T>(elem);
			DoubleNode<T> aux;
			aux = front;
			if(front.elem.equals(target)) {
				addFirst(elem);
			}else {
				while(!aux.elem.equals(target) && aux.next != null) {
					aux = aux.next;
				}
				if(aux.next == null) {
					aux.prev.next = nuevo;
					nuevo.prev = aux.prev;
					nuevo.next = aux;
					aux.prev = nuevo;
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
			if(front == last) {
				result = front;
				clear();
			}else if(pos == 1) {
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
				for(int i=1; i<pos-1 ;i++) {
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
		}else if(size()==1) {
			clear();
			n=1;
		}else{
			if(front.elem.equals(elem)) {
				front.next.prev = null;
				front = front.next;
				contador--;
				n++;
			}
			if(last.elem.equals(elem)) {
				last.prev.next = null;
				last = last.prev;
				contador--;
				n++;
			}
			if(size() >1) {
				previous = front;
				current = front.next;
				while(contador != 0 && current.next != null) {
					if(current.elem.equals(elem)) {
						current.next.prev = previous;
						previous.next = current.next;
						contador--;
						n++;
						current = current.next;
					}else {
						previous = current;
						current = current.next;
					}
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
		}else if(size()==2) {
			result = front.next;
			front.next = null;
			last = front;
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
		DoubleNode<T> current;
		if(isEmpty()) {
			return copia;
		}else if(size() == 1) {
			current = front;
			copia.addLast(current.elem);
			return copia;
		}else {
			current = front;
			for(int i=0; i<size(); i++) {
				copia.addLast(current.elem);
				current = current.next;
			}
		}
		return copia;
	}


	@Override
	public boolean contains(T elem) {
		boolean contains = false;
		DoubleNode<T> current;
		if(isEmpty()) {
			contains = false;
		}else if(elem == null) {
			throw new NullPointerException("");
		}else{
			if(size()==1) {
				if(front.elem.equals(elem)) {
					contains = true;
				}else {
					contains = false;
				}
			}else {
				current = front;
				for(int i=0; i<size(); i++) {
					if(current.elem.equals(elem)) {
						contains = true;
					}
					current = current.next;
				}
			}
		}
		return contains;
	}


	@Override
	public int size() {
		int size = 0;
		if(isEmpty()) {
		}else {
			DoubleNode <T> aux;
			aux = front;
			while(aux.next != null) {
				size++;
				aux = aux.next;
			}
			size++;
		}
		return size;
	}


	@Override
	public String toStringReverse() {
		StringBuffer salida = new StringBuffer("");
		salida.append("(");
		DoubleNode<T> aux;
		if(isEmpty()) {
			salida.append(")");
		}else{
			aux = last;
			while(aux.prev != null) {
				salida.append(aux.elem + " ");
				aux = aux.prev;
			}
			salida.append(aux.elem + " )");
		}
		return salida.toString();
	}


	@Override
	public int maxRepeated() {
		DoubleNode<T> current, aux;
		current = front;
		int maxRep = 0;
		while(current != null) {
			int rep = 0;
			aux = current;
			while(current != null) {
				if(aux.elem.equals(current.elem)) {
					rep++;
				}
				current = current.next;
			}
			if(rep > maxRep) {
				maxRep = rep;
			}
		}
		return maxRep;
	}


	@Override
	public boolean sameContent(DoubleList<T> other) {
		DoubleNode<T> current;
		int n1 = 0,n2 = 0;
		current = front;
		if(other == null) {
			throw new NullPointerException();
		}else if(other.maxRepeated() != maxRepeated()) {
			return false;
		}else if(other.size() != size()) {
			return false;
		}else {
			while(current != null) {
				if(!other.contains(current.elem)) {
					return false;
				}else {
					for(int i=1; i <= other.size();i++) {
						if(other.getElemPos(i).equals(current.elem)) {
							n1++;
						}
					}
					for(int i=1; i <= size();i++) {
						if(getElemPos(i).equals(current.elem)) {
							n2++;
						}
					}
					if(n1 != n2) {
						return false;
					}
				}
				current = current.next;
			}
			return true;
		}
	}



	@Override
	public String toStringFromUntil(int from, int until) {
		StringBuffer salida = new StringBuffer("");
		if(from<0 || until<0 || until<from) {
			throw new IllegalArgumentException();
		}else if(from>=size()) {
			salida.append("()");
		}else {
			salida.append("(");
			DoubleNode<T> aux;
			aux = front;
			for(int i=0; i<from-1;i++) {
				aux = aux.next;
			}if(until >= size()) {
				while(aux.next != null) {
					salida.append(aux.elem + " ");
					aux = aux.next;
				}
				salida.append(aux.elem + " ");
			}else {
				for(int i=0;i<until-from;i++) {
					salida.append(aux.elem + " ");
					aux = aux.next;
				}
				salida.append(aux.elem + " ");
			}
			salida.append(")");
		}
		return salida.toString();
	}

	@Override
	public String toString() {
		StringBuffer salida = new StringBuffer("");
		salida.append("(");
		DoubleNode<T> aux;
		if(isEmpty()) {
			salida.append(")");
		}else{
			aux = front;
			while(aux.next != null) {
				salida.append(aux.elem + " ");
				aux = aux.next;
			}
			salida.append(aux.elem + " )");
		}
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
		return new DoubleLinkedListIteratorOddPositions<>(front);
	}


	@Override
	public Iterator<T> progressReverseIterator() {
		return new DoubleLinkedListIteratorProgressReverse<>(last);
	}

	

	



	@Override
	public String toStringFromUntilReverse(int from, int until) {
		StringBuffer salida = new StringBuffer("");
		if(from<0 || until<0 || until>from) {
			throw new IllegalArgumentException();
		}else if(from<1) {
			salida.append("()"); 
		}else {
			salida.append("(");
			DoubleNode<T> aux;
			aux = front;
			if(from >= size()) {
				aux = last;
			}else {
			for(int i=0; i<from-1;i++) {
				aux = aux.next;
			}
			}
			if(from >= size()) {
				if(until >= size()) {
					
				}else {
					for(int i=0;i<size()-until;i++) {
						salida.append(aux.elem + " ");
						aux = aux.prev;
					}
					salida.append(aux.elem + " ");
				}
			}else {
				for(int i=0;i<from-until;i++) {
					salida.append(aux.elem + " ");
					aux = aux.prev;
				}
				salida.append(aux.elem + " ");
			}
			salida.append(")");
		}
		return salida.toString();
	}


	




}