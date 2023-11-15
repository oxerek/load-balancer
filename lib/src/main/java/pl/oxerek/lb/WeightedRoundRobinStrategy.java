package pl.oxerek.lb;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;

public class WeightedRoundRobinStrategy implements Strategy {

    private List<String> addresses;

    private int counter;

    public WeightedRoundRobinStrategy() {
        this.addresses = emptyList();
        this.counter = 0;
    }

    @Override
    public synchronized int instanceIndex(Map<String, Instance> instances) {

        this.addresses = reset(instances);

        if (counter >= addresses.size()) {
            counter = 0;
        }

        var key = addresses.get(counter++);

        return instances.values().stream()
                .map(Instance::address)
                .toList()
                .indexOf(key);
    }

    private List<String> reset(Map<String, Instance> instances) {
        return instances.values().stream()
                .map(entry -> indexesFor(entry.weight(), entry.address()))
                .flatMap(Collection::stream)
                .toList();
    }

    private List<String> indexesFor(Integer weight, String key) {
        return IntStream.range(0, weight)
                .boxed()
                .map(i -> key)
                .toList();
    }
}
