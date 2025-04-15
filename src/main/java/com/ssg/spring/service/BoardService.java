package com.ssg.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssg.spring.domain.AttachVO;
import com.ssg.spring.domain.BoardVO;
import com.ssg.spring.domain.Criteria;
import com.ssg.spring.mappers.BoardMapper;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardService {

  private final BoardMapper boardMapper;

  public List<BoardVO> getList(Criteria criteria){

    return boardMapper.getPage(criteria);
  }

  public int getTotal(Criteria criteria){

    return boardMapper.getTotal(criteria);
  }

  public Long register(BoardVO boardVO){

    log.info("--------------"+ boardVO);

    int count = boardMapper.insert(boardVO);

    Long bno =  boardVO.getBno();

    List<AttachVO> attachVOList = boardVO.getAttachVOList();

    if(attachVOList != null && attachVOList.size() > 0){
      for (AttachVO attach: attachVOList) {
        attach.setBno(bno);
        boardMapper.insertAttach(attach);
      }
    }

    return bno;
  }

  public List<BoardVO> list() {

    return boardMapper.getList();

  }

  public BoardVO get(Long bno) {

    return boardMapper.select(bno);
  }

  public boolean modify(BoardVO vo, Long[] attachFileNums){

    int count =  boardMapper.update(vo);

    List<AttachVO> attachVOList = vo.getAttachVOList();
    
    if(attachFileNums != null && attachFileNums.length > 0){
      //한번에 boardMapper에서 삭제 처리
      boardMapper.deleteAttachFiles(attachFileNums);
    }

    if(attachVOList != null && attachVOList.size() > 0 && count == 1){
      for (AttachVO attach: attachVOList) {
        attach.setBno(vo.getBno());
        boardMapper.insertAttach(attach);
      }
    }
    return count == 1;
  }

  public boolean remove(Long bno) {
    return true;
  }
}
