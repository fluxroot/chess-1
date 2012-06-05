/**
 * GenericPositionTest.java
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
import jcpi.data.GenericPosition;
import jcpi.data.GenericRank;


import org.junit.Test;

/**
 * GenericPositionTest
 *
 * @author Phokham Nonava
 */
public class GenericPositionTest {

	@Test
	public void testPosition() {
		GenericPosition position1 = GenericPosition.valueOf(GenericFile.Fa, GenericRank.R1);
		GenericPosition position2 = GenericPosition.valueOf(GenericFile.Fa, GenericRank.R1);
		assertEquals(position1, position2);
	}

}
