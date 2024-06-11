package taba5.Artvis.Exception;

import lombok.Getter;

@Getter
public class RestApiException extends RuntimeException{
    private ErrorCode errorCode;
    public RestApiException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
