package pl.javastart.jpaoptimalization;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.jpaoptimalization.country.Country;
import pl.javastart.jpaoptimalization.country.CountryService;
import pl.javastart.jpaoptimalization.countrylanguage.CountryLanguageService;
import pl.javastart.jpaoptimalization.countrylanguage.LanguageWithCountryName;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final CountryService countryService;
    private final CountryLanguageService countryLanguageService;

    public MainController(CountryService countryService,
                          CountryLanguageService countryLanguageService) {
        this.countryService = countryService;
        this.countryLanguageService = countryLanguageService;
    }

    @GetMapping("/najwieksze-miasta")
    public String countryWithBiggestCity(Model model) {
        List<Country> countries = countryService.findAllWithCities();
        model.addAttribute("countries", countries);

        return "countryWithBiggestCity";
    }

    @GetMapping("/kraje-i-jezyki")
    public String countryWithLanguages(Model model) {
        List<Country> countries = countryService.findAllWithLanguages();

        model.addAttribute("countries", countries);

        return "countryWithLanguages";
    }

    @GetMapping("/jezyki-i-kraje")
    public String languagesWithCountries(Model model) {
        List<LanguageWithCountryName> languages = countryLanguageService.findAllWithCountries();
        Map<String, List<String>> languageMap = languages.stream()
                .collect(Collectors.groupingBy(LanguageWithCountryName::getLanguage,
                        TreeMap::new,
                        Collectors.mapping(LanguageWithCountryName::getCountryName, Collectors.toList())));

        model.addAttribute("languages", languageMap);

        return "languagesWithCountries";
    }
}
