package Assign;

/**
 * class that represents a car, has name
 */
abstract class Car {
    private String name;

    /**
     * constructor for car, accepts a name
     *
     * @param name name of car
     */
    public Car(String name) {
        this.name = name;
    }

    /**
     * getter for name of car
     *
     * @return name of car
     */
    public String getName() {
        return name;
    }

    /**
     * getter for type of car, or direction
     *
     * @return type of car / direction
     */
    public abstract String getType();
}
