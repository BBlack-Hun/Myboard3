package com.mayfarm.noticeboard.vo;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {
	
	private int no;
	private String title;
	private String content;
	private String writer;
	private Date regDt;
	private Date modDt;
	private int hit;
}
