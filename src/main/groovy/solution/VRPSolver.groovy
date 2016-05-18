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

    def static Map<String, List<Solution>> generateSolutions() {
        Map<String, List<Solution>> solution = [:]
        VRPFileReader.readInputFiles().eachWithIndex { VRPData entry, Integer i ->
            solution.put(entry.name, RandomSolutionGenerator.nextBatchSolutions(entry, Config.POPULATION_SIZE))
        }
        solution
    }
}
