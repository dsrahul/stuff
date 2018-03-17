package app.stuff;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CountriesTest {

    public static final String SUB_SAHARAN_AFRICA = "SUB-SAHARAN AFRICA";
    public static final String C_W_OF_IND_STATES = "C.W. OF IND. STATES";
    public static final String WESTERN_EUROPE = "WESTERN EUROPE";
    public static final String ASIA_EX_NEAR_EAST = "ASIA (EX. NEAR EAST)";
    public static final String LATIN_AMER_CARIB = "LATIN AMER. & CARIB";
    public static final String EASTERN_EUROPE = "EASTERN EUROPE";
    public static final String NEAR_EAST = "NEAR EAST";
    public static final String NORTHERN_AMERICA = "NORTHERN AMERICA";
    public static final String NORTHERN_AFRICA = "NORTHERN AFRICA";
    public static final String OCEANIA = "OCEANIA";
    public static final String BALTICS = "BALTICS";

    @Test
    public void testGroupByContinents() {
        Map<String, List<Country>> groupCountryByContinents = ContinentsRepository.getGroupCountriesByContinents(getFile());

        assertThat(groupCountryByContinents.size(), is(11));

        List<Country> subSaharaAfrica = groupCountryByContinents.get(SUB_SAHARAN_AFRICA);
        List<Country> indStates = groupCountryByContinents.get(C_W_OF_IND_STATES);
        List<Country> westEurope = groupCountryByContinents.get(WESTERN_EUROPE);
        List<Country> asia = groupCountryByContinents.get(ASIA_EX_NEAR_EAST);
        List<Country> latin = groupCountryByContinents.get(LATIN_AMER_CARIB);
        List<Country> eastEurope = groupCountryByContinents.get(EASTERN_EUROPE);
        List<Country> nearEast = groupCountryByContinents.get(NEAR_EAST);
        List<Country> northAmerica = groupCountryByContinents.get(NORTHERN_AMERICA);
        List<Country> northAfrica = groupCountryByContinents.get(NORTHERN_AFRICA);
        List<Country> oceania = groupCountryByContinents.get(OCEANIA);
        List<Country> baltics = groupCountryByContinents.get(BALTICS);

        assertThat(subSaharaAfrica.size(), is(51));
        assertThat(indStates.size(), is(12));
        assertThat(westEurope.size(), is(28));
        assertThat(asia.size(), is(28));
        assertThat(latin.size(), is(45));
        assertThat(eastEurope.size(), is(12));
        assertThat(nearEast.size(), is(16));
        assertThat(northAmerica.size(), is(5));
        assertThat(northAfrica.size(), is(6));
        assertThat(oceania.size(), is(21));
        assertThat(baltics.size(), is(3));

    }

    @Test
    public void testGroupContinetsSummingPopulation() {
        Map<String, Integer> groupContinentsSumPopulation = ContinentsRepository.getGroupContinentsSumPopulation(getFile());

        assertThat(groupContinentsSumPopulation.size(), is(11));

        assertThat(groupContinentsSumPopulation.get(C_W_OF_IND_STATES), is(280081548));
        assertThat(groupContinentsSumPopulation.get(WESTERN_EUROPE), is(396339998));
        assertThat(groupContinentsSumPopulation.get(ASIA_EX_NEAR_EAST), is(-606985060));
        assertThat(groupContinentsSumPopulation.get(LATIN_AMER_CARIB), is(561824599));
        assertThat(groupContinentsSumPopulation.get(EASTERN_EUROPE), is(119914717));
        assertThat(groupContinentsSumPopulation.get(NEAR_EAST), is(195068377));
        assertThat(groupContinentsSumPopulation.get(SUB_SAHARAN_AFRICA), is(749437000));
        assertThat(groupContinentsSumPopulation.get(NORTHERN_AMERICA), is(331672307));
        assertThat(groupContinentsSumPopulation.get(NORTHERN_AFRICA), is(161407133));
        assertThat(groupContinentsSumPopulation.get(OCEANIA), is(33131662));
        assertThat(groupContinentsSumPopulation.get(BALTICS), is(7184974));
    }

    @Test
    public void testGroupFilteredContriesByContinentSumPopulation() {
        Predicate<Country> predicate = (ele) -> ele.getName().startsWith("M");
        Map<String, Integer> groupedContinents = ContinentsRepository.getFilteredContriesByContinentSumPopulation(getFile(), predicate);
        assertThat(groupedContinents.size(), is(8));

        assertThat(groupedContinents.get(C_W_OF_IND_STATES), is(4466706));
        assertThat(groupedContinents.get(WESTERN_EUROPE), is(432757));
        assertThat(groupedContinents.get(ASIA_EX_NEAR_EAST), is(28030215));
        assertThat(groupedContinents.get(LATIN_AMER_CARIB), is(107895095));
        assertThat(groupedContinents.get(EASTERN_EUROPE), is(2050554));
        assertThat(groupedContinents.get(SUB_SAHARAN_AFRICA), is(67632178));
        assertThat(groupedContinents.get(NORTHERN_AFRICA), is(33241259));
        assertThat(groupedContinents.get(OCEANIA), is(60422));
    }

    private static Reader getFile() {
        Reader fileReader = null;
        try {
            fileReader = new FileReader(new File("src\\test\\resources\\countries.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileReader;
    }

}
