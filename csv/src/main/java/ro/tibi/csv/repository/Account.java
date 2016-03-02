package ro.tibi.csv.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.tibi.csv.util.Constants.AccountStatus;

@Entity
@Table(name="Accounts")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(unique=true, nullable=false) 
	private String username;
	
	@Column(nullable=false) 
	private String password;

	
	@Enumerated
	private AccountStatus status;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name="account_id")
    public List<Role> roles;


	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Version
	@JsonIgnore
	private int version;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String[] getAuthorities(){
		String[] authorities=new String[this.roles.size()];
		for (int i =0;i<this.roles.size();i++) {
			authorities[i] = "ROLE_"+this.roles.get(i).getRole();
		}
		return authorities;
	}
	
	
	

}
