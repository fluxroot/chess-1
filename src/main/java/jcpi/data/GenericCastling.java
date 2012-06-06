/**
 * GenericCastling.java
 * 
 * Copyright 2007 Java Chess Protocol Interface Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jcpi.data;

/**
 * GenericCastling
 *
 * @author Phokham Nonava
 */
public enum GenericCastling {

	KINGSIDE("O-O", 'K'),
	QUEENSIDE("O-O-O", 'Q');
	
	private final String longToken;
	private final char token;

	private GenericCastling(String longToken, char token) {
		this.longToken = longToken;
		this.token = token;
	}
	
	public static GenericCastling valueOf(char input) {
		for (GenericCastling castling: values()) {
			if (Character.toLowerCase(input) == Character.toLowerCase(castling.token)) {
				return castling;
			}
		}
		
		return null;
	}

	public static GenericCastling valueOfLongToken(String input) {
		if (input == null) throw new IllegalArgumentException();

		for (GenericCastling castling: values()) {
			if (castling.longToken.equalsIgnoreCase(input)) {
				return castling;
			}
		}
		
		return null;
	}

	public String toLongToken() {
		return this.longToken;
	}

	public char toChar(GenericColor color) {
		if (color == null) throw new IllegalArgumentException();

		return color.transform(this.token);
	}

}
