package string;

import java.util.regex.Pattern;

public class Cal {
	private static final String DEFAULT_DELIMITERS = ",|:";

	public int add(String input){
		if(input == null || input.isEmpty()){
			return 0;
		}
		String delimiter = DEFAULT_DELIMITERS;
		String numbers = input;

		if(input.startsWith("//")){
			int newlineIndex = input.indexOf('\n');
			if(newlineIndex == -1){
				throw new RuntimeException("잘못된 입력 형식");
			}
			delimiter  = Pattern.quote(input.substring(2,newlineIndex));
			numbers = input.substring(newlineIndex +1);
		}
		return calculateSum(numbers,delimiter);
	}

	private int calculateSum(String numbers, String delimiter) {
		String[] tokens = numbers.split(delimiter);
		int total = 0;

		for (String token : tokens) {
			int number = parseNumber(token);
			if (number < 0) {
				throw new RuntimeException("음수는 허용되지 않습니다: " + number);
			}
			total += number;
		}

		return total;
	}
	private int parseNumber(String token) {
		try {
			return Integer.parseInt(token);
		} catch (NumberFormatException e) {
			throw new RuntimeException("잘못된 숫자 형식: " + token);
		}
	}
}
