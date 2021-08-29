package me.seo.studyjpa.repository;


import me.seo.studyjpa.domain.Comment;
import me.seo.studyjpa.domain.Comment_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SpecificationCommentSpec {
    public static Specification<Comment> isBest(){
        return new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.isTrue(root.get(Comment_.best));
            }
        };
    }
    public static Specification<Comment> isGood(){
        return new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.greaterThan(root.get(Comment_.up),10);
            }
        };
    }

}
