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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.poi.xssf.usermodel.XSSFCell ;
import org.apache.poi.xssf.usermodel.XSSFRow ;
import org.apache.poi.xssf.usermodel.XSSFSheet ;
import org.apache.poi.xssf.usermodel.XSSFWorkbook ;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        
        int idx = 0;
        
        try {
            
            try {
                
                JSONObject resultJSON = new JSONObject( node.toString() );
                dfsJsonObject(resultJSON , idx);
                
            } catch (Exception e){

                JSONArray resultJSON = new JSONArray( node.toString() );
                dfsJsonArray(resultJSON , idx);
            }
            
        } catch (Exception e) {
            System.out.println("오류야");
        }
    }
    
    public static void dfsJsonObject(JSONObject node, int idx) {
       
        String[] namesArr = JSONObject.getNames(node);
        
        for(int i = 0 ; i < namesArr.length ; i++ ) {
            
            if( node.get(namesArr[i].toString()) instanceof JSONArray) { 
                
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("\t");
                }
                System.out.println( namesArr[i].toString() + " [" ); 
                dfsJsonArray(  (JSONArray)  node.get(namesArr[i].toString()) , idx+1 ); 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("\t");
                }
                
                System.out.println("]");
            } else if( node.get(namesArr[i].toString()) instanceof JSONObject) { 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("\t");
                }
                System.out.println(namesArr[i].toString() + "{"); 
                dfsJsonObject( (JSONObject) node.get(namesArr[i].toString()) , idx+1 ); 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("\t");
                }
                
                System.out.println("}");
                
            } else {
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("\t");
                }
                System.out.printf("%s \n", namesArr[i].toString());
            }
        }
    }
    
    public static void dfsJsonArray(JSONArray node, int idx) {
        
        for(int k = 0 ; k < node.length() ; k++ ) {
            
            if( node.get(k)      instanceof JSONArray)  { 
                
                
                dfsJsonArray(  (JSONArray)  node.get(k), idx+1 ); 
                
            } else if( node.get(k) instanceof JSONObject) { 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("\t");
                }
                
                System.out.println("{"); 
                dfsJsonObject( (JSONObject) node.get(k), idx+1 ); 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("\t");
                }
                
                System.out.println("}");
            
            
            } else {
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("\t");
                }
                System.out.printf("[] \n");
            }
        }
    }
    
    /**********************************************************************************************************************************/
    
    public static void searchForEntityExel(String node) {
        
        try {
            
            Integer row  = 1;
            Integer sell = 1;
            
            XSSFWorkbook  xssfWb    = null;
            XSSFSheet     xssfSheet = null;
            XSSFRow       xssfRow   = null;
            XSSFCell      xssfCell  = null;
            
            xssfWb    = new XSSFWorkbook(); //XSSFWorkbook 객체 생성
            xssfSheet = xssfWb.createSheet ("워크 시트1"); // 워크시트 이름 설정
            
            try {
                
                JSONObject resultJSON = new JSONObject( node.toString() );
                dfsJsonObjectExel( resultJSON, xssfSheet , row, sell );
                
            } catch (Exception e){

                JSONArray resultJSON = new JSONArray( node.toString() );
                dfsJsonArrayExel( resultJSON , xssfSheet, row, sell );
            }

            String localFile = "C:/Users/Hhome/Desktop/" + "excelDownTest" + ".xlsx";
            File file = new File(localFile);
            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            xssfWb.write (fos);
            if (fos != null) fos.close ();
            
            System.out.println("완료");
        } catch (Exception e) {
            System.out.println("오류야");
        }
    }
    
    public static void dfsJsonObjectExel( JSONObject node, XSSFSheet xssfSheet, Integer row, Integer sell ) {
       
        String[] namesArr = JSONObject.getNames(node);
        System.out.println("2 row : " + row + "cul : " + sell);
        
        for(int i = 0 ; i < namesArr.length ; i++ ) {
            
            try {
                
                int temInt = sell.intValue();
                
                if( node.get(namesArr[i].toString()) instanceof JSONArray) { 
                    
                    XSSFRow  xssfRow1  = xssfSheet.createRow  ( row.shortValue() );
                    XSSFCell xssfCell1 = xssfRow1.createCell  ( (short) temInt   );
                    xssfCell1.setCellValue (namesArr[i].toString() + " [");
                    row++;
                    
                    dfsJsonArrayExel(  (JSONArray)node.get(namesArr[i].toString()), xssfSheet, row, sell+1); 
                    
                    XSSFRow  xssfRow2  = xssfSheet.createRow  ( row.shortValue()  );
                    XSSFCell xssfCell2 = xssfRow2.createCell  ( (short) temInt    );
                    xssfCell2.setCellValue ("]");
                    row++;
                    
                } else if( node.get(namesArr[i].toString()) instanceof JSONObject) { 
                    
                    XSSFRow  xssfRow1  = xssfSheet.createRow  ( row.shortValue()     );
                    XSSFCell xssfCell1 = xssfRow1.createCell  ( (short) temInt  );
                    xssfCell1.setCellValue (namesArr[i].toString() + "{");
                    row++;
                    
                    dfsJsonObjectExel( (JSONObject) node.get(namesArr[i].toString()), xssfSheet, row, sell+1); 
                    
                    XSSFRow  xssfRow2  = xssfSheet.createRow  ( row     );
                    XSSFCell xssfCell2 = xssfRow2.createCell  ( (short) temInt  );
                    xssfCell2.setCellValue ("}");
                    row++;
                    
                } else {
                    
                    XSSFRow  xssfRow1  = xssfSheet.createRow  ( row.shortValue() + 1  );
                    
                    XSSFCell xssfCell1 = xssfRow1.createCell  ( (short) temInt     );
                    xssfCell1.setCellValue (namesArr[i].toString());
                    
                    XSSFCell xssfCell2 = xssfRow1.createCell  ( (short) temInt + 1 );
                    xssfCell2.setCellValue ( node.get(namesArr[i].toString()).toString() );
                    row++;
                }
            
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    
    public static void dfsJsonArrayExel( JSONArray node, XSSFSheet xssfSheet, Integer row, Integer sell ) {
        
        System.out.println("1 row : " + row + "cul : " + sell);
        
        for(int k = 0 ; k < node.length() ; k++ ) {
            
            try {
                
                int temInt = sell.intValue();
            
                if( node.get(k) instanceof JSONArray)  { 
                    
                    dfsJsonArrayExel(  (JSONArray)  node.get(k), xssfSheet, row, sell); 
                    
                } else if( node.get(k) instanceof JSONObject) { 
                    
                    XSSFRow  xssfRow1  = xssfSheet.createRow  ( row.shortValue()     );
                    XSSFCell xssfCell1 = xssfRow1.createCell  ( (short) temInt  );
                    xssfCell1.setCellValue ("{");
                    row++;
                    
                    dfsJsonObjectExel( (JSONObject) node.get(k), xssfSheet, row, sell); 
                    
                    XSSFRow  xssfRow2  = xssfSheet.createRow  ( row.shortValue()     );
                    XSSFCell xssfCell2 = xssfRow2.createCell  ( (short) temInt  );
                    xssfCell2.setCellValue ("}");
                    row++;
                
                } else {
                    
                    XSSFRow  xssfRow1  = xssfSheet.createRow  ( row.shortValue()      );
                    XSSFCell xssfCell1 = xssfRow1.createCell  ( (short) temInt   );
                    xssfCell1.setCellValue ("[]");
                }
            
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
    /**********************************************************************************************************************************/
    
    public static void searchForEntity1(String node) {
        
        int idx = 0;
        int row = 1;
        
        try {
            
            XSSFWorkbook  xssfWb    = null;
            XSSFSheet     xssfSheet = null;
            XSSFRow       xssfRow   = null;
            XSSFCell      xssfCell  = null;
            
            xssfWb    = new XSSFWorkbook(); //XSSFWorkbook 객체 생성
            xssfSheet = xssfWb.createSheet ("워크 시트1"); // 워크시트 이름 설정
            
            try {
                
                JSONObject resultJSON = new JSONObject( node.toString() );
                dfsJsonObject1(resultJSON, xssfSheet , idx, row);
                
            } catch (Exception e){

                JSONArray resultJSON = new JSONArray( node.toString() );
                dfsJsonArray1(resultJSON, xssfSheet , idx, row);
            }
            
            String localFile = "C:/Users/Hhome/Desktop/" + "excelDownTest" + ".xlsx";
            File file = new File(localFile);
            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            xssfWb.write (fos);
            if (fos != null) fos.close ();
            
            System.out.println("완료");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("오류야");
        }
    }
    
    public static void dfsJsonObject1(JSONObject node, XSSFSheet xssfSheet, int idx, int row) {
       
        String[] namesArr = JSONObject.getNames(node);
        
        for(int i = 0 ; i < namesArr.length ; i++ ) {
            
            if( node.get(namesArr[i].toString()) instanceof JSONArray) { 
                
                
                XSSFRow  xssfRow1  = xssfSheet.createRow  ( row     );
                XSSFCell xssfCell1 = xssfRow1.createCell  ( idx  );
                xssfCell1.setCellValue ( namesArr[i].toString() + " [" );
                row++;
                
                dfsJsonArray1(  (JSONArray)  node.get(namesArr[i].toString()), xssfSheet , idx+1, row+1 ); 
                
                XSSFRow  xssfRow2  = xssfSheet.createRow  ( row     );
                XSSFCell xssfCell2 = xssfRow2.createCell  ( idx  );
                xssfCell2.setCellValue ( "]" );
                row++;
                
            } else if( node.get(namesArr[i].toString()) instanceof JSONObject) { 
                
                XSSFRow  xssfRow1  = xssfSheet.createRow  ( row     );
                XSSFCell xssfCell1 = xssfRow1.createCell  ( idx  );
                xssfCell1.setCellValue ( namesArr[i].toString() + "{" );
                row++;
                
                dfsJsonObject1( (JSONObject) node.get(namesArr[i].toString()), xssfSheet , idx+1, row+1 ); 
                
                XSSFRow  xssfRow2  = xssfSheet.createRow  ( row     );
                XSSFCell xssfCell2 = xssfRow2.createCell  ( idx     );
                xssfCell2.setCellValue ( "}" );
                row++;
                
            } else {
                
                XSSFRow  xssfRow1  = xssfSheet.createRow  ( row +1 );
                XSSFCell xssfCell1 = xssfRow1.createCell  ( idx  );
                xssfCell1.setCellValue ( namesArr[i].toString() );
                int temInt = idx + 1;
                XSSFRow  xssfRow2  = xssfSheet.createRow  ( row    +1 );
                XSSFCell xssfCell2 = xssfRow2.createCell  ( temInt  );
                xssfCell2.setCellValue ( node.get(namesArr[i].toString()).toString() );
                row++;
               // System.out.printf("key : %-50s , %s \n", namesArr[i].toString(), node.get(namesArr[i].toString()).toString());
            }
        }
    }
    
    public static void dfsJsonArray1(JSONArray node, XSSFSheet xssfSheet, int idx, int row) {
        
        for(int k = 0 ; k < node.length() ; k++ ) {
            
            if( node.get(k)      instanceof JSONArray)  { 
                
                
                dfsJsonArray1(  (JSONArray)  node.get(k), xssfSheet, idx+1, row);
                
            } else if( node.get(k) instanceof JSONObject) { 
                
                XSSFRow  xssfRow1  = xssfSheet.createRow  ( row     );
                XSSFCell xssfCell1 = xssfRow1.createCell  ( idx  );
                xssfCell1.setCellValue ( "{" );
                row++;
                
                dfsJsonObject1( (JSONObject) node.get(k), xssfSheet, idx+1, row);
                
                XSSFRow  xssfRow2  = xssfSheet.createRow  ( row     );
                XSSFCell xssfCell2 = xssfRow2.createCell  ( idx     );
                xssfCell2.setCellValue ( "}" );
                row++;
            
            
            } else {
                
                XSSFRow  xssfRow1  = xssfSheet.createRow  ( row +1 );
                XSSFCell xssfCell1 = xssfRow1.createCell  ( idx  );
                xssfCell1.setCellValue ( "[]" );
                row++;
                break;
            }
        }
    }
    
    /**********************************************************************************************************************************/
    
    public static void searchForEntity2(String node) {
        
        int idx = 0;
        
        try {
            
            try {
                
                JSONObject resultJSON = new JSONObject( node.toString() );
                dfsJsonObject2(resultJSON , idx);
                
            } catch (Exception e){

                JSONArray resultJSON = new JSONArray( node.toString() );
                dfsJsonArray2(resultJSON , idx);
            }
            
        } catch (Exception e) {
            System.out.println("오류야");
        }
    }
    
    public static void dfsJsonObject2(JSONObject node, int idx) {
       
        String[] namesArr = JSONObject.getNames(node);
        
        for(int i = 0 ; i < namesArr.length ; i++ ) {
            
            if( node.get(namesArr[i].toString()) instanceof JSONArray) { 
                
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                System.out.println( namesArr[i].toString() + " [" ); 
                dfsJsonArray2(  (JSONArray)  node.get(namesArr[i].toString()) , idx+1 ); 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                
                System.out.println("]");
            } else if( node.get(namesArr[i].toString()) instanceof JSONObject) { 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                System.out.println(namesArr[i].toString() + "{"); 
                dfsJsonObject2( (JSONObject) node.get(namesArr[i].toString()) , idx+1 ); 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                
                System.out.println("}");
                
            } else {
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                System.out.printf("%s \n", namesArr[i].toString());
               // System.out.printf("key : %-50s , %s \n", namesArr[i].toString(), node.get(namesArr[i].toString()).toString());
            }
        }
    }
    
    public static void dfsJsonArray2(JSONArray node, int idx) {
        
        for(int k = 0 ; k < node.length() ; k++ ) {
            
            if( node.get(k)      instanceof JSONArray)  { 
                
                
                dfsJsonArray2(  (JSONArray)  node.get(k), idx+1 ); 
                
            } else if( node.get(k) instanceof JSONObject) { 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                
                System.out.println("{"); 
                dfsJsonObject2( (JSONObject) node.get(k), idx+1 ); 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                
                System.out.println("}");
            
            
            } else {
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                System.out.printf("(noname) \n");
            }
        }
    }
    
    /**********************************************************************************************************************************/
    
    public static void searchForEntity3(String node) {
        
        int     depth = 1;
        Integer row = 1;
        
        try ( XSSFWorkbook xssfWb = new XSSFWorkbook() ) {
            
            XSSFSheet xssfSheet = xssfWb.createSheet ("워크 시트1"); // 워크시트 이름 설정
            
            try {
                
                
                JSONObject resultJSON = new JSONObject( node.toString() );
                System.out.printf( "ROW : " + ( row.intValue() ) + " , DEPTH : " + depth + " ||| " + " {" + "\n");
                dfsJsonObject3(resultJSON, xssfSheet , ++depth, ++row);
                System.out.printf( "ROW : " + ( row.intValue() ) + " , DEPTH : " + depth + " ||| " + "}" + "\n");
                
            } catch (Exception e){

                JSONArray resultJSON = new JSONArray( node.toString() );
                System.out.printf( "ROW : " + ( row.intValue() ) + " , DEPTH : " + depth + " ||| " + " [" + "\n");
                dfsJsonArray3(resultJSON, xssfSheet , ++depth, ++row);
                System.out.printf( "ROW : " + ( row.intValue() ) + " , DEPTH : " + depth + " ||| " + "]" + "\n");
            }
            
            /*
             * File file = new File("C:/Users/Hhome/Desktop/" + "excelDownTest" + ".xlsx"); FileOutputStream fos
             * = new FileOutputStream(file); xssfWb.write (fos);
             * 
             * if (fos != null) fos.close ();
             */
            
            System.out.println("완료");
            
        }catch (Exception e) {
            
            e.printStackTrace();
            System.out.println("오류야");
        }
    }
    
    public static void dfsJsonObject3(JSONObject node, XSSFSheet xssfSheet, int depth, Integer row) {
       
        String[] namesArr = JSONObject.getNames(node);
        
        for(int i = 0 ; i < namesArr.length ; i++ ) {
            
            if( node.get( namesArr[i].toString() ) instanceof JSONArray) { 
                
                System.out.printf( "ROW1 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + namesArr[i].toString() + " [" + "\n"); 
                dfsJsonArray3(  (JSONArray) node.get(namesArr[i].toString()), xssfSheet , ++depth, ++row ); 
                System.out.printf( "ROW1 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + "]" + "\n");
                row++;
                
            } else if( node.get(namesArr[i].toString()) instanceof JSONObject) { 
                
                System.out.printf( "ROW2 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + namesArr[i].toString() + " {" + "\n"); 
                dfsJsonObject3( (JSONObject) node.get(namesArr[i].toString()), xssfSheet , ++depth, ++row ); 
                System.out.printf( "ROW2 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + "}" + "\n");
                row++;
                
            } else {
                
                System.out.printf( "ROW3 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + namesArr[i].toString() + " : " + node.get(namesArr[i].toString()).toString() + "\n"); 
                row++;
                
//                XSSFRow  xssfRow1  = xssfSheet.createRow  ( row +1 );
//                XSSFCell xssfCell1 = xssfRow1.createCell  ( idx  );
//                xssfCell1.setCellValue ( namesArr[i].toString() );
//                int temInt = idx + 1;
//                XSSFRow  xssfRow2  = xssfSheet.createRow  ( row    +1 );
//                XSSFCell xssfCell2 = xssfRow2.createCell  ( temInt  );
//                xssfCell2.setCellValue ( node.get(namesArr[i].toString()).toString() );
                
               // System.out.printf("key : %-50s , %s \n", namesArr[i].toString(), node.get(namesArr[i].toString()).toString());
            }
        }
    }
    
    public static void dfsJsonArray3(JSONArray node, XSSFSheet xssfSheet, int depth, Integer row) {
        
        for(int k = 0 ; k < node.length() ; k++ ) {
            
            if( node.get(k) instanceof JSONArray)  { 
                
                dfsJsonArray3(  (JSONArray) node.get(k), xssfSheet, ++depth, row);
                                
            } else if( node.get(k) instanceof JSONObject) { 
                
                System.out.printf( "ROW4 : " + ( row.intValue() + k ) + " , DEPTH : " + depth + " ||| " + " {" + "\n");
                dfsJsonObject3( (JSONObject) node.get(k), xssfSheet, ++depth, ++row);
                System.out.printf( "ROW4 : " + ( row.intValue() + k ) + " , DEPTH : " + depth + " ||| " + "}" + "\n");
                row++;
            
            } else {
                
                System.out.printf( "ROW5 : " + ( row.intValue() + k ) + " , DEPTH : " + depth + " ||| " + "[]" + "\n");
                row++;
            }
        }
    }
    
    /**********************************************************************************************************************************/

    /**********************************************************************************************************************************/
    
    public static void searchForEntity4(String node) {
        
        int     depth = 1;
        Integer row = 1;
        
        try ( XSSFWorkbook xssfWb = new XSSFWorkbook() ) {
            
            XSSFSheet xssfSheet = xssfWb.createSheet ("워크 시트1"); // 워크시트 이름 설정
            
            try {
                
                
                JSONObject resultJSON = new JSONObject( node.toString() );
                System.out.printf( "ROW : " + ( row.intValue() ) + " , DEPTH : " + depth + " ||| " + " {" + "\n");
                dfsJsonObject4(resultJSON, xssfSheet , ++depth, row);
                row++;
                System.out.printf( "ROW : " + ( row.intValue() ) + " , DEPTH : " + depth + " ||| " + "}" + "\n");
                
            } catch (Exception e){

                JSONArray resultJSON = new JSONArray( node.toString() );
                System.out.printf( "ROW : " + ( row.intValue() ) + " , DEPTH : " + depth + " ||| " + " [" + "\n");
                dfsJsonArray4(resultJSON, xssfSheet , ++depth, row);
                row++;
                System.out.printf( "ROW : " + ( row.intValue() ) + " , DEPTH : " + depth + " ||| " + "]" + "\n");
            }
            
            /*
             * File file = new File("C:/Users/Hhome/Desktop/" + "excelDownTest" + ".xlsx"); FileOutputStream fos
             * = new FileOutputStream(file); xssfWb.write (fos);
             * 
             * if (fos != null) fos.close ();
             */
            
            System.out.println("완료");
            
        }catch (Exception e) {
            
            e.printStackTrace();
            System.out.println("오류야");
        }
    }
    
    public static void dfsJsonObject4(JSONObject node, XSSFSheet xssfSheet, int depth, Integer row) {
       
        String[] namesArr = JSONObject.getNames(node);
        
        for(int i = 0 ; i < namesArr.length ; i++ ) {
            
            if( node.get( namesArr[i].toString() ) instanceof JSONArray) { 
                
                row++;
                System.out.printf( "ROW1 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + namesArr[i].toString() + " [" + "\n"); 
                dfsJsonArray4(  (JSONArray) node.get(namesArr[i].toString()), xssfSheet , ++depth, row ); 
                row++;
                System.out.printf( "ROW1 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + "]" + "\n");
                
                
            } else if( node.get(namesArr[i].toString()) instanceof JSONObject) { 
                
                row++;
                System.out.printf( "ROW2 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + namesArr[i].toString() + " {" + "\n"); 
                dfsJsonObject4( (JSONObject) node.get(namesArr[i].toString()), xssfSheet , ++depth, row ); 
                row++;
                System.out.printf( "ROW2 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + "}" + "\n");
                
                
            } else {
                
                row++;
                System.out.printf( "ROW3 : " + ( row.intValue() + i ) + " , DEPTH : " + depth + " ||| " + namesArr[i].toString() + " : " + node.get(namesArr[i].toString()).toString() + "\n"); 
                
                
//                XSSFRow  xssfRow1  = xssfSheet.createRow  ( row +1 );
//                XSSFCell xssfCell1 = xssfRow1.createCell  ( idx  );
//                xssfCell1.setCellValue ( namesArr[i].toString() );
//                int temInt = idx + 1;
//                XSSFRow  xssfRow2  = xssfSheet.createRow  ( row    +1 );
//                XSSFCell xssfCell2 = xssfRow2.createCell  ( temInt  );
//                xssfCell2.setCellValue ( node.get(namesArr[i].toString()).toString() );
                
               // System.out.printf("key : %-50s , %s \n", namesArr[i].toString(), node.get(namesArr[i].toString()).toString());
            }
        }
    }
    
    public static void dfsJsonArray4(JSONArray node, XSSFSheet xssfSheet, int depth, Integer row) {
        
        for(int k = 0 ; k < node.length() ; k++ ) {
            
            if( node.get(k) instanceof JSONArray)  { 
                
                dfsJsonArray3(  (JSONArray) node.get(k), xssfSheet, ++depth, row);
                                
            } else if( node.get(k) instanceof JSONObject) { 
                
                row++;
                System.out.printf( "ROW4 : " + ( row.intValue() + k ) + " , DEPTH : " + depth + " ||| " + " {" + "\n");
                dfsJsonObject4( (JSONObject) node.get(k), xssfSheet, ++depth, row);
                row++;
                System.out.printf( "ROW4 : " + ( row.intValue() + k ) + " , DEPTH : " + depth + " ||| " + "}" + "\n");
            
            } else {
                
                row++;
                System.out.printf( "ROW5 : " + ( row.intValue() + k ) + " , DEPTH : " + depth + " ||| " + "[]" + "\n");
            }
        }
    }
    
    
    /**********************************************************************************************************************************/
    // searchForEntity5
    /**********************************************************************************************************************************/
    
    public static void searchForEntity5(String node) {
        
        int idx = 0;
        
        try {
            
            try {
                
                JSONObject resultJSON = new JSONObject( node.toString() );
                dfsJsonObject5(resultJSON , idx);
                
            } catch (Exception e){

                JSONArray resultJSON = new JSONArray( node.toString() );
                dfsJsonArray5(resultJSON , idx);
            }
            
        } catch (Exception e) {
            System.out.println("오류야");
        }
    }
    
    public static void dfsJsonObject5(JSONObject node, int idx) {
       
        String[] namesArr = JSONObject.getNames(node);
        
        for(int i = 0 ; i < namesArr.length ; i++ ) {
            
            if( node.get(namesArr[i].toString()) instanceof JSONArray) { 
                
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                System.out.println( namesArr[i].toString() + " [" ); 
                dfsJsonArray5(  (JSONArray)  node.get(namesArr[i].toString()) , idx+1 ); 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                
                System.out.println("]");
            } else if( node.get(namesArr[i].toString()) instanceof JSONObject) { 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                System.out.println(namesArr[i].toString() + "{"); 
                dfsJsonObject5( (JSONObject) node.get(namesArr[i].toString()) , idx+1 ); 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                
                System.out.println("}");
                
            } else {
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                System.out.printf("%s \n", namesArr[i].toString());
            }
        }
    }
    
    public static void dfsJsonArray5(JSONArray node, int idx) {
        
        for(int k = 0 ; k < node.length() ; k++ ) {
            
            if( node.get(k)      instanceof JSONArray)  { 
                
                
                dfsJsonArray5(  (JSONArray)  node.get(k), idx+1 ); 
                
            } else if( node.get(k) instanceof JSONObject) { 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                
                System.out.println("{"); 
                dfsJsonObject5( (JSONObject) node.get(k), idx+1 ); 
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                
                System.out.println("}");
            
            
            } else {
                
                for(int l = 0 ; l < idx+1 ; l++) {
                    System.out.printf("-");
                }
                System.out.printf("[] \n");
            }
        }
    }
    
    /**********************************************************************************************************************************/
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
                    rawJson.append(tmpStr.trim());
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
            System.out.println("깃 테스트스 입니다aa.");
            searchForEntity5( rawJson.toString() );
        }
    }
}