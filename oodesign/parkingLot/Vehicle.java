package oodesign.parkingLot;

import java.util.ArrayList;
import java.util.List;
/**
 * @author sqzhang
 * @date 2020/5/25
 */
public abstract class Vehicle {
    protected int spotsNeeded;
    protected VehicleSize size;
    protected String licensePlate;

    protected List<ParkingSpot> parkingSpots = new ArrayList<>();

    public int getSpotsNeeded() {
        return spotsNeeded;
    }

    public VehicleSize getSize() {
        return size;
    }

    /* Park vehicle in this spot (among others, potentially) */
    public void parkInSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    /* Remove car from spot, and notify spot that it's gone */
    public void clearSpots() {
        for (ParkingSpot parkingSpot : parkingSpots) {
            parkingSpot.removeVehicle();
        }
        parkingSpots.clear();
    }
    //this need to be implement in subclass
    public abstract boolean canFitInSpot(ParkingSpot spot);
    public abstract void print();

}
