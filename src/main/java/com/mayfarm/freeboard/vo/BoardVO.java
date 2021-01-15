package com.mayfarm.freeboard.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private int no;
	private String title;
	private String content;
	private String writer;
	private Date regDt;
	private Date modDt;
	private int hit;
}
