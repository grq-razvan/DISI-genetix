package solution

import configuration.Config
import generator.RandomSolutionGenerator
import model.Solution
import model.VRPData
import utils.VRPFileReader

/**
 * Uncreated by stefangrecu on 12/05/16.
 */
class VRPSolver {

    def static Map<Integer, List<Solution>> generateSolutions() {
        Map<Integer, List<Solution>> solution = [:]
        VRPFileReader.readInputFiles().eachWithIndex { VRPData entry, Integer i ->
            solution.put(i, RandomSolutionGenerator.nextBatchSolutions(entry, Config.POPULATION_SIZE))
        }
        return solution
    }
}
