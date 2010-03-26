package be.vub.salesmen.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
public class SearchTerm implements Serializable
{
	// Private Attributes
	private Long id;
	private Integer version;
	private String term;

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

	private void setVersion(Integer version)
	{
		this.version = version;
	}

	@NotNull
	@Length(min=3, max = 20)
	public String getTerm()
	{
		return term;
	}

	public void setTerm(String term)
	{
		this.term = term;
	}

}