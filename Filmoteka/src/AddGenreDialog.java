import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddGenreDialog extends JDialog {
    private FilmLibrary library;
    private GenreTableModel tableModel;

    private JTextField nameField;

    public AddGenreDialog(JFrame parent, FilmLibrary library, GenreTableModel tableModel) {
        super(parent, "Добавить жанр", true);
        this.library = library;
        this.tableModel = tableModel;

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Название:"));
        nameField = new JTextField();
        panel.add(nameField);

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addGenre();
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

    private void addGenre() {
        String name = nameField.getText();
        library.addGenre(name);
        tableModel.updateData(library.getAllGenres());

        dispose();
    }
}
