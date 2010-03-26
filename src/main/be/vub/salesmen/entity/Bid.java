package be.vub.salesmen.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import org.hibernate.validator.Length;

@Entity
public class Bid implements Serializable
{
	private static final long serialVersionUID = -2396616177266665351L;
	
	// Private Attributes
    private Long id;
    private Integer version;
    private String name;
	
	// Public Attribute getters/setters with annotations 
    @Id @GeneratedValue
    public Long getId()
	{
        return id;
    }

    public void setId(Long id)
	{
        this.id = id;
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

    @Length(max = 20)
    public String getName()
	{
        return name;
    }

    public void setName(String name)
	{
        this.name = name;
    }

}