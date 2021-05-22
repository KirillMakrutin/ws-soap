package com.kmakrutin.soapconsumer;

import com.kmakrutin.soap.consumer.wsdl.Country;
import com.kmakrutin.soap.consumer.wsdl.GetCountryResponse;
import com.kmakrutin.soapconsumer.client.CountryClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoapConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapConsumerApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient quoteClient) {
        return args -> {
            String country = "Spain";

            if (args.length > 0) {
                country = args[0];
            }
            GetCountryResponse response = quoteClient.getCountry(country);
            System.err.println(new PrintableCountry(response.getCountry()));
        };
    }

    private static class PrintableCountry extends Country {

        private PrintableCountry(Country country) {
            this.name = country.getName();
            this.population = country.getPopulation();
            this.capital = country.getCapital();
            this.currency = country.getCurrency();
        }

        @Override
        public String toString() {
            return "PrintableCountry{" +
                    "name='" + name + '\'' +
                    ", population=" + population +
                    ", capital='" + capital + '\'' +
                    ", currency=" + currency +
                    '}';
        }
    }

}
