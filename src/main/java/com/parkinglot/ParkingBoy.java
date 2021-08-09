package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    public ParkingBoy(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        for (ParkingLot parkingLot: parkingLots){
            try {
                return parkingLot.park(car);
            } catch (NoAvailablePositionException e){

            }

        }
     throw new NoAvailablePositionException();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        for(ParkingLot parkingLot : parkingLots){
            return parkingLot.fetch(parkingTicket);
        }
        return null;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
