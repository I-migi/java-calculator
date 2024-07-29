package string;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SplitTest {
	private Set<Integer> numbers;

	@BeforeEach
	void setup() {
		numbers = new HashSet<>();
		numbers.add(1);
		// numbers.add(1);
		numbers.add(2);
		numbers.add(3);
	}

	@Test
	void setSize(){
		int result = numbers.size();
		assertThat(result).isEqualTo(3);
	}

	@ParameterizedTest
	// @ValueSource(ints = {1,2,3})
	@CsvSource({"1,true","2,true","3,true","4,false","5,false"})
	void contains(int number, boolean expectedResult){
		assertThat(numbers.contains(number)).isEqualTo(expectedResult);

	}

	@Test
	public void testSplit(){
		String input = "1,2";
		String[] result = input.split(",");
		assertThat(result).contains("1","2");
	}

	@Test
	public void testSplitExactly(){
		String input = "1,2";
		String[] result = input.split(",");
		assertThat(result).containsExactly("1","2");
	}

	@Test
	public void testSubstring(){
		String input = "(1,2)";
		String inputNumber = input.substring(1,input.length()-1);
		String[] result = inputNumber.split(",");
		assertThat(result).contains("1","2");
	}

	@Test
	@DisplayName("특정 위치의 문자를 가져온다")
	public void testCharAt(){
		String input = "abc";
		char charAt0 = input.charAt(0);
		char charAt1 = input.charAt(1);
		assertThat(charAt0).isEqualTo('a');
		assertThat(charAt1).isEqualTo('b');

		assertThatThrownBy(() -> {
			char charAtOutOfBounds = input.charAt(3);
		}).isInstanceOf(StringIndexOutOfBoundsException.class);
	}




}