
package org.celt.kopidlno.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FileUtils
{
  
  public static final String SEPARATOR=File.separator;
  private static final int ZIP_BUFFER_SIZE=4096;

  
  public static String extract(String archive, String directory)
      throws FileNotFoundException, IOException
  {
    byte[] buffer=new byte[ZIP_BUFFER_SIZE];
    ZipInputStream inputStream=new ZipInputStream(new FileInputStream(archive));
    String extractedName=null;
    ZipEntry entry=inputStream.getNextEntry();
    if (entry!=null)
    {
      extractedName=directory+SEPARATOR+entry.getName();
      File extractedFile=new File(extractedName);
      FileOutputStream outputStream=new FileOutputStream(extractedFile);
      int len;
      while ((len=inputStream.read(buffer))>0)
      {
        outputStream.write(buffer,0,len);
      }
      outputStream.close();
    }
    inputStream.closeEntry();
    inputStream.close();
    return extractedName;
  }
  
  
  public static boolean delete(String fileName)
  {
    File file=new File(fileName);
    return file.delete();
  }
  
}
