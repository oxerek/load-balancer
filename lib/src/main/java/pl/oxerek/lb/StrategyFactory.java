package pl.oxerek.lb;

import java.util.Map;

import static pl.oxerek.lb.StrategyFactory.Type.RANDOM;
import static pl.oxerek.lb.StrategyFactory.Type.ROUND_ROBIN;
import static pl.oxerek.lb.StrategyFactory.Type.WEIGHTED_ROUND_ROBIN;

public class StrategyFactory {

    private final static Map<Type, Strategy> strategies = Map.of(
            RANDOM, new RandomBalancingStrategy(),
            ROUND_ROBIN, new RoundRobinStrategy(),
            WEIGHTED_ROUND_ROBIN, new WeightedRoundRobinStrategy()
    );;

    public static Strategy get(Type type) {
        return strategies.get(type);
    }

    public enum Type {
        RANDOM, ROUND_ROBIN, WEIGHTED_ROUND_ROBIN;
    }
}
