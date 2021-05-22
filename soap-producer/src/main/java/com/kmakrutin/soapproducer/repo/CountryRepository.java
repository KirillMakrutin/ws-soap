package com.kmakrutin.soapproducer.repo;

import com.kmakrutin.soap.producer.Country;
import com.kmakrutin.soap.producer.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {
    private static final Map<String, Country> COUNTRIES = new HashMap<>();

    @PostConstruct
    public void init() {
        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setPopulation(46_704_314);
        spain.setCurrency(Currency.EUR);

        COUNTRIES.put(spain.getName(), spain);

        Country poland = new Country();
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38_186_860);

        COUNTRIES.put(poland.getName(), poland);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63_705_000);

        COUNTRIES.put(uk.getName(), uk);
    }

    public Country findCountry(String name) {
        Assert.notNull(name, "The country name must not be null");
        return COUNTRIES.get(name);
    }
}
