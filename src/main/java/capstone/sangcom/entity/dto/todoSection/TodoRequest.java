package capstone.sangcom.entity.dto.todoSection;

/**
 * TodoRequest.class
 * 데이터베이스로부터 요청과 관련된 클래스
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    private String body;
    private int year;
    private int month;
    private int day;
}
