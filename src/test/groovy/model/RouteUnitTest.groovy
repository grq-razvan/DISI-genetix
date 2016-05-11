package model

import javafx.geometry.Point2D
import org.junit.Before
import org.junit.Test
import utils.CityFactory
import utils.RouteFactory

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class RouteUnitTest {
    City depot
    Route initialRoute
    CityFactory cityFactory = new CityFactory()
    RouteFactory routeFactory = new RouteFactory()

    @Before
    void before() {
        depot = cityFactory.create().with {
            it.depot = true
            it
        }
        initialRoute = routeFactory.create(depot)
    }

    @Test
    void testCreateRoute() {
        Route route = routeFactory.create(this.depot)

        assert route
        assert route.depot
        assert route.depot == this.depot
        assert route.cities
        assert !route.cities.empty
        assert route.length == 2
        assert this.depot in route.cities
    }

    @Test
    void testAddCity() {
        City city = cityFactory.create(2, 1.0, 1.0, 10)
        initialRoute.addCity(city)
        assert initialRoute.length == 3
        assert initialRoute.cities.first() == depot
        assert initialRoute.cities.last() == depot
        assert initialRoute.cities.tail().head() == city
    }

    @Test
    void testTotalRouteCost() {
        City city = cityFactory.create(2, 1.0, 1.0, 10)
        initialRoute.addCity(city)
        assert initialRoute.length == 3
        double routeCost = initialRoute.cost
        assert routeCost != 0
        assert routeCost == (new Point2D(0.0, 0.0).distance(1.0, 1.0) * 2).doubleValue()
        assert routeCost.round(3) == 2.828.doubleValue()
    }

    @Test
    void testValidation() {
        City city = cityFactory.create(2, 1.0, 1.0, 10)
        initialRoute.addCity(city)
        assert initialRoute.cost.round(3) == 2.828.doubleValue()
        assert !initialRoute.isValid(2)
        assert initialRoute.isValid(3)
    }

    @Test
    void testAddCityBatch() {
        10.times {
            initialRoute.addCity(cityFactory.create(it + 1, it * 1.0, it * 1.0, it * 10))
        }
        assert initialRoute.length == 12
        assert initialRoute.cities.first() == depot
        assert initialRoute.cities.last() == depot
        assert !initialRoute.isValid(10)
        assert !initialRoute.isValid(15)
        assert initialRoute.isValid(100)
    }


}
