package com.epam.training.sportsbetting.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class ConsoleReaderWriter {
	public String readLine() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String result = "";
		try {
			result = bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
		return result;
	}

	public void printLine(String message) {
		System.out.println(message);
	}
}
