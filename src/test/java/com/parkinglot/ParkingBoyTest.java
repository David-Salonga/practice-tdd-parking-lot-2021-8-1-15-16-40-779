package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_return_exception_when_fetch_given_a_parking_lot_parking_boy_and_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();
        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_exception_with_unrecognized_parking_ticket_when_fetch_given_a_parking_lot_parking_boy_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car actualFirstCar = parkingBoy.fetch(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }


    @Test
    void should_return_exception_with_no_available_position_when_park_given_a_parking_lot_without_any_position_parking_boy_and_car() {
        //given
        int capacity = 10;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        for (int i = 0; i < capacity; i++) {
            parkingBoy.park(new Car());
        }
        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    void should_park_car_to_the_first_parking_lot_when_park_given_standard_parking_boy_two_parking_lots_with_available_positions_and_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        Car actualCar = parkingLot1.fetch(parkingTicket);
        assertEquals(car, actualCar);
    }

    @Test
    void should_park_car_to_the_second_parking_lot_when_park_given_standard_parking_boy_two_parking_lots_with_first_parking_lot_full_second_parking_lot_available_position_and_car() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        Car actualCar = parkingLot2.fetch(parkingTicket);
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_right_car_with_each_ticket_when_fetch_twice_given_standard_parking_boy_two_parking_lots_both_with_a_parked_car_lot_and_two_parking_tickets() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

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

    @Test

    public void should_return_nothing_with_error_message_unrecognized_parking_ticket_when_fetch_given_standard_parking_boy_who_manage_two_parking_lots_and_an_unrecognized_ticket() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy standardParkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();
        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetch(unrecognizedParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_nothing_with_error_message_unrecognized_parking_ticket_when_fetch_given_a_standard_parking_boy_who_manage_two_parking_lots_and_a_used_ticket() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy standardParkingBoy = new ParkingBoy(parkingLots);
        ParkingTicket usedParkingTicket = new ParkingTicket();
        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetch(usedParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_nothing_with_error_message_no_available_position_when_park_given_a_standard_parking_boy_who_manage_two_parking_lots_both_with_no_available_position_and_a_car() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car());
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy standardParkingBoy = new ParkingBoy(parkingLots);
        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> standardParkingBoy.park(car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }


}
