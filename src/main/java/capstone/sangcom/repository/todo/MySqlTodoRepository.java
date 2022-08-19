package capstone.sangcom.repository.todo;

import capstone.sangcom.entity.dto.todoSection.TodoDTO;
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

    private final RowMapper<TodoDTO> todoRowMapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MySqlTodoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcTemplate = namedParameterJdbcTemplate;
        todoRowMapper = new TodoRowMapper();
    }

    @Override // todolist 등록
    public int insert(TodoDTO todoDTO) {
        String query = "INSERT INTO " + TODOLIST_TABLE + " (user_id, body, year, month, day) VALUES (:user_id, :body, :year, :month, :day)";

        MapSqlParameterSource params = new MapSqlParameterSource() //?????????????? get~() 이게 뭐지..
                .addValue("user_id", todoDTO.getUser_id())
                .addValue("body", todoDTO.getBody())
                .addValue("year", todoDTO.getUser_id())
                .addValue("month", todoDTO.getMonth())
                .addValue("day", todoDTO.getDay());

        KeyHolder key = new GeneratedKeyHolder();
        if (jdbcTemplate.update(query, params, key, new String[]{"user_id"}) != 1)
            return -1;

        return key.getKey().intValue();
    }

    @Override // todolist - 날짜별 조회
     public List<TodoDTO> getTodoList(String user_id, TodoDTO todoDTO) {
        String query = "SELECT * FROM " + TODOLIST_TABLE + " WHERE user_id = :user_id AND year = :year AND month= :month AND day= :day";

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("todoDTO", todoDTO);

        return jdbcTemplate.query(query, params, todoRowMapper);    }

    @Override // todolist - user_id, list_id로 조회 // update, delite, check에 쓰임
    public List<TodoDTO> searchTodoList(String user_id, int list_id) {
        String query = "SELECT * FROM " + TODOLIST_TABLE + " WHERE user_id= :user_id AND list_id= :list_id";

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("list_id", list_id);

        return jdbcTemplate.query(query, params, todoRowMapper);
    }

    @Override // todolist update
    public boolean updateTodoList(String body, String user_id, int list_id) {
        String query = "UPDATE " + TODOLIST_TABLE + " SET body= :body WHERE user_id= :user_id AND list_id= :list_id";
        Map<String, Object> params = new HashMap<>();
        params.put("body", body);
        params.put("user_id", user_id);
        params.put("list_id", list_id);

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

    @Override // todolist check
    public boolean checkTodoList(boolean listCheck, String user_id, int list_id) {
        String query = "UPDATE " + TODOLIST_TABLE + " SET listCheck= :listCheck WHERE user_id= :user_id AND list_id= :list_id";

        HashMap<String, Object> params = new HashMap<>();
        params.put("listCheck", listCheck);
        params.put("user_id", user_id);
        params.put("list_id", list_id);

        List<String> result = jdbcTemplate.query(query, params,
                (rs, rowNum) -> rs.getString("user_id")
        );

        if(result.isEmpty())
            return true;
        else
            return false;
    }

    private class TodoRowMapper implements RowMapper<TodoDTO>{

        @Override
        public TodoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TodoDTO(
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
