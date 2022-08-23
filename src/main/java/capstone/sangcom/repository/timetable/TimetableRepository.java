package capstone.sangcom.repository.timetable;

public interface TimetableRepository {

    // 시간표 등록, 수정
    public void insertSelect(int user_id, String days, Number period);
    public boolean insert(int user_id, String subject, String days, Number period, String location, String teacher);
    public boolean update(String subject, String location, String teacher, String days, Number period, int user_id);

    // 시간표 조회
    public void getSelect(String subject, String days, Number period, String location, String teache, int user_id);

    // 시간표 삭제
    public boolean delete(int user_id, String days, Number period);

}
