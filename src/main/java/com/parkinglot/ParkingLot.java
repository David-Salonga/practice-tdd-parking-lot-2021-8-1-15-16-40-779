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
        this.capacity = MAX_CAPACITY;
    }

    public ParkingTicket park(Car car) {
        if (!parkingLotIsFull()) {
            ParkingTicket parkingTicket = new ParkingTicket();
            parkedPosition.put(parkingTicket, car);
            return parkingTicket;
        }
        throw new NoAvailablePositionException();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(isTicketUnrecognized(parkingTicket)){
            throw new UnrecognizedParkingTicketException();
        }

        Car car = parkedPosition.get(parkingTicket);
        parkedPosition.remove(parkingTicket);
        return car;
    }

    public boolean parkingLotIsFull(){
        return parkedPosition.size() == capacity;
    }

    public boolean isTicketUnrecognized(ParkingTicket parkingTicket){
        return !parkedPosition.containsKey(parkingTicket);
    }
}
