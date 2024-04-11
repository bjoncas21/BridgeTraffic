package Assign;

import java.util.Random;

/**
 * class that represents a car that is headed towards bishops, is of type bishops bound and has its
 * own run method
 */
public class Bishops extends Car implements Runnable {
    private Bridge bridge;

    /**
     * constructor for bishops
     *
     * @param name   name of car
     * @param bridge bridge
     */
    public Bishops(String name, Bridge bridge) {
        super(name);
        this.bridge = bridge;
    }

    /**
     * getter for type of car
     *
     * @return bishops bound
     */
    @Override
    public String getType() {
        return "Bishop's bound ";
    }

    /**
     * run method for bishops bound cars, sleeps while the car arrives, allows the car to cross the bridge
     * when it is available, sleeps while it crosses, removes the car once it has crossed, and lets the other side
     * know it can begin crossing
     */
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(new Random().nextInt(500));
                bridge.getSemaphoreForDirection(this.getClass()).acquire();
                bridge.addCar(this);
                Thread.sleep(400);
                bridge.removeCar(this);
                if (bridge.getSemaphoreForDirection(this.getClass()).availablePermits() == 0) {
                    bridge.getSemaphoreForDirection(Lions.class).release(); // Let the other side cars go through.
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
