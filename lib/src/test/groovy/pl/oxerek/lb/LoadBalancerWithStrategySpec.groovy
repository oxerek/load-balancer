package pl.oxerek.lb

import spock.lang.Specification

import static pl.oxerek.lb.StrategyFactory.Type.*

class LoadBalancerWithStrategySpec extends Specification {

    def "should successfully return random instance"() {

        given:
        def lb = new LoadBalancerWithStrategy(5, [:], StrategyFactory.get(RANDOM))
        def i1 = new Instance("192.168.1.11")
        def i2 = new Instance("192.168.1.12")

        when:
        lb.registerInstance(i1)
        lb.registerInstance(i2)

        def result = lb.getInstance()

        then:
        result != null
    }

    def "should successfully return next instance"() {

        given:
        def lb = new LoadBalancerWithStrategy(5, [:], StrategyFactory.get(ROUND_ROBIN))
        def i1 = new Instance("192.168.1.11")
        def i2 = new Instance("192.168.1.12")
        def i3 = new Instance("192.168.1.13")

        when:
        lb.registerInstance(i1)
        lb.registerInstance(i2)
        lb.registerInstance(i3)

        def result1 = lb.getInstance()
        def result2 = lb.getInstance()
        def result3 = lb.getInstance()
        def result4 = lb.getInstance()
        def result5 = lb.getInstance()
        def result6 = lb.getInstance()

        then:
        result1.address() == "192.168.1.11"
        result2.address() == "192.168.1.12"
        result3.address() == "192.168.1.13"
        result4.address() == "192.168.1.11"
        result5.address() == "192.168.1.12"
        result6.address() == "192.168.1.13"
    }

    def "should successfully return next instance considering weights"() {

        def instances = [
                "192.168.1.11": new Instance("192.168.1.11", 1),
                "192.168.1.12": new Instance("192.168.1.12", 2),
                "192.168.1.13": new Instance("192.168.1.13", 3),
                "192.168.1.14": new Instance("192.168.1.14", 6),
        ]
        given:
        def lb = new LoadBalancerWithStrategy(5, instances, StrategyFactory.get(WEIGHTED_ROUND_ROBIN))

        when:
        def result1 = lb.getInstance()
        def result2 = lb.getInstance()
        def result3 = lb.getInstance()
        def result4 = lb.getInstance()
        def result5 = lb.getInstance()
        def result6 = lb.getInstance()
        def result7 = lb.getInstance()
        def result8 = lb.getInstance()
        def result9 = lb.getInstance()
        def result10 = lb.getInstance()
        def result11 = lb.getInstance()
        def result12 = lb.getInstance()
        def result13 = lb.getInstance()
        def result14 = lb.getInstance()
        def result15 = lb.getInstance()

        then:
        result1.address() == "192.168.1.11"
        result2.address() == "192.168.1.12"
        result3.address() == "192.168.1.12"
        result4.address() == "192.168.1.13"
        result5.address() == "192.168.1.13"
        result6.address() == "192.168.1.13"
        result7.address() == "192.168.1.14"
        result8.address() == "192.168.1.14"
        result9.address() == "192.168.1.14"
        result10.address() == "192.168.1.14"
        result11.address() == "192.168.1.14"
        result12.address() == "192.168.1.14"
        result13.address() == "192.168.1.11"
        result14.address() == "192.168.1.12"
        result15.address() == "192.168.1.12"
    }
}
