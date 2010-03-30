package be.vub.salesmen.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
public class Bid implements Serializable
{
	private static final long serialVersionUID = -2396616177266665351L;
	
	// Private Attributes
    private Long id;
    private Integer version;


    private double amount;
    private UserAccount owner;
    private Auction auction;


    public Bid(double amount, UserAccount owner, Auction auction)
    {
        this.amount = amount;
        this.owner = owner;
        this.auction = auction;
    }

    public Bid()
    {
        this.amount=0;
    }

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


    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    @NotNull
	@ManyToOne
	@JoinColumn(name = "USERACCOUNT_ID")
    public UserAccount getOwner()
    {
        return owner;
    }

    public void setOwner(UserAccount owner)
    {
        this.owner = owner;
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