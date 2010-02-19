package be.vub.salesmen.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.Length;
import org.jboss.seam.annotations.Name;

@Entity
@Name("category")
public class Category implements Serializable
{
	private static final long serialVersionUID = -6813329677396178284L;

    private Long id;
    private Integer version;
    private String name;
    private Category parent;

    @Id @GeneratedValue
    public Long getCategoryId() {
        return id;
    }

    public void setCategoryId(Long id) {
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

    @ManyToOne
    @JoinColumn(name = "PARENT_CATEGORY_ID")    
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent)
    {
        this.parent = parent;
    }
}
