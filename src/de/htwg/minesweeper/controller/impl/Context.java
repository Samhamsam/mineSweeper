package de.htwg.minesweeper.controller.impl;

public class Context{
	
	private IStatus status;
	
	public void setStatus(IStatus status){
		this.status = status;
	}

	public IStatus getStatusRunning(){
		return status;
	}
	
	public Context(){
		status = null;
	}
	
	public boolean endStatus() {
		return status.endStatus();
	}
	

}
