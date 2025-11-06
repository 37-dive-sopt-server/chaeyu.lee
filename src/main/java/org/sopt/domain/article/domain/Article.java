package org.sopt.domain.article.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.article.domain.enums.Tag;
import org.sopt.domain.member.domain.Member;
import org.sopt.global.entity.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Tag tag;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Article(Member member, Tag tag, String title, String content) {
        this.member = member;
        this.tag = tag;
        this.title = title;
        this.content = content;
    }

    public static Article create(Member author, Tag tag, String title, String content) {
        return new Article(author, tag, title, content);
    }
}
