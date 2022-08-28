package capstone.sangcom.repository.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MySqlTimetableRepository implements TimetableRepository{

    private final String SUBJECT = "subject";

    private final String DAYS = "days";

    private final String PERIOD = "period";

    private final String LOCATION = "location";

    private final String TEACHER = "teacher";

    private final String TIMETABLE_TABLE = "timetable";

    private final String USER_ID = "user_id";

    private final RowMapper<TimetableDTO> timetableRowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

//    @Autowired
    public MySqlTimetableRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        timetableRowMapper = new TimetableRowMapper();
    }
    // MySqlBoardRepository 참고해서 작성함.

    @Override // 시간표 등록
    public boolean insertTimetable(String user_id, TimetableDTO timetableDTO){
        String query = "INSERT INTO " + TIMETABLE_TABLE + " (user_id, subject, days, period, location, teacher) VALUES (:user_id, :subject, :days, :period, :location, :teacher)";
        // if(!check[0]){
        //    await conn.query("INSERT INTO timetable (user_id, subject, days, period, location, teacher) VALUES(?, ?, ?, ?, ? ,?)",
        //            [user_id, subject, days, period, location, teacher]);

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", user_id)
                .addValue("subject", timetableDTO.getSubject())
                .addValue("days", timetableDTO.getDays())
                .addValue("period", timetableDTO.getPeriod())
                .addValue("location", timetableDTO.getLocation())
                .addValue("teacher", timetableDTO.getTeacher());

        KeyHolder key = new GeneratedKeyHolder();
        if (jdbcTemplate.update(query, params, key, new String[]{"user_id"}) != 1)
//            return -1;
            return false;

//        return key.getKey().intValue();
        return true;
    }
    // MySqlBoardRepository - "게시글 저장" 참고하여 작성함.
    // 자료형을 int로 해야하는지 boolean으로 해야하는지 헷갈림.

    @Override // 시간표 선택 (수정하기 전 단계) // 임의로 만들었음. 그냥 getTimetable() 써도 되려나..?
    public List<TimetableDTO> selectTimetable(String user_id, String days, Number period){
        String query = "SELECT " + USER_ID + " FROM " + TIMETABLE_TABLE + " WHERE USER_ID =:user_id AND DAYS =:days AND PERIOD =:period";
        // SELECT user_id FROM timetable WHERE user_id=? AND days=? AND period=?",[user_id, days, period]

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("days", days);
        params.put("period", period);

        return jdbcTemplate.query(query, params, timetableRowMapper);
    }
    // MySqlBoardRepository - "검색어에 해당하는 게시글 목록 검색" 참고하여 작성함.

    @Override // 시간표 수정
    public boolean updateTimetable(TimetableDTO timetableDTO, String user_id) {
        String query = "UPDATE " + TIMETABLE_TABLE + " SET SUBJECT =:subject, LOCATION =:location, TEACHER =:teacher WHERE DAYS =:days AND PERIOD =:period AND USER_ID =:user_id";
            // else{
            //    await conn.query("UPDATE timetable set subject=?, location=?, teacher=? WHERE days=? AND period=? AND user_id =?",
            //            [subject, location, teacher, days, period, user_id]);
            // }
        Map<String, Object> params = new HashMap<>();
        params.put("subject", timetableDTO.getSubject());
        params.put("location", timetableDTO.getLocation());
        params.put("teacher", timetableDTO.getTeacher());
        params.put("days", timetableDTO.getDays());
        params.put("period", timetableDTO.getPeriod());
        params.put("user_id", user_id);

        int update = jdbcTemplate.update(query, params);
        System.out.println(update);
        if(update != 1)
            return false;
        else
            return true;
    }
    // MySqlBoardRepository - "게시글의 제목과 본문 수정(이미지 제외) 참고하여 작성함.

    @Override
    public List<TimetableDTO> getTimetable() {
        String query = "SELECT " + SUBJECT + DAYS + PERIOD + LOCATION + TEACHER + " FROM " + TIMETABLE_TABLE + " WHERE USER_ID =:user_id";
                     // SELECT subject, days, period, location, teacher FROM timetable WHERE user_id=? // Node.js 코드

        return jdbcTemplate.query(query, timetableRowMapper);
    }
    // MySqlBoardRepository 참고해서 작성함.

    @Override
    public boolean deleteTimetable(String days, Number period) {
        String query = "DELETE FROM " + TIMETABLE_TABLE + " WHERE USER_ID =:user_id AND DAYS =:days AND PERIOD =:period"; // ?
                    // "DELETE FROM timetable WHERE user_id=? AND days=? AND period=?", [user_id, element.days, element.period]);
        if(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("DAYS", days)
                        .addValue("PERIOD", period)) != 1)
            return false;
        else
            return true;
    }
    // MySqlBoardRepository 참고해서 작성함.

    private class TimetableRowMapper implements RowMapper<TimetableDTO>{

        @Override
        public TimetableDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TimetableDTO(
                    rs.getString("user_id"),
                    rs.getString("subject"),
                    rs.getString("title"),
                    rs.getInt("period"), // 확실하지 않음
                    rs.getString("location"),
                    rs.getString("teacher"));
        }
    }
    // MySqlBoardRepository 참고해서 작성함.
}
