import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class FilmLibraryGUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTable filmsTable, actorsTable, genresTable;
    private FilmTableModel filmTableModel;
    private ActorTableModel actorTableModel;
    private GenreTableModel genreTableModel;
    private FilmLibrary library;

    public FilmLibraryGUI() {
        frame = new JFrame("Фильмотека");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        library = new FilmLibrary();

        filmTableModel = new FilmTableModel(library.getAllFilms());
        filmsTable = new JTable(filmTableModel);
        filmsTable.setAutoCreateRowSorter(true);

        actorTableModel = new ActorTableModel(library.getAllActors());
        actorsTable = new JTable(actorTableModel);
        actorsTable.setAutoCreateRowSorter(true);

        genreTableModel = new GenreTableModel(library.getAllGenres());
        genresTable = new JTable(genreTableModel);
        genresTable.setAutoCreateRowSorter(true);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Фильмы", new JScrollPane(filmsTable));
        tabbedPane.addTab("Актеры", new JScrollPane(actorsTable));
        tabbedPane.addTab("Жанры", new JScrollPane(genresTable));

        frame.getContentPane().add(tabbedPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem addFilmItem = new JMenuItem("Добавить фильм");
        JMenuItem addActorItem = new JMenuItem("Добавить актера");
        JMenuItem addGenreItem = new JMenuItem("Добавить жанр");

        addFilmItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddFilmDialog(frame, library, filmTableModel);
            }
        });

        addActorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddActorDialog(frame, library, actorTableModel);
            }
        });

        addGenreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddGenreDialog(frame, library, genreTableModel);
            }
        });

        fileMenu.add(addFilmItem);
        fileMenu.add(addActorItem);
        fileMenu.add(addGenreItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }

    public void run() {
        // Метод run() пустой, так как основная логика обработки событий
        // происходит в обработчиках событий, установленных в конструкторе
    }
}
