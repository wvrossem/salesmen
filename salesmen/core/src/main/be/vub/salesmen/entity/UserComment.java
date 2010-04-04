package be.vub.salesmen.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.validator.NotNull;

@Entity
public class UserComment implements Serializable
{
	private static final long serialVersionUID = 7444022244366099972L;
	
	// Private attributes
	private String content = "No detailed comments"; 
	private int rating = 0;
	private Date date = new Date();
	private User user;
	private Auction auction;
	private Long commentId;
	
	// Public attribute getters/setters with annotations
	@Id @GeneratedValue
    public Long getId()
	{
        return commentId;
    }

    public void setId(Long commentId)
    {
        this.commentId = commentId;
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
	public int getRating()
	{
		return rating;
	}
	
	public void setRating(int rating)
	{
		this.rating = rating;
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
	@OneToOne
	@JoinColumn(name = "userid")
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "auctionid")
	public Auction getAuction()
	{
		return auction;
	}
	
	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}
}
