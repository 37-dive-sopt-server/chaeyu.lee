package org.sopt.domain.comment.repository;

import org.sopt.domain.article.domain.Article;
import org.sopt.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c join fetch c.member where c.article = :article")
    List<Comment> findAllByArticleWithMember(@Param("article") Article article);
    @Query("select c from Comment c join fetch c.member where c.id = :id")
    Optional<Comment> findWithMemberById(@Param("id") Long id);
}
