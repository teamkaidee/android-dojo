package com.app.kaidee.counter.repository

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CounterRepositoryImplTest {

	private lateinit var repository: CounterRepository

	@Before
	fun setUp() {
		repository = CounterRepositoryImpl()
	}

	@Test
	fun `When generate session should get 2 different number and less than 5, greater than 0`() {
		// GIVEN

		// WHEN
		val (goal, startNumber) = repository.generateGameSession()

		// THEN
		assertTrue(goal != startNumber)
		assertTrue(goal in 1..4)
		assertTrue(startNumber in 1..4)
	}

	@Test
	fun `When goal is equal value should win`() {
		// GIVEN

		// WHEN
		val (goal, startNumber) = repository.generateGameSession()
		val diff = goal - startNumber
		val isWin = repository.isWin(diff)

		// THEN
		assertTrue(isWin)
	}

	@Test
	fun `When goal is not equal value should not win`() {
		// GIVEN

		// WHEN
		val (goal, startNumber) = repository.generateGameSession()
		val value = goal + startNumber
		val isWin = repository.isWin(value)

		// THEN
		assertFalse(isWin)
	}

}