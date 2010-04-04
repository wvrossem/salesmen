package be.vub.salesmen.session;

import be.vub.salesmen.entity.UserComment;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.faces.FacesMessages;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

import static org.jboss.seam.ScopeType.CONVERSATION;

@Scope(CONVERSATION)
@Name("viewComment")
public class ViewComment implements Serializable 
{
	private static final long serialVersionUID = 5953054119221671736L;
	
	// Private attributes
	private String text;
	private int rating;
	
	// Request Parameters
    @RequestParameter
    Long commentId;

    @In
    FacesMessages facesMessages;

    @DataModel
	private List<UserComment> comments;

	//@In annotations
	@In
    EntityManager entityManager;
	
	public void AddComment(UserComment comment) {
		if(comment != null)
		{
			comments.add(comment);
		}
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public List<UserComment> getComments() 
    {
        return comments;
    }

    public void setComments(List<UserComment> comments)
    {
        this.comments = comments;
    }

}