package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_parking_ticket_when_park_given_a_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_with_a_parked_car_and_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        //when
        Car actualCar = parkingLot.fetch(parkingTicket);
        //then
        assertNotNull(actualCar);
    }

    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_twice_given_parking_lot_with_two_parked_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstCarTicket = parkingLot.park(firstCar);
        ParkingTicket secondCarTicket = parkingLot.park(secondCar);

        //when
        Car actualFirstCar = parkingLot.fetch(firstCarTicket);
        Car actualSecondCar = parkingLot.fetch(secondCarTicket);

        //then
        assertEquals(firstCar, actualFirstCar);
        assertEquals(secondCar, actualSecondCar);
    }

    @Test
    void should_return_exception_when_fetch_given_a_parking_lot_and_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();
        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(unrecognizedParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_unrecognized_parking_ticket_when_fetch_given_a_parking_lot_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        Car actualFirstCar = parkingLot.fetch(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }


    @Test
    void should_return_exception_with_no_available_position_when_park_given_a_parking_lot_without_any_position_and_car() {
        //given
        int capacity = 10;
        ParkingLot parkingLot = new ParkingLot(capacity);
        Car car = new Car();
        for (int i = 0; i < capacity; i++) {
            parkingLot.park(new Car());
        }
        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }
}
