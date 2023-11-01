import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddFilmDialog extends JDialog {
    private FilmLibrary library;
    private FilmTableModel tableModel;

    private JTextField titleField, yearField, actorIdField, studioField, genreIdField;

    public AddFilmDialog(JFrame parent, FilmLibrary library, FilmTableModel tableModel) {
        super(parent, "Добавить фильм", true);
        this.library = library;
        this.tableModel = tableModel;

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Название:"));
        titleField = new JTextField();
        panel.add(titleField);
        panel.add(new JLabel("Год:"));
        yearField = new JTextField();
        panel.add(yearField);
        panel.add(new JLabel("ID актера:"));
        actorIdField = new JTextField();
        panel.add(actorIdField);
        panel.add(new JLabel("Студия:"));
        studioField = new JTextField();
        panel.add(studioField);
        panel.add(new JLabel("ID жанра:"));
        genreIdField = new JTextField();
        panel.add(genreIdField);

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFilm();
            }
        });

        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void addFilm() {
        String title = titleField.getText();
        int year = Integer.parseInt(yearField.getText());
        int actorId = Integer.parseInt(actorIdField.getText());
        String studio = studioField.getText();
        int genreId = Integer.parseInt(genreIdField.getText());

        library.addFilm(title, year, actorId, studio, genreId);
        tableModel.updateData(library.getAllFilms());

        dispose();
    }
}
