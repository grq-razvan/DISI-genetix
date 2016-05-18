package configuration

import org.apache.commons.math3.util.FastMath

/**
 * Uncreated by stefangrecu on 08/05/16.
 */

class Config {

    /*static final List<Integer> RESTARTS = (1..10).collect()
    static final List<Integer> ITERATIONS = (1..19).collect { (int) FastMath.pow(2.0, it) }
    static final List<String> INPUT_PATHS = ['test-data/Golden_01.vrp', 'test-data/Golden_02.vrp',
                                             'test-data/Golden_03.vrp', 'test-data/Golden_04.vrp',
                                             'test-data/Golden_05.vrp', 'test-data/Golden_06.vrp',
                                             'test-data/Golden_07.vrp', 'test-data/Golden_08.vrp']
    static final Integer POPULATION_SIZE = 30 */

    static final List<String> INPUT_PATHS = ['test-data/Golden_01.vrp']
    static final List<Integer> RESTARTS = [1]
    static final List<Integer> ITERATIONS = [256]
    static final Integer POPULATION_SIZE = 30
}
