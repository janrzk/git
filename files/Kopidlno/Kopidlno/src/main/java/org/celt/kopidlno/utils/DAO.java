
package org.celt.kopidlno.utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.celt.kopidlno.models.CastObce;
import org.celt.kopidlno.models.Obec;


public class DAO
{
  
  private static final Connection connection=Connector.getConnetion();

  
  public static void storeObce(List<Obec> obce)
      throws SQLException
  {
    for (Obec obec:obce)
    {
      PreparedStatement statement=connection.prepareStatement("INSERT INTO obce"
          +" (kod, nazev) VALUES (?, ?)");
      statement.setInt(1,obec.getKod());
      statement.setString(2,obec.getNazev());
      statement.executeUpdate();
    }
  }

  
  public static void storeCastiObci(List<CastObce> castiObci)
      throws SQLException
  {
    for (CastObce castObce:castiObci)
    {
      PreparedStatement statement=connection.prepareStatement("INSERT INTO casti_obci"
          +" (kod, nazev, kod_obce) VALUES (?, ?, ?)");
      statement.setInt(1,castObce.getKod());
      statement.setString(2,castObce.getNazev());
      statement.setInt(3,castObce.getKodObce());
      statement.executeUpdate();
    }
  }

}
