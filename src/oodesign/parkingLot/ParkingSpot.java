package oodesign.parkingLot;

/**
 * @author sqzhang
 * @date 2020/5/25
 */
public class ParkingSpot {
    private Vehicle vehicle;
    private VehicleSize spotSize;
    private int row;
    private int spotNumber;
    private Level level;

    public ParkingSpot(Level lvl, int r, int n, VehicleSize sz) {
        level = lvl;
        row = r;
        spotNumber = n;
        spotSize = sz;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return isAvailable() && vehicle.canFitInSpot(this);
    }

    public boolean park(Vehicle v) {
        if (!canFitVehicle(v)) {
            return false;
        }
        vehicle = v;
        vehicle.parkInSpot(this);
        return true;
    }

    public void removeVehicle() {
        level.spotFreed();
        vehicle = null;
    }

    public int getRow() {
        return row;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleSize getSize() {
        return spotSize;
    }
    public void print() {
        if (vehicle == null) {
            if (spotSize == VehicleSize.COMPACT) {
                System.out.print("c");
            } else if (spotSize == VehicleSize.LARGE) {
                System.out.print("l");
            } else if (spotSize == VehicleSize.MOTORCYCLE) {
                System.out.print("m");
            }
        } else {
            vehicle.print();
        }
    }

}
