package capstone.sangcom.controller.api.timetable;

import capstone.sangcom.controller.api.response.common.SimpleResponse;
import capstone.sangcom.controller.api.response.timetable.TimetableResponse;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.timetableSection.DeleteTimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.entity.dto.timetableSection.GetTimetableResponseDTO;
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
                                                          @RequestBody TimetableDTO timetableData) {
        JwtUser user = (JwtUser) request.getAttribute("user"); // const user_id = req.body.data.id;
        // 로그인 -> Jwt토큰에서 USER 정보를 가져와서 -> HttpServletRequest에 저장 -> request
        // 인터셉터: Dispatcher Servlet -> Controller 사이에 가로채서 특정 임무를 수행하는 것
        // 모든 요청들이 디스패쳐서블릿을 거친다

        if (timetableService.insertTimetable(user.getId(), timetableData)) // user_id (x) -> user.getId(o)
            return ResponseEntity
                    .ok(new SimpleResponse(true));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(false));
    }

    /**
     * 시간표 수정
     */
    @PutMapping
    public ResponseEntity<SimpleResponse> updateTimetable(HttpServletRequest request,
                                                          @RequestBody TimetableDTO timetableDTO) {
        JwtUser user = (JwtUser) request.getAttribute("user"); // const user_id = req.body.data.id;
        // 로그인 -> Jwt토큰에서 USER 정보를 가져와서 -> HttpServletRequest에 저장 -> request
        // 인터셉터: Dispatcher Servlet -> Controller 사이에 가로채서 특정 임무를 수행하는 것
        // 모든 요청들이 디스패쳐서블릿을 거친다

        if (timetableService.updateTimetable(user.getId(), timetableDTO)) // user_id (x) -> user.getId(o)
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
    public ResponseEntity<TimetableResponse> getTimetable(HttpServletRequest request)
        {
            JwtUser user = (JwtUser) request.getAttribute("user"); // const user_id = req.body.data.id;

            List<GetTimetableResponseDTO> timetable = timetableService.getTimetable(user.getId());


            return ResponseEntity
                    .ok(new TimetableResponse(true, timetable));


//        if(timetableService.getTimetable(user.getId()))
//            return ResponseEntity
//                   .ok(new TimetableResponse(true,timetable));
//        else
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new TimetableResponse(false));
////                    .build();
        }

        /**
         * 시간표 삭제
         */
        @DeleteMapping
        public ResponseEntity<SimpleResponse> deleteTimetable (HttpServletRequest request,
                                                               @RequestBody DeleteTimetableDTO deleteTimetableDTO){
            // Delete라서 @RequestBody 사용하려면 Server.xml에 수정이 필요함

            JwtUser user = (JwtUser) request.getAttribute("user");

            if (timetableService.deleteTimetable(user.getId(), deleteTimetableDTO))
                return ResponseEntity
                        .ok(new SimpleResponse(true));
            else
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new SimpleResponse(false));
        }
    }
