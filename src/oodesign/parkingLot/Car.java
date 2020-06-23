package oodesign.parkingLot;

/**
 * @author sqzhang
 * @date 2020/5/25
 */
public class Car extends Vehicle {
    public Car() {
        spotsNeeded = 1;
        size = VehicleSize.COMPACT;
    }

    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.LARGE || spot.getSize() == VehicleSize.COMPACT;
    }

    public void print() {
        System.out.print("C");
    }
}
