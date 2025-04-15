package com.ssg.spring.domain;


/*
    create table tbl_attach (
        ano int auto_increment primary key ,
        uuid varchar(50) null,
        bno int not null,
        filename varchar(500) not null
        );

    create index idx_attach on tbl_attach(bno desc, ano asc);

*/

import lombok.Data;

@Data
public class AttachVO {

  private Long ano;
  private Long bno;
  private String uuid;
  private String fileName;

  public String getFullName() {
    if(ano == null){
      return null;
    }

    return uuid+"_"+fileName;
  }

}
