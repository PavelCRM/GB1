public class Main {
    public static void main(String[] args) {
        // Пример использования калькулятора
        System.out.println("Sum: " + Calculator.sum(5, 3));
        System.out.println("Multiply: " + Calculator.multiply(5.0, 3));
        System.out.println("Divide: " + Calculator.divide(10, 2));
        System.out.println("Subtract: " + Calculator.subtract(8, 3));

        // Пример использования метода compareArrays()
        Integer[] array1 = {1, 2, 3};
        Integer[] array2 = {1, 2, 3};
        System.out.println("Arrays are equal: " + ArrayComparator.compareArrays(array1, array2));

        // Пример использования класса Pair
        Pair<String, Integer> pair = new Pair<>("Hello", 42);
        System.out.println("Pair: " + pair.toString());
    }
}

