package capstone.sangcom.entity.dto.mealsSection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MealsOutputDTO {

//    public MealsOutputDTO(String MMEAL_SC_NM, String CAL_INFO, String DDISH_NM) {
//        this.MMEAL_SC_NM = MMEAL_SC_NM;
//        this.CAL_INFO = CAL_INFO;
//        this.DDISH_NM = DDISH_NM;
//    }

//    private String MMEAL_SC_NM; // 식사구분명
//    private String CAL_INFO; // 칼로리
//    private String DDISH_NM; // 요리명

    private String ATPT_OFCDC_SC_CODE;
    private String ATPT_OFCDC_SC_NM;
    private String SD_SCHUL_CODE;
    private String SCHUL_NM;
    private String MMEAL_SC_CODE;
    private String MMEAL_SC_NM;
    private String MLSV_YMD;
    private String MLSV_FGR;
    private String DDISH_NM;
    private String ORPLC_INFO;
    private String CAL_INFO;
    private String NTR_INFO;
    private String MLSV_FROM_YMD;
    private String MLSV_TO_YMD;

//    private final String year;
//    private final String month;
//    private final String day;
//    private final ArrayList<String> dish;
//    private final String cal_info;
}
