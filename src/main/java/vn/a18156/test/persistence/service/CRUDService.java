package vn.a18156.test.persistence.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

public abstract class CRUDService<T, ID, R extends JpaRepository<T, ID>  & JpaSpecificationExecutor<T>, M> {
    protected R repository;
    protected M manager;

    public T create(T entity) {
        return repository.save(entity);
    }

    public T update(ID id, T entity) {
        return repository.findById(id).map(e -> {
            BeanUtils.copyProperties(entity, e, "id");
            return repository.save(e);
        }).orElseThrow(() -> new EntityNotFoundException("Id không tồn tại: " + id));
    }

    public void delete(ID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy Id: " + id);
        }
        repository.deleteById(id);
    }

    public Page<T> Pagination(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public Page<T> search(String keyword, Pageable pageable, List<String> fields) {
        return repository.findAll(buildSearchSpecification(keyword, fields), pageable);
    }

    private Specification<T> buildSearchSpecification(String keyword, List<String> fields) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            String likePattern = "%" + keyword + "%";

            for (String field : fields) {
                predicates.add(cb.like(root.get(field), likePattern));
            }

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }

}
