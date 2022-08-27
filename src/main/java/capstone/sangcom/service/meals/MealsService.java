package capstone.sangcom.service.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsInputDTO;
import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;

import java.util.List;

public interface MealsService {

    public List<MealsOutputDTO> getMeals(MealsInputDTO mealsInput, String MLSV_FROM_YMD, String MLSV_TO_YMD);
    // 시도교육청코드, 표준학교코드, 시작일자, 종료일자를 받아서 MealsOutputDTO 내용을 List 형식으로 출력한다.

    public boolean getMealsOK();
    // 성공 메시지를 반환한다.


//    public List<MealsDTO> getMealsInfo(String MLSV_FROM_YMD, String MLSV_TO_YMD);
//
//    public MealsDTO getMealsInfo(String MLSV_FROM_YMD, String MLSV_TO_YMD);
//
//    public boolean get(boolean success);
//
//    boolean getMeals(MealsDTO mealsData);
}
