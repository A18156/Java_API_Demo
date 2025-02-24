package vn.a18156.test.business.usecase;

public interface UseCase<I,O> {
    O execute(I input);
}
