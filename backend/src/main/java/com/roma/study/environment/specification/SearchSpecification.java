package com.roma.study.environment.specification;

import com.roma.study.dto.page.SearchOptionDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SearchSpecification {
    public static <T> Specification<T> searchWith(List<SearchOptionDTO> searchKeyword) {
        return (root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        };
    }

    private static List<Predicate> getPredicateWithKeyword(List<SearchOptionDTO> searchKeyword, Root<?> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();
        searchKeyword.stream().forEach(searchOption -> {
            if (null == searchOption.getValue()) {
                predicate.add(root.get(searchOption.getKey()).isNull());
            } else {
                if (searchOption.getOperation() == null) {
                    predicate.add(builder.equal(root.get(searchOption.getKey()), searchOption.getValue()));
                } else {
                    if (searchOption.getOperation().equalsIgnoreCase(">")) {
                        predicate.add(builder.greaterThanOrEqualTo(root.get(searchOption.getKey()), searchOption.getValue().toString()));
                    } else if (searchOption.getOperation().equalsIgnoreCase("<")) {
                        predicate.add(builder.lessThanOrEqualTo(root.<String>get(searchOption.getKey()), searchOption.getValue().toString()));
                    } else if (searchOption.getOperation().equalsIgnoreCase(":")) {
                        if (root.get(searchOption.getKey()).getJavaType() == String.class) {
                            predicate.add(builder.like(root.<String>get(searchOption.getKey()), "%" + searchOption.getValue() + "%"));
                        } else {
                            predicate.add(builder.equal(root.get(searchOption.getKey()), searchOption.getValue()));
                        }
                    }
                }
            }
        });
        return predicate;
    }
}
