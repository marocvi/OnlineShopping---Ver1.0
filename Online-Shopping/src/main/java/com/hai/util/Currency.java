package com.hai.util;

public enum Currency {

	DOLLA, YEN, EURO;
	@Override
	public String toString() {

		if (this == DOLLA)

			return "$";
		else if (this == YEN)
			return "¥";
		else 
			return "€";
		
	}
	
}
