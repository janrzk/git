
package org.celt.springpojisteni.utils;


import lombok.Getter;


@Getter
public enum FlashMessageType
{
    
    error("danger"),
    success("success");
    
    
    private final String styleClass;
    
    
    FlashMessageType(String styleClass)
    {
        this.styleClass=styleClass;
    }

}
