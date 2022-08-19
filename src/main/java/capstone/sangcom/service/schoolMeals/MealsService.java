package capstone.sangcom.service.schoolMeals;

import capstone.sangcom.controller.api.response.meals.MealsResponse;
import capstone.sangcom.entity.dto.mealsSection.MealsParameterDTO;
import capstone.sangcom.entity.dto.mealsSection.MealsResponseDTO;

import java.util.List;

public interface MealsService {

    public List<MealsResponseDTO> getMeals(String MLSV_FROM_YMD, String MLSV_TO_YMD);

    public boolean getMealsOK();



////    public List<MealsDTO> getMealsInfo(String MLSV_FROM_YMD, String MLSV_TO_YMD);
//
//    public MealsDTO getMealsInfo(String MLSV_FROM_YMD, String MLSV_TO_YMD);
//
//    public boolean get(boolean success);
//
//    boolean getMeals(MealsDTO mealsData);
}
