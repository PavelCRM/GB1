import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Класс, представляющий сотрудника
class Employee {
    private int employeeId;
    private String phoneNumber;
    private String name;
    private int experience;

    public Employee(int employeeId, String phoneNumber, String name, int experience) {
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }
}

// Класс, представляющий справочник сотрудников
class EmployeeDirectory {
    private List<Employee> employees;

    public EmployeeDirectory() {
        employees = new ArrayList<>();
        // Добавление примеров сотрудников
        employees.add(new Employee(1, "1234567890", "Иван Сергеев", 5));
        employees.add(new Employee(2, "9876543210", "Мария Петрова", 8));
        employees.add(new Employee(3, "6584584584", "Катерина Смолова", 8));
        employees.add(new Employee(4, "0498569500", "Костантин Журов", 4));
        employees.add(new Employee(5, "0945842004", "Борис Вольский", 7));
        employees.add(new Employee(6, "9582095958", "Юлия Захарова", 5));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    // Метод для добавления нового сотрудника
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Метод для поиска сотрудника по табельному номеру
    public Employee findEmployeeById(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null; // Возвращает null, если сотрудник с указанным табельным номером не найден
    }

    // Метод для поиска сотрудников по стажу
    public List<Employee> findEmployeesByExperience(int targetExperience) {
        List<Employee> foundEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getExperience() == targetExperience) {
                foundEmployees.add(employee);
            }
        }
        return foundEmployees;
    }

    // Метод для поиска номеров телефонов сотрудника по имени
    public List<String> findPhoneNumbersByName(String targetName) {
        List<String> phoneNumbers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(targetName)) {
                phoneNumbers.add(employee.getPhoneNumber());
            }
        }
        return phoneNumbers;
    }

    // Метод для поиска сотрудника по табельному номеру
    public Employee findEmployeeByEmployeeId(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null; // Возвращает null, если сотрудник с указанным табельным номером не найден
    }
}

// Класс с основным методом (точкой входа в программу)
public class Main {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nМеню:");
            System.out.println("1. Просмотреть список сотрудников");
            System.out.println("2. Добавить нового сотрудника");
            System.out.println("3. Найти сотрудника по табельному номеру");
            System.out.println("4. Найти сотрудников по стажу");
            System.out.println("5. Найти номера телефонов по имени");
            System.out.println("6. Выйти");

            System.out.print("Выберите опцию: ");
            choice = readIntInput(scanner);

            switch (choice) {
                case 1:
                    displayEmployeeList(directory.getEmployees());
                    break;
                case 2:
                    addEmployee(directory, scanner);
                    break;
                case 3:
                    findEmployeeById(directory, scanner);
                    break;
                case 4:
                    findEmployeesByExperience(directory, scanner);
                    break;
                case 5:
                    findPhoneNumbersByName(directory, scanner);
                    break;
                case 6:
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        } while (choice != 6);
    }

    // Метод для вывода списка сотрудников
    private static void displayEmployeeList(List<Employee> employees) {
        System.out.println("\nСписок сотрудников:");
        for (Employee employee : employees) {
            System.out.println("Имя и фамилия: " + employee.getName() +
                    ", Табельный номер: " + employee.getEmployeeId() +
                    ", Стаж: " + employee.getExperience());
        }
    }

    // Метод для добавления нового сотрудника
    private static void addEmployee(EmployeeDirectory directory, Scanner scanner) {
        System.out.print("Введите табельный номер нового сотрудника: ");
        int employeeId = readIntInput(scanner);

        System.out.print("Введите номер телефона нового сотрудника: ");
        String phoneNumber = scanner.next();
        scanner.nextLine();  // Добавим дополнительный вызов nextLine(), чтобы "прочитать" символ новой строки

        System.out.print("Введите имя и фамилию нового сотрудника (через пробел): ");
        String fullName = scanner.nextLine();

        String[] nameParts = fullName.split("\\s+");
        String firstName = nameParts[0];
        String lastName = (nameParts.length > 1) ? nameParts[1] : "";

        System.out.print("Введите стаж нового сотрудника: ");
        int experience = readIntInput(scanner);

        Employee newEmployee = new Employee(employeeId, phoneNumber, firstName + " " + lastName, experience);
        directory.addEmployee(newEmployee);
        System.out.println("Сотрудник успешно добавлен.");
    }

    // Метод для поиска сотрудника по табельному номеру
    private static void findEmployeeById(EmployeeDirectory directory, Scanner scanner) {
        System.out.print("Введите табельный номер сотрудника для поиска: ");
        int targetEmployeeId = readIntInput(scanner);
        scanner.nextLine();  // Добавим эту строку, чтобы "прочитать" символ новой строки
        Employee foundEmployee = directory.findEmployeeByEmployeeId(targetEmployeeId);
        if (foundEmployee != null) {
            System.out.println("Найден сотрудник: " + foundEmployee.getName());
        } else {
            System.out.println("Сотрудник с табельным номером " + targetEmployeeId + " не найден.");
        }
    }

    // Метод для поиска сотрудников по стажу
    private static void findEmployeesByExperience(EmployeeDirectory directory, Scanner scanner) {
        System.out.print("Введите стаж для поиска сотрудников: ");
        int targetExperience = readIntInput(scanner);
        scanner.nextLine();  // Добавим эту строку, чтобы "прочитать" символ новой строки
        List<Employee> foundEmployees = directory.findEmployeesByExperience(targetExperience);
        if (!foundEmployees.isEmpty()) {
            System.out.println("Найдены сотрудники со стажем " + targetExperience + ":");
            for (Employee employee : foundEmployees) {
                System.out.println("Имя и фамилия: " + employee.getName() +
                        ", Табельный номер: " + employee.getEmployeeId() +
                        ", Стаж: " + employee.getExperience());
            }
        } else {
            System.out.println("Сотрудники со стажем " + targetExperience + " не найдены.");
        }
    }

    // Метод для поиска номеров телефонов сотрудника по имени
    private static void findPhoneNumbersByName(EmployeeDirectory directory, Scanner scanner) {
        System.out.print("Введите имя сотрудника для поиска номера телефона: ");
        String targetName = scanner.nextLine().trim();
        if (!targetName.isEmpty()) {
            List<String> phoneNumbers = directory.findPhoneNumbersByName(targetName);
            if (!phoneNumbers.isEmpty()) {
                System.out.println("Найдены номера телефонов для сотрудника по имени " + targetName + ":");
                for (String phoneNumber : phoneNumbers) {
                    System.out.println(phoneNumber);
                }
            } else {
                System.out.println("Сотрудник с именем " + targetName + " не найден.");
            }
        } else {
            System.out.println("Пожалуйста, введите имя сотрудника.");
        }
    }

    // Метод для считывания целочисленного ввода с учетом обработки ошибок
    private static int readIntInput(Scanner scanner) {
        int result = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                result = scanner.nextInt();
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите целое число.");
            } finally {
                // Очистка буфера после считывания числа
                scanner.nextLine();
            }
        }
        return result;
    }
}
