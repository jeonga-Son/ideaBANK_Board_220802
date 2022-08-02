//Entity는 데이터베이스 테이블과 매핑되는 객체입니다.

package com.ideaBANK.board.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 10, nullable = false) //해당 필드는 DDL 생성 시 not null이라는 조건이 붙은 채로 생성된다.
    private String author;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //columnDefinition은 컬럼 속성 정의시 사용
    private String content;

    @CreatedDate
    @Column(updatable = false) //읽기 전용 필드라고 볼수있다.
    private LocalDateTime createdDate;

    @LastModifiedDate //마지막 수정일자를 채워 넣는다
    private LocalDateTime modifiedDate;

    @Builder
    public Board(Long id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
