package ro.tibi.csv.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.tibi.csv.dto.ClientSearchDTO;
import ro.tibi.csv.util.Constants.Sex;

@Entity
@Table(name="Clients")
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Version
	@JsonIgnore
	private Integer version;
	
	@Column(unique=true, nullable=false) 
	private Long securityCode;
	
	private String serialCode;
	private Integer serialNumber;
	private String lastName;
	private String firstName1;
	private String firstName2;
	private Date dateOfBirth;
	
	@Enumerated
	private Sex sex;
	
	private Date expireDate;
	private String emailAddress;
	private String phoneNumber;
	private String address;
//	private byte[] identityCardScan;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Long getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(Long securityCode) {
		this.securityCode = securityCode;
	}
	public String getSerialCode() {
		return serialCode;
	}
	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName1() {
		return firstName1;
	}
	public void setFirstName1(String firstName1) {
		this.firstName1 = firstName1;
	}
	public String getFirstName2() {
		return firstName2;
	}
	public void setFirstName2(String firstName2) {
		this.firstName2 = firstName2;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public byte[] getIdentityCardScan() {
//		return identityCardScan;
//	}
//	public void setIdentityCardScan(byte[] identityCardScan) {
//		this.identityCardScan = identityCardScan;
//	}
	
	public static Specification<Client> findByCriteria(final ClientSearchDTO searchCriteria) {

        return new Specification<Client>() {

            @Override
            public Predicate toPredicate(
                Root<Client> root,
                CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (searchCriteria.getSecurityCode() != null && !searchCriteria.getSecurityCode().isEmpty()) {
                    predicates.add(cb.equal(root.get("securityCod"), searchCriteria.getSecurityCode()));
                }
                if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
                    predicates.add(cb.equal(root.get("lastName"), searchCriteria.getName()));
                }
                if (searchCriteria.getFirstName() != null && !searchCriteria.getFirstName().isEmpty()) {
                    predicates.add(cb.equal(root.get("firstName1"), searchCriteria.getFirstName()));
                }
                

                return cb.and(predicates.toArray(new Predicate[] {}));
            }
        };
    }
	

	
}
