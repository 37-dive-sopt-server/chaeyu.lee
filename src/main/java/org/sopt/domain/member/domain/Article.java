package org.sopt.domain.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String tag;

    private LocalDateTime publishedAt;

    private String title;
    private String content;

    public Article(Member member, String tag, String title, String content) {
        this.member = member;
        this.publishedAt = LocalDateTime.now();
        this.tag = tag;
        this.title = title;
        this.content = content;
    }
}
