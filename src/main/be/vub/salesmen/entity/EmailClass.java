package be.vub.salesmen.entity;

import java.io.Serializable;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

@Name("emailClass")
public class EmailClass implements Serializable {
	
	private static final long serialVersionUID = 4629653426165185678L;
	
	// Private attributes
	private String fullName;
	private String emailAddress;
	private String subject;
	private String content;
	
	public EmailClass(String fullName, String emailAddress, String subject, String content)
	{
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.subject = subject;
		this.content = content;
	}
	
	public EmailClass() {}
	
	// Public getter/setters with annotations
	@NotNull
	@Length(min=3, max=64)
	public String getFullName()
	{
		return fullName;
	}
	
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}
	
	@NotNull
	@Length(min=3, max=32)
	public String getEmailAddress()
	{
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}
	
	@NotNull
	@Length(min=3, max=64)
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	@NotNull
	@Length(min=3, max=1024)
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
}