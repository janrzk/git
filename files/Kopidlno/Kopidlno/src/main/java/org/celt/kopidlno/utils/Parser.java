
package org.celt.kopidlno.utils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.celt.kopidlno.models.CastObce;
import org.celt.kopidlno.models.Obec;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Parser
{
  
  private static Element findElement(NodeList nodeList, String name)
  {
    Element result=null;
    int i=0;
    while ((i++)<nodeList.getLength()
        && !nodeList.item(i).getNodeName().equals(name)) {}
    if (i<nodeList.getLength() && nodeList.item(i).getNodeType()==Node.ELEMENT_NODE)
    {
      result=(Element)nodeList.item(i);
    }
    return result;
  }


  private static List<Element> findElements(NodeList nodeList, String name)
  {
    List<Element> result=new ArrayList();
    for (int i=0; i<nodeList.getLength(); i++)
    {
      Node node=nodeList.item(i);
      if (node.getNodeType()==Node.ELEMENT_NODE
          && node.getNodeName().equals(name))
      {
        result.add((Element)node);
      }
    }
    return result;
  }

  
  public static void parse(String fileName, List<Obec> obce, List<CastObce> castiObci)
      throws ParserConfigurationException, SAXException, IOException
  {
    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    DocumentBuilder builder=factory.newDocumentBuilder();
    Document document=builder.parse(new File(fileName));
    
    Node root=document.getDocumentElement();
    root.normalize();
    
    Node elementData=findElement(root.getChildNodes(),"vf:Data");
    if (elementData!=null)
    {
      Node elementObce=findElement(elementData.getChildNodes(),"vf:Obce");
      if (elementObce!=null)
      {
        List<Element> elementsObec=findElements(elementObce.getChildNodes(),"vf:Obec");
        for (Element e:elementsObec)
        {
          Element elementKod=findElement(e.getChildNodes(),"obi:Kod");
          int kod=Integer.parseInt(elementKod.getTextContent());
          Element elementNazev=findElement(e.getChildNodes(),"obi:Nazev");
          String nazev=elementNazev.getTextContent();
          obce.add(new Obec(kod,nazev));
        }
      }
      
      Node elementCastiObci=findElement(elementData.getChildNodes(),"vf:CastiObci");
      if (elementCastiObci!=null)
      {
        List<Element> elementsCastObce=findElements(elementCastiObci.getChildNodes(),"vf:CastObce");
        for (Element e:elementsCastObce)
        {
          Element elementKod=findElement(e.getChildNodes(),"coi:Kod");
          int kod=Integer.parseInt(elementKod.getTextContent());
          Element elementNazev=findElement(e.getChildNodes(),"coi:Nazev");
          String nazev=elementNazev.getTextContent();
          Element elementObec=findElement(e.getChildNodes(),"coi:Obec");
          int kodObce=0;
          if (elementObec!=null)
          {
            Element elementKodObce=findElement(elementObec.getChildNodes(),"obi:Kod");
            kodObce=Integer.parseInt(elementKodObce.getTextContent());
          }
          castiObci.add(new CastObce(kod,nazev,kodObce));
        }
      }
    }
  }

}
