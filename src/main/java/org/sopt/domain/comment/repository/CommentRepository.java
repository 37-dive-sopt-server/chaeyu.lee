package org.sopt.domain.comment.repository;

import org.sopt.domain.article.domain.Article;
import org.sopt.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticle(Article article);
}
