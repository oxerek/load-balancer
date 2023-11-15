package pl.oxerek.lb;

import java.util.Map;

class RoundRobinStrategy implements Strategy {

    private int counter;

    RoundRobinStrategy() {
        this.counter = 0;
    }

    @Override
    public synchronized int instanceIndex(Map<String, Instance> instances) {

        if (counter == instances.size()) {
            counter = 0;
        }

        return counter++;
    }
}
