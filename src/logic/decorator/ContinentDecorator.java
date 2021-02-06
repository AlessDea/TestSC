package logic.decorator;



public class ContinentDecorator extends Decorator{

	private String continentName;
	
	public ContinentDecorator(GeneralFilter filter){
		super(filter);
		this.continentName = "";
        this.filter = filter;
    }
		
    @Override
    public String getFilterSelected() {
        return filter.getFilterSelected() + "-" + this.getContinentName();
    }

	private String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
    
}
