package be.vub.salesmen.entity;

/**
 * Created by IntelliJ IDEA.
 * User: Bart
 * Date: 2-apr-2010
 * Time: 10:52:50
 */
import java.io.IOException;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

//other imports
import org.hibernate.validator.NotNull;

@Entity
@Table(name="AuctionImage")
public class AuctionImage implements Serializable
{
    private static final long serialVersionUID = -1219357931402690891L;

    private Integer imageId;
    private Auction auction;
    private byte[] data;
    private String contentType;

    public AuctionImage()
    {

    }

    public AuctionImage(String path, String contentType) throws IOException
    {
        this.data= loadBytesFromFile(path);
        this.contentType=contentType;
    }

    @Id @GeneratedValue
    public Integer getImageId()
    {
        return imageId;
    }

    public void setImageId(Integer imageId)
    {
        this.imageId = imageId;
    }

    @XmlTransient
    @NotNull
    @ManyToOne
    @JoinColumn(name = "AUCTION_ID")
    public Auction getAuction()
    {
        return auction;
    }

    public void setAuction(Auction auction)
    {
        this.auction = auction;
    }

    @Lob
    public byte[] getData()
    {
        return data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }


    //experiment
 	private static final int DEFAULT_CHUNK_SIZE = 1024;
    private byte[] loadBytesFromFile( String fileName ) throws java.io.IOException
    {
        return loadBytesFromStream( new java.io.FileInputStream( fileName ), DEFAULT_CHUNK_SIZE );
    }

    private byte[] loadBytesFromStream( java.io.InputStream in ) throws java.io.IOException
    {
        return loadBytesFromStream( in, DEFAULT_CHUNK_SIZE );
    }

    private byte[] loadBytesFromStream( java.io.InputStream in, int chunkSize )
    throws java.io.IOException
    {
        if( chunkSize < 1 )
        chunkSize = DEFAULT_CHUNK_SIZE;

        int count;
        java.io.ByteArrayOutputStream bo = new java.io.ByteArrayOutputStream();
        byte[] b = new byte[chunkSize];
        try
        {
            while( ( count = in.read( b, 0, chunkSize ) ) > 0 )
            {
                bo.write( b, 0, count );
            }
            return bo.toByteArray();
        }
        finally
        {
            bo.close();
        }
    }
}

