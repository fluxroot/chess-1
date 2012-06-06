/**
 * GenericFileTest.java
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

import static org.junit.Assert.*;
import jcpi.data.GenericFile;


import org.junit.Test;

/**
 * GenericFileTest
 *
 * @author Phokham Nonava
 */
public class GenericFileTest {

	@Test
	public void testValueOf() {
		assertEquals(GenericFile.Fa, GenericFile.valueOf('a'));
		assertEquals(GenericFile.Fa, GenericFile.valueOf('A'));
		assertNull(GenericFile.valueOf('i'));
	}

	@Test
	public void testPrev() {
		assertEquals(GenericFile.Fg, GenericFile.Fh.prev());
		assertNull(GenericFile.Fa.prev());
		
		assertEquals(GenericFile.Fa, GenericFile.Fh.prev(7));
		assertNull(GenericFile.Fg.prev(7));
	}
	
	@Test
	public void testNext() {
		assertEquals(GenericFile.Fb, GenericFile.Fa.next());
		assertNull(GenericFile.Fh.next());
		
		assertEquals(GenericFile.Fh, GenericFile.Fa.next(7));
		assertNull(GenericFile.Fb.next(7));
	}
	
	@Test
	public void testToChar() {
		assertEquals(GenericFile.Fa.toChar(), 'a');
	}

	@Test
	public void testToString() {
		assertEquals(GenericFile.Fa.toString(), "a");
	}

}
