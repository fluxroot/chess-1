/**
 * EngineReadyRequestCommandTest.java
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
package jcpi.commands;

import static org.junit.Assert.*;
import jcpi.commands.EngineReadyRequestCommand;

import org.junit.Test;

/**
 * EngineReadyRequestCommandTest
 *
 * @author Phokham Nonava
 */
public class EngineReadyRequestCommandTest {

	@Test
	public final void testEngineReadyRequestCommand() {
		EngineReadyRequestCommand command = new EngineReadyRequestCommand();
		assertEquals("", command.token);

		command = new EngineReadyRequestCommand("mytoken");
		assertEquals("mytoken", command.token);
	}

}
