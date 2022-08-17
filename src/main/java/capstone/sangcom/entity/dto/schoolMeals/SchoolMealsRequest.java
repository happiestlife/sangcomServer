package capstone.sangcom.entity.dto.schoolMeals;
public class SchoolMealsRequest {

    private String countryCode; // 교육청 코드 B10
    private String schulCode; // 학교 코드 7010178
    private String insttNm; // 학교 이름
    private String schulCrseScCode; // 학교종류 코드
    private String schMmealScCode; // 급식종류 코드 2
    private String schYmd; // 날짜

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSchulCode() {
        return schulCode;
    }

    public void setSchulCode(String schulCode) {
        this.schulCode = schulCode;
    }

    public String getInsttNm() {
        return insttNm;
    }

    public void setInsttNm(String insttNm) {
        this.insttNm = insttNm;
    }

    public String getSchulCrseScCode() {
        return schulCrseScCode;
    }

    public void setSchulCrseScCode(String schulCrseScCode) {
        this.schulCrseScCode = schulCrseScCode;
    }

    public String getSchMmealScCode() {
        return schMmealScCode;
    }

    public void setSchMmealScCode(String schMmealScCode) {
        this.schMmealScCode = schMmealScCode;
    }

    public String getSchYmd() {
        return schYmd;
    }

    public void setSchYmd(String schYmd) {
        this.schYmd = schYmd;
    }

    @Override
    public String toString() {
        return "SchoolMealsRequest{" +
                "countryCode='" + countryCode + '\'' +
                ", schulCode='" + schulCode + '\'' +
                ", insttNm='" + insttNm + '\'' +
                ", schulCrseScCode='" + schulCrseScCode + '\'' +
                ", schMmealScCode='" + schMmealScCode + '\'' +
                ", schYmd='" + schYmd + '\'' +
                '}';
    }

    //    private String schoolCode;
//    private String schulKndScCode;
//    private String schulCrseScCode;

//    private String KEY; // 인증키
//    private String Type; // 호출 문서(xml, json)
//    private int pIndex; // 페이지 위치
//    private int pSize; // 페이지당 신청 숫자
//
//    private String ATPT_OFCDC_SC_CODE; // 시도교육청코드
//    private String SD_SCHUL_CODE; // 표준학교코드
//    private String MMEAL_SC_CODE; // 식사코드
//    private String MLSV_YMD; // 급식일자
//    private String MLSV_FROM_YMD; // 급식시작일자
//    private String MLSV_TO_YMD; // 급식종료일자

//    @Override
//    public String toString() {
//        return "";
//    }
}

