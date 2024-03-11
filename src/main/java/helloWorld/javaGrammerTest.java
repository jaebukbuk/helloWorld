/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : javaGrammerTest.java
 * @Date        : 2024. 3. 11.
 * @Author      : Hhome
 */
package helloWorld;

import java.util.ArrayList;

/**
 * @ClassName    : javaGrammerTest
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.03.11
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.03.11       Hhome            최초생성
 * </pre>
 */
public class javaGrammerTest {
    
    //1. 
    public static void main(String[] args) {
        forGrammerTest();
    }
    
    public static void forGrammerTest() {
        
        ArrayList<String> testArrList1 = new ArrayList<String>();
        ArrayList<String> testArrList2 = null;
        int cnt = 0;
        
        for(String tmpStr : testArrList1) {
            System.out.println(cnt++);
        }
        
        cnt = 0;
        
        for(String tmpStr : testArrList2) {
            System.out.println(cnt++);
        }
        
    }

}
