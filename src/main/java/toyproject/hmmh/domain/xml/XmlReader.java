package toyproject.hmmh.domain.xml;


import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import toyproject.hmmh.domain.Apt;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlReader {
    public List<Apt> parser(Document doc) throws ParserConfigurationException, IOException, SAXException {
        List<Apt> aptList = new ArrayList<>();

        // 제일 첫번째 태그
        doc.getDocumentElement().normalize();

        // 파싱할 tag
        NodeList nList = doc.getElementsByTagName("item");


        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            org.w3c.dom.Element eElement = (Element) nNode;

            aptList.add(new Apt(Integer.parseInt(getTagValue("거래금액", eElement).trim().replace(",", "")), Integer.parseInt(getTagValue("건축년도", eElement)), Integer.parseInt(getTagValue("년", eElement)), Integer.parseInt(getTagValue("월", eElement)), Integer.parseInt(getTagValue("일", eElement)), Integer.parseInt(getTagValue("법정동시군구코드", eElement)), Integer.parseInt(getTagValue("법정동읍면동코드", eElement)), getTagValue("중개사소재지", eElement), getTagValue("법정동", eElement).trim(), getTagValue("아파트", eElement), Double.parseDouble(getTagValue("전용면적", eElement)), Integer.parseInt(getTagValue("층", eElement))));
        }

        return aptList;
    }


    public static String getTagValue(String tag, Element eElement) {

        //결과를 저장할 result 변수 선언
        String result = "";

        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        result = nlList.item(0).getTextContent();

        return result;
    }

    // tag값의 정보를 가져오는 함수
    public static String getTagValue(String tag, String childTag, Element eElement) {

        //결과를 저장할 result 변수 선언
        String result = "";

        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        for (int i = 0; i < eElement.getElementsByTagName(childTag).getLength(); i++) {

            //result += nlList.item(i).getFirstChild().getTextContent() + " ";
            result += nlList.item(i).getChildNodes().item(0).getTextContent() + " ";
        }

        return result;
    }
}
