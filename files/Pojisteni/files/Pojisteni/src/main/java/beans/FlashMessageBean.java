
package beans;


import java.io.Serializable;
import java.util.LinkedList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.FlashMessageType;
import models.FlashMessage;


@SessionScoped()
@Named()
public class FlashMessageBean implements Serializable
{

    private final LinkedList<FlashMessage> flashMessages=new LinkedList<>();
    

    public void add(String message, FlashMessageType type)
    {
        flashMessages.addFirst(new FlashMessage(message,type));
    }

    
    public Iterable<FlashMessage> getFlashMessages()
    {
        LinkedList<FlashMessage> result=new LinkedList<>(flashMessages);
        flashMessages.clear();
        return result;
    }

    
    public boolean isEmpty()
    {
        return flashMessages.isEmpty();
    }
    
}
