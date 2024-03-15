package api.dieti2024.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private final HttpStatus status;
    public ApiException(String messaggio, HttpStatus httpStatus) {
        super(messaggio);
        this.status = httpStatus;
    }

}
