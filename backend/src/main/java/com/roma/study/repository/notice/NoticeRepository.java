package com.roma.study.repository.notice;

import com.roma.study.model.notice.Notice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, JpaSpecificationExecutor<Notice>, NoticeQueryDslRepository,
        QuerydslPredicateExecutor<Notice> {
    Slice<Notice> findSliceBy(Pageable pageable);

    @Query("""
                select n from Notice n
                where n.seq = :seq
            """)
    Notice findSampleBy(Long seq);
}
