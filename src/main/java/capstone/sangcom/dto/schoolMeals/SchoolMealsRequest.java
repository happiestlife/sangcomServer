package capstone.sangcom.dto.schoolMeals;
public class SchoolMealsRequest {

//    private String schoolCode;
//    private String schulKndScCode;
//    private String schulCrseScCode;

    private String KEY; // 인증키
    private String Type; // 호출 문서(xml, json)
    private int pIndex; // 페이지 위치
    private int pSize; // 페이지당 신청 숫자

    private String ATPT_OFCDC_SC_CODE; // 시도교육청코드
    private String SD_SCHUL_CODE; // 표준학교코드
    private String MMEAL_SC_CODE; // 식사코드
    private String MLSV_YMD; // 급식일자
    private String MLSV_FROM_YMD; // 급식시작일자
    private String MLSV_TO_YMD; // 급식종료일자



//    @Override
//    public String toString() {
//        return "";
//    }
}

