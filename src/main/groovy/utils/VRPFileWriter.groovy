package utils

import model.Solution
import org.apache.commons.io.FileUtils

/**
 * Uncreated by sergiufalcusan on 11/05/16.
 */
class VRPFileWriter {
    public static void writeSolutionToFile(Map<String, List<Solution>> params) {
        params.each {
            File file = new File("src/main/resources/" + it.key + ".txt")
            List<String> lines = it.value.collect { solution -> solution.label }
            FileUtils.writeLines(file, lines)
        }
    }


}
