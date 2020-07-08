package com.afkl.cases.df.services;

import com.afkl.cases.df.client.GetAirportServiceClient;
import com.afkl.cases.df.domain.FareDetails;
import com.afkl.cases.df.domain.Location;
import com.afkl.cases.df.utils.TravelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class which access the fare services for the given origin and destination
 * @author Maheswari
 */
@Service
public class AirportFareService {

    @Autowired
    GetAirportServiceClient restClient;

    /**
     * Method call to get the fare details
     * @param origin
     * @param destination
     * @param currency
     * @return fare details
     */
    public FareDetails getFareDetails(String origin, String destination, String currency) {
        return (restClient.getFareData(origin, destination, currency));
    }


    /**
     * Method call to get the origin destination details
     * @param code airport code
     * @return origin destination details
     */
    public Location getOrgDestDetails(String code) {
        return (restClient.getOrigDestDetails(code));
    }

    /**
     * Method call to get the airport codes for the locations
     * @param code
     * @return list of codes
     * @throws TravelException
     */
    public List<Location> getCodes(String code)  throws TravelException {

        return restClient.getCodes(code).getEmbedded().getLocations().
                stream().
                map(p -> new Location(p.getCode(), p.getName(), p.getDescription()))
                .collect(Collectors.toList());
    }
}
