package logic.decorator;

public class Filter implements GeneralFilter {
	
	protected String filterSelected = "";
	
	public Filter(String name) {
        this.filterSelected = name;
    }

	@Override
	public String getFilterSelected() {
		return this.filterSelected;
	}
	
}
