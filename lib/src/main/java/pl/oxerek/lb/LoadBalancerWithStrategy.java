package pl.oxerek.lb;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoadBalancerWithStrategy {

    private final static int DEFAULT_LIMIT = 10;

    private final int instancesLimit;

    private final Map<String, Instance> instances;

    private final Strategy strategy;

    public LoadBalancerWithStrategy(Map<String, Instance> instances, Strategy strategy) {
        this(DEFAULT_LIMIT, instances, strategy);
    }

    public LoadBalancerWithStrategy(int instancesLimit, Map<String, Instance> instances, Strategy strategy) {
        this.instancesLimit = instancesLimit;
        this.instances = instances;
        this.strategy = strategy;
    }

    public Instance getInstance() {

        if (instances.size() == 0) {
            throw new NoRegisteredInstance("No registered instance.");
        }

        var index = strategy.instanceIndex(instances);
        var key = instances.keySet().stream().toList().get(index);

        return instances.get(key);
    }

    public boolean registerInstance(Instance instance) {

        checkNotNull(instance);

        if (instances.size() == instancesLimit) {
            throw new InstancesLimitExceeded("Instances limit reached.");
        }

        if (putIfUnique(instance)) {
            return true;
        }

        throw new NotUniqueInstanceException("Instance address already exists.", instance.address());
    }

    private synchronized boolean putIfUnique(Instance instance) {
        if (!instances.containsKey(instance.address())) {
            instances.put(instance.address(), instance);
            return true;
        }
        return false;
    }
}

