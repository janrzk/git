
package org.celt.kopidlno.utils;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class Downloader
{
  
  public static void download(String source, String destination)
      throws MalformedURLException, IOException
  {
    URL link=new URL(source);
    ReadableByteChannel channel=Channels.newChannel(link.openStream());
    FileOutputStream stream=new FileOutputStream(destination);
    stream.getChannel().transferFrom(channel,0,Long.MAX_VALUE);
  }

}
