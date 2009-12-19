package be.vub.salesmen.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import org.jboss.seam.annotations.security.management.RoleConditional;
import org.jboss.seam.annotations.security.management.RoleGroups;
import org.jboss.seam.annotations.security.management.RoleName;

//import org.hibernate.validator.Length;

@Entity
public class UserRole implements Serializable
{
	
	private static final long serialVersionUID = 1391628537025835188L;
    private Integer version;

    private Long roleId;
    private String name;
    private boolean conditional;
    
    private Set<UserRole> groups;

    @Id @GeneratedValue
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