import javax.swing.table.*;
import java.util.List;

public class FilmTableModel extends AbstractTableModel {
    private List<Film> films;
    private String[] columnNames = {"ID", "Название", "Год", "ID актера", "Студия", "ID жанра"};

    public FilmTableModel(List<Film> films) {
        this.films = films;
    }

    public int getRowCount() {
        return films.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int row, int col) {
        Film film = films.get(row);
        switch (col) {
            case 0:
                return film.getId();
            case 1:
                return film.getTitle();
            case 2:
                return film.getYear();
            case 3:
                return film.getActorId();
            case 4:
                return film.getStudio();
            case 5:
                return film.getGenreId();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

    public void updateData(List<Film> newFilms) {
        films = newFilms;
        fireTableDataChanged();
    }
}
