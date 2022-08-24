package capstone.sangcom.service.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;

import java.util.List;

public interface MealsService {

    public List<MealsOutputDTO> getMeals(String MLSV_FROM_YMD, String MLSV_TO_YMD);

//    public List<MealsOutputDTO> getMonthMeals(String MLSV_FROM_YMD, String MLSV_TO_YMD);
      // 왜 써야하지?

    public boolean getMealsOK();
    // 왜 써야하지?



////    public List<MealsDTO> getMealsInfo(String MLSV_FROM_YMD, String MLSV_TO_YMD);
//
//    public MealsDTO getMealsInfo(String MLSV_FROM_YMD, String MLSV_TO_YMD);
//
//    public boolean get(boolean success);
//
//    boolean getMeals(MealsDTO mealsData);
}
