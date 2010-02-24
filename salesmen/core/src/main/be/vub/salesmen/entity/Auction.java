package be.vub.salesmen.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import org.hibernate.validator.Length;

//other imports
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.validator.NotNull;

@Entity
public class Auction implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2195670493386700385L;
	//attributes (you should probably edit these)
    private Long id;
    private Integer version;
    private String name;

    // add additional entity attributes
    private User owner;
    private Category category;
    private String title;
    private String description;
    private Date endDate;
    private Bid highBid;
   // private Bid[] bids;
    private double startingPrice;

    //attribute getters/setters with annotations (you probably should edit)

    @Id @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    @SuppressWarnings("unused")
	private void setVersion(Integer version) {
        this.version = version;
    }

    @Length(max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

	@NotNull
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

	@NotNull
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@NotNull
	public Bid getHighBid() {
		return highBid;
	}

	public void setHighBid(Bid highBid) {
		this.highBid = highBid;
	}

	@NotNull
	public double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

}
