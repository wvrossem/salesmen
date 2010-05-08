package be.vub.salesmen.session;

import be.vub.salesmen.entity.Auction;
import be.vub.salesmen.entity.AuctionImage;
import be.vub.salesmen.entity.Category;
import be.vub.salesmen.entity.UserAccount;
import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.async.QuartzTriggerHandle;
import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;

import javax.ejb.Remove;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.jboss.seam.ScopeType.CONVERSATION;

@Name("manageAuction")
@Scope(CONVERSATION)
public class ManageAuctionBean implements ManageAuction, Serializable 
{
	private static final long serialVersionUID = 5797405997183391745L;


	// Private attributes
	Auction auction; 
	private boolean inputIsOk=false;
	private Category category;
    private Date auctionEndDate;
    private Long auctionId;

    //image related stuff
    private List<AuctionImage> images = new ArrayList<AuctionImage>();
    private byte[] imageData;
    private String imageContentType;

	// In annotations
	@In EntityManager entityManager;
    @In FacesMessages facesMessages;
	@In StatusMessages statusMessages;
	@In AuctionProcessor processor;

    public ManageAuctionBean()
    {
        long currentTimeInMillisec = System.currentTimeMillis();
        this.auctionEndDate = new Date( currentTimeInMillisec+24*60*60*1000 );//1day = 24*60*60*1000 milliseconds
    }

	@Begin(join = true)
	public void createAuction()
	{
		if(this.auction==null)  //REQUIRED, otherwise view-fields will be emptied on error message
		{
			this.setAuction(new Auction());
		}
	}

    public void uploadImage()
    {
        if (imageData == null || imageData.length == 0)
        {
            FacesMessages.instance().add("No image selected");
        }
        else
        {
            AuctionImage img = new AuctionImage();
            img.setAuction(this.auction);
            img.setData(imageData);
            img.setContentType(imageContentType);
            images.add(img);
            imageData = null;
            imageContentType = null;
        }
    }

    public boolean verifyPrice()
    {
        if(this.auction.getStartingPrice()<=0)
		{
            facesMessages.addToControlFromResourceBundle("price", FacesMessage.SEVERITY_INFO, "salesmen.Auction.create.priceBelowZero");
            return false;
        }
        return true;
    }

    public boolean verifyEndDate()
    {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();//current date & time
        this.auction.setEndDate(this.auctionEndDate);
        if(now.after(this.auction.getEndDate()))
        {
            facesMessages.addToControlFromResourceBundle("endDate", FacesMessage.SEVERITY_INFO, "salesmen.Auction.create.endDateBeforeNow");
            return false;
        }
        return true;
    }

    public boolean verifyCategory()
    {
		if(category==null)
		{
            facesMessages.addToControlFromResourceBundle("category", FacesMessage.SEVERITY_INFO, "salesmen.Auction.create.invalidCategory");
           // return false;
            return false;//TODO: remove when category-select works as it should
		}
        else
		{
			auction.setCategory(category);
			return true;
		}
    }

	
	public boolean checkInput()
	{
        //make sure input is ok
        if(!verifyPrice() || !verifyEndDate() || !verifyCategory())
        {
            inputIsOk = false;
            return false;
        }

        //all ok
		inputIsOk = true;
        return true;
	}

	public void saveAndSchedule(UserAccount user)
    {
		this.auction.setOwner(user);
        
        QuartzTriggerHandle handle =
			processor.scheduleAuction(auction.getEndDate(), null, auction.getEndDate(), auction);        
        auction.setQuartzTriggerHandle(handle);
    }

	@End
	public void confirm()
	{
        Calendar cal = Calendar.getInstance();
        this.auction.setStartDate(cal.getTime());  //current date & time
		this.auction.setStatus(Auction.AuctionStatus.LISTED);


        /*
        (BART: keep for a while)

        //if no images were uploaded, add the default salesmen image (this.contentType=contentType;)
        if(images.size()==0)
        {
            //TODO: load default image
            try
            {
                AuctionImage defaultImage=new AuctionImage("salesmen_default_auction_image.png","image/png");
            }
            catch (IOException e)
            {
                System.out.println("ERROR: defaultImage failed");
            }
        }
        */

        //save the auction (id gets filled in, perform before persisting images!)
        entityManager.persist(this.auction);

        //save the images
        for (AuctionImage img : images)
        {
            img.setAuction(this.auction);
            entityManager.persist(img);
        }
        //cleanup
        this.auctionId=this.auction.getId();
        this.auction=null;
        this.images=null;
	}
	
	@Destroy @Remove
	public void destroy()
	{

	}
	
	// Public Attribute getters/setters with annotations 
	public void setAuction(Auction auction)
	{
		this.auction = auction;
	}
	
	public Auction getAuction()
	{
		return auction;
	}

	public void setInputIsOk(boolean inputIsOk)
	{
		this.inputIsOk = inputIsOk;
	}

	public boolean isInputIsOk()
	{
		return inputIsOk;
	}
	
	public Category getCategoryId()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
		statusMessages.add("Category " + category.getName() + " selected");
	}

    public void processTreeNodeImplSelection(Category cat) {
		category = cat;
    }

    public Date getAuctionEndDate()
    {
        return auctionEndDate;
    }

    public void setAuctionEndDate(Date auctionEndDate)
    {
        this.auctionEndDate = auctionEndDate;
    }

    /* image upload related functions */
    public void setImageData(byte[] imageData)
    {
        this.imageData = imageData;
    }

    public void setImageContentType(String contentType)
    {
        this.imageContentType = contentType;
    }

    public List<AuctionImage> getImages()
    {
        return images;
    }

    public Long getAuctionId()
    {
        return auctionId;
    }
}