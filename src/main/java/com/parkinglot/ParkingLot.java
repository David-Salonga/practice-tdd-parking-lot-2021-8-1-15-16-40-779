package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int MAX_CAPACITY = 10;
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this(10);
    }

    public ParkingTicket park(Car car) {
        if (!parkingLotIsFull()) {
            ParkingTicket parkingTicket = new ParkingTicket();
            parkedPosition.put(parkingTicket, car);
            return parkingTicket;
        }
        return null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car = parkedPosition.get(parkingTicket);
        parkedPosition.remove(parkingTicket);
        return car;
    }

    public boolean parkingLotIsFull(){
        return parkedPosition.size() == capacity;
    }
}
