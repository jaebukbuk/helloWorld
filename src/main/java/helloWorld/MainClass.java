/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : MainClass.java
 * @Date        : 2024. 1. 24.
 * @Author      : Hhome
 */
package helloWorld;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.codec.binary.Base64;
/**
 * @ClassName    : MainClass
 * @Description  : 
 * @Author       : Hhome
 * @Since        : 2024.01.24
 * <pre>
 *    date          author          description
 *   ========       ========       ===============
 *   2024.01.24       Hhome            최초생성
 * </pre>
 */
public class MainClass {

    public static void main(String[] args) {
        
        try {
            
        	String asd = generate(AucGmrkAccount.AUCHOMESHOP);
            System.out.println(asd);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    

    
    public static String generate(AucGmrkAccount aucGmrkAccount) throws JsonGenerationException, JsonMappingException, IOException {
    	
    		final String HEADER_ALG = "HS256";
    		final String HEADER_TYP = "JWT";
    		final String HEADER_KID_PREFIX = "";
    		final String PAYLOAD_ISS = "www.hmall.com"; 
    		final String PAYLOAD_SUB = "sell";
    		final String PAYLOAD_AUD = "sa.esmplus.com"; 
    		final String SECRET_KEY;
    		
            Calendar calDate = Calendar.getInstance();
            calDate.setTime ( new Date()          );
            calDate.add     ( Calendar.MINUTE , 2 );   
            
            long expMillies =  ( (Date)calDate.getTime() ).getTime()  / 1000;
            System.out.println( "만료시간 : " +  calDate.getTime() );
            
            //System.out.println( calDate.getTime()  );
            //dateFormatGmt.format(calDate.getTime());
            
            JSONObject  tokenHeader  = new JSONObject();
            tokenHeader.put("typ", HEADER_TYP);
            tokenHeader.put("alg", HEADER_ALG);
            
            JSONObject  tokenPayload  = new JSONObject();
            tokenPayload.put("iss", PAYLOAD_ISS);
            tokenPayload.put("sub", PAYLOAD_SUB);
            tokenPayload.put("aud", PAYLOAD_AUD);
            tokenPayload.put("exp", expMillies);         
            //tokenPayload.put("iat", prtMillies);        
            
            switch ( aucGmrkAccount ) {
            
            	case AUCNOMAL -> {
            		tokenHeader.put("kid", AucGmrkAccount.AUCNOMAL.getMasterId() );
                	tokenPayload.put("ssi", "A:"+AucGmrkAccount.AUCNOMAL.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.AUCNOMAL.getSecretKey();
            	}
            	
              	case AUCDEPART -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.AUCDEPART.getMasterId() );
                	tokenPayload.put("ssi", "A:"+AucGmrkAccount.AUCDEPART.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.AUCDEPART.getSecretKey();
            	}
              	
              	case AUCHOMESHOP -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.AUCHOMESHOP.getMasterId() );
                	tokenPayload.put("ssi", "A:"+AucGmrkAccount.AUCHOMESHOP.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.AUCHOMESHOP.getSecretKey();
            	}
              	
//              	case "지마켓일반웹" -> {
//            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.AUCNOMAL.getMasterId() );
//                	tokenPayload.put("ssi", "G:"+AucGmrkAccount.AUCNOMAL.getSellerId() );
//            	}
              	
              	case GMRKDEPART -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.GMRKDEPART.getMasterId() );
                	tokenPayload.put("ssi", "G:"+AucGmrkAccount.GMRKDEPART.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.GMRKDEPART.getSecretKey();
            	}
              	
              	case GMRKHOMESHOP -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.GMRKHOMESHOP.getMasterId() );
                	tokenPayload.put("ssi", "G:"+AucGmrkAccount.GMRKHOMESHOP.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.GMRKHOMESHOP.getSecretKey();
            	}
              	
              	case AUCREAL -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.AUCREAL.getMasterId() );
                	tokenPayload.put("ssi", "G:"+AucGmrkAccount.AUCREAL.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.AUCREAL.getSecretKey();
            	}
              	
              	default -> {
              		SECRET_KEY = "";
              	}
            }
            
            System.out.println("tokenHeader.toString()   : " + tokenHeader.toString() );
            System.out.println("tokenPayload.toString()  : " + tokenPayload.toString());
           
            String encodedHeader  =  new String( Base64.encodeBase64URLSafe( tokenHeader.toString().getBytes(Charset.forName("UTF-8"))  ) );
            String encodedPayload =  new String( Base64.encodeBase64URLSafe( tokenPayload.toString().getBytes(Charset.forName("UTF-8")) ) );

            String signature = "";
            
            System.out.println(" encodedHeader  : " + encodedHeader);
            System.out.println(" encodedPayload : " + encodedPayload);
            
            try {

            	Mac hasher = Mac.getInstance ("HmacSHA256");
                hasher.init( new SecretKeySpec( SECRET_KEY.getBytes(Charset.forName("UTF-8")), "HmacSHA256") );
                byte[] hash = hasher.doFinal( (encodedHeader+"."+encodedPayload).toString().getBytes(Charset.forName("UTF-8")) );
                signature = new String(Base64.encodeBase64URLSafe(hash));
                
                System.out.println("signature : " + signature );
                
            } catch (GeneralSecurityException var14) {
            	
                throw new IllegalArgumentException("Unexpected error while creating hash: " + var14.getMessage(),var14);
            }
            	
            return encodedHeader+"."+encodedPayload+"."+signature;
    }
    
    public enum AucGmrkAccount {
    	
	  	    AUCNOMAL     ( "hmall01t"    ,  "옥션일반웹"    ,    "hmall01"    ,  "" )
	      , AUCDEPART    ( "hdepart01t"  ,  "옥션백화점"    ,    "hdepart01"  ,  "" )
	      , AUCHOMESHOP  ( "hhome01t"    ,  "옥션홈쇼핑"    ,    "hhome01"    ,  "" )
	                                                                          
	      , GMRKNOMAL    ( "hmall01t"    ,  "지마켓일반웹"  ,    "hmall00"    ,  "")
	      , GMRKDEPART   ( "hdepart01t"  ,  "지마켓백화점"  ,    "hdepart01"  ,  "" )
	      , GMRKHOMESHOP ( "hhome01t"    ,  "지마켓홈쇼핑"  ,    "hhome01"    ,  "" )
	                                                                          
	      , AUCREAL      ( "hhome01"     ,  "옥션운영"      ,    "hhome01"    ,  "" )
	      ;
	      
	    AucGmrkAccount(String sellerId, String sellerNm, String masterId, String secretKey) {
			this.sellerId 				= sellerId;
			this.sellerNm 		        = sellerNm;
			this.masterId		        = masterId;
			this.secretKey              = secretKey;
		}
	    
		// 제휴몰 코드 
	    private String sellerId; 
	    // 제휴몰 명 
	    private String sellerNm;
	    // 제휴몰 통합분개전표코드매핑 
	    private String masterId;
	    // 제휴몰 통합분개전표코드매핑 
	    private String secretKey;
	    
		public String getSellerId() {
			return sellerId;
		}
		public String getSellerNm() {
			return sellerNm;
		}
		public String getMasterId() {
			return masterId;
		}
		public String getSecretKey() {
			return secretKey;
		}
    }
}