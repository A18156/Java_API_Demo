package vn.a18156.test.shared.model.io.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResPage<T> {
    private List<T> data;
    private int totalPages;
    private long totalElements;
    private int size;
    private int pageNumber;
}
