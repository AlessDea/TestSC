package logic.decorator;



public class ArtDecorator extends Decorator{
	
	public ArtDecorator(GeneralFilter filter){
		super(filter);
        this.filter = filter;
    }
	
    @Override
    public String getFilterSelected() {
        return filter.getFilterSelected()+ "-ART";
    }
    
   
}
