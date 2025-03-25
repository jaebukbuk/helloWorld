/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : javaGrammerTest.java
 * @Date        : 2024. 3. 11.
 * @Author      : Hhome
 */
package helloWorld;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
        //forGrammerTest();
    	
    	System.out.println(2500 % 10);
    	String a = "0101234567";
    	String b = "01012345678";
    	String c = "15882222";
    	System.out.println(c.length());
    	System.out.println(a.substring(0, 3) +"-"+ a.substring(3, 6) +"-"+ a.substring(6,10));
    	System.out.println(b.substring(0, 3) +"-"+ b.substring(3, 7) +"-"+ b.substring(7,11));
    	System.out.println( "asdf".startsWith("zxcvzxcvzxc") );
    	//System.out.println("asdf".substring(0, 13));
    	System.out.println((null + "asdf").replaceAll("[^0-9]", ""));
    	System.out.println("01 24 1241235 &&& ㅁㄴ아로ㅓㅁ디ㅏㄱㅎ".replaceAll("[^0-9]", ""));
    	System.out.println(2501 % 10);
    	System.out.println("여기야여기" + c.substring(0, 4) + "-" + c.substring(4, 8));
    	;
    	Calendar calendar = new GregorianCalendar();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
		calendar.add(Calendar.DATE, -1);
		System.out.println(SDF.format(calendar.getTime()));
		
		List<String> as = new ArrayList<String>();
		as=null;
		forGrammerTest(as) ;
		System.out.println("여기야여기1 : " );
		String slitmCds = " ";
		String[] arrySlitmCd = Arrays.stream(slitmCds.split("\n")).filter(item -> !item.isEmpty()).toArray(String[]::new);
		//arrySlitmCd = Arrays.stream(slitmCds.split("\n")).filter(item -> !item.equals((""))).toArray(String[]::new);
		System.out.println("여기야여기1 : " + arrySlitmCd.length  );
		
		
//    	System.out.println("02일".substring(1, 2));
//    	
//    	for(int i = 0 ; i <100 ;) {
//    		System.out.println(i);
//    		if( 100 < i++ ) {
//    			break;
//    		}
//    		
//    	}
    		
    	
    	int i =1;
    	int j =0;
    	
    	
    		j = i;
    	
    		System.out.println(i+" "+j);
    	i++;
    	System.out.println(Integer.toString(j));
    	
    	System.out.println(i+" "+j);
    	
    }
    
    public static void forGrammerTest(List<String> as) {
    	System.out.println("여기야여기1 : " + as.isEmpty()  );
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

