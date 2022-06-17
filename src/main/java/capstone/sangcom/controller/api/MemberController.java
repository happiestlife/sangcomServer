package capstone.sangcom.controller.api;

import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.service.user.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    /*
     * 회원정보수정
     * */
    @PutMapping("/member")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserInfo(String id, @RequestBody UpdateUserInfoDTO updateUserInfoDTO) throws Exception {
        memberService.update(id, updateUserInfoDTO);
    }
}
