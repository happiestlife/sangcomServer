package capstone.sangcom.entity.dto.mealsSection;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MealsOutputDTO {
    private final String year;
    private final String month;
    private final String day;
    private final ArrayList<String> dish;
    private final String cal_info;
}
