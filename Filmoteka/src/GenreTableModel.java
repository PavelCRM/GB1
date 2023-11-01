import javax.swing.table.*;
import java.util.List;

public class GenreTableModel extends AbstractTableModel {
    private List<Genre> genres;
    private String[] columnNames = {"ID", "Название"};

    public GenreTableModel(List<Genre> genres) {
        this.genres = genres;
    }

    public int getRowCount() {
        return genres.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int row, int col) {
        Genre genre = genres.get(row);
        switch (col) {
            case 0:
                return genre.getId();
            case 1:
                return genre.getName();
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

    public void updateData(List<Genre> newGenres) {
        genres = newGenres;
        fireTableDataChanged();
    }
}
