package tekup.web;


import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Converter implements org.springframework.core.convert.converter.Converter<String, LocalDate>{

	@Override
	public LocalDate convert(String source) {
		return LocalDate.parse(source).plusDays(1);
	}

}
