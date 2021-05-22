package com.kmakrutin.soapproducer.endpoint;

import com.kmakrutin.soap.producer.GetCountryRequest;
import com.kmakrutin.soap.producer.GetCountryResponse;
import com.kmakrutin.soapproducer.repo.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
public class CountryEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryEndpoint.class);

    public static final String NAMESPACE_URI = "http://kmakrutin.com/soap/producer";

    private final CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        LOGGER.info("Get country request: {}", Optional.ofNullable(request).map(GetCountryRequest::getName).orElse(null));

        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
