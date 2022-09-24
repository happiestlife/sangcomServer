package capstone.sangcom.controller.web.main;

import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.dto.timetableSection.TimetableDTO;
import capstone.sangcom.service.timetable.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/timeTable/edit")
public class TimeTableController {

    private final TimetableService timetableService;

    @GetMapping
    public String getEditSchedulePage() {
        return "editTimeTable";
    }

    @PostMapping
    public String editSchedule(HttpServletRequest request,
                               TimetableDTO timetableDTO) {
        JwtUser user = (JwtUser) request.getAttribute("user");

        if(timetableService.getTimetable(user.getId(), timetableDTO.getDays(), timetableDTO.getPeriod().intValue()) == null)
            timetableService.insertTimetable(user.getId(), timetableDTO);
        else
            timetableService.updateTimetable(user.getId(), timetableDTO);

        return "redirect:/";
    }
}
