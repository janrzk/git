
package utils;


import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


@ApplicationScoped
public class Navigator
{
  
    public void redirect(String url) throws IOException
    {
        FacesContext context=FacesContext.getCurrentInstance();
        ExternalContext externalContext=context.getExternalContext();
        
        externalContext.redirect(externalContext.getRequestContextPath()+url);
        context.responseComplete();
    }

}
