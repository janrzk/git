
package org.celt.springpojisteni.utils;


import lombok.Getter;


@Getter
public class FlashMessage
{

    private final FlashMessageType type;
    private final String text;

    
    public FlashMessage(String text, FlashMessageType type)
    {
        this.type=type;
        this.text=text;
    }

}
