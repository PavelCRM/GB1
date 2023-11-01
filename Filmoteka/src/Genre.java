import java.io.Serializable;

public class Genre implements Serializable {
    private int id;
    private String name;

    // Геттеры и сеттеры

    @Override
    public String toString() {
        return name;
    }
}
