package logic.decorator;

public abstract class Decorator implements GeneralFilter {
	
	protected GeneralFilter filter;
	
	protected Decorator(GeneralFilter filter){
		this.filter = filter;
	}
	
}
