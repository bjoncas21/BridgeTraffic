package Assign;

/**
 * Name : Benjamin Joncas
 * Date : April 10, 2024
 * Description : this program represents the traffic that is crossing a bridge in Lennoxville while
 * there is some construction. It makes use of semaphores to synchronize which cars(from which direction)
 * are allowed to cross the bridge at what time.
 */

/**
 * this class runs the simulation by creating and starting threads that represent cars headed
 * in both directions.
 */
public class BridgeSim {
    /**
     * the main method where threads are initialized representing cars, some heading in one direction
     * and some the other, the threads are started and run for a short amount of time
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Bridge bridge = new Bridge();

        // Creating Bishop's bound cars
        Thread bishopsCar1 = new Thread(new Bishops("Civic", bridge));
        Thread bishopsCar2 = new Thread(new Bishops("Tacoma", bridge));
        Thread bishopsCar3 = new Thread(new Bishops("Supra", bridge));

        // Creating Lion's bound cars
        Thread lionsCar1 = new Thread(new Lions("Lambo", bridge));
        Thread lionsCar2 = new Thread(new Lions("Scooter", bridge));

        // Starting all car threads
        bishopsCar1.start();
        bishopsCar2.start();
        bishopsCar3.start();
        lionsCar1.start();
        lionsCar2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // After timeout, interrupt all threads (you might skip this part for an endless run)
        bishopsCar1.interrupt();
        bishopsCar2.interrupt();
        bishopsCar3.interrupt();
        lionsCar1.interrupt();
        lionsCar2.interrupt();

        System.out.println("Simulation ended.");
    }
}
