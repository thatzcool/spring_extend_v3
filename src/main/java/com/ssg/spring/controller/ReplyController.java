package com.ssg.spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.ssg.spring.domain.Criteria;
import com.ssg.spring.domain.PageDTO;
import com.ssg.spring.domain.ReplyVO;
import com.ssg.spring.service.ReplyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@Log4j2
public class ReplyController {

  private final ReplyService replyService;

  @PostMapping("/register")
  public Map<String, Long> register( @RequestBody ReplyVO replyVO ){

    log.info(replyVO);
    Long rno = replyService.register(replyVO);

    int replyCount = replyService.getReplyCountOfBoard(replyVO.getBno());

    return Map.of("RNO", rno, "COUNT", (long) replyCount);

  }

  @GetMapping("/{rno}")
  public ReplyVO get(@PathVariable("rno")Long rno){

    return replyService.get(rno);
  }

  @DeleteMapping("/{rno}")
  public Map<String, Boolean> delete(@PathVariable("rno")Long rno) {

    boolean result = replyService.remove(rno);

    return Map.of("Result", result);

  }

  @PutMapping("/{rno}")
  public Map<String, Boolean> modify(
          @PathVariable("rno")Long rno ,
          @RequestBody ReplyVO replyVO ) {

    replyVO.setRno(rno);

    boolean result = replyService.modify(replyVO);

    return Map.of("Result", result);

  }


  @GetMapping("/list/{bno}")
  public Map<String, Object> getListOfBoard(@PathVariable("bno") Long bno, Criteria criteria){
      log.info("bno: " + bno);
      log.info(criteria);

      List<ReplyVO> replyList = replyService.getListWithBno(criteria, bno);

      int total = replyService.getTotalWithBno(criteria, bno);

      PageDTO pageDTO = new PageDTO(criteria, total);


      Map<String, Object> map = new HashMap<>();
      map.put("replyList", replyList);
      map.put("pageDTO", pageDTO);

      return map;

  }

}
