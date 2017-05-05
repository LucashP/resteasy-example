package pl.lucash.resteasy.resource

import spock.lang.Specification

class UserResourceSpec extends Specification {

    UserResource userResource = UserResource.instance;

    def "should add numbers"() {
        given:
        def i = 10
        def j = 20

        when:
        def result = userResource.add(i, j)

        then:
        result == 30
    }

    def "maximum of two numbers"() {
        expect:
        // exercise math method for a few different inputs
        Math.max(1, 3) == 3
        Math.max(7, 4) == 7
        Math.max(0, 0) == 0
    }
}
