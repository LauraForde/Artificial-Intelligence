package ie.gmit.sw.ai;

import java.security.*;
import java.util.*;

// This class is handle the key needed for decryption
// It will generate keys after priming the text
// Will replace duplicate letters with X
public class Key {
	
	private static Key instanceKey;
	
	// If there is no instance create and return a new one
	public static Key newInstance() {
		return (instanceKey == null) ? new Key() : instanceKey;
	}
	
	
	public String genKey() {
		
		String cipKey = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
		char [] key = cipKey.toCharArray();
		
		// Fisher Yates
		int count;
		Random rand = new SecureRandom();
		for (int i = key.length - 1; i > 0; i--) {
			count = rand.nextInt(i + 1);
			if(count != i) {
				// ^ copy bit
				key[count] ^= key[i];
				key[i] ^= key[count];
				key[count] ^= key[i];
			}
		}
		
		return new String(key);
	}// genKey
	
	// Method to shuffle the key
	public String shuffle(String key) {
		Random rand = new SecureRandom();
		int i = rand.nextInt(100);
		
		if( i >= 0 && i < 2) {
			return  swapRow(key, rand.nextInt(4), rand.nextInt(4));
		}
		else if( i >= 2 && i < 4) {
			return swapColumn(key, rand.nextInt(4), rand.nextInt(4));
		}
		else if( i >= 4 && i < 6) {
			return flipRow(key);
		}
		else if (i >= 6 && i < 8) {
			return flipColumn(key);	
		}
		else if (i >=8 && i < 10) {
			return new StringBuffer(key).reverse().toString();
		}
		else {
			// This will swap letters
			int a = rand.nextInt(key.length() - 1);
			int b = rand.nextInt(key.length() - 1);
			b = ( a== b) ? (b == key.length()- 1) ? b - 1 : b + 1 : rand.nextInt(key.length() - 1);
			char[] result = key.toCharArray();
			char temp = result[a];
			result[a] = result[b];
			result[b] = temp;
			return new String(result);
		}
	}
	
	// Method used to flip rows of array
	private String flipRow(String key) {
		String[] row = new String[5];
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < 5; i++) {
			row[i] = key.substring(i * 5, i * 5 + 5);
			// Getting the reverse of the row and making it a string
			row[i] = new StringBuffer(row[i]).reverse().toString();
			builder.append(row[i]);
		}
		return builder.toString();
	}
	
	// Method to flip columns
	private String flipColumn(String key) {
		char[] column = key.toCharArray();
		int length = key.length() - (key.length()/5);
		
		for(int i = 0; i < key.length()/5; i++) {
			for(int j = 0; j < key.length()/5; j++) {
				char temp = key.charAt(i * 5 + j);
				column[(i * 5) + j] = key.charAt(length+ j);
				column[length + j] = temp;
			}
			length -= 5;
		}
		
		return new String(column);
	}
	
	// Method to swap rows
	private String swapRow(String key, int row1, int row2) {
		if(row1 == row2) {
			return swapRow(key, (int)(Math.random() * 4), (int)(Math.random() * 4));
		}
		else {
			row1 *= 5;
			row2 *= 5;
			
			char[] newK = key.toCharArray();
			for(int i = 0; i < key.length()/5; i++) {
				char temp = newK[(i + row2)];
				newK[(i + row1)] = newK[(i + row2)];
				newK[(i + row2)] = temp;
			}
			return new String(newK);
		}
	}
	
	// Method to swap columns
		private String swapColumn(String key, int column1, int column2) {
			if(column1 == column2) {
				return swapRow(key, (int)(Math.random() * 4), (int)(Math.random() * 4));
			}
			else {				
				char[] newK = key.toCharArray();
				for(int i = 0; i < key.length()/5; i++) {
					// Need to know what row to be able to swap columns correctly
					int row = i * 5;
					char temp = newK[(i + column2)];
					newK[(row + column1)] = newK[(row + column2)];
					newK[(row + column2)] = temp;
				}
				return new String(newK);
			}
		}

}
