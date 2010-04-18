package be.vub.salesmen.session;

import be.vub.salesmen.entity.Transaction;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.io.Serializable;

import static org.jboss.seam.ScopeType.CONVERSATION;

@Name("manageTransaction")
@Scope(CONVERSATION)
public class ManageTransaction implements Serializable
{
	private Transaction transaction;

	@In
	EmailService emailService;

	public void requestPayment() {
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
}
