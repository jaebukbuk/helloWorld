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
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

        	
        	System.out.println("지마켓 : 개발 : 일반웹 토큰 -> Bearer "+ generate(AucGmrkAccount.GMRKNOMAL));
        	System.out.println("옥션   : 개발 : 일반웹 토큰 -> Bearer "+ generate(AucGmrkAccount.AUCNOMAL));
            System.out.println("지마켓 : 운영 : 일반웹 토큰 -> Bearer "+ generate(AucGmrkAccount.REAL_GMRKNOMAL));
            System.out.println("옥션   : 운영 : 일반웹 토큰 -> Bearer "+ generate(AucGmrkAccount.REAL_AUCNOMAL));
            
            
            System.out.println("지마켓 : 개발 : 백화점 토큰 -> Bearer "+ generate(AucGmrkAccount.GMRKDEPART));
            System.out.println("옥션   : 개발 : 백화점 토큰 -> Bearer "+ generate(AucGmrkAccount.AUCDEPART));
            System.out.println("지마켓 : 운영 : 백화점 토큰 -> Bearer "+ generate(AucGmrkAccount.REAL_GMRKDEPART));
            System.out.println("옥션   : 운영 : 백화점 토큰 -> Bearer "+ generate(AucGmrkAccount.REAL_AUCDEPART));
            
            System.out.println("지마켓 : 개발 : 홈쇼핑 토큰 -> Bearer "+ generate(AucGmrkAccount.GMRKHOMESHOP));
            System.out.println("옥션   : 개발 : 홈쇼핑 토큰 -> Bearer "+ generate(AucGmrkAccount.AUCHOMESHOP));
            System.out.println("지마켓 : 운영 : 홈쇼핑 토큰 -> Bearer "+ generate(AucGmrkAccount.REAL_GMRKHOMESHOP));
            System.out.println("옥션   : 운영 : 홈쇼핑 토큰 -> Bearer "+ generate(AucGmrkAccount.REAL_AUCHOMESHOP));
            
            System.out.println("지마켓 : 개발 : 티켓   토큰 -> Bearer "+ generate(AucGmrkAccount.GMRKTIKET));
            System.out.println("옥션   : 개발 : 티켓   토큰 -> Bearer "+ generate(AucGmrkAccount.AUCTIKET));
            System.out.println("지마켓 : 운영 : 티켓   토큰 -> Bearer "+ generate(AucGmrkAccount.REAL_GMRKTIKET));
            System.out.println("옥션   : 운영 : 티켓   토큰 -> Bearer "+ generate(AucGmrkAccount.REAL_AUCTIKET));
            
            
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
            calDate.add     ( Calendar.HOUR , 4 );              // 여거 4시간으로 우선 해놨어여 
            
            long expMillies =  ( (Date)calDate.getTime() ).getTime()  / 1000;
            
            //System.out.println( "만료시간 : " +  calDate.getTime() );
            
            //System.out.println( calDate.getTime()  );
            //dateFormatGmt.format(calDate.getTime());
            
            JSONObject  tokenHeader  = new JSONObject();
            tokenHeader.put("typ", HEADER_TYP);
            tokenHeader.put("alg", HEADER_ALG);
            
            JSONObject  tokenPayload  = new JSONObject();
            tokenPayload.put("iss", PAYLOAD_ISS);
            tokenPayload.put("sub", PAYLOAD_SUB);
            tokenPayload.put("aud", PAYLOAD_AUD);
            //tokenPayload.put("exp", expMillies);                    // 요거 주석 처리하면 만료시간 없어여!!!!!!!!!!
            //tokenPayload.put("iat", calDate.getTime() );        
            switch ( aucGmrkAccount ) {
            
         	case AUCTIKET -> {
        		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.AUCTIKET.getMasterId() );
            	tokenPayload.put("ssi", "A:"+AucGmrkAccount.AUCTIKET.getSellerId() );
            	SECRET_KEY = AucGmrkAccount.AUCTIKET.getSecretKey();
        	} 
         	
         	case GMRKTIKET -> {
        		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.GMRKTIKET.getMasterId() );
            	tokenPayload.put("ssi", "G:"+AucGmrkAccount.GMRKTIKET.getSellerId() );
            	SECRET_KEY = AucGmrkAccount.GMRKTIKET.getSecretKey();
        	} 
         	
         	case REAL_AUCTIKET -> {
        		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.REAL_AUCTIKET.getMasterId() );
            	tokenPayload.put("ssi", "A:"+AucGmrkAccount.REAL_AUCTIKET.getSellerId() );
            	SECRET_KEY = AucGmrkAccount.REAL_AUCTIKET.getSecretKey();
        	} 
         	
         	case REAL_GMRKTIKET -> {
        		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.REAL_GMRKTIKET.getMasterId() );
            	tokenPayload.put("ssi", "G:"+AucGmrkAccount.REAL_GMRKTIKET.getSellerId() );
            	SECRET_KEY = AucGmrkAccount.REAL_GMRKTIKET.getSecretKey();
        	} 
            	///개발
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
              	
              	case GMRKNOMAL -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.GMRKNOMAL.getMasterId() );
                	tokenPayload.put("ssi", "G:"+AucGmrkAccount.GMRKNOMAL.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.GMRKNOMAL.getSecretKey();
            	}
              	
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
              	
              	
              	/**운영******////
              	
              	case REAL_AUCNOMAL -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.REAL_AUCNOMAL.getMasterId() );
                	tokenPayload.put("ssi", "A:"+AucGmrkAccount.REAL_AUCNOMAL.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.REAL_AUCNOMAL.getSecretKey();
            	}
              	
              	case REAL_AUCDEPART -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.REAL_AUCDEPART.getMasterId() );
                	tokenPayload.put("ssi", "A:"+AucGmrkAccount.REAL_AUCDEPART.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.REAL_AUCDEPART.getSecretKey();
            	}
              	
              	case REAL_AUCHOMESHOP -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.REAL_AUCHOMESHOP.getMasterId() );
                	tokenPayload.put("ssi", "A:"+AucGmrkAccount.REAL_AUCHOMESHOP.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.REAL_AUCHOMESHOP.getSecretKey();
            	}
              	
              	case REAL_GMRKNOMAL -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.REAL_GMRKNOMAL.getMasterId() );
                	tokenPayload.put("ssi", "G:"+AucGmrkAccount.REAL_GMRKNOMAL.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.REAL_GMRKNOMAL.getSecretKey();
            	}
              	
              	case REAL_GMRKDEPART -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.REAL_GMRKDEPART.getMasterId() );
                	tokenPayload.put("ssi", "G:"+AucGmrkAccount.REAL_GMRKDEPART.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.REAL_GMRKDEPART.getSecretKey();
            	}
              	
              	case REAL_GMRKHOMESHOP -> {
            		tokenHeader.put("kid", HEADER_KID_PREFIX + AucGmrkAccount.REAL_GMRKHOMESHOP.getMasterId() );
                	tokenPayload.put("ssi", "G:"+AucGmrkAccount.REAL_GMRKHOMESHOP.getSellerId() );
                	SECRET_KEY = AucGmrkAccount.REAL_GMRKHOMESHOP.getSecretKey();
            	}
              	
              	default -> {
              		SECRET_KEY = "";
              	}
            }
            
            //System.out.println("tokenHeader.toString()   : " + tokenHeader.toString() );
            //System.out.println("tokenPayload.toString()  : " + tokenPayload.toString());
           
            String encodedHeader  =  new String( Base64.encodeBase64URLSafe( tokenHeader.toString().getBytes(Charset.forName("UTF-8"))  ) );
            String encodedPayload =  new String( Base64.encodeBase64URLSafe( tokenPayload.toString().getBytes(Charset.forName("UTF-8")) ) );

            String signature = "";
            
            //System.out.println(" encodedHeader  : " + encodedHeader);
            //System.out.println(" encodedPayload : " + encodedPayload);
            
            try {

            	Mac hasher = Mac.getInstance ("HmacSHA256");
                hasher.init( new SecretKeySpec( SECRET_KEY.getBytes(Charset.forName("UTF-8")), "HmacSHA256") );
                byte[] hash = hasher.doFinal( (encodedHeader+"."+encodedPayload).toString().getBytes(Charset.forName("UTF-8")) );
                signature = new String(Base64.encodeBase64URLSafe(hash));
                
                //System.out.println("signature : " + signature );
                
            } catch (GeneralSecurityException var14) {
            	
                throw new IllegalArgumentException("Unexpected error while creating hash: " + var14.getMessage(),var14);
            }
            	
            return encodedHeader+"."+encodedPayload+"."+signature;
    }
    
    public enum AucGmrkAccount {
    																												  // 개발  // 운영	
    	    GMRKNOMAL         ( "hmall01t"    ,  "지마켓일반웹"   ,    "hmall00"    ,  " " )   // G     // N
    	  , AUCNOMAL          ( "hmall01t"    ,  "옥션일반웹"     ,    "hmall01"    ,  " " )   // N     // N
	  	  , REAL_GMRKNOMAL    ( "hmall01"     ,  "지마켓일반웹"   ,    "hmall01"    ,  " " )   // N     // N
	  	  , REAL_AUCNOMAL     ( "hmall01"     ,  "옥션일반웹"     ,    "hmall01"    ,  " " )   // N     // N
                                                                                         
	  	  , GMRKTIKET         ( "hhome01trt"  ,  "지마켓무형"     ,    "hhome01tr"  ,  " " )   // N     // T
	      , AUCTIKET          ( "hhome01trt"  ,  "옥션무형"       ,    "hhome01tr"  ,  " " )   // N     // T
	      , REAL_GMRKTIKET    ( "hhome01tr"   ,  "지마켓무형"     ,    "hhome01tr"  ,  " " )   // N     // T
	      , REAL_AUCTIKET     ( "hhome01tr"   ,  "옥션무형"       ,    "hhome01tr"  ,  " " )   // N     // T
	                                                                                     
	  	  , GMRKDEPART        ( "hdepart01t"  ,  "지마켓백화점"   ,    "hdepart01"  ,  " " )   // D     // D
	  	  , AUCDEPART         ( "hdepart01t"  ,  "옥션백화점"     ,    "hdepart01"  ,  " " )   // D     // D
	  	  , REAL_GMRKDEPART   ( "hdepart01"   ,  "지마켓백화점"   ,    "hdepart01"  ,  " " )   // D     // D
	  	  , REAL_AUCDEPART    ( "hdepart01"   ,  "옥션백화점"     ,    "hdepart01"  ,  " " )   // D     // D 
	  	                                                                                 
	  	  , GMRKHOMESHOP      ( "hhome01t"    ,  "지마켓홈쇼핑"   ,    "hhome01"    ,  " " )   // H     // H
	  	  , AUCHOMESHOP       ( "hhome01t"    ,  "옥션홈쇼핑"     ,    "hhome01"    ,  " " )   // H     // H
	  	  , REAL_GMRKHOMESHOP ( "hhome01"     ,  "지마켓홈쇼핑"   ,    "hhome01"    ,  " " )   // H     // H
	      , REAL_AUCHOMESHOP  ( "hhome01"     ,  "옥션홈쇼핑"     ,    "hhome01"    ,  " " )   // H     // H 
	  	  
 
	      
	      ;
    	
	    AucGmrkAccount(String sellerId, String sellerNm, String masterId, String secretKey) {
			this.sellerId 				= sellerId;
			this.sellerNm 		        = sellerNm;
			this.masterId		        = masterId;
			this.secretKey              = secretKey;
		}
	    
	    private String sellerId; 
	    private String sellerNm;
	    private String masterId;
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