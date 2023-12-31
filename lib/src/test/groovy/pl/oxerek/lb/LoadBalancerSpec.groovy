/*
 * This Spock specification was generated by the Gradle 'init' task.
 */
package pl.oxerek.lb

import spock.lang.Specification

class LoadBalancerSpec extends Specification {

    def "should successfully register instance"() {

        given:
        def lb = new LoadBalancer(5, [:])
        def i = new Instance("192.168.1.12")

        when:
        def result = lb.registerInstance(i)

        then:
        result
    }

    def "should throw NullPointException when instance is null"() {

        given:
        def lb = new LoadBalancer(5, [:])

        when:
        lb.registerInstance(null)

        then:
        thrown NullPointerException
    }

    def "should throw NotUniqueInstanceException when instance is not unique"() {

        given:
        def lb = new LoadBalancer(5, [:])
        def i1 = new Instance("192.168.1.11")
        def i2 = new Instance("192.168.1.12")
        def i3 = new Instance("192.168.1.13")

        when:
        lb.registerInstance(i1)
        lb.registerInstance(i2)
        lb.registerInstance(i3)
        lb.registerInstance(i1)

        then:
        def exception = thrown(NotUniqueInstanceException)

        exception != null
        exception.address() == i1.address()
    }

    def "should throw InstancesLimitExceeded when instances limit is reached"() {

        given:
        def lb = new LoadBalancer(5, [:])
        def i1 = new Instance("192.168.1.11")
        def i2 = new Instance("192.168.1.12")
        def i3 = new Instance("192.168.1.13")
        def i4 = new Instance("192.168.1.14")
        def i5 = new Instance("192.168.1.15")
        def i6 = new Instance("192.168.1.16")

        when:
        lb.registerInstance(i1)
        lb.registerInstance(i2)
        lb.registerInstance(i3)
        lb.registerInstance(i4)
        lb.registerInstance(i5)
        lb.registerInstance(i6)

        then:
        thrown InstancesLimitExceeded
    }

    def "should throw NoRegisteredInstance when there is no instance"() {

        given:
        def lb = new LoadBalancer(5, [:])

        when:
        lb.getInstance()

        then:
        thrown NoRegisteredInstance
    }

    def "should successfully return instance"() {

        given:
        def lb = new LoadBalancer(5, [:])
        def i1 = new Instance("192.168.1.11")
        def i2 = new Instance("192.168.1.12")
        def i3 = new Instance("192.168.1.13")

        when:
        lb.registerInstance(i1)
        lb.registerInstance(i2)
        lb.registerInstance(i3)

        def result = lb.getInstance()

        then:
        result != null
    }
}
