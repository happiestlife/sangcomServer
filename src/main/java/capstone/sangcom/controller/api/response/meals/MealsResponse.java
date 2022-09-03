package capstone.sangcom.controller.api.response.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MealsResponse {

    private boolean success;
    private List<MealsOutputDTO> mealsOutput;


//    private final List<MealsOutputDTO> paths; // 무슨 뜻인지 모르겠다.
}
