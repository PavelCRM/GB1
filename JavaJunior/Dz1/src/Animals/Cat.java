package Animals;

public class Cat extends Animal {
    private boolean isFriendly;

    public Cat(String name, int age, boolean isFriendly) {
        super(name, age);
        this.isFriendly = isFriendly;
    }

    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }

    public void purr() {
        System.out.println("Purring contentedly.");
    }
}
