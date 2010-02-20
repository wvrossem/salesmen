package be.vub.salesmen.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import org.hibernate.validator.Length;

/*
 * Extra imports
 * 
 */
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Category implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6813329677396178284L;
	// attributes (you should probably edit these)
    private Long id;
    private Integer version;
    private String name;
    private Category parent;

    // add additional entity attributes

    // attribute getters/setters with annotations (you probably should edit)

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

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

}
