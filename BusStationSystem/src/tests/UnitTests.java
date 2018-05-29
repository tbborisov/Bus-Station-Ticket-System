package tests;

import java.io.IOException;

import org.junit.Test;

import station.Station;

import org.junit.Assert;
import org.junit.Before;

public class UnitTests {

	Station station;

	@Before
	public void setup() throws IOException {
		station = new Station();
		station.sellTicket("Варна", 1, 1);
		station.sellTicket("Пловдив", 2, 0);
		station.sellTicket("Благоевград", 3, 1);
		station.sellTicket("Силистра", 4, 0);
		station.sellTicket("София", 5, 1);
		station.sellTicket("Бургас", 6, 0);
		station.sellTicket("ВеликоТърново", 7, 0);
	}

	@Test
	public void soldTicketsFromEveryRouteTest() {
		String resultString = new String();
		resultString = station.soldTicketsFromEveryRoute();
		String expectedString = "За маршрута до ﻿Варна са продадени 0 места.\n"
				+ "За маршрута до Пловдив са продадени 1 места.\n"
				+ "За маршрута до Благоевград са продадени 1 места.\n"
				+ "За маршрута до Силистра са продадени 1 места.\n" 
				+ "За маршрута до София са продадени 1 места.\n"
				+ "За маршрута до Бургас са продадени 1 места.\n"
				+ "За маршрута до ВеликоТърново са продадени 1 места.\n";
		Assert.assertTrue(resultString.equals(expectedString));
	}

	@Test
	public void soldTicketsFromEveryRouteExpandedTest() {
		String resultString = new String();
		resultString = station.soldTicketsFromEveryRouteExpanded();
		String expectedString = "За маршрута до ﻿Варна са продадени 0 онлайн билета и 0 билета на каса.\n"
				+ "За маршрута до Пловдив са продадени 0 онлайн билета и 1 билета на каса.\n"
				+ "За маршрута до Благоевград са продадени 1 онлайн билета и 0 билета на каса.\n"
				+ "За маршрута до Силистра са продадени 0 онлайн билета и 1 билета на каса.\n"
				+ "За маршрута до София са продадени 1 онлайн билета и 0 билета на каса.\n"
				+ "За маршрута до Бургас са продадени 0 онлайн билета и 1 билета на каса.\n"
				+ "За маршрута до ВеликоТърново са продадени 0 онлайн билета и 1 билета на каса.\n";
		Assert.assertTrue(resultString.equals(expectedString));
	}
	
	@Test
	public void freeSeatsFromEveryRouteTest() {
		String resultString = new String();
		resultString = station.freeSeatsFromEveryRoute();
		String expectedString = "Маршрута до ﻿Варна има 48 останали свободни места.\n"
				+ "Маршрута до Пловдив има 47 останали свободни места.\n"
				+ "Маршрута до Благоевград има 32 останали свободни места.\n"
				+ "Маршрута до Силистра има 29 останали свободни места.\n"
				+ "Маршрута до София има 54 останали свободни места.\n"
				+ "Маршрута до Бургас има 47 останали свободни места.\n"
				+ "Маршрута до ВеликоТърново има 35 останали свободни места.\n";
		Assert.assertTrue(resultString.equals(expectedString));
	}
}