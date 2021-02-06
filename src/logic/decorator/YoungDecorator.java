package logic.decorator;


public class YoungDecorator extends Decorator{
	
	
	public YoungDecorator(GeneralFilter filter){
		super(filter);
        this.filter = filter;
    }
	
	
    @Override
    public String getFilterSelected() {
        return filter.getFilterSelected()+ "-YOUNG";
    }
    
}
