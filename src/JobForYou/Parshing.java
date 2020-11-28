package JobForYou;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parshing {
       // tag값의 정보를 가져오는 메소드
      private static String getTagValue(String tag, Element eElement) {
          NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
          Node nValue = (Node) nlList.item(0);
          if(nValue == null) 
              return null;
          return nValue.getNodeValue();
      }
   public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
      
      // XML 문서 파싱
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = factory.newDocumentBuilder();
      Document document = documentBuilder.parse("JobPolicy.xml");
      
      // root 구하기
      Element root = document.getDocumentElement();
      
      document.getDocumentElement().normalize();
      System.out.println("Root element: " + document.getDocumentElement().getNodeName()); 
      
      // root의 속성
      System.out.println("class name: " + root.getAttribute("total"));
      
      // 파싱할 tag
      NodeList nList = document.getElementsByTagName("jynEmpSptList");
      System.out.println("파싱할 리스트 수 : "+ nList.getLength());  // 파싱할 리스트 수
      
      for(int temp = 12; temp < 80; temp++){      
          Node nNode = nList.item(temp);
          if(nNode.getNodeType() == Node.ELEMENT_NODE){
                         
             Element eElement = (Element) nNode;
             //System.out.println(eElement.getTextContent());
             System.out.println(temp+"Insert into JobPolicy values(" + getTagValue("ageEtcCont", eElement));
          }   // for end
       }   // if end
      System.out.println("\n");
   }
}