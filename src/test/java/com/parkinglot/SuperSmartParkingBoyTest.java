package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperSmartParkingBoyTest {
    @Test
    void should_park_car_to_the_first_parking_lot_when_park_given_super_smart_parking_boy_and_two_parking_lots_with_available_positions_and_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //then
        Car actualCar = parkingLot1.fetch(parkingTicket);
        assertEquals(car, actualCar);
    }

    @Test
    public void should_park_to_second_parking_lot_when_park_the_car_given_two_parking_lot_with_second_parking_lot_has_larger_position_rate_and_a_car_to_a_parking_boy(){
        //given
        ParkingLot parkingLot1 = new ParkingLot(2);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot(5);
        parkingLot2.park(new Car());
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //then
        Car actualCar = parkingLot2.fetch(parkingTicket);
        assertEquals(car, actualCar);
    }
}
