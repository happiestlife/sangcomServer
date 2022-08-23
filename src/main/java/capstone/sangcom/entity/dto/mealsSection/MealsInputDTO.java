package capstone.sangcom.entity.dto.mealsSection;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MealsInputDTO {

    private String year;
    private String month;
    private String day;
    private ArrayList<String> dish;
    private String cal_info;

}
