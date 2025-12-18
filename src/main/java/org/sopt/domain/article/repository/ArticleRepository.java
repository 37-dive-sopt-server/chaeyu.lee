package org.sopt.domain.article.repository;

import org.sopt.domain.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a JOIN FETCH a.member")
    List<Article> findAllWithMember();

    @Query("SELECT a FROM Article a JOIN FETCH a.member WHERE a.id = :id")
    Optional<Article> findWithMemberById(@Param("id") Long id);

    Optional<Article> findByTitle(String title);

}
