package model

import org.junit.Before
import org.junit.Test
import utils.CityFactory

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class CityUnitTest {

    City city1, city2
    CityFactory factory = new CityFactory()

    @Before
    void before() {
        city1 = factory.create(10, 1.0, 1.0, 0, true)
        city2 = factory.create(15, 5.0, 5.0, 10)
    }

    @Test
    void testCreateCity() {
        Integer cityId = 1
        Double x = 0.0
        Double y = 0.0
        Integer cityDemand = 10
        City city = factory.create(cityId, x, y, cityDemand)
        assert city
        assert city.id == cityId
        assert city.coords.x == x
        assert city.coords.y == y
        assert city.demand == cityDemand
        assert !city.depot
    }

    @Test
    void testDistanceTwoCities() {
        Double distance = city1.distanceTo(city2)
        assert distance
        assert distance == city2.distanceTo(city1)
        assert distance.round(3) == 5.657.toDouble()
    }

    @Test
    void testClearDemand() {
        assert city2.demand > 0
        city2.clearDemand()
        assert city2.demand == 0
    }

}
