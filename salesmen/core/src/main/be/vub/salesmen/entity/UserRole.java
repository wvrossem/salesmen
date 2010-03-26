package be.vub.salesmen.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.jboss.seam.annotations.security.management.RoleConditional;
import org.jboss.seam.annotations.security.management.RoleGroups;
import org.jboss.seam.annotations.security.management.RoleName;

@Entity
public class UserRole implements Serializable
{
	private static final long serialVersionUID = 8164732519163535494L;

	// Private Attributes
	private Integer version;
	private Long roleId;
	private String name;
	private boolean conditional;
	private Set<UserRole> groups;

	// Public Attribute getters/setters with annotations 
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getRoleId() 
	{
		return roleId;
	}

	public void setRoleId(Long roleId) 
	{
		this.roleId = roleId;
	}

	@RoleName
	public String getName() 
	{
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@RoleGroups
	@ManyToMany(targetEntity = UserRole.class)
	@JoinTable(name = "UserRoleGroup", 
		joinColumns = @JoinColumn(name = "RoleId"),
		inverseJoinColumns = @JoinColumn(name = "MemberOf")
		)
	public Set<UserRole> getGroups()
	{
		return groups;
	}

	public void setGroups(Set<UserRole> groups)
	{
		this.groups = groups;
	}   
	
	@RoleConditional
	public boolean isConditional()
	{
		return conditional;
	}

	public void setConditional(boolean conditional)
	{
		this.conditional = conditional;
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