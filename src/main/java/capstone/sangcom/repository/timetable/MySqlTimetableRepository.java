package capstone.sangcom.repository.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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


    @Override // 시간표 등록
    public boolean insertTimetable(TimetableDTO timetableDTO) {
        return false;
    }

    @Override // 시간표 수정
    public boolean updateTimetable(TimetableDTO timetableDTO) {
        return false;
    }

    @Override
    public List<TimetableDTO> getTimetable() {
        String query = "SELECT " + SUBJECT, DAYS, PERIOD, LOCATION, TEACHER + " FROM " + TIMETABLE_TABLE + " WHERE " + "USER_ID";
//                      SELECT subject, days, period, location, teacher FROM timetable WHERE user_id=?

        return jdbcTemplate.query(query, timetableRowMapper);
    }

    @Override
    public boolean deleteTimetable(String days, Number period) {
        return false;
    }


    private class TimetableRowMapper implements RowMapper<TimetableDTO>{

        @Override
        public TimetableDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TimetableDTO(
                    rs.getString("subject"),
                    rs.getString("title"),
                    rs.getInt("period"), // 확실하지 않음
                    rs.getString("location"),
                    rs.getString("teacher"));
        }
    }
}
