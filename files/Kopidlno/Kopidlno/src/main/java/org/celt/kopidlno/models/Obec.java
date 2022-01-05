
package org.celt.kopidlno.models;


public class Obec
{
  
  private final int kod;
  private final String nazev;


  public Obec(int kod, String nazev)
  {
    this.kod=kod;
    this.nazev=nazev;
  }
  
  
  public int getKod()
  {
    return kod;
  }

  public String getNazev()
  {
    return nazev;
  }

}
