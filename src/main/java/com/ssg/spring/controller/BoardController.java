package com.ssg.spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ssg.spring.domain.AttachVO;
import com.ssg.spring.domain.BoardVO;
import com.ssg.spring.domain.Criteria;
import com.ssg.spring.domain.PageDTO;
import com.ssg.spring.service.BoardService;
import com.ssg.spring.util.UpDownUtil;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

  private final UpDownUtil upDownUtil;

  private final BoardService boardService;
  //list
//  @GetMapping("/list")
//  public void list(Model model){
//    log.info("list................");
//
//    java.util.List<BoardVO> list = boardService.list();
//
//    log.info(list);
//    log.info("리스트 출력");
//    log.info("리스트 출력");
//    log.info("리스트 출력");
//    log.info("리스트 출력");
//
//    model.addAttribute("list",list);
//
//  }

  @GetMapping("/list")
  public void list(
          @ModelAttribute("cri") Criteria criteria,
          Model model){
    log.info("list................");
    log.info("criteria: " + criteria);

    List<BoardVO> list = boardService.getList(criteria);

    log.info(list);
    log.info("리스트 출력");
    log.info("리스트 출력");
    log.info("리스트 출력");
    log.info("리스트 출력");

    model.addAttribute("list",list);

    PageDTO pageDTO = new PageDTO(criteria, boardService.getTotal(criteria));

    model.addAttribute("pageMaker", pageDTO);

  }


  @GetMapping({"/{job}/{bno}"})
  public String read(
          @PathVariable(name = "job") String job,
          @PathVariable(name = "bno") Long bno,
          @ModelAttribute("cri") Criteria criteria,
          Model model ) {

    log.info("job: " + job);
    log.info("bno: " + bno);

    if( !(  job.equals("read") || job.equals("modify")  ) ){
      throw new RuntimeException("Bad Request job");
    }

    BoardVO boardVO = boardService.get(bno);

    log.info("boardVO: " + boardVO);

    model.addAttribute("vo", boardVO);

    return "/board/"+job;

  }

  @GetMapping("/register")
  public void register() {

  }

  @PostMapping("/register")
  public String registerPost(
          BoardVO boardVO,
          @RequestParam(value = "files", required = false ) MultipartFile[] files,
          RedirectAttributes rttr){

    log.info("boardVO: " + boardVO);

    log.info("------------------------------------------");
    log.info(Arrays.toString(files));

    List<AttachVO> attachVOList = upDownUtil.upload(files);

    boardVO.setAttachVOList(attachVOList);

    Long bno = boardService.register(boardVO);

    log.info("bno: " + bno);

    rttr.addFlashAttribute("result", bno);

    return "redirect:/board/list";
  }


  @PostMapping("/remove/{bno}")
  public String remove(
          @PathVariable(name="bno") Long bno,
          @RequestParam(value = "anos", required = false ) Long[] anos,
          @RequestParam(value = "fullNames", required = false ) String[] fullNames,
          RedirectAttributes rttr){

    BoardVO boardVO = new BoardVO();
    boardVO.setBno(bno);
    boardVO.setTitle("해당 글은 삭제 되었습니다");
    boardVO.setContent("해당 글은 삭제 되었습니다.");
    boardVO.setDelFlag(true);

    log.info("boardVO: " + boardVO);

    boardService.modify(boardVO, anos);

    upDownUtil.deleteFiles(fullNames);

    rttr.addFlashAttribute("result", boardVO.getBno());

    return "redirect:/board/list";

  }

  @PostMapping("/modify/{bno}")
  public String modify(
          @PathVariable(name="bno") Long bno,
          BoardVO boardVO,
          @RequestParam(value = "files", required = false ) MultipartFile[] files,
          @RequestParam(value = "anos", required = false ) Long[] anos,
          @RequestParam(value = "fullNames", required = false ) String[] fullNames,
          RedirectAttributes rttr){

    boardVO.setBno(bno);

    List<AttachVO> attachVOList = upDownUtil.upload(files);

    if(attachVOList != null && attachVOList.size() > 0){
      boardVO.setAttachVOList(attachVOList);
    }

    log.info("boardVO: " + boardVO);

    boardService.modify(boardVO, anos);

    //삭제할 파일들을 삭제
    upDownUtil.deleteFiles(fullNames);

    rttr.addFlashAttribute("result", boardVO.getBno());

    return "redirect:/board/read/"+bno;

  }



//  @GetMapping("/modify/{bno}")
//  public String modify(
//          @PathVariable(name = "bno") Long bno, Model model ) {
//
//    log.info("bno: " + bno);
//
//    BoardVO boardVO = boardService.get(bno);
//
//    log.info("boardVO: " + boardVO);
//
//    model.addAttribute("vo", boardVO);
//
//    return "/board/modify";
//
//  }




}
