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
	private String content; 
	private int rating;
	private Date date = new Date();
	private UserAccount user;
	private Auction auction;
	private Long commentId;
	
	public UserComment(UserAccount user, Auction auction) {
		content = "No detailed comment";
		rating = 0;
		this.auction = auction;
		this.user = user;
		date = java.util.Calendar.getInstance().getTime();
	}
	
	public UserComment(UserAccount user, Auction auction, int rating, String content) {
		this.content = content;
		this.rating = rating;
		this.auction = auction;
		this.user = user;
		date = java.util.Calendar.getInstance().getTime();
	}
	
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
	public UserAccount getUser()
	{
		return user;
	}
	
	public void setUser(UserAccount user)
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
