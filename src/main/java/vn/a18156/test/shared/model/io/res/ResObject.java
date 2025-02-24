package vn.a18156.test.shared.model.io.res;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResObject<T> {
        private String status;
        private HttpStatus httpStatus;
        private String message;
        private T data;
}
