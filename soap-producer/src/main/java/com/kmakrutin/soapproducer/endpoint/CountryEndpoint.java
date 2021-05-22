package com.kmakrutin.soapproducer.endpoint;

import com.kmakrutin.soap.producer.GetCountryRequest;
import com.kmakrutin.soap.producer.GetCountryResponse;
import com.kmakrutin.soapproducer.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint
public class CountryEndpoint {
    public static final String NAMESPACE_URI = "http://kmakrutin.com/soap/producer";

    private final CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
