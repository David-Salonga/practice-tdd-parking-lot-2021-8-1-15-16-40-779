package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots = new ArrayList<>();
    public ParkingBoy(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        for (ParkingLot parkingLot: parkingLots){
            return parkingLot.park(car);
        }
      return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
