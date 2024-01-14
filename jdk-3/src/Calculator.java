// Задание 1: Класс Калькулятор
public class Calculator {
    // Обобщенный метод для сложения двух чисел разного типа
    public static <T extends Number> T sum(T a, T b) {
        if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() + b.intValue());
        } else if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() + b.doubleValue());
        } else if (a instanceof Float) {
            return (T) Float.valueOf(a.floatValue() + b.floatValue());
        } else if (a instanceof Long) {
            return (T) Long.valueOf(a.longValue() + b.longValue());
        } else {
            throw new IllegalArgumentException("Unsupported number type");
        }
    }

    // Обобщенный метод для умножения двух чисел разного типа
    public static <T extends Number> T multiply(T a, T b) {
        if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() * b.intValue());
        } else if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() * b.doubleValue());
        } else if (a instanceof Float) {
            return (T) Float.valueOf(a.floatValue() * b.floatValue());
        } else if (a instanceof Long) {
            return (T) Long.valueOf(a.longValue() * b.longValue());
        } else {
            throw new IllegalArgumentException("Unsupported number type");
        }
    }

    // Обобщенный метод для деления двух чисел разного типа
    public static <T extends Number> Double divide(T a, T b) {
        if (a instanceof Number && b instanceof Number) {
            return a.doubleValue() / b.doubleValue();
        } else {
            throw new IllegalArgumentException("Unsupported number type");
        }
    }

    // Обобщенный метод для вычитания двух чисел разного типа
    public static <T extends Number> T subtract(T a, T b) {
        if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() - b.intValue());
        } else if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() - b.doubleValue());
        } else if (a instanceof Float) {
            return (T) Float.valueOf(a.floatValue() - b.floatValue());
        } else if (a instanceof Long) {
            return (T) Long.valueOf(a.longValue() - b.longValue());
        } else {
            throw new IllegalArgumentException("Unsupported number type");
        }
    }
}