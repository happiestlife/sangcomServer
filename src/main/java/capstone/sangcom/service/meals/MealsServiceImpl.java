package capstone.sangcom.service.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealsServiceImpl implements MealsService{
    @Override
    public List<MealsOutputDTO> getMeals(String MLSV_FROM_YMD, String MLSV_TO_YMD) {
        return null;
    }

    @Override
    public boolean getMealsOK() {
        return false;
    }
}
