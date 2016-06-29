package FLOG_LOGIC;

/**
 * UCD ID - 14208891
 * @author Pasindu
 */
class VariableElement extends FlogElement{
	private String name = "";

	VariableElement(String name, double value) {
		this.value = value;
		this.name = name;
	}
	

	public String getName(){
		return this.name;
	}

	public void setValue(double value){
		this.value = value;
	}

	public void setValue(int value){
		this.value = (double) value;
	}

	public void setValue(float value){
		this.value = (double) value;
	}

	public double getValue(){
		return this.value;
	}
}

