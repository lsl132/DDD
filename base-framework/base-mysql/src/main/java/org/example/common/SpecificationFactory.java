package org.example.common;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Date;

/**
 *  规格查询条件工厂
 * @author SHK
 */
public final class SpecificationFactory {

    /**
     * 模糊查询，匹配对应字段
     */
    public static Specification containsLike(final String attribute,final String value) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.like(root.get(attribute), "%" + value + "%");
            }
        };
    }

    /**
     * 某字段的值等于value的查询
     */
    public static Specification equal(final String attribute,final String value) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.equal(root.get(attribute),  value );
            }
        };
    }


    /**
     * 区间查询
     */
    public static Specification isBetween(final String attribute,final int min, final int max) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.between(root.get(attribute),  min, max);
            }
        };
    }

    public static Specification isBetween(final String attribute,final double min, final double max) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.between(root.get(attribute),  min, max);
            }
        };
    }

    public static Specification isBetween(final String attribute, final Date min, final Date max) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.between(root.get(attribute),  min, max);
            }
        };
    }

    /**
     * in查询
     */
    public static Specification in(final String attribute, final Collection c) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return root.get(attribute).in(c);
            }
        };
    }
}
