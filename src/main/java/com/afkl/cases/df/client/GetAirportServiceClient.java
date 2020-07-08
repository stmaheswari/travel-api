package com.afkl.cases.df.client;

import com.afkl.cases.df.config.TravelAPIConfiguration;
import com.afkl.cases.df.domain.AirportCodes;
import com.afkl.cases.df.domain.Fare;
import com.afkl.cases.df.domain.FareDetails;
import com.afkl.cases.df.domain.Location;
import com.afkl.cases.df.utils.Constants;
import com.afkl.cases.df.utils.TravelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Client class which handles oAuthRest template to form the endpoint with oauth details
 * @author Maheswari
 */
@Component
public class GetAirportServiceClient {

    private static Logger LOG = LoggerFactory.getLogger(GetAirportServiceClient.class);

    @Autowired
    private TravelAPIConfiguration apiConfiguration;

    @Autowired
    private RestTemplate oAuth2RestTemplate;

    /**
     * Get the airports code
     * @return airportcodes in a domain
     */
     public AirportCodes getAirports() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiConfiguration.getAirportsUrl());
        try {
            ResponseEntity<AirportCodes> codesResponseEntity = oAuth2RestTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, null, AirportCodes.class);
            return codesResponseEntity.getBody();
        }
        catch (RestClientException ex){
            LOG.error(" Could not get the details =", ex.getMessage());
            return null;
        }
    }

    /**
     * Get fare details
     * @param origin
     * @param destination
     * @param currency
     * @return fare details from the mock service
     */
    public FareDetails getFareData(String origin, String destination, String currency) {
        FareDetails fareResults = new FareDetails();
        Map<String, String> queryParams = null;
        queryParams = new HashMap<>();
        queryParams.put(Constants.VAR_ORIGIN, origin);
        queryParams.put(Constants.VAR_DEST, destination);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiConfiguration.getFareUrl())
                .queryParam(Constants.VAR_CURRENCY, currency);

        try {

            ResponseEntity<Fare> fare = oAuth2RestTemplate.exchange(
                    builder.buildAndExpand(queryParams).toUri().toString(), HttpMethod.GET, null, Fare.class,
                    queryParams);
            fareResults.setFare(fare.getBody());
        } catch (Exception ex) {
            LOG.error("Exception occurred in getFareData ::" + ex.getLocalizedMessage());
        }
        return fareResults;

    }

    /**
     * Method call to get origin destination details
     * @param code
     * @return the location in domain
     */
    public Location getOrigDestDetails(String code)  {
        try {
            return Optional.of(getCodes(code).getEmbedded().getLocations()
                    .stream()
                    .map(p -> new Location(p.getCode(), p.getName(), p.getDescription()))
                    .collect(Collectors.toList()).get(0)).orElseThrow(Exception::new);

        } catch (Exception e) {
            LOG.error("Exception occurred in getOrigDestDetails ::" + e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * Method call to get the airport codes
     * @param code
     * @return airport codes in domain
     */
    public AirportCodes getCodes(String code) throws TravelException {
       UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiConfiguration.getSearchUrl())
                .queryParam(Constants.VAR_SEARCH_PARAM, code);
        ResponseEntity<AirportCodes> locations = null;
        try {
            locations = oAuth2RestTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, null,
                    AirportCodes.class);
        } catch (Exception e) {
            throw new TravelException(e.getMessage());
        }
        return locations.getBody();
    }

}

