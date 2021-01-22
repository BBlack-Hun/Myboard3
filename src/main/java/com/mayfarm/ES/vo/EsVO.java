package com.mayfarm.ES.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "bank", type="_doc")
public class EsVO {
	@Id
	private String id;
	private String index;
	private String type;
	private int account_number;
	private String address;
	private int age;
	private int balance;
	private String city;
	private String email;
	private String employer;
	private String firstname;
	private String gender;
	private String lastname;
	private String state;
	
	

}
