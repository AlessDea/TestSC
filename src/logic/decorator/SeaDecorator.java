package logic.decorator;


public class SeaDecorator extends Decorator{

	public SeaDecorator(GeneralFilter filter){
		super(filter);
        this.filter = filter;
    }

    @Override
    public String getFilterSelected() {
        return filter.getFilterSelected()+ "-SEA";
    }

	
   
}
