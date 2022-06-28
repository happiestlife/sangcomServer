package capstone.sangcom.controller.api.token;

import capstone.sangcom.config.auth.JwtManager;
import capstone.sangcom.controller.api.response.login.TokenResponse;
import capstone.sangcom.dto.token.RefreshTokenDTO;
import capstone.sangcom.dto.token.TokenValidateDTO;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> reissueAccessToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        System.out.println("reissue");
        String accessToken = tokenService.createNewTokenWithRefreshToken(refreshTokenDTO.getToken());
        System.out.println(accessToken);
        if (accessToken == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        else
            return ResponseEntity
                    .ok(new TokenResponse(accessToken, refreshTokenDTO.getToken()));
    }

    @GetMapping("/valid")
    public ResponseEntity<TokenValidateDTO> validateToken(HttpServletRequest request){
        String token = JwtManager.getTokenFromHeader(request.getHeader("Authorization"));

        if(JwtManager.isValidToken(token)){
            JwtUser user = JwtManager.getUserFromToken(token);

            return ResponseEntity
                    .ok(new TokenValidateDTO(true, user));
        }else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
    }
}
