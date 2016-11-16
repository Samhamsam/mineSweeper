package de.htwg.se.minesweeper.model.commands;

public class Context{
	
	private IStatus status;

	public Context(IStatus status) {
		this.status = status;
	}

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
