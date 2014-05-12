package lists;

public class DoubleNode<T> {

    private T content;
    private DoubleNode<T> next;
    private DoubleNode<T> previous;

    public DoubleNode() {
        content = null;
        next = null;
        previous = null;
    }

    public DoubleNode(T data) {
        content = data;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    public DoubleNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }
}
