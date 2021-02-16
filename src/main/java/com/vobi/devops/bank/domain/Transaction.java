package com.vobi.devops.bank.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 *
 */
@Entity
@Table(name = "transaction", schema = "public")

public class Transaction implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tran_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tranId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acco_id")
	@NotNull
	private Account account;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trty_id")
	@NotNull
	private TransactionType transactionType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_email")
	@NotNull
	private Users users;
	@NotNull
	@Column(name = "amount", nullable = false)
	private Double amount;
	@NotNull
	@Column(name = "date", nullable = false)
	private Date date;

	public Transaction(@NotNull Integer tranId, @NotNull Account account, @NotNull TransactionType transactionType,
			@NotNull Users users, @NotNull Double amount, @NotNull Date date) {
		super();
		this.tranId = tranId;
		this.account = account;
		this.transactionType = transactionType;
		this.users = users;
		this.amount = amount;
		this.date = date;
	}

	public Transaction() {
		super();
	}

	public Integer getTranId() {
		return tranId;
	}

	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
