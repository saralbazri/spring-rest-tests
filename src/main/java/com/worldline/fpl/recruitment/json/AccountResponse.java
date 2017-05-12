package com.worldline.fpl.recruitment.json;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Account json representation
 * 
 * @author A525125
 *
 */
@Data
public class AccountResponse implements Serializable {

	private static final long serialVersionUID = 1311670657098492357L;

	@NotNull
	private Long id;

	@NotNull
	private String number;

	@NotNull
	private String type;

	@NotNull
	private BigDecimal balance;

}
