/**
 * @author Matthew Shoemate
 */

package DVD.DTO;

public class DVD
{
    private int dvdIndex;
    private String dvdName, dvdDate, dvdDirectorName, dvdRating, dvdStudio, userRating;
    
    public DVD(String dvdName)
    {
        this.dvdName = dvdName;
    }
    
    public int getDVDIndex()
    {
        return dvdIndex;
    }
    
    public String getDVDName()
    {
        return dvdName;
    }
    
    public String getDVDDate()
    {
        return dvdDate;
    }
    
    public String getDVDDirectorName()
    {
        return dvdDirectorName;
    }
    
    public String getDVDStudio()
    {
        return dvdStudio;
    }
    
    public String getUserRating()
    {
        return userRating;
    }
    
    public String getDVDRating()
    {
        return dvdRating;
    }
    
    public void setDVDIndex(int dvdIndex)
    {
        this.dvdIndex = dvdIndex;
    }
    
    public void setDVDName(String dvdName)
    {
        this.dvdName = dvdName;
    }
    
    public void setDVDDate(String dvdDate)
    {
        this.dvdDate = dvdDate;
    }
    
    public void setDVDDirectorName(String dvdDirectorName)
    {
        this.dvdDirectorName = dvdDirectorName;
    }
    
    public void setDVDStudio(String dvdStudio)
    {
        this.dvdStudio = dvdStudio;
    }
    
    public void setUserRating(String userRating)
    {
        this.userRating = userRating;
    }
    
    public void setDVDRating(String dvdRating)
    {
        this.dvdRating = dvdRating;
    }
}