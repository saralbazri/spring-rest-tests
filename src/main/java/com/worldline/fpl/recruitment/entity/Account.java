package com.worldline.fpl.recruitment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;

/**
 * Account entity
 * 
 * @author A525125
 *
 */
@Data
@Entity

public class Account implements Serializable {

	private static final long serialVersionUID = -3548441891975414771L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="NUMBER")
	private String number;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="BALANCE")
	private BigDecimal balance;
	
	@Column(name="CREATIONDATE")
	private Date creationDate;
	
	@Column(name="ISACTIVE")
	private boolean isActive;
	
	@OneToMany(mappedBy = "account", cascade = { CascadeType.ALL})
			 private List<Transaction> transactions;
}
