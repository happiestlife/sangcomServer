package capstone.sangcom.entity.dto.mealSection;

import lombok.Data;

import java.util.List;

@Data
public class MealDTO {
    private final String year;
    private final String month;
    private final String day;
    private final List<String> dish;
    private final String cal_info;
}
