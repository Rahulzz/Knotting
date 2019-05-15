package com.foolishworks.knotting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="config")
public class Configuration implements Serializable {

	private static final long serialVersionUID = -8169858139464886505L;

	@Id
	@Column(name="CONID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long configId;

	@Column(name="CONCODE")
	private String configCode;

	@Column(name="CONFLD1")
	private String configField1;

	@Column(name="CONFLD2")
	private String configField2;

	@Column(name="CONFLD3")
	private String configField3;

	@Column(name="CONFLD4")
	private String configField4;

	@Column(name="CONFLD5")
	private String configField5;

	@Column(name="CONFLD6")
	private String configField6;

	@Column(name="CONFLD7")
	private String configField7;

	@Column(name="CONFLD8")
	private String configField8;

	@Column(name="CONFLD9")
	private String configField9;

	@Column(name="CONFLD10")
	private String configField10;

	@Column(name="CONFLD11")
	private String configField11;

	@Column(name="CONFLD12")
	private String configField12;

	@Column(name="CONFLD13")
	private String configField13;

	@Column(name="CONFLD14")
	private String configField14;

	@Column(name="CONFLD15")
	private String configField15;

	@Column(name="CONFLD16")
	private String configField16;

	@Column(name="CONFLD17")
	private String configField17;

	@Column(name="CONFLD18")
	private String configField18;

	@Column(name="CONFLD19")
	private String configField19;

	@Column(name="CONFLD20")
	private String configField20;

	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigField1() {
		return configField1;
	}

	public void setConfigField1(String configField1) {
		this.configField1 = configField1;
	}

	public String getConfigField2() {
		return configField2;
	}

	public void setConfigField2(String configField2) {
		this.configField2 = configField2;
	}

	public String getConfigField3() {
		return configField3;
	}

	public void setConfigField3(String configField3) {
		this.configField3 = configField3;
	}

	public String getConfigField4() {
		return configField4;
	}

	public void setConfigField4(String configField4) {
		this.configField4 = configField4;
	}

	public String getConfigField5() {
		return configField5;
	}

	public void setConfigField5(String configField5) {
		this.configField5 = configField5;
	}

	public String getConfigField6() {
		return configField6;
	}

	public void setConfigField6(String configField6) {
		this.configField6 = configField6;
	}

	public String getConfigField7() {
		return configField7;
	}

	public void setConfigField7(String configField7) {
		this.configField7 = configField7;
	}

	public String getConfigField8() {
		return configField8;
	}

	public void setConfigField8(String configField8) {
		this.configField8 = configField8;
	}

	public String getConfigField9() {
		return configField9;
	}

	public void setConfigField9(String configField9) {
		this.configField9 = configField9;
	}

	public String getConfigField10() {
		return configField10;
	}

	public void setConfigField10(String configField10) {
		this.configField10 = configField10;
	}

	public String getConfigField11() {
		return configField11;
	}

	public void setConfigField11(String configField11) {
		this.configField11 = configField11;
	}

	public String getConfigField12() {
		return configField12;
	}

	public void setConfigField12(String configField12) {
		this.configField12 = configField12;
	}

	public String getConfigField13() {
		return configField13;
	}

	public void setConfigField13(String configField13) {
		this.configField13 = configField13;
	}

	public String getConfigField14() {
		return configField14;
	}

	public void setConfigField14(String configField14) {
		this.configField14 = configField14;
	}

	public String getConfigField15() {
		return configField15;
	}

	public void setConfigField15(String configField15) {
		this.configField15 = configField15;
	}

	public String getConfigField16() {
		return configField16;
	}

	public void setConfigField16(String configField16) {
		this.configField16 = configField16;
	}

	public String getConfigField17() {
		return configField17;
	}

	public void setConfigField17(String configField17) {
		this.configField17 = configField17;
	}

	public String getConfigField18() {
		return configField18;
	}

	public void setConfigField18(String configField18) {
		this.configField18 = configField18;
	}

	public String getConfigField19() {
		return configField19;
	}

	public void setConfigField19(String configField19) {
		this.configField19 = configField19;
	}

	public String getConfigField20() {
		return configField20;
	}

	public void setConfigField20(String configField20) {
		this.configField20 = configField20;
	}

}
