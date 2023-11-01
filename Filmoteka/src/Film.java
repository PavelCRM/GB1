import java.io.Serializable;

public class Film implements Serializable {
    private int id;
    private String title;
    private int year;
    private int actorId;
    private String studio;
    private int genreId;

    #Геттеры и сеттеры

    @Override
    public String toString() {
        return title + " (" + year + "), Актер ID: " + actorId + ", Студия: " + studio + ", Жанр ID: " + genreId;
    }
}
