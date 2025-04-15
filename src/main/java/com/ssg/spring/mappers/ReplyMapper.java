package com.ssg.spring.mappers;

import org.apache.ibatis.annotations.Param;
import com.ssg.spring.domain.Criteria;
import com.ssg.spring.domain.ReplyVO;

public interface ReplyMapper {

  Long insert(ReplyVO replyVO);

  ReplyVO selectOne(Long rno);

  int deleteOne(Long rno);

  int updateOne(ReplyVO replyVO);

  java.util.List<ReplyVO> getList(
          @Param("cri") Criteria cri,
          @Param("bno") Long bno);

  int getTotal(@Param("cri") Criteria cri,
               @Param("bno") Long bno);

}
