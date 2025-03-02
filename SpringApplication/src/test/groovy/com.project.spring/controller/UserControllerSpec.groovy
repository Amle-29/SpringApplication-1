package com.project.spring.controller

import com.project.spring.entity.User
import com.project.spring.service.UserService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class UserControllerSpec extends Specification {

    def userService = Mock(UserService)
    MockMvc mockMvc

    def setup() {
        def userController = new UserController(userService)
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build()
    }

    def "should get user by id"() {
        given:
        def user = new User(id: 1, name: "John Doe", email: "john@example.com")
        userService.getUserById(1) >> user

        expect:
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.name').value("John Doe"))
                .andExpect(jsonPath('$.email').value("john@example.com"))
    }

    def "should return 404 if user not found"() {
        given:
        userService.getUserById(99) >> { throw new NoSuchElementException("User not found") }

        expect:
        mockMvc.perform(get("/users/99"))
                .andExpect(status().isNotFound())
    }

    def "should create a user"() {
        given:
        def user = new User(id: 1, name: "Jane Doe", email: "jane@example.com")
        userService.createUser(_) >> user

        expect:
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content('{"name": "Jane Doe", "email": "jane@example.com"}'))
                .andExpect(status().isCreated())
                .andExpect(jsonPath('$.id').value(1))
    }

    def "should update a user"() {
        given:
        def updatedUser = new User(id: 1, name: "Updated Name", email: "updated@example.com")
        userService.updateUser(1, _) >> updatedUser

        expect:
        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content('{"name": "Updated Name", "email": "updated@example.com"}'))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.name').value("Updated Name"))
    }

    def "should delete a user"() {
        expect:
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent())
    }
}
