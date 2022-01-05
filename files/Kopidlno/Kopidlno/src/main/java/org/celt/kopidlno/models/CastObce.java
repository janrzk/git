
package org.celt.kopidlno.models;


public class CastObce
{
  
  private final int kod;
  private final String nazev;
  private final int kodObce;

  
  public CastObce(int kod, String nazev, int kodObce)
  {
    this.kod=kod;
    this.nazev=nazev;
    this.kodObce=kodObce;
  }
  
  
  public int getKod()
  {
    return kod;
  }

  public String getNazev()
  {
    return nazev;
  }

  public int getKodObce()
  {
    return kodObce;
  }

}
