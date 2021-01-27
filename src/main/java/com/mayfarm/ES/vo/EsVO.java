package com.mayfarm.ES.vo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "data")
public class EsVO {
	
	@Id
	private String no;
	private String violt_cas_nm;
	private String violt_cas_cn;
	private String violt_ctgr_cd_nm;
	private String violt_ctgr_cd;
	private Date date;	

}
