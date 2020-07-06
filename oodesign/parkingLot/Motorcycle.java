package oodesign.parkingLot;

/**
 * @author sqzhang
 * @date 2020/5/25
 */
public class Motorcycle extends Vehicle {

    public Motorcycle() {
        spotsNeeded = 1;
        size = VehicleSize.MOTORCYCLE;
    }

    public boolean canFitInSpot(ParkingSpot spot) {
        return true;
    }

    public void print() {
        System.out.print("M");
    }
}