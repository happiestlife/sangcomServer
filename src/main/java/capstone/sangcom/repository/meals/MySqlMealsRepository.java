package capstone.sangcom.repository.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MySqlMealsRepository implements MealsRepository{

    @Override // 급식 출력
    public ArrayList<String> getMealsInfo(MealsOutputDTO mealsOutputDTO) {
        return mealsOutputDTO.getDish();
    }

}
