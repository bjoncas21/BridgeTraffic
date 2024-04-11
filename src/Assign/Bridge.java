package Assign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * class that represents a bridge, has a list of cars that will be crossing, semaphores for cars
 * in each direction, and methods to add a car, remove a car, and print te cars currently on the bridge
 */
public class Bridge {
    List<Car> cars = new ArrayList<>();
    Semaphore BishopsBoundCars = new Semaphore(1, true);
    Semaphore LionsBoundCars = new Semaphore(1, true);

    /**
     * method to add a car to the list of cars on the bridge
     *
     * @param car car on bridge
     */
    public synchronized void addCar(Car car) {
        cars.add(car);
        System.out.println(car.getType() + car.getName() + " is on the bridge");
    }

    /**
     * method to remove a car from the bridge
     *
     * @param car car on bridge
     */
    public synchronized void removeCar(Car car) {
        cars.remove(car);
        System.out.println(car.getType() + car.getName() + " is off the bridge");

        if (cars.isEmpty()) {
            BishopsBoundCars.release();
            LionsBoundCars.release();
        }
    }

    /**
     * method to print all the cars that are currently on the bridge by looping through the list of cars
     */
    public synchronized void printCars() {
        System.out.print("Cars on bridge : ");
        for (Car c : cars) {
            System.out.println(c.getName());
        }
    }

    /**
     * getter for the bishops bound semaphore
     *
     * @return bishops bound cars
     */
    public Semaphore getBishopsBoundCars() {
        return BishopsBoundCars;
    }

    /**
     * getter for the lion bound semaphore
     *
     * @return lion bound cars
     */
    public Semaphore getLionsBoundCars() {
        return LionsBoundCars;
    }

    /**
     * getter for the direction of the car
     *
     * @param carClass car type
     * @return the direction
     */
    public Semaphore getSemaphoreForDirection(Class<? extends Car> carClass) {
        return Bishops.class.isAssignableFrom(carClass) ? BishopsBoundCars : LionsBoundCars;
    }
}


