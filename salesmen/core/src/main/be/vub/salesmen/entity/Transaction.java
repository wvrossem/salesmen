package be.vub.salesmen.entity;

import org.hibernate.validator.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Transaction implements Serializable
{
	// Private Attributes
	private Long id;
	private Integer version;

	private boolean isPayed;
	private boolean isShipped;
	private UserAccount buyer;
	private UserAccount seller;
	private Auction auction;
	private int rating;

	// Public Attribute getters/setters with annotations
	@Id
	@GeneratedValue
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

	public boolean isPayed()
	{
		return isPayed;
	}

	public void setPayed(boolean payed)
	{
		isPayed = payed;
	}

	public boolean isShipped()
	{
		return isShipped;
	}

	public void setShipped(boolean shipped)
	{
		isShipped = shipped;
	}

	@NotNull
	@OneToOne
	@JoinColumn(name = "BUYERACCOUNT_ID")
	public UserAccount getBuyer()
	{
		return buyer;
	}

	public void setBuyer(UserAccount buyer)
	{
		this.buyer = buyer;
	}

	@NotNull
	@OneToOne
	@JoinColumn(name = "SELLERACCOUNT_ID")
	public UserAccount getSeller()
	{
		return seller;
	}

	public void setSeller(UserAccount seller)
	{
		this.seller = seller;
	}

	@NotNull
	@OneToOne
	@JoinColumn(name = "AUCTION_ID")
	public Auction getAuction()
	{
		return auction;
	}

	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}

	public int getRating()
	{
		return rating;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
	}
}