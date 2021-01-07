package com.katas.kata2;

import com.katas.kata2.config.FootballGamesReport;
import com.katas.kata2.config.WeatherAnalysis;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class Kata2ApplicationTests {

	@Test
	void testFootball() {
		assertEquals("Aston_Villa", new FootballGamesReport("football.dat").getSmallestDifferenceGoals());
	}

	@Test
	void testWeather() {
		assertEquals(14, new WeatherAnalysis("weather.dat").getSmallestTemperatureSpread());
	}

}
