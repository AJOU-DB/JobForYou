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
       // tag���� ������ �������� �޼ҵ�
      private static String getTagValue(String tag, Element eElement) {
          NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
          Node nValue = (Node) nlList.item(0);
          if(nValue == null) 
              return null;
          return nValue.getNodeValue();
      }
   public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
      
      // XML ���� �Ľ�
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = factory.newDocumentBuilder();
      Document document = documentBuilder.parse("JobPolicy.xml");
      
      // root ���ϱ�
      Element root = document.getDocumentElement();
      
      document.getDocumentElement().normalize();
      System.out.println("Root element: " + document.getDocumentElement().getNodeName()); 
      
      // root�� �Ӽ�
      System.out.println("class name: " + root.getAttribute("total"));
      
      // �Ľ��� tag
      NodeList nList = document.getElementsByTagName("jynEmpSptList");
      System.out.println("�Ľ��� ����Ʈ �� : "+ nList.getLength());  // �Ľ��� ����Ʈ ��
      
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