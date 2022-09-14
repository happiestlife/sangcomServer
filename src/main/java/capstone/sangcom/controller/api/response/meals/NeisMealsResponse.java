package capstone.sangcom.controller.api.response.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import capstone.sangcom.entity.dto.mealsSection.NeisMealsOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NeisMealsResponse {

    private boolean success;
    private List<NeisMealsOutputDTO> neisMealsOutput;
}
