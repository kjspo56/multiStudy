package com.roma.study.model.notice;

import com.roma.study.model.CommonField;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Notice extends CommonField {

    @Id
    @Column(name = "seq", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;
    private String content;

    @Builder
    public Notice(Long seq, String title, String content){
        this.seq = seq;
        this.title = title;
        this.content = content;
    }

    public void modifyTitleAndContent(String title, String content){
        this.title = title;
        this.content = content;
    }
}
