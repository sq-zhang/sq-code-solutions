package oodesign.parkingLot;

/**
 * @author sqzhang
 * @date 2020/5/25
 */
public class Bus extends Vehicle {

    public Bus() {
        spotsNeeded = 5;
        size = VehicleSize.LARGE;
    }

    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.LARGE;
    }

    public void print() {
        System.out.print("B");
    }
}
