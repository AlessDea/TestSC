package logic.decorator;



public class MountainDecorator extends Decorator{

	public MountainDecorator(GeneralFilter filter){
		super(filter);
        this.filter = filter;
    }
	
    @Override
    public String getFilterSelected() {
        return filter.getFilterSelected()+ "-MOUNTAIN";
    }

	

    
    
    
}
