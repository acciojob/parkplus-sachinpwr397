package com.driver.controllers;

import com.driver.models.Reservation;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reserveSpot")
    public Reservation reserveSpot(@RequestParam Integer userId, @RequestParam Integer parkingLotId,
                                   @RequestParam Integer timeInHours, @RequestParam Integer numberOfWheels) throws Exception {
        // Reserve a spot in the given parkingLot such that the total price is minimum.
        // Note that the price per hour for each spot is different.
        // Note that the vehicle can only be parked in a spot having a type equal to or larger than the given vehicle.
        // If parkingLot is not found, user is not found, or no spot is available, throw "Cannot make reservation" exception.

        return reservationService.reserveSpot(userId, parkingLotId, timeInHours, numberOfWheels);
    }
}
