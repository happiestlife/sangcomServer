package capstone.sangcom.repository.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsInputDTO;
import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;

import java.util.ArrayList;
import java.util.List;

public interface MealsRepository {

    public List<MealsOutputDTO> getMealsInfo(MealsInputDTO mealsInput, String MLSV_FROM_YMD, String MLSV_TO_YMD);

}
