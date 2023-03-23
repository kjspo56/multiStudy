package com.roma.study.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
public class PageDTO {
    private String sortField = "regDate";
    private int page = 0;
    private int size = 10;
    private PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortField));
    private List<SearchOptionDTO> searchOption;

    public <T> T getValue(String key) {
        Optional<SearchOptionDTO> optionDTO = this.searchOption.stream()
                .filter(searchOption -> key.equals(searchOption.getKey())).findFirst();
        return optionDTO.map(searchOptionDTO -> (T) searchOptionDTO.getValue()).orElse(null);
    }
}
