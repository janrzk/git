
package org.celt.kopidlno;


import org.celt.kopidlno.models.CastObce;
import org.celt.kopidlno.models.Obec;
import org.celt.kopidlno.utils.Downloader;
import org.celt.kopidlno.utils.FileUtils;
import org.celt.kopidlno.utils.Parser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.celt.kopidlno.utils.Connector;
import org.celt.kopidlno.utils.DAO;
import org.xml.sax.SAXException;


public class Main
{
  
  private static final String SOURCE="https://www.smartform.cz/download/20210331_OB_573060_UZSZ.xml.zip";
  

  private static final String DB_URL="jdbc:mysql://localhost/test_db";
  private static final String DB_USERNAME="";
  private static final String DB_PASSWORD="";
  
  
  public static void main(String[] args)
  {

    String fileName=SOURCE.substring(SOURCE.lastIndexOf('/')+1,SOURCE.length());
    String tempDir=System.getProperty("java.io.tmpdir");
    String localFile=tempDir+FileUtils.SEPARATOR+fileName;

    
    System.out.println("Stahuje se soubor "+SOURCE+"…");
    try
    {
      Downloader.download(SOURCE, localFile);
    }
    catch (IOException e)
    {
      System.err.println("Chyba: Soubor se nepodařilo stáhnout.");
      System.err.println(e.getMessage());
      return;
    }
    System.out.println("Soubor byl uložen do složky "+tempDir+".");
    
    
    System.out.println("Rozbaluje se soubor "+localFile+"…");
    String localUnzipped;
    try
    {
      localUnzipped=FileUtils.extract(localFile, tempDir);
    }
    catch (IOException e)
    {
      System.err.println("Chyba: Soubor se nepodařilo rozbalit.");
      System.err.println(e.getMessage());
      return;
    }
    
    if (localUnzipped==null)
    {
      System.err.println("Chyba: Archív je prázdný.");
      return;
    }
    System.out.println("Soubor byl rozbalen.");
    
    
    System.out.println("Načítají se data ze souboru "+localUnzipped+"…");
    List<Obec> obce=new ArrayList();
    List<CastObce> castiObci=new ArrayList();
    try
    {
      Parser.parse(localUnzipped, obce, castiObci);
    }
    catch (ParserConfigurationException | SAXException | IOException e)
    {
      System.err.println("Chyba: Soubor se nepodařilo naparsovat.");
      System.err.println(e.getMessage());
      return;
    }
    System.out.println("Data byla načtena.");

    
    System.out.println("Probíhá připojování k databázi…");
    try
    {
      Connector.openConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
    }
    catch (SQLException e)
    {
      System.err.println("Chyba: Nepodařilo se připojit k databázi.");
      System.err.println(e.getMessage());
      return;
    }


    System.out.println("Ukládají se data…");
    try
    {
      DAO.storeObce(obce);
      DAO.storeCastiObci(castiObci);
    }
    catch (SQLException e)
    {
      System.err.println("Chyba: Nepodařilo se uložit data.");
      System.err.println(e.getMessage());
      return;
    }
    
    
    System.out.println("Uzavírá se připojení…");
    try
    {
      Connector.closeConnection();
    }
    catch (SQLException e)
    {
      System.err.println("Chyba: Připojení se nepodařilo uzavřít.");
      System.err.println(e.getMessage());
      return;
    }
    System.out.println("Připojení bylo uzavřeno.");
    
    
    System.out.println("Mažou se nepotřebné soubory…");
    if (!FileUtils.delete(localUnzipped))
    {
      System.err.println("Chyba: Nepodařilo se smazat soubor "+localUnzipped+".");
      return;
    }
    if (!FileUtils.delete(localFile))
    {
      System.err.println("Chyba: Nepodařilo se smazat soubor "+localFile+".");
      return;
    }
    
    System.out.println("Hotovo.");
  }

}
