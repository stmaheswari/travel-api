package com.afkl.cases.df.controller;

import com.afkl.cases.df.domain.FareDetails;
import com.afkl.cases.df.domain.Location;
import com.afkl.cases.df.services.AirportFareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Controller which provides API endpoints to get the fare details
 * @author Maheswari
 */
@RestController
@RequestMapping("/")
public class GetFareDetailsController {

    @Autowired
    private AirportFareService airportFareService;

    /**
     * Exposed as a Rest GET API which gets the fares of origin and destination airports given
     * @param origin
     * @param destination
     * @param currency
     * @return airport fares
     */
    @RequestMapping(method = GET, value = "fares/{origin}/{destination}")
    public FareDetails calculateFare(@PathVariable("origin") String origin,
                                     @PathVariable("destination") String destination,
                                     @RequestParam(value = "currency", defaultValue = "EUR") String currency) {

        return getAirportFare(origin, destination, currency);

    }

    /**
     * Get the airport fare
     * @param origin
     * @param destination
     * @param currency
     * @return faredetails
     */
    public FareDetails getAirportFare(String origin,
                                      String destination,
                                      String currency) {
       FareDetails fareDetails = airportFareService.getFareDetails(origin, destination, currency);
       Location originDetails = airportFareService.getOrgDestDetails(origin);
       Location destinationDetails = airportFareService.getOrgDestDetails(destination);
       fareDetails.setOrigin(originDetails);
       fareDetails.setDestination(destinationDetails);

       return fareDetails;

    }
}
