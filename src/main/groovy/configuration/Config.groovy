package configuration
/**
 * Uncreated by stefangrecu on 08/05/16.
 */

class Config {

    static final List<String> FLK_INPUT = ['test-data/Golden_01.vrp', 'test-data/Golden_02.vrp',
                                           'test-data/Golden_03.vrp', 'test-data/Golden_04.vrp']

    static final List<String> GRQ_INPUT = ['test-data/Golden_05.vrp', 'test-data/Golden_06.vrp',
                                           'test-data/Golden_07.vrp', 'test-data/Golden_08.vrp']

    static final def RESTARTS = [1, 5, 10]
    static final List<Integer> ITERATIONS = [100, 200, 500, 1000]
    static final Integer POPULATION_SIZE = 64
}
