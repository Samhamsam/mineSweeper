package de.htwg.minesweeper.controller.impl;

import de.htwg.minesweeper.controller.IStatus;

public class Context{
	
	private IStatus status;
	
	public Context(){
		status = null;
	}
	
	public void setStatus(IStatus status){
		this.status = status;
	}

	public IStatus getStatusRunning(){
		return status;
	}

	public boolean endStatus() {
		return status.endStatus();
	}
	

}
