package com.afkl.cases.df.controller;

import com.afkl.cases.df.domain.AirportList;
import com.afkl.cases.df.domain.Location;
import com.afkl.cases.df.services.AirportDetailService;
import com.afkl.cases.df.services.AirportFareService;
import com.afkl.cases.df.utils.TravelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Controller class is used to access the API to get the Airport details
 * @author Maheswari
 */
@RestController
@RequestMapping("/")
public class GetAirportDetailsController {

    @Autowired
    private AirportDetailService airportDetailService;

    @Autowired
    private AirportFareService airportFareService;


    /**
     * Exposed as a Rest GET API which consumes airports services
     * @return list of airports
     */
    @RequestMapping(method = GET, value = "/airports")
    public AirportList getAirportsService() {
        return airportDetailService.getAirports();

    }

    /**
     * Exposed as a Rest GET API which search airports services based on the given Code
     * @param term airport code
     * @param lang language
     * @return airport codes
     * @throws TravelException
     */
    @RequestMapping(method = GET, value = "search/code")
    public List<Location> populateOriginDest(@RequestParam("term") String term,
                                             @RequestParam(value = "lang", defaultValue = "en") String lang) throws TravelException {

        return airportFareService.getCodes(term);
    }

}
