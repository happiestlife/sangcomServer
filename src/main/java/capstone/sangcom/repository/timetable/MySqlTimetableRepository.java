package capstone.sangcom.repository.timetable;

import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.repository.board.board.MySqlBoardRepository;
import capstone.sangcom.repository.todo.MySqlTodoRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MySqlTimetableRepository implements  TimetableRepository{

    private final String TIMETABLE_TABLE = "timetable";

//    private final RowMapper<TimetableDTO> timetableRowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MySqlTimetableRepository(NamedParameterJdbcTemplate jdbcTemplate) {
//        this.timetableRowMapper = new TimetableRowMapper();
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override // 시간표 등록
    public void insertSelect(int user_id, String days, Number period) {

    }

    @Override
    public boolean insert(int user_id, String subject, String days, Number period, String location, String teacher) {
        return false;
    }

    @Override
    public boolean update(String subject, String location, String teacher, String days, Number period, int user_id) {
        return false;
    }

    @Override
    public void getSelect(String subject, String days, Number period, String location, String teache, int user_id) {

    }

    @Override
    public boolean delete(int user_id, String days, Number period) {
        return false;
    }
}
