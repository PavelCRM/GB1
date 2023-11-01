import javax.swing.table.*;
import java.util.List;

public class ActorTableModel extends AbstractTableModel {
    private List<Actor> actors;
    private String[] columnNames = {"ID", "Имя"};

    public ActorTableModel(List<Actor> actors) {
        this.actors = actors;
    }

    public int getRowCount() {
        return actors.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int row, int col) {
        Actor actor = actors.get(row);
        switch (col) {
            case 0:
                return actor.getId();
            case 1:
                return actor.getName();
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

    public void updateData(List<Actor> newActors) {
        actors = newActors;
        fireTableDataChanged();
    }
}
