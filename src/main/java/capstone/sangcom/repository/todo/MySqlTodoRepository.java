package capstone.sangcom.repository.todo;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.todoSection.*;
//import capstone.sangcom.repository.board.board.MySqlBoardRepository;
//import org.apache.poi.ss.formula.functions.T;
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
public class MySqlTodoRepository implements TodoRepository{


    private final String TODOLIST_TABLE = "todoList";

    private final RowMapper<GetTodolistResponseDTO> getTodolistResponseRowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MySqlTodoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
        getTodolistResponseRowMapper = new GetTodolistResponseRowMapper();
    }

    @Override // todolist 등록
    public boolean insertTodolist(String user_id, InsertTodoListDTO insertTodoListDTO) {
        String query = "INSERT INTO " + TODOLIST_TABLE + " (user_id, body, year, month, day) VALUES (:user_id, :body, :year, :month, :day)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", user_id)
                .addValue("body", insertTodoListDTO.getBody())
                .addValue("year", insertTodoListDTO.getYear())
                .addValue("month", insertTodoListDTO.getMonth())
                .addValue("day", insertTodoListDTO.getDay());

        KeyHolder key = new GeneratedKeyHolder();
        if (jdbcTemplate.update(query, params, key, new String[]{"user_id"}) != 1)
            return false;
//            return -1;

        return true;
//        return key.getKey().intValue();
    }

    @Override // todolist - 날짜별 조회
     public List<GetTodolistResponseDTO> getTodoList(String user_id, int year, int month, int day) {
//        String query = "SELECT LIST_ID, BODY, LISTCHECK, YEAR, MONTH, DAY FROM " + TODOLIST_TABLE + " WHERE USER_ID =:user_id";
        String query = "SELECT * FROM " + TODOLIST_TABLE + " WHERE user_id= :user_id AND year= :year AND month= :month AND day= :day";

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("year", year);
        params.put("month", month);
        params.put("day", day);

        return jdbcTemplate.query(query, params, getTodolistResponseRowMapper);    }

    @Override // todolist - user_id, list_id로 조회 // update, delite, check에 쓰임
    public List<GetTodolistResponseDTO> searchTodoList(String user_id, int list_id) {
        String query = "SELECT * FROM " + TODOLIST_TABLE + " WHERE user_id= :user_id AND list_id= :list_id";

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("list_id", list_id);

        return jdbcTemplate.query(query, params, getTodolistResponseRowMapper);
    }

    @Override // todolist update
    public boolean updateTodoList(String user_id, int list_id, UpdateTodoListDTO updateTodoListDTO) {
        String query = "UPDATE " + TODOLIST_TABLE + " SET body= :body WHERE user_id= :user_id AND list_id= :list_id";
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("list_id", list_id);
        params.put("body", updateTodoListDTO.getBody());

        int update = jdbcTemplate.update(query, params);
        System.out.println(update);
        if(update != 1)
            return false;
        else
            return true;
    }

    @Override // todolist delete
    public boolean deleteTodoList(String user_id, int list_id) {
        String query = "DELETE FROM " + TODOLIST_TABLE + " WHERE user_id= :user_id AND list_id= :list_id";
        if(jdbcTemplate.update(query,
                new MapSqlParameterSource()
                        .addValue("user_id", user_id)
                        .addValue("list_id", list_id)) != 1)
            return false;
        else
            return true;    }

//    @Override // 해당 할 일을
//    public boolean isUserWriteTodoList(String userId, int listId) {
//        return false;
//    }

    @Override // todolist check
    public boolean checkTodoList(String user_id, int list_id, ListCheckDTO listCheckDTO) {
        String query = "UPDATE " + TODOLIST_TABLE + " SET listCheck= :listCheck WHERE user_id= :user_id AND list_id= :list_id";

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("list_id", list_id);
        params.put("listCheck", listCheckDTO.getListCheck());

//        List<String> result = jdbcTemplate.query(query, params,
//                (rs, rowNum) -> rs.getString("user_id")
//        );
//
//        if(result.isEmpty())
//            return true;
//        else
//            return false;
        int update = jdbcTemplate.update(query, params);
        System.out.println(update);
        if((update != 0) && (listCheckDTO.getListCheck()==1)||(listCheckDTO.getListCheck()==0))
            return true;
        else
            return false;
    }


    private class GetTodolistResponseRowMapper implements RowMapper<GetTodolistResponseDTO>{

        @Override
        public GetTodolistResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new GetTodolistResponseDTO(
                    rs.getString("user_id"),
                    rs.getInt("list_id"),
                    rs.getString("body"),
                    rs.getInt("listCheck"),
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("day"));
        }
    }

}
