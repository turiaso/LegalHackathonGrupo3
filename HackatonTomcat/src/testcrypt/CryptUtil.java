package testcrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptUtil {
	
	public static String encrypt(String text){
		String s = new String();
	    for (int i = 0; i < text.length(); ++i){
		   s = s.concat(BitArray.shred((int)text.charAt(i)).pushOff(8).toString());
	    } 
	    String offusque = new String();
		for(int i = 0; i < s.length(); ++i){
			offusque = offusque.concat(""+s.charAt(i));
			offusque = offusque.concat(""+Math.round(Math.random()));
		}
			
		String out = new String();	
		String auxByte = new String();
		for(int i = 0; i < offusque.length(); ++i){
			auxByte = auxByte.concat(""+offusque.charAt(i));
			if(i%8 == 7){
				long number = (BitArray.fromString(auxByte).toNumber()+ 31) % 10176;
				out = out.concat(((char)number)+"");
				auxByte = new String();
			}
		}		
	    return new String(Base64.getEncoder().encode(out.getBytes(StandardCharsets.UTF_16)), StandardCharsets.UTF_16);
	}

	public static String decrypt(String text){
		
		String textDecoded = new String(Base64.getDecoder().decode(text.getBytes(StandardCharsets.UTF_16)), StandardCharsets.UTF_16);
		String offusque = new String();
	    for (int i = 0; i < textDecoded.length(); ++i){
		   offusque = offusque.concat(BitArray.shred((10176 + (int)textDecoded.charAt(i) - 31) % 10176).pushOff(8).toString());
	    } 
		
	    String desOffusque = new String();
		for(int i = 0; i < offusque.length(); ++i){
			if(i%2 == 0){
				desOffusque = desOffusque.concat(offusque.charAt(i)+"");
			}
		}	
		String out = new String();	
		String auxByte = new String();
		for(int i = 0; i < desOffusque.length(); ++i){
			auxByte = auxByte.concat(desOffusque.charAt(i)+"");
			if(i%8 == 7){
				out = out.concat(""+(char)BitArray.fromString(auxByte).toNumber());
				auxByte = new String();
			}
		}
			
	    return out;
		
	}

}
