package pl.javastart.jpaoptimalization.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {

    @Query("SELECT DISTINCT c FROM Country c LEFT JOIN FETCH c.cities order by c.name")
    List<Country> findAllWithCities();

    @Query("SELECT DISTINCT c FROM Country c LEFT JOIN FETCH c.languages order by c.name")
    List<Country> findAllWithLanguages();
}
