package pl.javastart.jpaoptimalization.countrylanguage;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class CountryLanguageService {

    private final CountryLanguageRepository countryLanguageRepository;

    public CountryLanguageService(CountryLanguageRepository countryLanguageRepository) {
        this.countryLanguageRepository = countryLanguageRepository;
    }

    public Map<String, List<String>> findAllWithCountries() {
        List<LanguageWithCountryName> languages = countryLanguageRepository.findAllWithCountries();
        return languages.stream()
                .collect(Collectors.groupingBy(LanguageWithCountryName::getLanguage,
                        TreeMap::new,
                        Collectors.mapping(LanguageWithCountryName::getCountryName, Collectors.toList())));
    }
}
