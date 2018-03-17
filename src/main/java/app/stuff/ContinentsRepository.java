package app.stuff;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public class ContinentsRepository {

    private static Function<String, Country> mapToCountry = (line) -> {
        String[] split = line.split(",");
        return new Country(Integer.parseInt(split[0]), split[1].trim(), split[2].trim(), Integer.parseInt(split[3]));
    };

    public static Map<String, List<Country>> getGroupCountriesByContinents(Reader file) {

        List<Country> lOfCountries = getCountries(file);

        Map<String, List<Country>> groupByContinents = lOfCountries.stream().collect(groupingBy(Country::getContinent));
        return groupByContinents;
    }

    private static List<Country> getCountries(Reader file) {

        BufferedReader br = new BufferedReader(file);
        return br.lines().skip(1).map((line) -> {
            String[] split = line.split(",");
            return new Country(Integer.parseInt(split[0]), split[1].trim(), split[2].trim(), Integer.parseInt(split[3]));
        }).collect(toList());
    }

    public static Map<String, Integer> getGroupContinentsSumPopulation(Reader file) {
        List<Country> countries = getCountries(file);
        return countries.stream().collect(groupingBy(Country::getContinent, summingInt(Country::getPopulation)));
    }

    public static Map<String, Integer> getFilteredContriesByContinentSumPopulation(Reader file, Predicate<Country> filter) {
        List<Country> countries = getCountries(file);

        return countries.stream().filter(filter).collect(groupingBy(Country::getContinent, summingInt(Country::getPopulation)));
    }
}
