package be.vub.salesmen.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import javax.persistence.Version;
import org.hibernate.validator.Length;

//other imports
/*
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
*/
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;


@Entity
@Name("auction")
@Table(name="Auction")
public class Auction implements Serializable
{
	private final double DEFAULT_STARTING_PRICE=1.00;
	public enum AuctionStatus
	{
		UNLISTED,
		LISTED,
		FINISHED,
		CLOSED,
		REMOVED
	}
    /**
	 * 
	 */
	private static final long serialVersionUID = 2195670493386700385L;
	//attributes (you should probably edit these)
    private int id;
    private Integer version;

    // add additional entity attributes
   // private User owner;
    //private Category category;
    private String title;
    private String description;
    //private Date endDate;
    //private Bid highBid;
   // private Bid[] bids;
    private double startingPrice;
    private AuctionStatus status;
    
    public Auction()
    {
    	this.setStartingPrice(DEFAULT_STARTING_PRICE);
        this.setStatus(AuctionStatus.UNLISTED);
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    
	private void setVersion(Integer version)
    {
        this.version = version;
    }

/*
    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@NotNull   
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
*/
    @NotNull
    @Length(min=5, max=32)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
/*
	@NotNull
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	//@NotNull
	public Bid getHighBid() {
		return highBid;
	}

	public void setHighBid(Bid highBid) {
		this.highBid = highBid;
	}
*/
	@NotNull
	public double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public void setStatus(AuctionStatus status) {
		this.status = status;
	}

	public AuctionStatus getStatus() {
		return status;
	}

}
