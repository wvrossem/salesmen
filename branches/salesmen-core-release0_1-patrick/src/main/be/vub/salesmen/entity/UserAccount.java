package be.vub.salesmen.entity;

import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.NotNull;

import org.jboss.seam.annotations.security.management.PasswordSalt;
import org.jboss.seam.annotations.security.management.UserEnabled;
import org.jboss.seam.annotations.security.management.UserPassword;
import org.jboss.seam.annotations.security.management.UserPrincipal;
import org.jboss.seam.annotations.security.management.UserRoles;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UserAccount implements Serializable
{
	private static final long serialVersionUID = -4294661352692699050L;
	private Integer version;

	private Long accountId;
	private String username;
	private String passwordHash;
	private String passwordSalt;
	private boolean enabled;

    private Set<UserRole> roles;
    private User user;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAccountId() 
    {
        return accountId;
    }

    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    @NotNull
    @UserPrincipal
    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    @UserPassword
    public String getPasswordHash() 
    {
    	return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash)
    {
    	this.passwordHash = passwordHash;
    }
    
    @PasswordSalt
    public String getPasswordSalt()
    {
       return passwordSalt;
    }
    
    public void setPasswordSalt(String passwordSalt)
    {
       this.passwordSalt = passwordSalt;
    }
    
    @UserEnabled
    public boolean isEnabled()
    {
       return enabled;
    }

    public void setEnabled(boolean enabled)
    {
       this.enabled = enabled;      
    }
    
    @UserRoles
    @ManyToMany(targetEntity = UserRole.class)
    @JoinTable(name = "UserAccountRoles",
    	joinColumns = @JoinColumn(name = "AccountId"),
    	inverseJoinColumns = @JoinColumn(name = "RoleId"))
    public Set<UserRole> getRoles()
    {
    	return roles;
    }
    
    public void setRoles(Set<UserRole> roles)
    {
    	this.roles = roles;
    }
    
    @OneToOne
    @JoinColumn(name = "userid")
    public User getUser() {
    	return user;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }
    
    @Version
    public Integer getVersion() 
    {
        return version;
    }

	@SuppressWarnings("unused")
	private void setVersion(Integer version) 
    {
        this.version = version;
    }
}