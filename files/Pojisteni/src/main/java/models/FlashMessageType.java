
package models;


public enum FlashMessageType
{
    
    error("danger"),
    success("success");
    
    
    private final String styleClass;
    
    
    FlashMessageType(String styleClass)
    {
        this.styleClass=styleClass;
    }
    
    
    public String getStyleClass()
    {
        return this.styleClass;
    }

}
