
/**
 * classname: Counter.
 */
public class Counter {
    private int counter;

    /**
     * A constructor.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * A constructor with input.
     * @param c the counter.
     */
    public Counter(int c) {
        this.counter = c;
    }
    /**
     * This function adds a number to the current count.
     * @param number the number.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * This function subtracts a number to the current count.
     * @param number the number.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * This function returns the current count.
     * @return the counter.
     */
    public int getValue() {
        return this.counter;

    }
}
