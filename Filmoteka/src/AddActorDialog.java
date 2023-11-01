import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddActorDialog extends JDialog {
    private FilmLibrary library;
    private ActorTableModel tableModel;

    private JTextField nameField;

    public AddActorDialog(JFrame parent, FilmLibrary library, ActorTableModel tableModel) {
        super(parent, "Добавить актера", true);
        this.library = library;
        this.tableModel = tableModel;

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Имя:"));
        nameField = new JTextField();
        panel.add(nameField);

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addActor();
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

    private void addActor() {
        String name = nameField.getText();
        library.addActor(name);
        tableModel.updateData(library.getAllActors());

        dispose();
    }
}
