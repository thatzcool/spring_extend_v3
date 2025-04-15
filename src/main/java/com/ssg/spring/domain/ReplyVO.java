package com.ssg.spring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
create table tbl_reply
(
    rno        int auto_increment
        primary key,
    bno        int                                   not null,
    replyText  varchar(2000)                         not null,
    replyer    varchar(100)                          not null,
    regDate    timestamp default current_timestamp() null,
    updateDate timestamp default current_timestamp() null,
    constraint fk_reply_board
        foreign key (bno) references tbl_board (bno)
);
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {

  private Long rno;
  private Long bno;
  private String replyText;
  private String replyer;


  @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
  private LocalDateTime regDate;
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
  private LocalDateTime updateDate;
}
