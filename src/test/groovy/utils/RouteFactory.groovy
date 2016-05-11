package utils

import model.City
import model.Route

/**
 * Uncreated by stefangrecu on 11/05/16.
 */
class RouteFactory {

    private static final CityFactory factory = new CityFactory()

    public static Route create(City depot = factory.create().with {
        it.depot = true
        return it
    }, List<City> content = []) {
        Route route = new Route(depot)
        content.each { city ->
            route.addCity(city)
        }
        return route
    }
}
