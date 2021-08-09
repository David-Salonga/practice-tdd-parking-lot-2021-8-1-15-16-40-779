package com.parkinglot;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithManyPositions = null;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailablePosition() > 0) {
                if (parkingLotWithManyPositions == null || parkingLot.getAvailablePosition() > parkingLotWithManyPositions.getAvailablePosition()) {
                    parkingLotWithManyPositions = parkingLot;
                }
            }
        }
        if(parkingLotWithManyPositions == null){
            throw new NoAvailablePositionException();
        }
        return parkingLotWithManyPositions.park(car);
    }
}
