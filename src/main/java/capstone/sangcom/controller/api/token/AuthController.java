package capstone.sangcom.controller.api.token;

import capstone.sangcom.util.auth.JwtUtils;
import capstone.sangcom.controller.api.response.login.TokenResponse;
import capstone.sangcom.entity.dto.tokenSection.RefreshTokenDTO;
import capstone.sangcom.entity.dto.tokenSection.TokenValidateDTO;
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
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> reissueAccessToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        String accessToken = tokenService.createNewTokenWithRefreshToken(refreshTokenDTO.getToken());
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
        String token = JwtUtils.getTokenFromHeader(request.getHeader("Authorization"));

        if(JwtUtils.isValidToken(token)){
            JwtUser user = JwtUtils.getUserFromToken(token);

            return ResponseEntity
                    .ok(new TokenValidateDTO(true, user));
        }else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
    }
}
