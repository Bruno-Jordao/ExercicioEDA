package lista.generica;

/**
 * Lista duplamente encadeada com Nó descritor
 * 
 * @param <T>
 */
public class DoublyLinkedList<T> implements List<T>{
	//Nó descritor
	private SentinelNo<T> sentinelNo;

	public DoublyLinkedList() {
		sentinelNo = new SentinelNo<T>();
	}

	@Override
	public void insertEnd(T e) {
		No<T> no = new No<T>();
		no.setData(e);

		if(sentinelNo.getSize() > 0) {
			no.setPrev(sentinelNo.getEnd());
			sentinelNo.getEnd().setNext(no);
		}

		sentinelNo.setEnd(no);
		sentinelNo.addSize();

		if (size() == 1) {
			sentinelNo.setBegin(no);
		}
	}

	@Override
	public void insertBegin(T e) {
		No<T> no = new No<T>();
		no.setData(e);

		if(sentinelNo.getSize() > 0) { 
			sentinelNo.getBegin().setPrev(no);
			no.setNext(sentinelNo.getBegin());
		}
		sentinelNo.setBegin(no);
		sentinelNo.addSize();

		if (size() == 1) {
			sentinelNo.setEnd(no);
		}
	}

	@Override
	public void print() {
		No<T> temp = sentinelNo.getBegin();
		while (temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getNext();
		}
		System.out.println();
	}

	@Override
	public int size() {
		return sentinelNo.getSize();
	}

	public void insert(T e, int i) {
		if (i < 0 || i > size()) {
			throw new IndexOutOfBoundsException("Índice fora dos limites.");
		}

		No<T> no = new No<T>();
		no.setData(e);

		if (i == 0) {
			insertBegin(e);
		} else if (i == size()) {
			insertEnd(e);
		} else {
			No<T> temp = sentinelNo.getBegin();
			for (int j = 0; j < i - 1; j++) {
				temp = temp.getNext();
			}

			no.setNext(temp.getNext());
			no.setPrev(temp);
			temp.getNext().setPrev(no);
			temp.setNext(no);
			sentinelNo.addSize();
		}
	}
	
	public T removeBegin() {
		if (size() == 0) {
			throw new RuntimeException("A lista está vazia.");
		}

		No<T> temp = sentinelNo.getBegin();
		sentinelNo.setBegin(temp.getNext());

		if (sentinelNo.getBegin() != null) {
			sentinelNo.getBegin().setPrev(null);
		} else {
			sentinelNo.setEnd(null);
		}

		sentinelNo.subtractSize();
		return temp.getData();
	}

	public T removeEnd() {
		if (size() == 0) {
			throw new RuntimeException("A lista está vazia.");
		}

		No<T> temp = sentinelNo.getEnd();
		sentinelNo.setEnd(temp.getPrev());

		if (sentinelNo.getEnd() != null) {
			sentinelNo.getEnd().setNext(null);
		} else {
			sentinelNo.setBegin(null);
		}

		sentinelNo.subtractSize();
		return temp.getData();
	}
}