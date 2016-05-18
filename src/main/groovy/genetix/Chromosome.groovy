package genetix

import org.apache.commons.math3.random.RandomDataGenerator

/**
 * Uncreated by stefangrecu on 12/05/16.
 */
trait Chromosome {

    Integer generationCount
    private static final MUTATION_PROBABILITY_THRESHOLD = 0.51
    private static final SURVIVAL_PROBABILITY_THRESHOLD = 0.52
    RandomDataGenerator randomDataGenerator = new RandomDataGenerator()

    public boolean mutate() {
//        double exponent = -(randomDataGenerator.nextUniform(0.01, 0.99) / generationCount)
//        double mutationProbability = FastMath.exp(exponent)
//        return mutationProbability >= MUTATION_PROBABILITY_THRESHOLD
        return randomDataGenerator.nextUniform(0.01, 0.99) >= MUTATION_PROBABILITY_THRESHOLD
    }

    public boolean survives() {
        return randomDataGenerator.nextUniform(0.01, 0.99) >= SURVIVAL_PROBABILITY_THRESHOLD
    }

}