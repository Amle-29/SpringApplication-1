package com.project.spring.repository

import com.project.spring.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

@DataJpaTest
class UserRepositorySpec extends Specification {

    @Autowired
    UserRepository userRepository

    def "should save and retrieve user"() {
        given:
        def user = new User(name: "John Doe", email: "john@example.com")

        when:
        def savedUser = userRepository.save(user)
        def retrievedUser = userRepository.findById(savedUser.id).get()

        then:
        retrievedUser.name == "John Doe"
        retrievedUser.email == "john@example.com"
    }

    def "should delete user"() {
        given:
        def user = new User(name: "Jane Doe", email: "jane@example.com")
        def savedUser = userRepository.save(user)

        when:
        userRepository.deleteById(savedUser.id)
        def found = userRepository.findById(savedUser.id)

        then:
        found.isEmpty()
    }
}
