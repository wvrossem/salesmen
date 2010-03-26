package be.vub.salesmen.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import org.hibernate.validator.Length;
import org.jboss.seam.annotations.Name;


/*
* Extra imports
* 
*/
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.Table;
import org.hibernate.validator.NotNull;
import javax.persistence.GenerationType;

@Entity
@Name("category")
@Table(name="Category",uniqueConstraints = {@UniqueConstraint(columnNames={"name"})})
public class Category implements Serializable
{
	private static final long serialVersionUID = -6813329677396178284L;
	
	// Private Attributes
	private int id;
	private Integer version;
	private String name;
	private Category parent;


	// Public Attribute getters/setters with annotations 
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public Category getParent()
	{
		return parent;
	}

	public void setParent(Category parent)
	{
		this.parent = parent;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId()
	{
		return id;
	}

	public void setId(int id)
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

	@NotNull
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
