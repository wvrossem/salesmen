package be.vub.salesmen.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

@Entity
@Name("userComment")
@Table(name="UserComment")
public class UserComment implements Serializable
{
	private static final long serialVersionUID = 7444022244366099972L;
	
	// Private attributes
	private Long id;
    private Integer version;
	private String content; 
	private Date date;
	private UserAccount user;
	private Auction auction;
	
	public UserComment()
	{
		content = "";
		date = java.util.Calendar.getInstance().getTime();
	}
	
	public UserComment(UserAccount user, Auction auction) {
		content = "No comment";
		this.auction = auction;
		this.user = user;
		date = java.util.Calendar.getInstance().getTime();
	}
	
	public UserComment(UserAccount user, Auction auction, String content) {
		this.content = content;
		this.auction = auction;
		this.user = user;
		date = java.util.Calendar.getInstance().getTime();
	}
	
	// Public attribute getters/setters with annotations
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
	
	@NotNull
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	@NotNull
	public Date getDate()
	{
		return date;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "USERACCOUNT_ID")
	public UserAccount getUser()
	{
		return user;
	}
	
	public void setUser(UserAccount user)
	{
		this.user = user;
	}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "AUCTION_ID")
	public Auction getAuction()
	{
		return auction;
	}
	
	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}
}
