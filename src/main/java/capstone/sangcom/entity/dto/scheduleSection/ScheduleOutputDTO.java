package capstone.sangcom.entity.dto.scheduleSection;


import lombok.Data;

/**
 * API명세서 - 성공 응답 메시지 - List<schedule> 안의 변수들
 */

@Data
public class ScheduleOutputDTO {

    private String ATPT_OFCDC_SC_CODE; //
    private String ATPT_OFCDC_SC_NM;
    private String SD_SCHUL_CODE; //
    private String AY;
    private String DGHT_CRSE_SC_NM;
    private String SCHUL_CRSE_SC_NM;
    private String SBTR_DD_SC_NM;
    private String AA_YMD; //
    private String EVENT_NM;
    private String EVENT_CNTNT;
    private String ONE_GRADE_EVENT_YN;
    private String TW_GRADE_EVENT_YN;
    private String THREE_GRADE_EVENT_YN;
    private String FR_GRADE_EVENT_YN;
    private String FIV_GRADE_EVENT_YN;
    private String SIX_GRADE_EVENT_YN;
    private String LOAD_DTM;
}
