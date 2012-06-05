/**
 * GenericChessman.java
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
 * GenericChessman
 *
 * @author Phokham Nonava
 */
public enum GenericChessman {

	PAWN('P'),
	KNIGHT('N'),
	BISHOP('B'),
	ROOK('R'),
	QUEEN('Q'),
	KING('K');

	private static final GenericChessman[] promotions = new GenericChessman[]{QUEEN, ROOK, BISHOP, KNIGHT};

	private final char token;

	private GenericChessman(char token) {
		this.token = token;
	}

	public static GenericChessman valueOf(char input) {
		for (GenericChessman chessman: values()) {
			if (Character.toLowerCase(input) == Character.toLowerCase(chessman.token)) {
				return chessman;
			}
		}
		
		return null;
	}

	public static GenericChessman valueOfPromotion(char input) {
		for (GenericChessman chessman: promotions) {
			if (Character.toLowerCase(input) == Character.toLowerCase(chessman.token)) {
				return chessman;
			}
		}
		
		return null;
	}

	public boolean isValidPromotion() {
		for (GenericChessman chessman : promotions) {
			if (this == chessman) {
				return true;
			}
		}
		
		return false;
	}

	public char toCharAlgebraic() {
		if (this == PAWN) {
			throw new UnsupportedOperationException();
		}
		
		return this.token;
	}
	
	public char toChar(GenericColor color) {
		if (color == null) throw new IllegalArgumentException();

		return color.transform(this.token);
	}

}
