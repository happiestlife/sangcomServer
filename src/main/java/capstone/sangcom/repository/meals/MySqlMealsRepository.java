package capstone.sangcom.repository.meals;

import capstone.sangcom.entity.dto.mealsSection.MealsInputDTO;
import capstone.sangcom.entity.dto.mealsSection.MealsOutputDTO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MySqlMealsRepository implements MealsRepository{

//    private final RowMapper<MealsOutputDTO> mealsRowMapper;
//
//    private final NamedParameterJdbcTemplate jdbcTemplate;

//    public MySqlBoardRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        jdbcTemplate = namedParameterJdbcTemplate;
//        mealsRowMapper = new MySqlBoardRepository.MealsRowMapper();
//    }
//
//    public MySqlMealsRepository(RowMapper<MealsOutputDTO> mealsRowMapper, NamedParameterJdbcTemplate jdbcTemplate) {
//        this.mealsRowMapper = mealsRowMapper;
//        this.jdbcTemplate = jdbcTemplate;
//    }

    private final MealsOutputDTO mealsOut;

    public MySqlMealsRepository(MealsOutputDTO mealsOut) {
        this.mealsOut = mealsOut;
    }

    @Override // 급식 출력
    public List<MealsOutputDTO> getMealsInfo(MealsInputDTO mealsInput, String MLSV_FROM_YMD, String MLSV_TO_YMD) {
        return mealsOut;
    }

}
