
package models;


public class FlashMessage
{

    private final FlashMessageType type;
    private final String message;

    
    public FlashMessage(String message, FlashMessageType type)
    {
        this.type=type;
        this.message=message;
    }

    
    public FlashMessageType getType()
    {
        return type;
    }

    
    public String getMessage()
    {
        return message;
    }

}
