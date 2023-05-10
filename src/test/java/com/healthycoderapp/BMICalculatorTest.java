//$Id$
package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

class BMICalculatorTest {
	
    static int beforevalue=1, aftervalue=1;
	
	@BeforeAll
	static void beforeAll()
	{
		System.out.println("Before All Tests "+beforevalue);
		beforevalue++;
	}
	
	@AfterAll
	static void afterAll()
	{
		System.out.println("After All Tests "+aftervalue);
		aftervalue++;
	}
	
	@BeforeEach
	void beforeEach()
	{
		System.out.println("Before each Tests "+beforevalue);
		beforevalue++;
	}
	
	@AfterEach
	void afterEach()
	{
		System.out.println("After each Tests "+aftervalue);
		aftervalue++;
	}
	
	@Nested
	class IsDietRecommendedTests{
		@ParameterizedTest(name = "weight {0}, height {1}")
		@CsvFileSource(resources="/diet-recommended-input-data.csv", numLinesToSkip=1)
		void Should_ReturnTrue_When_DietRecommended(Double personWeight, Double personHeight) {
			
			//given
		    double weight = personWeight;
		    double height = personHeight;
			
			//when
		    boolean recommend = BMICalculator.isDietRecommended(weight, height);
		    
			//then
		    assertTrue(recommend);  		
		}
		
		@Test
		void Should_ReturnFalse_When_DietNotRecommended() {
			
			//given
		    double weight = 50.0;
		    double height = 1.92;
			
			//when
		    boolean recommend = BMICalculator.isDietRecommended(weight, height);
		    
			//then
		    assertFalse(recommend);  		
		}
		
		@Test
		void Should_ThrowArithmeticException_When_HeightZero() {
			
			//given
		    double weight = 50.0;
		    double height = 0.0;
			
			//when
		    Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
		    
			//then
		    assertThrows(ArithmeticException.class, executable);  		
		}
	}
	
	@Nested
	class FindCoderWithWorstBMITests{	
		@Test
		void Should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty()
		{
			
		     //given
			 List<Person> person = new ArrayList<>();
			 person.add(new Person(1.80, 60.0));
			 person.add(new Person(1.82, 98.0));
			 person.add(new Person(1.82, 64.7));
			 
			//when
			 Person coderWorstBMI = BMICalculator.findCoderWithWorstBMI(person);
			
			//then
			 assertAll (
			 () -> assertEquals(1.82, coderWorstBMI.getHeight()),
			 () -> assertEquals(98.0, coderWorstBMI.getWeight()));
		}
		
		@Test
		void Should_ReturnNullWorstBMICoder_When_CoderListEmpty()
		{
			
		     //given
			 List<Person> person = new ArrayList<>();
					 
			//when
			 Person coderWorstBMI = BMICalculator.findCoderWithWorstBMI(person);
			
			//then
			 assertNull(coderWorstBMI);
		}
		
		@Test
		void Should_ReturnCoderWithWorstBMIIn1Ms_When_CoderListHas10000Elements()
		{
					//given
					List<Person> person = new ArrayList<>();
					for(int i=0; i<10000; i++)
					{
						person.add(new Person(1.0+i, 10.0+i));
					}
					 
					//when
					Executable executable = () -> BMICalculator.findCoderWithWorstBMI(person);
					
					//then
					assertTimeout(Duration.ofMillis(500), executable);	
		}
	}
	
	@Nested
	class getBMIScoresTests{
		@Test
		void Should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty()
		{
			
		     //given
			 List<Person> person = new ArrayList<>();
			 person.add(new Person(1.80, 60.0));
			 person.add(new Person(1.82, 98.0));
			 person.add(new Person(1.82, 64.7));
			 double[] expected = {18.52, 29.59, 19.53};
					 
			//when
			 double[] bmiscores = BMICalculator.getBMIScores(person);
			
			//then
			 assertArrayEquals(expected,bmiscores);
		}
	}
}
