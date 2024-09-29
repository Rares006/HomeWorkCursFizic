package org.beta.workshop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CountryService {
    private final List<Country> countries = new ArrayList<>();


    public CountryService(CountryReader countryReader) {
        System.out.println("Creating service");
        this.countries.addAll(countryReader.read());
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<String> getCountriesName(){
        return countries.stream()
                .map(Country::name)
                .toList();
    }

    public String getCapitalOfCountry(String countryName){
      Optional<String> capitalOptional = countries.stream()
                .filter(country -> country.name().equals(countryName))
                .map(country -> country.capital())
                .findFirst();
      if(capitalOptional.isPresent()){
          return capitalOptional.get();
      } else {
          throw new RuntimeException();
      }
    }

    public long populationOfACountry(String countryName){
        Optional<Integer> populationOptional = countries.stream()
                .filter(country -> country.name().equals(countryName))
                .map(country -> country.population())
                .findFirst();
        if(populationOptional.isPresent()){
            return populationOptional.get();
        }
        throw new RuntimeException();
    }

    public List<Country> countriesInContinents(String continentName){
        return countries.stream()
                .filter(country -> country.continent().equals(continentName))
                .toList();
    }

    public List<String> getCountriesNeighbours(String countryName) {
        return countries.stream()
                .filter(country -> country.name().equals(countryName))
                .map(Country::neighbours)
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
    }

    public List<Country> countriesWithMinimumPopulation(String continentName, int minPopulation){
        return countries.stream()
                .filter(country -> country.continent().equals(continentName))
                .filter(country -> country.population() > minPopulation)
                .toList();
    }

    public List<Country> neighboringCountry(String neighbor , String notNeighbor){
        return countries.stream()
                .filter(country -> country.neighbours().contains(neighbor))
                .filter(country -> !country.neighbours().contains(notNeighbor))
                .toList();

    }
}
