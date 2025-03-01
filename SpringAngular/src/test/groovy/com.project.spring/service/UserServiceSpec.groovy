
package com.project.spring.service

import spock.lang.Specification

class UserServiceSpec extends Specification {

    def "should return true when user is valid"() {
        given: "A user service"
        def userService = new UserService()

        when: "Validating a user"
        def result = userService.isValidUser("John Doe")

        then: "It should return true"
        result == true
    }
}
