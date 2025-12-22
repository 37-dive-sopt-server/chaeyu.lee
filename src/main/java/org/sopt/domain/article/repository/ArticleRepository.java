package org.sopt.domain.article.repository;

import org.sopt.domain.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "SELECT a FROM Article a JOIN FETCH a.member",
            countQuery = "SELECT COUNT(a) FROM Article a")
    Page<Article> findAllWithMember(Pageable pageable);

    @Query(value = "SELECT a FROM Article a JOIN FETCH a.member m " +
            "WHERE a.title LIKE %:keyword% OR m.name LIKE %:keyword%",
            countQuery = "SELECT COUNT(a) FROM Article a JOIN a.member m " +
                    "WHERE a.title LIKE %:keyword% OR m.name LIKE %:keyword%")   Page<Article> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT a FROM Article a JOIN FETCH a.member WHERE a.id = :id")
    Optional<Article> findWithMemberById(@Param("id") Long id);

    Optional<Article> findByTitle(String title);

}
