package com.worldline.fpl.recruitment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Transaction entity
 * 
 * @author A525125
 *
 */
@Data
@Entity
public class Transaction implements Serializable {

	private static final long serialVersionUID = 706690724306325415L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String number;

	private BigDecimal balance;

	@ManyToOne
	@JoinColumn(name = "ACCOUNTID", nullable = false)
	private Account account;
}
