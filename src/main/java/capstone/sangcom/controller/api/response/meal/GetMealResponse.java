package capstone.sangcom.controller.api.response.meal;

import capstone.sangcom.entity.dto.mealSection.MealDTO;
import lombok.Data;

import java.util.List;

@Data
public class GetMealResponse {
    private final boolean success;
    private final List<MealDTO> mealInfo;
}
