package helloWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class chageCas {
	
	public static void main(String[] args) {
		
		List<String> tgt = new ArrayList<> ( Stream.of(  new String("prcAthzGbcd")
                , new String("venMdAthzDtm")
                , new String("prcAthzDtm")
                , new String("prcAplyStrtDtm")
                , new String("prcAplyStrtTime")
                , new String("prcDcEndDtm")
                , new String("prcDcEndTime")
                , new String("venItemCd")
                , new String("dptsVenOpCd")
                , new String("cmisGbcd")
                , new String("sellPrc")
                , new String("saleCost")
                , new String("buyPrice ")
                , new String("prchPrc")
                , new String("mrgnRate")
                , new String("dptsOpCd ")
                , new String("insmPossMths")
                , new String("spymDcYn")
                , new String("spymDcPossAmt")
                , new String("spymDcAmt")
                , new String("gnrlCopnDcVal")
                , new String("gnrlCopnGbcd")
                , new String("nexpCopnDcVal")
                , new String("nexpCopnGbcd")
                , new String("excsCopnDcVal")
                , new String("excsCopnGbcd")
                , new String("prmoDcMargin")
                , new String("ctpfAmt")
                , new String("ctpfRate")).collect( Collectors.toList() ) );

		for(String str : tgt) {
			char[] chgStr = str.toCharArray();
			String rst ="";
			for(char c : chgStr) {
				if(Character.isUpperCase(c)) {
					rst += "_"+c;
				}else {
					rst += c;
				}
			}
			System.out.println(rst);
			rst = "";
		}
	}
}

 