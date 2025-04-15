package com.ssg.spring.mappers;

import org.apache.ibatis.annotations.Param;
import com.ssg.spring.domain.AttachVO;
import com.ssg.spring.domain.BoardVO;
import com.ssg.spring.domain.Criteria;

public interface BoardMapper {

  java.util.List<BoardVO> getList();

  java.util.List<BoardVO> getPage(Criteria criteria);

  int getTotal(Criteria criteria);


  int insert(BoardVO boardVO);

  BoardVO select(Long bno);

  int update(BoardVO boardVO);

  int insertAttach(AttachVO attachVO);

  void deleteAttachFiles( @Param("anos") Long[] anos);

}
