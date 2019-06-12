package hr.web.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {
    public NotFound() {
        super();
    }

    public NotFound(long id) {
        super("实体不存在；编号="+id);
    }
}
