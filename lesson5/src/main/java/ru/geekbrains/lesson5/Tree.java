package ru.geekbrains.lesson5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Tree {

    public static void main(String[] args) {
        printTree(new File("."), "");
        createBackup();
    }

    static void printTree(File file, String indent) {
        System.out.print(indent);
        if (file.isDirectory()) {
            System.out.print("├─");
            System.out.println(file.getName() + "/");
            File[] files = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (i == files.length - 1) {
                        printTree(files[i], indent + "  ");
                    } else {
                        printTree(files[i], indent + "│ ");
                    }
                }
            }
        } else {
            System.out.print("├─");
            System.out.println(file.getName());
        }
    }

    static void createBackup() {
        // Определяем исходную и целевую директории
        File sourceDirectory = new File(".");
        File backupDirectory = new File("./backup");

        // Создаем директорию для резервных копий, если ее нет
        if (!backupDirectory.exists()) {
            backupDirectory.mkdir();
        }

        // Проверяем, что исходная директория является директорией
        if (sourceDirectory.isDirectory()) {
            File[] files = sourceDirectory.listFiles();
            if (files != null) {
                // Проходимся по всем файлам в исходной директории
                for (File file : files) {
                    // Если файл - обычный файл (не директория)
                    if (file.isFile()) {
                        try {
                            // Создаем объект для хранения бэкапа файла в папке 'backup'
                            File backupFile = new File(backupDirectory, file.getName());
                            Files.copy(file.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            // Выводим сообщение о каждом скопированном файле
                            System.out.println("Файл " + file.getName() + " скопирован в папку 'backup'.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
