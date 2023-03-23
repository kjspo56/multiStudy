package com.roma.study.environment.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.roma.study.dto.page.SearchOptionDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QueryDslPredicateBuilder {

    private final List<SearchOptionDTO> searchOptionList;

    public QueryDslPredicateBuilder() {
        this.searchOptionList = new ArrayList<>();
    }

    public QueryDslPredicateBuilder with(List<SearchOptionDTO> searchOption) {
        searchOptionList.addAll(searchOption);
        return this;
    }

    public BooleanExpression build(Object entity, String variable) {
        if (searchOptionList.isEmpty()) {
            return null;
        }

        List<BooleanExpression> predicates = searchOptionList.stream().map(s -> {
            QuerydslPredicate predicate = new QuerydslPredicate(s);
            return predicate.predicate(entity, variable);
        }).filter(Objects::nonNull).toList();

        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }

        return result;
    }
}
