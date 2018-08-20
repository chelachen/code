package com.jifan.pssmis.model.vo.sys;
import java.util.Date;
import java.math.BigDecimal;
import com.jifan.pssmis.model.vo.base.BaseVO;
public class TestQueryVO extends BaseVO {
	private static final long serialVersionUID = -1L;

	private String testOne; //测1

	private Integer testTwo; //测2

	private Date testThree; //测3

	private BigDecimal testFour; //测4

	private Boolean testFive; //测5

	public TestQueryVO(){
	}
	public TestQueryVO(String testOne,Integer testTwo,Date testThree,BigDecimal testFour,Boolean testFive){
		this.testOne=testOne;
		this.testTwo=testTwo;
		this.testThree=testThree;
		this.testFour=testFour;
		this.testFive=testFive;
	}
	 public String getTestOne(){
			return  this.testOne;
	}
	 public void setTestOne(String testOne){
			 this.testOne = testOne;
	}
	 public Integer getTestTwo(){
			return  this.testTwo;
	}
	 public void setTestTwo(Integer testTwo){
			 this.testTwo = testTwo;
	}
	 public Date getTestThree(){
			return  this.testThree;
	}
	 public void setTestThree(Date testThree){
			 this.testThree = testThree;
	}
	 public BigDecimal getTestFour(){
			return  this.testFour;
	}
	 public void setTestFour(BigDecimal testFour){
			 this.testFour = testFour;
	}
	 public Boolean getTestFive(){
			return  this.testFive;
	}
	 public void setTestFive(Boolean testFive){
			 this.testFive = testFive;
	}
}
                    	