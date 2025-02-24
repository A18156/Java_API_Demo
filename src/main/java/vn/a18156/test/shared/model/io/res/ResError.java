package vn.a18156.test.shared.model.io.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResError {
    private String errMessage;
    private Object details;
}
