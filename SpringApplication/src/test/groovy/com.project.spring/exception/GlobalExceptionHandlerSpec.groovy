package com.project.spring.exception

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

class GlobalExceptionHandlerSpec extends Specification {

    MockMvc mockMvc

    def setup() {
        def exceptionHandler = new GlobalExceptionHandler()
        mockMvc = MockMvcBuilders.standaloneSetup(exceptionHandler).build()
    }

    def "should handle NoSuchElementException"() {
        expect:
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath('$.message').value("Resource not found"))
    }
}
