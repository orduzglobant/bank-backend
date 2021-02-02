package co.com.coomeva.devops.bank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 *
 */
@Entity
@Table(name = "registered_account", schema = "public")

public class RegisteredAccount implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "reac_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reacId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acco_id")
	@NotNull
	private Account account;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id")
	@NotNull
	private Customer customer;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "enable", nullable = false)
	private String enable;

	public RegisteredAccount(@NotNull Integer reacId, @NotNull Account account, @NotNull Customer customer,
			@NotNull @NotEmpty @Size(max = 255) String enable) {
		super();
		this.reacId = reacId;
		this.account = account;
		this.customer = customer;
		this.enable = enable;
	}

	public RegisteredAccount() {
		super();
	}

	public Integer getReacId() {
		return reacId;
	}

	public void setReacId(Integer reacId) {
		this.reacId = reacId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
