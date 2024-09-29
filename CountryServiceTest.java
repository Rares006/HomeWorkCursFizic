package org.beta.workshop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CountryServiceTest {
    @Autowired
    CountryService countryService;
    @Test
    void testAllCapitals(){
        String capital = countryService.getCapitalOfCountry("Romania");
        assertThat(capital).isEqualTo("Bucharest");
    }

    @Test
    void testPopulation(){
        long population = countryService.populationOfACountry("Romania");
        assertThat(population).isEqualTo(19861408);
    }

    @Test
    void testListOfContryFromAContinent(){
        List<Country> countries = countryService.countriesInContinents("Europe");
        assertThat(countries).hasSize(52);
    }

    @Test
    void testListOfCountryNeighbours() {
        List<String> neighbours = countryService.getCountriesNeighbours("Romania");
        List<String> expectedNeighbours = Arrays.asList("BGR", "HUN", "MDA", "SRB", "UKR");
        assertThat(neighbours).isEqualTo(expectedNeighbours);
    }

    @Test
    void testCountriesWithMinimumPopulation(){
        List<Country> countryList = countryService.countriesWithMinimumPopulation("Europe",78015);
        assertThat(countryList).hasSize(43);
    }

    @Test
    void testNeighboringCountry(){
        List<Country> countryList = countryService.neighboringCountry("MEX", "CAN");

        assertThat(countryList).hasSize(2);
    }

}