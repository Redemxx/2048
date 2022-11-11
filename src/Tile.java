public class Tile {
    private int value;

    public Tile() {
        value = 2;
    }

    public Tile(int value) {
        setValue(value);
    }

    /**
     * Returns the current value of the tile.
     * @return int value of the tile.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the tile.
     * @param value int value to set.
     * @throws IllegalArgumentException if value is not a multiple of 2.
     */
    public void setValue(int value) {
        if (power2(value))
            this.value = value;
        else
            throw new IllegalArgumentException();
    }

    /**
     * Recursively finds if a value is a multiple of 2
     * @param value Double value to check.
     * @return Boolean; true if a multiple of 2, false if not.
     */
    public static boolean power2(double value) {
        if (value == 2)
            return true;
        if (value < 2)
            return false;
        value = value / 2;
        return power2(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
