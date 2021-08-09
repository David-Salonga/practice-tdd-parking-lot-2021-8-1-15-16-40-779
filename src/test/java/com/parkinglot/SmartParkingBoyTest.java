package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmartParkingBoyTest {

    @Test
    void should_park_car_to_the_first_parking_lot_when_park_given_standard_parking_boy_two_parking_lots_with_available_positions_and_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        Car actualCar = parkingLot1.fetch(parkingTicket);
        assertEquals(car, actualCar);
    }

    @Test
    void should_park_car_to_the_second_parking_lot_when_park_given_standard_parking_boy_two_parking_lots_with_first_parking_lot_full_second_parking_lot_available_position_and_car() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(3);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot(5);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        Car actualCar = parkingLot2.fetch(parkingTicket);
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_right_car_with_each_ticket_when_fetch_twice_given_standard_parking_boy_two_parking_lots_both_with_a_parked_car_lot_and_two_parking_tickets() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstCarTicket = parkingLot1.park(firstCar);
        ParkingTicket secondCarTicket = parkingLot2.park(secondCar);

        //when
        Car actualFirstCar = smartParkingBoy.fetch(firstCarTicket);
        Car actualSecondCar = smartParkingBoy.fetch(secondCarTicket);

        //then
        assertEquals(firstCar, actualFirstCar);
        assertEquals(secondCar, actualSecondCar);
    }

}
