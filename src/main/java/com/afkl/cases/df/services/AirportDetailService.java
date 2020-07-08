package com.afkl.cases.df.services;

import com.afkl.cases.df.client.GetAirportServiceClient;
import com.afkl.cases.df.domain.AirportCodes;
import com.afkl.cases.df.domain.AirportList;
import com.afkl.cases.df.domain.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Service class which consume airports service and expose the API
 * @author M
 */
@Service
public class AirportDetailService {

    private static Logger log = LoggerFactory.getLogger(AirportDetailService.class);

    @Autowired
    GetAirportServiceClient restClient;

    /**
     * Get all the airports
     * @return list of airports
     */
    public AirportList getAirports() {

        AirportCodes airportCodes = restClient.getAirports();
        AirportList airportList = new AirportList();
        airportList.setPage(airportCodes.getPage());
        airportList.setLocations(airportCodes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getCode)).collect(Collectors.toList()));
        return airportList;
    }

}
