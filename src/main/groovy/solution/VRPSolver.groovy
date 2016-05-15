package solution

import configuration.Config
import generator.RandomSolutionGenerator
import model.Solution
import utils.VRPFileReader

/**
 * Uncreated by stefangrecu on 12/05/16.
 */
class VRPSolver {

    def static List<List<Solution>> generateSolutions() {
        VRPFileReader.readInputFiles().collect { data ->
            RandomSolutionGenerator.nextBatchSolutions(data, Config.POPULATION_SIZE)
        }
    }
}
