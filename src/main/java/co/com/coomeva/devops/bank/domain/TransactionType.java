package co.com.coomeva.devops.bank.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "transaction_type", schema = "public")

public class TransactionType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "trty_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trtyId;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "enable", nullable = false)
	private String enable;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionType")
	private List<Transaction> transactions = new ArrayList<>();

	public TransactionType(@NotNull Integer trtyId, @NotNull @NotEmpty @Size(max = 255) String enable,
			@NotNull @NotEmpty @Size(max = 255) String name, List<Transaction> transactions) {
		super();
		this.trtyId = trtyId;
		this.enable = enable;
		this.name = name;
		this.transactions = transactions;
	}

	public TransactionType() {
		super();
	}

	public Integer getTrtyId() {
		return trtyId;
	}

	public void setTrtyId(Integer trtyId) {
		this.trtyId = trtyId;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}