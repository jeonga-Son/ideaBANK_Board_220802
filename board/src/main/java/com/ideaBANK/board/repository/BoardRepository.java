//Repository는 데이터 조작을 담당하며, JpaRepository를 상속받습니다.
//JpaRepository의 값은 매핑할 Entity와 Id의 타입입니다.

package com.ideaBANK.board.repository;

import com.ideaBANK.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
