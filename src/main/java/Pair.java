import java.util.Objects;

public class Pair<F, S> {

    private F  key;
    private S value;

    public Pair(F key, S Value) {
        this.key = key;
        this.value = Value;
    }

    public S getValue() {
        return value;
    }

    public void setValue(S value) {
        this.value = value;
    }

    public F getKey() {
        return key;
    }

    public void setKey(F key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
