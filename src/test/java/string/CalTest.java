package string;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CalTest {
	private final Cal calculator = new Cal();

	@Test
	void testEmptyString() {
		assertThat(calculator.add("")).isEqualTo(0);
	}

	@Test
	void testCommaSeparatedNumbers() {
		assertThat(calculator.add("1,2")).isEqualTo(3);
		assertThat(calculator.add("1,2,3")).isEqualTo(6);
	}

	@Test
	void testColonSeparatedNumbers() {
		assertThat(calculator.add("1:2")).isEqualTo(3);
		assertThat(calculator.add("1:2:3")).isEqualTo(6);
	}

	@Test
	void testMixedSeparators() {
		assertThat(calculator.add("1,2:3")).isEqualTo(6);
	}

	@Test
	void testCustomDelimiter() {
		assertThat(calculator.add("//;\n1;2;3")).isEqualTo(6);
	}

	@Test
	void testNegativeNumbers() {
		assertThatThrownBy(() -> calculator.add("1,-2,3"))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("음수는 허용되지 않습니다");
	}

	@Test
	void testInvalidNumber() {
		assertThatThrownBy(() -> calculator.add("1,a,3"))
			.isInstanceOf(RuntimeException.class)
			.hasMessageContaining("잘못된 숫자 형식");
	}

	@Test
	void testInvalidCustomDelimiterFormat() {
		assertThatThrownBy(() -> calculator.add("//;\n1;2,3"))
			.isInstanceOf(RuntimeException.class);
	}
}
