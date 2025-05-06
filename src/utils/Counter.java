// 325516193 Cathrine Abu-Elazam
package arkanoid.utils;

/**
 * The counter class is in charge of doing regular counting tasks.
 * It has a value that can be increased, decreased and being returned.
 */
public class Counter {
    private int count = 0;

    /**
     * add number to current count.
     *
     * @param number the number we add
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number we subtract
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * get current count.
     *
     * @return count
     */
    public int getValue() {
        return count;
    }
}