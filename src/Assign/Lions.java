package Assign;

import java.util.Random;

/**
 * class that represents a car that is headed towards the lion, is of type lion bound and has its
 * own run method
 */
public class Lions extends Car implements Runnable {
    private Bridge bridge;

    /**
     * constructor for lions
     *
     * @param name   name of car
     * @param bridge bridge
     */
    public Lions(String name, Bridge bridge) {
        super(name);
        this.bridge = bridge;
    }

    /**
     * getter for type of car
     *
     * @return lion bound
     */
    @Override
    public String getType() {
        return "Lion bound ";
    }

    /**
     * run method for lion bound cars, sleeps while the car arrives, allows the car to cross the bridge
     * when it is available, sleeps while it crosses, removes the car once it has crossed and lets the other
     * side know it can begin crossing
     */
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(new Random().nextInt(5));
                bridge.getSemaphoreForDirection(this.getClass()).acquire();
                bridge.addCar(this);
                Thread.sleep(100);
                bridge.removeCar(this);
                if (bridge.getSemaphoreForDirection(this.getClass()).availablePermits() == 0) {
                    bridge.getSemaphoreForDirection(Bishops.class).release(); // Let the other side cars go through.
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
