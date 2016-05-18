package utils

import model.Solution
import org.apache.commons.io.FileUtils

/**
 * Uncreated by sergiufalcusan on 11/05/16.
 */
class VRPFileWriter {
    public static void writeSolutionToFile(String name, List<Solution> solutions) {
            File file = new File("src/main/resources/" + name + ".txt")
            List<String> lines = solutions.collect { solution -> solution.label }
            FileUtils.writeLines(file, lines)
    }


}
