package org.sopt.domain.article.repository;

import org.sopt.domain.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a JOIN FETCH a.member")
    List<Article> findAllWithMember();
}
