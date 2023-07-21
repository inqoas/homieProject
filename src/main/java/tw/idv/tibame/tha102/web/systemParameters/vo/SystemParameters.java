package tw.idv.tibame.tha102.web.systemParameters.vo;

import java.io.Serializable;

public class SystemParameters implements Serializable{

	private static final long serialVersionUID = -4976091587146499749L;

	private String parameters_name;
    private Integer parameters_value;
	public String getParameters_name() {
		return parameters_name;
	}
	public void setParameters_name(String parameters_name) {
		this.parameters_name = parameters_name;
	}
	public Integer getParameters_value() {
		return parameters_value;
	}
	public void setParameters_value(Integer parameters_value) {
		this.parameters_value = parameters_value;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
