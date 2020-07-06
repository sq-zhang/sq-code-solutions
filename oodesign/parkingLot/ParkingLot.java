package oodesign.parkingLot;

/**
 * @author sqzhang
 * @date 2020/5/25
 */
public class ParkingLot {

    private Level[] levels;

    public ParkingLot() {
        int NUM_LEVELS = 5;
        levels = new Level[NUM_LEVELS];
        for (int i = 0; i < NUM_LEVELS; i++) {
            levels[i] = new Level(i, 30);
        }
    }
    /* Park the vehicle in a spot (or multiple spots). Return false if failed. */
    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.parkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < levels.length; i++) {
            System.out.print("Level " + i + ": ");
            levels[i].print();
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        for(int i = 0;i < 100;i++) {
            Bus bus = new Bus();
            Car car = new Car();
            parkingLot.parkVehicle(bus);
            parkingLot.parkVehicle(car);
        }
        parkingLot.print();
    }

}
