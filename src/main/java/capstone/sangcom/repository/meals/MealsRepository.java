package capstone.sangcom.repository.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;

import java.util.ArrayList;

public interface MealsRepository {

    public ArrayList<String> getMealsInfo (MealsOutputDTO mealsOutputDTO);

}
