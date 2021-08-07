package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {

    @Test
    void should_return_parking_ticket_when_park_given_a_parking_lot_standard_parking_boy_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_with_a_parked_car_standard_parking_boy_and_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car actualParkingTicket = parkingBoy.fetch(parkingTicket);

        //then
        assertNotNull(actualParkingTicket);
    }

    @Test
    void should_return_car_when_fetch_twice_given_a_parking_lot_with_two_parked_cars_standard_parking_boy_and_two_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstCarTicket = parkingBoy.park(firstCar);
        ParkingTicket secondCarTicket = parkingBoy.park(secondCar);

        //when
        Car actualFirstCar = parkingBoy.fetch(firstCarTicket);
        Car actualSecondCar = parkingBoy.fetch(secondCarTicket);

        //then
        assertEquals(firstCar, actualFirstCar);
        assertEquals(secondCar, actualSecondCar);
    }

}
