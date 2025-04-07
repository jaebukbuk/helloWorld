package helloWorld;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class chageCaseCamelDb {
	
	public static void main(String[] args) {
		
		System.out.println("Snake case => Camel Case ? - input : 1 "+ "\n"
     		             + "Camel Case => Snake case ? - input : 2 ");
		try {
			
			String camelGb = (new BufferedReader(new InputStreamReader(System.in))).readLine();

			System.out.println("Please input the message separated by Enter : ");
			
			String msg = (new BufferedReader(new InputStreamReader(System.in))).readLine();
			
			StringTokenizer st = new StringTokenizer(msg, System.getProperty("line.separator"));
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			while (st.hasMoreTokens()) {

				if (Integer.parseInt(camelGb) == 1) {
					bw.write( getCamelCaseBySnake(st.nextToken().toString()) );
				} else if (Integer.parseInt(camelGb) == 2) {
					bw.write( getSnakeCaseByCamel(st.nextToken().toString()) );
				}
				
				bw.newLine();
			}
			
			bw.flush();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public static String getCamelCaseBySnake(String str) {
		
		StringBuffer rst = new StringBuffer("");
		
		for(char c : str.toCharArray()) {
			if(Character.isUpperCase(c)) {
				rst.append("_"+Character.toLowerCase(c));
			}else {
				rst.append(c);
			}
		}
		
		System.out.println(str);
		return rst.toString();
	}
	
	public static String getSnakeCaseByCamel(String str) {
		
		StringBuffer rst = new StringBuffer("");
		char[] chrArr = str.toCharArray();
		
		for( int idx = 0 ; idx < chrArr.length ; idx++) {
			
			char tgtChr = chrArr[idx]; 
			
			if(tgtChr == '_') {
				rst.append(Character.toUpperCase(chrArr[idx+1]));
				idx = idx+1;
			} if(Character.isUpperCase(tgtChr)) {
				rst.append(Character.toLowerCase(tgtChr));
			}else {
				rst.append(tgtChr);
			}
		}
		
		System.out.println(str);
		return rst.toString();
	}
}



 