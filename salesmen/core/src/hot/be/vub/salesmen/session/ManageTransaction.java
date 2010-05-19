package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.Transaction;
import be.vub.salesmen.entity.UserAccount;
import be.vub.salesmen.entity.UserComment;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.web.RequestParameter;

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

	@In
	Search search;
	
	@RequestParameter
    Long transactionId;


	@Begin(join = true)
	public void start()
	{
		System.out.println("Starting manage transaction");
		transaction = search.findTransaction(transactionId);
		System.out.println("Found transaction: " + transaction.getId());
	}

	public void setPayed()
	{
		if(transaction!=null && transaction.getId()!=null)
		{
			System.out.println("Setting transaction to payed");
			transaction.setPayed(true);
		}
	}

	public void setShipped()
	{
		if(transaction!=null && transaction.getId()!=null)
		{
			System.out.println("Setting transaction to shipped");
			transaction.setShipped(true);
		}
	}

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
		if(transaction!=null && transaction.getId()!=null)
		{
			comments = search.findComments(transaction);
		}
	}

	/*
	Leave a comment
	*/
	public void addComment(UserAccount user)
	{
		if(transaction!=null && transaction.getId()!=null)
		{
			UserComment userComment = new UserComment(user, transaction, commentText);
			this.entityManager.persist(userComment);
			updateComments();
		}		
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

	public Long getTransactionId()
	{
		return transactionId;
	}

	public void setTransactionId(Long transactionId)
	{
		this.transactionId = transactionId;
	}


}
