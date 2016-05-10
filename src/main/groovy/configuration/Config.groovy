package configuration

import org.apache.commons.math3.util.FastMath

/**
 * Uncreated by stefangrecu on 08/05/16.
 */

class Config {

    static final List<Integer> restarts = (1..10).collect()
    static final List<Integer> iterations = (1..19).collect { (int) FastMath.pow(2.0, it) }
    static final List<String> inputPaths = ['']

}
