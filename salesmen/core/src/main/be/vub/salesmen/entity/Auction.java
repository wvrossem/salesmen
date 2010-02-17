package be.vub.salesmen.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import org.hibernate.validator.Length;

@Entity
public class Auction implements Serializable
{
    // seam-gen attributes (you should probably edit these)
    private int id;
    private Integer version;
    private String name;
    
    private User owner;
    private Category category;
    private String title;
    private String description;
    private Date endDate;
    private Bid highBid;
    private Bid[] bidHistory;
    private double startingPrice;

    // add additional entity attributes

    // seam-gen attribute getters/setters with annotations (you probably should edit)

    @Id @GeneratedValue
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

}
