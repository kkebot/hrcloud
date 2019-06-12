package hr.web.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerFault extends RuntimeException {

    public ServerFault() {
        super();
    }

    public ServerFault(String message) {
        super(message);
    }
}
