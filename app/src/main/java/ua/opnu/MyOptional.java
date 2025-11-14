package ua.opnu;

public class MyOptional<T> {

    private T value;
    private boolean present;

    public MyOptional() {
        this.value = null;
        this.present = false;
    }

    public MyOptional(T value) {
        if (value == null) {
            throw new IllegalArgumentException("MyOptional не приймає null");
        }
        this.value = value;
        this.present = true;
    }

    public boolean isPresent() {
        return present;
    }

    public boolean isEmpty() {
        return !present;
    }

    public T get() {
        if (!present) {
            throw new IllegalStateException("Не можна викликати get() для порожнього MyOptional");
        }
        return value;
    }

    public T orElse(T defaultValue) {
        if (present) {
            return value;
        } else {
            return defaultValue;
        }
    }

    @Override
    public String toString() {
        if (present) {
            return "MyOptional[value=" + value + "]";
        } else {
            return "MyOptional[empty]";
        }
    }
}
