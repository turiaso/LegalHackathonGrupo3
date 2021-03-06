package testcrypt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BitArray{
	
	private static final Integer _OFF = 0;
	private static final Integer _ON = 1;
	private List<Integer> m_bits = new ArrayList<>();
	
	public BitArray() {}	
	
	public BitArray(int bits) {		   
	    this.m_bits = BitArray.shred(bits).m_bits;
	}
	
	public BitArray(Integer[] bits) {	    
        for (int i = 0; i < bits.length; i++)
            this.m_bits.add(bits[i].equals(BitArray._ON) ? BitArray._ON : BitArray._OFF);        
	}
	
	public BitArray(int size, int bits) {
	   
	    this.m_bits = BitArray.shred(bits).m_bits;
	    
	    if (this.m_bits.size() != size) {
	        if (this.m_bits.size() < size) {
	            for (int i = this.m_bits.size(); i < size; i++) {
	                this.m_bits.add(BitArray._OFF);
	            }
	        } else {
	            for(int i = size; i > this.m_bits.size(); i--){
	                this.m_bits.remove(this.m_bits.size()-1);
	            }
	        }
	    }
	}
	
	public BitArray(int size, Integer[] bits) {		
	    
        for (int i = 0; i < bits.length; i++)
            this.m_bits.add(bits[i].equals(BitArray._ON) ? BitArray._ON : BitArray._OFF);
	     
        if (this.m_bits.size() != size) {
	        if (this.m_bits.size() < size) {
	            for (int i = this.m_bits.size(); i < size; i++) {
	                this.m_bits.add(BitArray._OFF);
	            }
	        } else {
	            for(int i = size; i > this.m_bits.size(); i--){
	                this.m_bits.remove(this.m_bits.size()-1);
	            }
	        }
	    }
	}
	
	public int getLength() {
		return this.m_bits.size(); 
	}
	
	public Integer getAt(int index) {
	    if (index < this.m_bits.size()) {
	        return this.m_bits.get(index);
	    }
	    return null;
	}
	
	public void setAt(int index, Integer value) {
	    if (index < this.m_bits.size()) {
	        this.m_bits.set(index, value!=0 ? BitArray._ON : BitArray._OFF);
	    }
	}
	
	public BitArray resize(int newSize) {
	    List<Integer> tmp = new ArrayList<Integer>();
	    for (int i = 0; i < newSize; i++) {
	        if (i < this.m_bits.size()) {
	            tmp.add(this.m_bits.get(i));
	        } else {
	            tmp.add(BitArray._OFF);
	        }
	    }
	    this.m_bits = tmp;
	    return this;
	}

	public BitArray pushOff(int newSize) {
		List<Integer> tmp = new ArrayList<>();
	    for (int i = 0; i < newSize; i++) {		
	        if (i >= newSize - this.m_bits.size()) {
	            tmp.add(this.m_bits.get(i-(newSize - this.m_bits.size())));
	        } else {
	            tmp.add(BitArray._OFF);
	        }
	    }
	    this.m_bits = tmp;
		return this;
	}

	public BitArray getCompliment() {
		BitArray result = new BitArray(this.m_bits.size());
	    for (int i = 0; i < this.m_bits.size(); i++) {
	        result.setAt(i, this.m_bits.get(i).equals(BitArray._OFF) ? BitArray._OFF : BitArray._ON);
	    }
	    return result;
	}
	
	public String toString() {
	    String s = new String();
	    for (int i = 0; i < this.m_bits.size(); i++) {
	        s = s.concat(this.m_bits.get(i).equals(BitArray._ON) ? "1" : "0");
	    }
	    return s;
	}
	
	public int toNumber() {
	    int pow = 0;
	    int n = 0;
	    for (int i = this.m_bits.size() - 1; i >= 0; i--) {
	        if (this.m_bits.get(i).equals(BitArray._ON)) {
	            n += Math.pow(2, pow);
	        }
	        pow++;
	    }
	    return n;
	}
	
	public static BitArray getUnion(BitArray bitArray1, BitArray bitArray2) {
	    int len = BitArray._getLen(bitArray1, bitArray2, true);
	    BitArray result = new BitArray(len);
	    for (int i = 0; i < len; i++) {
	        result.setAt(i, BitArray._union(bitArray1.getAt(i), bitArray2.getAt(i)));
	    }
	    return result;
	}
	
	public static BitArray getIntersection(BitArray bitArray1, BitArray bitArray2) {
	    int len = BitArray._getLen(bitArray1, bitArray2, true);
	    BitArray result = new BitArray(len);
	    for (int i = 0; i < len; i++) {
	        result.setAt(i, BitArray._intersect(bitArray1.getAt(i), bitArray2.getAt(i)));
	    }
	    return result;
	}
	
	public static BitArray getDifference(BitArray bitArray1, BitArray bitArray2) {
	    int len = BitArray._getLen(bitArray1, bitArray2, true);
	    BitArray result = new BitArray(len);
	    for (int i = 0; i < len; i++) {
	        result.setAt(i, BitArray._difference(bitArray1.getAt(i), bitArray2.getAt(i)));
	    }
	    return result;
	}
	
	public static BitArray shred(int number) {
	    List<Integer> bits = new ArrayList<>();
	    int q = number;
	    do {
	        bits.add(q % 2);
	        q = q / 2;
	    } while (q > 0);
	    Collections.reverse(bits);
	    return new BitArray(bits.size(), bits.toArray(new Integer[bits.size()]));
	};
	
	public static BitArray fromString(String string) {
		BitArray bits = new BitArray();
		for(int i = 0; i < string.length(); i++){
			bits.m_bits.add(Integer.valueOf((int)string.charAt(i)).equals(BitArray._ON) ? BitArray._ON : BitArray._OFF);
		}
		return bits;
	}
	
	private static Integer _intersect(Integer bit1,Integer bit2) {
	    return bit1 == BitArray._ON && bit2 == BitArray._ON ? BitArray._ON : BitArray._OFF;
	}

	private static Integer _union(Integer bit1, Integer bit2) {
	    return bit1 == BitArray._ON || bit2 == BitArray._ON ? BitArray._ON : BitArray._OFF;
	}

	private static Integer _difference(Integer bit1,Integer bit2) {
	    return bit1 == BitArray._ON && bit2 != BitArray._ON ? BitArray._ON : BitArray._OFF;
	}

	private static int _getLen(BitArray bitArray1, BitArray bitArray2, Boolean smallest) {
	    int l1 = bitArray1.getLength();
	    int l2 = bitArray2.getLength();

	    return l1 > l2 ? smallest ? l2 : l1 : smallest ? l2 : l1;
	}
};
