package capstone.sangcom.controller.api.timetable;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.service.timetable.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/school/timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    /**
     * 시간표 등록/수정
     */
    @PostMapping
    public ResponseEntity<SimpleResponse> insertTimetable(HttpServletRequest request,
                                                          TimetableDTO timetableData){
        JwtUser user = (JwtUser) request.getAttribute("user");

        if(timetableService.insertTimetable(timetableData))
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }

    /**
     * 시간표 조회
     */
    @GetMapping
    public ResponseEntity<SimpleResponse> getTimetable(TimetableDTO timetableDTO){
        List<TimetableDTO> timetable = timetableService.getTimetable(timetableDTO);

        return ResponseEntity
                .ok(new SimpleResponse(true));
    }

    /**
     * 시간표 삭제
     */
//    @DeleteMapping
//    public ResponseEntity<SimpleResponse> deleteTimetable(HttpServletRequest request,
//                                                          TimetableDTO timetableDTO){
//        JwtUser user = (JwtUser) request.getAttribute("user");
//
//        if(TimetableService.deleteTimetable(timetableDTO))
//            return ResponseEntity
//                    .ok(new SimpleResponse(true));
//        else
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new SimpleResponse(false));
//    }
}
