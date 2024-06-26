package team.haedal.gifticionfunding.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 대문자로 ErrorCode명 정의 (상태코드 / HttpStatus / 메세지)
     */

    NOT_FOUND(404,HttpStatus.NOT_FOUND, "데이터를 찾을 수 없습니다"),
    INVALID_PERMISSION(401,HttpStatus.UNAUTHORIZED, "권한이 없습니다"),
    INVALID_INPUT(400, HttpStatus.BAD_REQUEST,"잘못된 요청입니다."),
    USER_NOT_FOUND(400,HttpStatus.BAD_REQUEST,"사용자를 찾을 수 없습니다.");
    private int status;
    private HttpStatus error;
    private String message;
}
