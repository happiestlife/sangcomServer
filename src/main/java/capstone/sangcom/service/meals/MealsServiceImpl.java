package capstone.sangcom.service.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsInputDTO;
import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import capstone.sangcom.repository.meals.MealsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealsServiceImpl implements MealsService{

    private final MealsRepository mealsRepository;

    @Override
    @Transactional
    public List<MealsOutputDTO> getMeals(MealsInputDTO mealsInput, String MLSV_FROM_YMD, String MLSV_TO_YMD) {
        return mealsRepository.getMealsInfo(mealsInput, MLSV_FROM_YMD, MLSV_TO_YMD);
    }

    @Override
    @Transactional
    public boolean getMealsOK() {
        return true;
    }
}
