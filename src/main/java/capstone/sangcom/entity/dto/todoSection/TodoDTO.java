package capstone.sangcom.entity.dto.todoSection;

import lombok.Data;

@Data
public class TodoDTO {

    /**
     * BoardDAO로 refactoring 필요
     */
    private final String user_id;
    private final int list_id;
    private final String body;
    private final int listCheck;
    private final int year;
    private final int month;
    private final int day;

}
