//@author Matthew Shoemate

package ClassModeling;

public class Book
{
    private String publishSystem;
    private String catalogSystem;
    
    public Book(){}
    
    public Book(String inPublishSystem, String inCatalogSystem)
    {
        this.publishSystem = inPublishSystem;
        this.catalogSystem = inCatalogSystem;
    }
    
    public String getPublishSystem()
    {
        return publishSystem;
    }
    
    public void setPublishSystem(String publishSystem)
    {
        this.publishSystem = publishSystem;
    }
    
    public String getCatalogSystem()
    {
        return catalogSystem;
    }
    
    public void setCatalogSystem(String catalogSystem)
    {
        this.catalogSystem = catalogSystem;
    }
}