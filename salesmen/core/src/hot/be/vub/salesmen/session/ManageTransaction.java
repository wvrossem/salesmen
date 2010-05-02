package be.vub.salesmen.session;

import be.vub.salesmen.entity.Transaction;
import be.vub.salesmen.entity.UserAccount;
import be.vub.salesmen.entity.UserComment;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

import static org.jboss.seam.ScopeType.CONVERSATION;

@Name("manageTransaction")
@Scope(CONVERSATION)
public class ManageTransaction implements Serializable
{
	private Transaction transaction;
	private String commentText = "";

	@DataModel
	private List<UserComment> comments;

	@In
	EmailService emailService;

	@In
	EntityManager entityManager;

	public void requestPayment()
	{
		emailService.sendRequestPaymentEmail(transaction);
	}

	public Transaction getTransaction()
	{
		return transaction;
	}

	public void setTransaction(Transaction transaction)
	{
		this.transaction = transaction;
	}

	private void updateComments()
	{
		BasicSearchBean search = new BasicSearchBean();
		comments = search.findComments(transaction.getAuction(), this.entityManager);
	}

	/*
	Leave a comment
	*/
	public void addComment(UserAccount user)
	{
		updateComments();
		UserComment userComment = new UserComment(user, transaction.getAuction(), commentText);
		this.entityManager.persist(userComment);
		updateComments();
	}

	public String getCommentText()
	{
		return commentText;
	}

	public void setCommentText(String commentText)
	{
		this.commentText = commentText;
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
