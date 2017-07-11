package pl.lucash.resteasy.user

import pl.lucash.resteasy.infrastructure.AppConfig
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class UserServiceUnitSpec extends Specification {

    @Shared
    AppConfig appConfig = new AppConfig(null)
    UserService userResource = new UserService(appConfig)

    @Unroll("should add numbers i=[#i] j=[#j]")
    def "should add numbers"() {
        when:
        def result = userResource.add(i, j)

        then:
        result == expectedResult

        where:
        i  | j  || expectedResult
        10 | 20 || 30
        2  | 2  || 4
        1  | 2  || 3
        0  | 20 || 20
    }

    def "maximum of two numbers"() {
        expect:
        // exercise math method for a few different inputs
        Math.max(1, 3) == 3
        Math.max(7, 4) == 7
        Math.max(0, 0) == 0
    }
}
