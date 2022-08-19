package capstone.sangcom.controller.api.response.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MealsResponse {

    private boolean success;
    private final List<MealsResponseDTO> paths;
}
