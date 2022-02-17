package com.example.essstore


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class testingTest {
    @Test
    fun `empty username returns false`() {
        val result = testing.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = testing.validateRegistrationInput(
            "gama",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`() {
        val result = testing.validateRegistrationInput(
            "ghulamrasuldev",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly confirmed password returns false`() {
        val result = testing.validateRegistrationInput(
            "osman",
            "123456",
            "abcdefg"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = testing.validateRegistrationInput(
            "hello",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `less than 2 digit password returns false`() {
        val result = testing.validateRegistrationInput(
            "mohsin",
            "abcdefg5",
            "abcdefg5"
        )
        assertThat(result).isFalse()
    }
}