package utils

import model.Solution

/**
 * Uncreated by sergiufalcusan on 11/05/16.
 */
class VRPFileWriter {
    public static void writeSolutionToFile(Solution solution, String filePath) {
        new File(filePath).withWriter { out ->
            solution.routes.each {
                out.println it
            }
        }
    }
}
