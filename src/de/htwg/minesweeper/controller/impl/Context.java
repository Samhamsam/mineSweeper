package de.htwg.minesweeper.controller.impl;

import de.htwg.minesweeper.model.Model;

public class Context{
	
	private IStatus status;
	private Model field;
	
	public void setStatus(IStatus status){
		this.status = status;
	}

	public IStatus getStatusRunning(){
		return status;
	}
	
	public Context(){
		status = null;
		field = new Model(10, 10, 10);
	}
	
	public boolean endStatus() {
		return status.endStatus();
	}
	

}
