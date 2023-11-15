package pl.oxerek.lb;

import java.util.Map;
import java.util.Random;

class RandomBalancingStrategy implements Strategy {

    private final Random random;

    RandomBalancingStrategy() {
        this.random = new Random();
    }

    @Override
    public int instanceIndex(Map<String, Instance> instances) {
        return random.nextInt(instances.size());
    }
}
