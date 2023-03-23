package com.roma.study.environment.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.roma.study.dto.page.SearchOptionDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuerydslPredicate {

    private final SearchOptionDTO searchOption;

    public <T> BooleanExpression predicate(T clz, String variable) {
        PathBuilder<T> pathBuilder = new PathBuilder(clz.getClass(), variable);

        if (null == searchOption.getValue()) {
            return null;
        }

        if (isNumeric(searchOption.getValue())) {
            NumberPath<Integer> path = pathBuilder.getNumber(searchOption.getKey(), Integer.class);
            int value = Integer.parseInt(searchOption.getValue().toString());
            return switch (searchOption.getOperation()) {
                case ":" -> path.eq(value);
                case "<" -> path.loe(value);
                case ">" -> path.goe(value);
                default -> isNullOperation(path, value);
            };
        } else {
            StringPath path = pathBuilder.getString(searchOption.getKey());
            String value = searchOption.getValue().toString();
            return switch (searchOption.getOperation()) {
                case ":" -> path.eq(value);
                case "<" -> path.loe(value);
                case ">" -> path.goe(value);
                case "c" -> path.contains(value);
                case "s" -> path.startsWith(value);
                case "e" -> path.endsWith(value);
                case "l" -> path.like(value + "%");
                default -> isNullOperation(path, value);
            };
        }
    }

    private BooleanExpression isNullOperation(Path path, Object val) {
        if (path instanceof NumberPath numberPath) {
            numberPath.eq(val);
        } else if (path instanceof StringPath stringPath) {
            stringPath.eq((String) val);
        }
        return null;
    }

    private boolean isNumeric(Object val) {
        return (val instanceof Integer || val instanceof Double);
    }


}
