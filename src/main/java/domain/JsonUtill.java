/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : JsonUtill.java
 * @Date        : 2024. 2. 2.
 * @Author      : Hhome
 */
package domain;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @ClassName    : JsonUtill
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.02.02
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.02.02       Hhome            최초생성
 * </pre>
 */
public class JsonUtill {
    
    String jsonStr;
    
    JSONObject jsonObject = new JSONObject();
    JSONArray  jsonArr    = new JSONArray();
    
    public void setJsonStr(String str) {
        this.jsonStr = str;
    }
    
}
