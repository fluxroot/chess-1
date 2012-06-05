/**
 * EngineAnalyzeCommand.java
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

import java.util.ArrayList;
import java.util.List;

import jcpi.IEngine;
import jcpi.data.GenericBoard;
import jcpi.data.GenericMove;


/**
 * EngineAnalyzeCommand
 * 
 * @author Phokham Nonava
 */
public class EngineAnalyzeCommand implements IEngineCommand {

	public final GenericBoard board;
	public final List<GenericMove> moveList;
	
	public EngineAnalyzeCommand(GenericBoard board) {
		this(board, new ArrayList<GenericMove>());
	}
	
	public EngineAnalyzeCommand(GenericBoard board, List<GenericMove> moveList) {
		if (board == null) throw new IllegalArgumentException();
		if (moveList == null) throw new IllegalArgumentException();
		
		this.board = board;
		this.moveList = moveList;
	}
	
	public void accept(IEngine v) {
		v.visit(this);
	}

}
