package de.htwg.se.minesweeper.controller.impl;

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
