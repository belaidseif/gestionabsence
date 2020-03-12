package tekup.data;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;
@Data
public class FormWeekMatiere {
	private LocalDate week;
	private Set<Long> ids;
}
