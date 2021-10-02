package com.census;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyzer {
	private String filePath = "";
	
	public StateCensusAnalyzer() {
		
	}

	public StateCensusAnalyzer(String filePath) {
		this.filePath = filePath;
	}

	public <T> int readStateRecord(Class<T> className) throws CustomException {
		int count = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			@SuppressWarnings("unchecked")
			CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader).withType(StateCensus.class).withIgnoreLeadingWhiteSpace(true).build();
			Iterator<T> userIterator = csvToBean.iterator();
			while (userIterator.hasNext()) {
				T state = userIterator.next();
				count++;
				System.out.println(count);

				if (state instanceof StateCensus) { // comparing if read file is of CsvStateCensus class
					if (((StateCensus) state).getState() == null || ((StateCensus) state).getPopulation() == 0
							|| ((StateCensus) state).getArea() == 0) {

						throw new CustomException(CustomException.ExceptionType.INCORRECT_HEADER, "Header doesn't match");
					}
				}

				if (state instanceof StateCode) { // comparing if read file is of CsvStateCode class
					if (((StateCode) state).getSrNo() == 0 || ((StateCode) state).getStateName() == null
							|| ((StateCode) state).getStateCode() == null) {

						throw new CustomException(CustomException.ExceptionType.INCORRECT_HEADER, "Header doesn't match");
					}
				}
			}

		} catch(NoSuchFileException e) {
			if (filePath.contains(".csv")) {
				throw new CustomException(CustomException.ExceptionType.FILE_NOT_FOUND, "File not found");
			}
			throw new CustomException(CustomException.ExceptionType.INCORRECT_TYPE, "Incorrect File Type");
		} catch (RuntimeException e) {
			throw new CustomException(CustomException.ExceptionType.DELIMITER_NOT_FOUND, "Incorrect Delimeter");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
	
//	public static void main(String[] args) {
//		StateCensusAnalyzer analyser = new StateCensusAnalyzer(
//				"data/StateCensusData.csv");
//		try {
//			analyser.readStateRecord(StateCensus.class);
//		} catch (CustomException e) {
//			e.printStackTrace();
//		}
//	}
}

