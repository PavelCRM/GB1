import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmLibrary {
    Connection connection;

    public FilmLibrary() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:films.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS films (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, year INTEGER, actor_id INTEGER, studio TEXT, genre_id INTEGER)"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS actors (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS genres (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFilm(String title, int year, int actorId, String studio, int genreId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO films (title, year, actor_id, studio, genre_id) VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, actorId);
            preparedStatement.setString(4, studio);
            preparedStatement.setInt(5, genreId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addActor(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO actors (name) VALUES (?)"
            );
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGenre(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO genres (name) VALUES (?)"
            );
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM films");
            while (resultSet.next()) {
                Film film = new Film(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("actor_id"),
                        resultSet.getString("studio"),
                        resultSet.getInt("genre_id")
                );
                films.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    public List<Actor> getAllActors() {
        List<Actor> actors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM actors");
            while (resultSet.next()) {
                Actor actor = new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                actors.add(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM genres");
            while (resultSet.next()) {
                Genre genre = new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    public static void main(String[] args) {
        FilmLibrary library = new FilmLibrary();
        // Здесь можно использовать методы для добавления фильмов, актеров и жанров
    }
}
