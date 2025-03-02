package com.project.spring.service

import com.project.spring.entity.User
import com.project.spring.repository.UserRepository
import spock.lang.Specification

class UserServiceSpec extends Specification {

    def userRepository = Mock(UserRepository)
    def userService = new UserService(userRepository)

    def "should return user by id"() {
        given:
        def user = new User(id: 1, name: "John Doe", email: "john@example.com")
        userRepository.findById(1) >> Optional.of(user)

        when:
        def result = userService.getUserById(1)

        then:
        result.name == "John Doe"
        result.email == "john@example.com"
    }

    def "should throw exception if user not found"() {
        given:
        userRepository.findById(99) >> Optional.empty()

        when:
        userService.getUserById(99)

        then:
        thrown(NoSuchElementException)
    }

    def "should create a new user"() {
        given:
        def user = new User(name: "Jane Doe", email: "jane@example.com")
        userRepository.save(_) >> { User u -> u.id = 1; u }

        when:
        def result = userService.createUser(user)

        then:
        result.id == 1
        result.name == "Jane Doe"
    }

    def "should update an existing user"() {
        given:
        def existingUser = new User(id: 1, name: "Old Name", email: "old@example.com")
        def updatedUser = new User(id: 1, name: "New Name", email: "new@example.com")
        userRepository.findById(1) >> Optional.of(existingUser)
        userRepository.save(_) >> updatedUser

        when:
        def result = userService.updateUser(1, updatedUser)

        then:
        result.name == "New Name"
        result.email == "new@example.com"
    }

    def "should delete a user"() {
        given:
        def existingUser = new User(id: 1)
        userRepository.findById(1) >> Optional.of(existingUser)

        when:
        userService.deleteUser(1)

        then:
        1 * userRepository.deleteById(1)
    }
}
