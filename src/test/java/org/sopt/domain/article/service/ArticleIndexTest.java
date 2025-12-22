package org.sopt.domain.article.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.domain.article.domain.enums.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ArticleIndexTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ArticleService articleService;

    @Test
    @Rollback(false)
    @DisplayName("DB에 게시물 데이터를 3만개 삽입한다.")
    void insert30kArticles() {
        Long memberId = 2L;
        Tag validTag = Tag.ETC;

        String sql = "INSERT INTO article (member_id, tag, title, content, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, NOW(), NOW())";

        List<Object[]> batchArgs = new ArrayList<>();

        System.out.println("데이터 삽입 시작");
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 30000; i++) {
            batchArgs.add(new Object[]{memberId, validTag.name(), "제목이지렁이 " + i, "내용이지렁 " + i});

            if (i % 1000 == 0) {
                jdbcTemplate.batchUpdate(sql, batchArgs);
                batchArgs.clear();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("소요 시간: " + (endTime - startTime) + "ms");
    }

    @Test
    @DisplayName("인덱스 적용 전 소요 시간을 측정한다.")
    void measureTimeWithoutIndex() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        long startTime = System.currentTimeMillis();

        articleService.findAllArticles(null, pageable);

        long endTime = System.currentTimeMillis();
        System.out.println("인덱스 적용 전 소요 시간: " + (endTime - startTime) + "ms");
    }

    @Test
    @DisplayName("인덱스 적용 후 소요 시간을 측정한다.")
    void measureTimeWithIndex() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        long startTime = System.currentTimeMillis();

        articleService.findAllArticles(null, pageable);

        long endTime = System.currentTimeMillis();
        System.out.println("인덱스 적용 후 소요 시간: " + (endTime - startTime) + "ms");
    }
}