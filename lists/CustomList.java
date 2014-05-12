package lists;

public interface CustomList<T> {

    public void addToFront(T element);
    
    public void addToRear(T element);

  	 public void addAfter(T element, T target);
	 
	 public void addBefore(T element, T target);
	 
    public T removeFirst();

    public T removeLast();

    public T remove(T target);
	 
	 public void clear();
	 
	 public void moveUp(T target);
	 
	 public void moveDown(T target);

    public boolean contains(T target);
	 
    public void ascendingSort();
	 
	 public void descendingSort();
	 
	 public void slide(int amount);
	 
	 public void shuffle();
	 
	 public boolean isEmpty();

    public int size();
	 
	 @Override
	 public String toString();
}