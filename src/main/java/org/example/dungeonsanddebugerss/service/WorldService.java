package org.example.dungeonsanddebugerss.service;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.entities.CountryEntity;
import org.example.dungeonsanddebugerss.entities.CountrylanguageEntity;
import org.example.dungeonsanddebugerss.entities.CountrylanguageEntityId;
import org.example.dungeonsanddebugerss.respositories.CityEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountryEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountrylanguageEntityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class WorldService {

    private CityEntityRepository cityEntityRepository;
    private CountryEntityRepository countryEntityRepository;
    private CountrylanguageEntityRepository countrylanguageEntityRepository;


    public WorldService(CityEntityRepository cityEntityRepository, CountryEntityRepository countryEntityRepository, CountrylanguageEntityRepository countrylanguageEntityRepository) {
        this.cityEntityRepository = cityEntityRepository;
        this.countryEntityRepository = countryEntityRepository;
        this.countrylanguageEntityRepository = countrylanguageEntityRepository;
    }

    public List<CountryEntity> findCountriesWithNoHeadOfState() {
        return null;
    }


    public float findPercentageOfPopulationInLargestCity(String countryName) {
        return 0f;
    }

    public List<CountryEntity> findCountryWithMostCity() {
        return null;
    }

    public int returnCountOfCitiesInCountry() {
        return 0;
    }

    public List<CityEntity> find5SmallestDistrictsOfCity() {
        return null;
    }
    // For a given country, approximately how many people speak its most popular official language?
    public int findCountOfMostPopularLanguage(String countryName) {
        // Get the country code
        // Get total population
        // Find highest percentage and convert to int
        int totalPopulation;
        String countryCode;

        CountryEntity country = findCountry(countryName);
        if(country != null){
            countryCode = country.getCode();
            totalPopulation = country.getPopulation();
        }else{
            return -1;
        }

        ArrayList<Integer> peopleWhoSpeakLanguage = new ArrayList<Integer>();
        for(CountrylanguageEntity language : getAllLanguageCountrySpeaks(countryCode)){
            if(language.getIsOfficial().equals("T")){
                peopleWhoSpeakLanguage.add(Math.round(totalPopulation * (language.getPercentage().floatValue()/100)));
            }
        }

        if(peopleWhoSpeakLanguage.isEmpty()){
            return -1;
        }

        Collections.sort(peopleWhoSpeakLanguage);
        return peopleWhoSpeakLanguage.getLast();
    }

    private CountryEntity findCountry(String countryName){
        for(CountryEntity country : countryEntityRepository.findAll()) {
            if(country.getName().equals(countryName)) {
                return country;
            }
        }
        return null;
    }

    private List<CountrylanguageEntity> getAllLanguageCountrySpeaks(String countryCode){
        List<CountrylanguageEntity> countries = new ArrayList<>();
        for(CountrylanguageEntity countrylanguageEntity : countrylanguageEntityRepository.findAll()){
            if(countrylanguageEntity.getCountryCode().getCode().equals(countryCode)){
                countries.add(countrylanguageEntity);
            }
        }
        return countries;
    }

}
