package com.xingniu.zoon.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XmlUtils {
    public static Map<String, String> reqMsg2MapWith2Level(String xml) throws DocumentException {
        Map<String, String> maps = new HashMap<String, String>();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        List<Element> eles = root.elements();
        for (Element item : eles) {
            List<Element> elements2 = item.elements();
            if (elements2 == null || elements2.size() == 0 ) {
                return null;
            }
            for (Element item2 : elements2) {
                maps.put(item2.getName(), item2.getTextTrim());
            }

        }
        return maps;
    }

    public static Map<String, String> reqMsg2MapWith3Level(String xml) throws DocumentException {
        Map<String, String> maps = new HashMap<String, String>();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        List<Element> eles = root.elements();
        for (Element item : eles) {
            List<Element> elements2 = item.elements();
            if (elements2 == null || elements2.size() == 0 ) {
                return null;
            }
            for (Element item2 : elements2) {
                List<Element> eles3 = item2.elements();
                if (eles3 == null || eles3.size() == 0 ) {
                    return null;
                }
                for (Element item3 : eles3) {
                    maps.put(item3.getName(), item3.getTextTrim());
                }
            }

        }
        return maps;
    }

    public static JSONObject changeXml2JsonObject(String xml) throws DocumentException {
        JSONObject result = new JSONObject();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        return changeXml2JsonObject(root,result);

    }

    public static JSONObject changeXml2JsonObject(Element element,JSONObject jsonObject) {
        List<Element> elementList = element.elements();
        if (element == null || elementList.size() == 0 ) {
            return null;
        }

        JSONObject itemObj = new JSONObject();

        for (Element item : elementList) {
            itemObj.put(item.getName(), item.getTextTrim());
            changeXml2JsonObject(item,itemObj);

        }
        jsonObject.put(element.getName(),itemObj);
        return jsonObject;
    }

    public static String findItemByKey(String xml,String key) throws DocumentException {

        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        return findItemByKey(root,key);

    }

    public static String  findItemByKey(Element element,String key) throws DocumentException {
        String ret = null;
        if (element == null ) {
            return null;
        }
        List<Element> elementList = element.elements();
        if (elementList == null || elementList.size() == 0 ) {
            return null;
        }
        for (Element item : elementList) {
            ret = item.getName();
            if (key.equals(ret)) {
                //找到了对应key
                if (StringUtils.isBlank(item.getTextTrim())) {
                    //key 对应的文本为空， 表示有子节点 ，拼成json格式
                    return elementToJsonString(item);
                } else {
                    return item.getTextTrim();
                }
            }
            String temp = findItemByKey(item,key);
            if (StringUtils.isBlank(temp)) {
                continue;
            } else {
                return temp;
            }
        }
        return null;
    }

    private static String elementToJsonString(Element item) {
        List<Element> list = item.elements();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Element element1 : list) {
            sb.append("\"").append(element1.getName()).append("\"").append(":").append("\"").append(element1.getTextTrim()).append("\"").append(",");
        }
        sb.delete(sb.length() - 1,sb.length());
        sb.append("}");
        return String.valueOf(sb.toString());
    }

    /**
     * xml转json
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Json(String xmlStr) throws DocumentException{
        Document doc= DocumentHelper.parseText(xmlStr);
        JSONObject json=new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }

    /**
     * xml转json
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element,JSONObject json){
        //如果是属性
        for(Object o:element.attributes()){
            Attribute attr=(Attribute)o;
            if(!isEmpty(attr.getValue())){
                json.put("@"+attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl=element.elements();
        if(chdEl.isEmpty() && !isEmpty(element.getText())){//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for(Element e:chdEl){//有子元素
            if(!e.elements().isEmpty()){//子元素也有子元素
                JSONObject chdjson=new JSONObject();
                dom4j2Json(e,chdjson);
                Object o=json.get(e.getName());
                if(o!=null){
                    JSONArray jsona=null;
                    if(o instanceof JSONObject){//如果此元素已存在,则转为jsonArray
                        JSONObject jsono=(JSONObject)o;
                        json.remove(e.getName());
                        jsona=new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if(o instanceof JSONArray){
                        jsona=(JSONArray)o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                }else{
                    if(!chdjson.isEmpty()){
                        json.put(e.getName(), chdjson);
                    }
                }


            }else{//子元素没有子元素
                for(Object o:element.attributes()){
                    Attribute attr=(Attribute)o;
                    if(!isEmpty(attr.getValue())){
                        json.put("@"+attr.getName(), attr.getValue());
                    }
                }
                if(!e.getText().isEmpty()){
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static boolean isEmpty(String str) {

        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "<Hotel_feed_full><hotels><hotel><hotel_id>4122973</hotel_id><hotel_name>Sleep Inn  Suites Monroe - Woodbury</hotel_name><hotel_formerly_name/><translated_name>门罗-伍德伯里司丽普套房酒店</translated_name><star_rating>2.5</star_rating><continent_id>6</continent_id><country_id>181</country_id><city_id>720531</city_id><area_id>571652</area_id><longitude>-74.19209</longitude><latitude>41.36043</latitude><hotel_url>https://www.agoda.com/partners/partnersearch.aspx?hid=4122973</hotel_url><rates_from>125.0000</rates_from><rates_currency>USD</rates_currency><popularity_score>3106</popularity_score><remark></remark><number_of_reviews>3</number_of_reviews><rating_average>8.5</rating_average><rates_from_exclusive>111.0000</rates_from_exclusive><child_and_extra_bed_policy><infant_age>2</infant_age><children_age_from>3</children_age_from><children_age_to>12</children_age_to><children_stay_free>True</children_stay_free><min_guest_age>0</min_guest_age></child_and_extra_bed_policy><accommodation_type>酒店</accommodation_type><nationality_restrictions></nationality_restrictions></hotel></hotels><addresses><address><hotel_id>4122973</hotel_id><address_type>English address</address_type><address_line_1>1 Sleep Way</address_line_1><address_line_2></address_line_2><postal_code>10950</postal_code><state>New York</state><City>South Blooming Grove (NY)</City><country>United States</country></address><address><hotel_id>4122973</hotel_id><address_type>Local language</address_type><address_line_1>1 Sleep Way</address_line_1><address_line_2></address_line_2><postal_code>10950</postal_code><state>New York</state><City>South Blooming Grove (NY)</City><country>United States</country></address></addresses><hotel_descriptions><hotel_description><hotel_id>4122973</hotel_id><overview>无论深度游还是一日游，Sleep Inn  Suites Monroe - Woodbury都是出行伍德伯里(NY)的热门之选。 住宿设施一应俱全，确保您享有良好的住宿体验。 Sleep Inn  Suites Monroe - Woodbury的工作人员秉承顾客至上的服务理念，为您提供宾至如归的入住感受。 客房设计极其舒适，装饰优雅，此外还配备了众多便捷设施，部分客房还配有独立客厅, WiFi上网, 吸烟政策 - 禁烟, 电话, 电视等设施。 住宿内设多种休闲娱乐设施。 Sleep Inn  Suites Monroe - Woodbury位置便利且舒适，是游览伍德伯里(NY)时的理想住宿选择。</overview><snippet>无论深度游还是一日游，Sleep Inn  Suites Monroe - Woodbury都是出行伍德伯里(NY)的热门之选。 住宿设施一应俱全，确保您享有良好的住宿体验。 Sleep Inn </snippet></hotel_description></hotel_descriptions><facilities><facility><hotel_id>4122973</hotel_id><property_group_description>Hotel Facilities</property_group_description><property_id>351</property_id><property_name>free breakfast</property_name><property_translated_name>早餐(免费)</property_translated_name></facility><facility><hotel_id>4122973</hotel_id><property_group_description>Room Facilities</property_group_description><property_id>47</property_id><property_name>coffee/tea maker</property_name><property_translated_name>咖啡/茶冲泡器具</property_translated_name></facility><facility><hotel_id>4122973</hotel_id><property_group_description>Room Facilities</property_group_description><property_id>15</property_id><property_name>smoking policy – non-smoking available</property_name><property_translated_name>吸烟政策 - 禁烟</property_translated_name></facility><facility><hotel_id>4122973</hotel_id><property_group_description>Room Facilities</property_group_description><property_id>35</property_id><property_name>television</property_name><property_translated_name>电视</property_translated_name></facility></facilities><pictures><picture><hotel_id>4122973</hotel_id><picture_id>61661804</picture_id><picture_caption_title></picture_caption_title><caption>Restaurant</caption><caption_translated>餐厅</caption_translated><URL>http://pix1.agoda.net/hotelimages/412/4122973/4122973_18020910500061661804.jpg?s=312x</URL></picture><picture><hotel_id>4122973</hotel_id><picture_id>61661773</picture_id><picture_caption_title></picture_caption_title><caption>Business Center</caption><caption_translated>商务中心</caption_translated><URL>http://pix1.agoda.net/hotelimages/412/4122973/4122973_18020910500061661773.jpg?s=312x</URL></picture><picture><hotel_id>4122973</hotel_id><picture_id>61661774</picture_id><picture_caption_title></picture_caption_title><caption>Swimming Pool</caption><caption_translated>游泳池</caption_translated><URL>http://pix5.agoda.net/hotelimages/412/4122973/4122973_18020910500061661774.jpg?s=312x</URL></picture><picture><hotel_id>4122973</hotel_id><picture_id>61661783</picture_id><picture_caption_title></picture_caption_title><caption>Exterior</caption><caption_translated>酒店外观</caption_translated><URL>http://pix5.agoda.net/hotelimages/412/4122973/4122973_18020910500061661783.jpg?s=312x</URL></picture><picture><hotel_id>4122973</hotel_id><picture_id>61661784</picture_id><picture_caption_title></picture_caption_title><caption>Exterior</caption><caption_translated>酒店外观</caption_translated><URL>http://pix4.agoda.net/hotelimages/412/4122973/4122973_18020910500061661784.jpg?s=312x</URL></picture></pictures><roomtypes><roomtype><hotel_id>4122973</hotel_id><hotel_room_type_id>25437011</hotel_room_type_id><hotel_room_type_name>2 Queen beds, No Smoking</hotel_room_type_name><standard_caption>2 Queen beds, No Smoking</standard_caption><standard_caption_translated>客房(2张大床)-禁烟</standard_caption_translated><max_occupancy_per_room>4</max_occupancy_per_room><no_of_room>3</no_of_room><size_of_room>0</size_of_room><room_size_incl_terrace>False</room_size_incl_terrace><views/><max_extrabeds>0</max_extrabeds><max_infant_in_room>0</max_infant_in_room><max_children_in_room>0</max_children_in_room><hotel_room_type_picture></hotel_room_type_picture><bed_type>1 大床</bed_type><hotel_master_room_type_id>0</hotel_master_room_type_id></roomtype><roomtype><hotel_id>4122973</hotel_id><hotel_room_type_id>25437012</hotel_room_type_id><hotel_room_type_name>1 King Bed, No Smoking</hotel_room_type_name><standard_caption>1 King Bed, No Smoking</standard_caption><standard_caption_translated>客房(特大床)-禁烟</standard_caption_translated><max_occupancy_per_room>2</max_occupancy_per_room><no_of_room>5</no_of_room><size_of_room>0</size_of_room><room_size_incl_terrace>False</room_size_incl_terrace><views/><max_extrabeds>0</max_extrabeds><max_infant_in_room>0</max_infant_in_room><max_children_in_room>0</max_children_in_room><hotel_room_type_picture></hotel_room_type_picture><bed_type>1 特大床</bed_type><hotel_master_room_type_id>0</hotel_master_room_type_id></roomtype><roomtype><hotel_id>4122973</hotel_id><hotel_room_type_id>25437013</hotel_room_type_id><hotel_room_type_name>1 King Bed, Suite, No Smoking</hotel_room_type_name><standard_caption>1 King Bed, Suite, No Smoking</standard_caption><standard_caption_translated>套房(特大床)-禁烟</standard_caption_translated><max_occupancy_per_room>2</max_occupancy_per_room><no_of_room>3</no_of_room><size_of_room>0</size_of_room><room_size_incl_terrace>False</room_size_incl_terrace><views/><max_extrabeds>0</max_extrabeds><max_infant_in_room>0</max_infant_in_room><max_children_in_room>0</max_children_in_room><hotel_room_type_picture></hotel_room_type_picture><bed_type>1 特大床</bed_type><hotel_master_room_type_id>0</hotel_master_room_type_id></roomtype></roomtypes></Hotel_feed_full>";

        try {
            JSONObject jsonObject = xml2Json(str);
            System.out.println("转换xml : " + JSONObject.toJSONString(jsonObject));

//            String value = findItemByKey(str,"child_and_extra_bed_policy");
//            System.out.println("xml 查找key : " + value);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
