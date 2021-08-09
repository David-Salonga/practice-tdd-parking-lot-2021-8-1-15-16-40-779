package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {

    @Test
    void should_park_car_to_the_first_parking_lot_when_park_given_smart_parking_boy_and_two_parking_lots_with_available_positions_and_car() {
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
    void should_park_car_to_the_second_parking_lot_when_park_given_smart_parking_boy_two_parking_lots_with_first_parking_lot_full_second_parking_lot_available_position_and_car() {
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
    void should_return_right_car_with_each_ticket_when_fetch_twice_given_smart_parking_boy_two_parking_lots_both_with_a_parked_car_lot_and_two_parking_tickets() {
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
    @Test
    public void should_return_nothing_with_error_message_unrecognized_parking_ticket_when_fetch_given_smart_parking_boy_who_manage_two_parking_lots_and_an_unrecognized_ticket() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();
        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(unrecognizedParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    public void should_return_nothing_with_error_message_unrecognized_parking_ticket_when_fetch_given_a_smart_parking_boy_who_manage_two_parking_lots_and_a_used_ticket (){
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket usedParkingTicket = new ParkingTicket();
        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(usedParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    public void should_return_nothing_with_error_message_no_available_position_when_park_given_a_smart_parking_boy_who_manage_two_parking_lots_both_with_no_available_position_and_a_car(){
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car());
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> smartParkingBoy.park(car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }
}
