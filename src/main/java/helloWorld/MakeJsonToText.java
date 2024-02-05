/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : MakeJsonToText.java
 * @Date        : 2024. 2. 2.
 * @Author      : Hhome
 */
package helloWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import common.StringUtils;

/**
 * @ClassName    : MakeJsonToText
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.02.02
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.02.02       Hhome            최초생성
 * </pre>
 */
public class MakeJsonToText {

    public static void searchForEntity(String node) {
        
        try {
            
            try {
                
                JSONObject resultJSON = new JSONObject( node.toString() );
                dfsJsonObject(resultJSON);
                
            } catch (Exception e){

                JSONArray resultJSON = new JSONArray( node.toString() );
                dfsJsonArray(resultJSON);
            }
            
        } catch (Exception e) {
            System.out.println("오류야");
        }
    }
    
    public static void dfsJsonObject(JSONObject node) {
       
        String[] namesArr = JSONObject.getNames(node);
        
        for(int i = 0 ; i < namesArr.length ; i++ ) {
            
            if( node.get(namesArr[i].toString()) instanceof JSONArray) { 
                
                System.out.println(" ARR : " + namesArr[i].toString() + " ["); 
                dfsJsonArray(  (JSONArray)  node.get(namesArr[i].toString()) ); 
                System.out.println(" ]"); 
                
            } else if( node.get(namesArr[i].toString()) instanceof JSONObject) { 
                System.out.println(" ["); 
                dfsJsonObject( (JSONObject) node.get(namesArr[i].toString()) ); 
                System.out.println(" ]"); 
            } else {
                System.out.printf("key : %-50s , %s \n", namesArr[i].toString(), node.get(namesArr[i].toString()).toString());
            }
        }
    }
    
    public static void dfsJsonArray(JSONArray node) {
        
        for(int k = 0 ; k < node.length() ; k++ ) {
            
            if( node.get(k)      instanceof JSONArray)  { dfsJsonArray(  (JSONArray)  node.get(k) ); }
            else if( node.get(k) instanceof JSONObject) { dfsJsonObject( (JSONObject) node.get(k) ); }
            else System.out.printf("%-50s \n", node.get(k).toString());
        }
    }
    
    /**
     * @Description : 
     * @MethodName  : main
     * @Author      : Hhome
     * @Date        : 2024.02.02
     * @param args
     * @throws IOException 
     * @throws JsonProcessingException 
     */
    public static void main(String[] args) throws JsonProcessingException, IOException {

        StringBuilder rawJson = new StringBuilder(); 
        
        System.out.println("Json 을 입력하시오 : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            String tmpStr = null;
            
            while( ( tmpStr = br.readLine() ) != null ) {
                
                if("END".equals(tmpStr.trim())) {
                    break;
                    
                }else {
                    //StringEscapeUtils.escapeJava(
                    rawJson.append(tmpStr);
                }
            }
            
            System.out.println("읽은 문자열 입니다.");
            System.out.println(rawJson);
            
        } catch (IOException e) {
            System.out.println("Json 읽기에 실패 하였습니다.");
        }
        
        if( StringUtils.isNotBlank(rawJson) ) {
            
            JSONObject resultJSON = new JSONObject(rawJson.toString());
            
            System.out.println("변환 JSON 입니다.");
            System.out.println( resultJSON.toString() );
            
            searchForEntity( resultJSON.toString() );
        }
    }

}

