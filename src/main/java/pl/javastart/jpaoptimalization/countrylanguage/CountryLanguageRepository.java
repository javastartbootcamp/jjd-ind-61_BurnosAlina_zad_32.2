package pl.javastart.jpaoptimalization.countrylanguage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageKey> {

    @Query("SELECT distinct c.name as countryName, cl.language as language " +
            "FROM CountryLanguage cl " +
            "LEFT JOIN Country c " +
            "ON cl.countryCode = c.code ")
    List<LanguageWithCountryName> findAllWithCountries();
}
