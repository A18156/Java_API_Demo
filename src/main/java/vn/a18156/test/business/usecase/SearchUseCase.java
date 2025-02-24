package vn.a18156.test.business.usecase;

public interface SearchUseCase <I, P, O> {
    O execute(I input, P pageable);
}
