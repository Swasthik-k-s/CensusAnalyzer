package com.test;

import org.junit.Assert;
import org.junit.Test;

import com.census.CustomException;
import com.census.StateCensus;
import com.census.StateCensusAnalyzer;
import com.census.StateCode;

public class StateCensusTest {
	
	@Test
	public void givenCsvFile_with8rowscomparingwith8_returnstrue() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/StateCensusData.csv");
			Assert.assertEquals(8, analyser.readStateRecord(StateCensus.class));
		} catch (CustomException e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void givenCsvFile_whichIsIncorrect_expectsException() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/StateCensusData1.csv");
			analyser.readStateRecord(StateCensus.class);
		} catch (CustomException e) {
			Assert.assertEquals("File not found", e.getMessage());
			System.out.println(e);
		}
	}
	
	@Test
	public void givenCsvFile_whichIsWrongType_expectsException() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/book1.txt");
			analyser.readStateRecord(StateCensus.class);
		} catch (CustomException e) {
			Assert.assertEquals("Incorrect File Type", e.getMessage());
			System.out.println(e);
		}
	}

	@Test
	public void givenCsvFile_withwrongdelimiter_expectsException() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/InvalidDelimeter.csv");
			analyser.readStateRecord(StateCensus.class);
		} catch (CustomException e) {

			System.out.println(e);
			Assert.assertEquals("Incorrect Delimeter", e.getMessage());
		}
	}
	
	@Test
	public void givenCsvFile_withwrongdHeader_expectsException() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/IncorrectHeader.csv");
			analyser.readStateRecord(StateCensus.class);
		} catch (CustomException e) {

			System.out.println(e);
			Assert.assertEquals("Header doesn't match", e.getMessage());
		}
	}
	
	@Test
	public void givenStateCodeCsvFile_with3rowscomparingwith3_returnstrue() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/StateCode.csv");
			Assert.assertEquals(3, analyser.readStateRecord(StateCode.class));
		} catch (CustomException e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void givenStateCodeCsvFile_whichIsIncorrect_expectsException() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/StateCode1.csv");
			analyser.readStateRecord(StateCode.class);
		} catch (CustomException e) {
			Assert.assertEquals("File not found", e.getMessage());
			System.out.println(e);
		}
	}
	
	@Test
	public void givenStateCodeCsvFile_whichIsWrongType_expectsException() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/book1.txt");
			analyser.readStateRecord(StateCode.class);
		} catch (CustomException e) {
			Assert.assertEquals("Incorrect File Type", e.getMessage());
			System.out.println(e);
		}
	}
	
	@Test
	public void givenStateCodeCsvFile_withwrongDelimeter_expectsException() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/DelimeterCode.csv");
			analyser.readStateRecord(StateCode.class);
		} catch (CustomException e) {

			System.out.println(e);
			Assert.assertEquals("Incorrect Delimeter", e.getMessage());
		}
	}
	
	@Test
	public void givenStateCodeCsvFile_withwrongdHeader_expectsException() {
		try {
			StateCensusAnalyzer analyser = new StateCensusAnalyzer(
					"/data/IncorrectHeaderStateCode.csv");
			analyser.readStateRecord(StateCode.class);
		} catch (CustomException e) {

			System.out.println(e);
			Assert.assertEquals("Header doesn't match", e.getMessage());
		}
	}
}

