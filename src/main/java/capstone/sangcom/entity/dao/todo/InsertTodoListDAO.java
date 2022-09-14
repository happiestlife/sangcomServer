package capstone.sangcom.entity.dao.todo;

import capstone.sangcom.entity.dto.todoSection.InsertTodoListDTO;
import lombok.Data;

@Data
//@AllArgsConstructor
public class InsertTodoListDAO {

    private final String user_id;
    private final InsertTodoListDTO insertTodoListDTO;

    public InsertTodoListDAO(String user_id, InsertTodoListDTO insertTodoListDTO){
        this.user_id = user_id;
        this.insertTodoListDTO = insertTodoListDTO;
    }

}
