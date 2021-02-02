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
@Table(name = "user_type", schema = "public")
public class UserType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usty_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ustyId;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userType")
	private List<Users> userses = new ArrayList<>();

	public UserType(@NotNull Integer ustyId, @NotNull @NotEmpty @Size(max = 255) String enable,
			@NotNull @NotEmpty @Size(max = 255) String name, List<Users> userses) {
		super();
		this.ustyId = ustyId;
		this.enable = enable;
		this.name = name;
		this.userses = userses;
	}

	public UserType() {
		super();
	}

	public Integer getUstyId() {
		return ustyId;
	}

	public void setUstyId(Integer ustyId) {
		this.ustyId = ustyId;
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

	public List<Users> getUserses() {
		return userses;
	}

	public void setUserses(List<Users> userses) {
		this.userses = userses;
	}

}