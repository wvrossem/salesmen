package be.vub.salesmen.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;

@Entity
public class Auction implements Serializable
{
	private static final long serialVersionUID = -398119848361114787L;

    private int id;
    private Integer version;
    private String name;
    
    private User owner;
    private Category category;
    private String title;
    private String description;
    private Date endDate;
    private Bid highBid;
    //private Bid[] bidHistory;
    private double startingPrice;

    // add additional entity attributes



    @Id @GeneratedValue
    public int getId() {
        return id;
    }

    /**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	@NotNull
    @Length(min=8, max=32)
    @Pattern(regex="[a-zA-Z0-9,.:\t]+",
    		message="Titles can only contain letters, numbers, ',', '.', ':' and spaces.")
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the highBid
	 */
	public Bid getHighBid() {
		return highBid;
	}

	/**
	 * @param highBid the highBid to set
	 */
	public void setHighBid(Bid highBid) {
		this.highBid = highBid;
	}

	/**
	 * @return the startingPrice
	 */
	public double getStartingPrice() {
		return startingPrice;
	}

	/**
	 * @param startingPrice the startingPrice to set
	 */
	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public void setId(int id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }



    @Length(max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
