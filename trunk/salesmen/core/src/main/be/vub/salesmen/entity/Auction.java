package be.vub.salesmen.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.Length;


//other imports
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.async.QuartzTriggerHandle;

@Entity
@Name("auction")
@Table(name="Auction")
public class Auction implements Serializable
{

	private static final long serialVersionUID = 2195670493386700385L;
	
	//Private Constants
	private final double DEFAULT_STARTING_PRICE=1.00;
	
	// Public Enumerations
	public enum AuctionStatus
	{
		UNLISTED,
		LISTED,
		FINISHED,
		CLOSED,
		REMOVED
	}
	
	// Private Attributes
	private Long auctionId;
	private Integer version;
	private UserAccount owner;
	private Category category;
	private String title;
	private String description;
  private Date startDate=new Date();
	private Date endDate=new Date();
	private double startingPrice;
	private AuctionStatus status=AuctionStatus.UNLISTED;
  private boolean hotAuction=false;
	
	@Lob
    private QuartzTriggerHandle quartzTriggerHandle;
	
	public Auction()
	{
		this.setStartingPrice(DEFAULT_STARTING_PRICE);
		this.setStatus(AuctionStatus.UNLISTED);
	}

	// Public Attribute getters/setters with annotations
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId()
	{
		return auctionId;
	}

	public void setId(Long id)
	{
		this.auctionId = id;
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


	//@NotNull
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public UserAccount getOwner()
    {
		return owner;
	}

	public void setOwner(UserAccount owner)
    {
		this.owner = owner;
	}

	//@NotNull   
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@NotNull
	@Length(min=5, max=32)
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@NotNull
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@NotNull
	//@Pattern(regex="^[0-9]*\.?[0-9]+$",
	//		 message="Illegal amount")
	public double getStartingPrice()
	{
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice)
	{
		this.startingPrice = startingPrice;
	}

	public void setStatus(AuctionStatus status)
	{
		this.status = status;
	}

	public AuctionStatus getStatus()
	{
		return status;
	}
    @NotNull
    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
    @NotNull
    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
      this.startDate = startDate;
    }

    public boolean isHotAuction() {
      return hotAuction;
    }

    public void setHotAuction(boolean hotAuction) {
      this.hotAuction = hotAuction;
    }

	public QuartzTriggerHandle getQuartzTriggerHandle() {
        return quartzTriggerHandle;
    }
    public void setQuartzTriggerHandle(QuartzTriggerHandle quartzTriggerHandle) {
        this.quartzTriggerHandle = quartzTriggerHandle;
    }
	
}