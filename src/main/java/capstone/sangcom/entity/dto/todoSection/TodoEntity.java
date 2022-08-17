package capstone.sangcom.entity.dto.todoSection;

/**
 * TodoEntity.class
 * 실제 데이터베이스와 연관된 클래
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {

    private String body;
    private int year;
    private int month;
    private int day;
}
