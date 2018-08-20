package com.jifan.pssmis.model.bo.sys;
import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.OptimisticLockType;
import com.jifan.pssmis.model.bo.base.AuditableBO;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_TEST")
public class TestBO extends AuditableBO {
	private static final long serialVersionUID = -1L;

	@Column(name = "TEST_ONE", columnDefinition=" varchar(255)")
	private String testOne; //测1

	@Column(name = "TEST_TWO", length=255)
	private Integer testTwo; //测2

	@Column(name = "TEST_THREE")
	private Date testThree; //测3

	@Column(name = "TEST_FOUR", precision=15, scale = 5,nullable = false)
	private BigDecimal testFour; //测4

	@Column(name = "TEST_FIVE",nullable = false)
	private Boolean testFive; //测5

	public TestBO(){
	}
	public TestBO(String testOne,Integer testTwo,Date testThree,BigDecimal testFour,Boolean testFive){
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