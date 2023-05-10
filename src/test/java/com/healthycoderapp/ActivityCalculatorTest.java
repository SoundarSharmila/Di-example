//$Id$
package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class ActivityCalculatorTest {
	
	@Test
	void Should_ReturnBad_When_AvgBelow20()
	{
		//given
		int weeklyCardioMins = 40;
		int weeklyWorkouts = 1;
				
		//when
		String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMins, weeklyWorkouts);
		
		//then
		assertEquals("bad", actual);
	}
	
	@Test
	void Should_ReturnAverage_When_AvgBetween20And40()
	{
		//given
		int weeklyCardioMins = 40;
		int weeklyWorkouts = 3;
				
		//when
		String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMins, weeklyWorkouts);
		
		//then
		assertEquals("average", actual);
	}
	
	@Test
	void Should_ReturnGood_When_AvgAbove40()
	{
		//given
		int weeklyCardioMins = 40;
		int weeklyWorkouts = 7;
				
		//when
		String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMins, weeklyWorkouts);
		
		//then
		assertEquals("good", actual);	
	}
	
	@Test
	void Should_ThrowException_When_InpuBelowZero()
	{
		//given
		int weeklyCardioMins = -40;
		int weeklyWorkouts = 7;
				
		//when
		Executable executable = () -> ActivityCalculator.rateActivityLevel(weeklyCardioMins, weeklyWorkouts);
		
		//then
		assertThrows(RuntimeException.class, executable);
	}
}
