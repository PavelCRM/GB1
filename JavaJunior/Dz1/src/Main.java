import Animals.Animal;
import Animals.Dog;
import Animals.Cat;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = new Animal[2];
        animals[0] = new Dog("Buddy", 3, "Golden Retriever");
        animals[1] = new Cat("Whiskers", 5, true);

        // Using Reflection API to print information about each object
        for (Animal animal : animals) {
            Class<? extends Animal> animalClass = animal.getClass();
            System.out.println("Class: " + animalClass.getSimpleName());
            Field[] fields = animalClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    System.out.println(field.getName() + ": " + field.get(animal));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            animal.makeSound();
            System.out.println();
        }
    }
}
