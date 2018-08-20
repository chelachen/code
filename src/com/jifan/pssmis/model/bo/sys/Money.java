package com.jifan.pssmis.model.bo.sys;

import java.io.Serializable;
import java.math.BigDecimal;

import com.jifan.pssmis.foundation.util.MathUtil;

/**
 * 金额
 * 
 * @author Administrator
 * 
 */
public class Money  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978574131409233265L;
	
	private BigDecimal value;
	private static final String UNIT = "Y";

	public Money() {
		this.value=BigDecimal.ZERO;
	}

	public Money(final BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal getValueRound(){
		return this.value.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getFormatString() {
		return this.value + UNIT;
	}
	
	public String getStr() {
		if(this.equalsZero())
			return "";
		return String.valueOf(this.getValueRound()) ;
	}

	public BigDecimal getValue() {
		return value;
	}

	/**
	 * 加
	 * 
	 * @param gamegold
	 * @return
	 */
	public Money addition(Money money) {
		return new Money(this.value.add(money.getValue()));
	}

	/**
	 * 减
	 * 
	 * @param gamegold
	 * @return
	 */
	public Money subtraction(Money money) {
		return new Money(this.value.subtract(money.getValue()));
	}

	/**
	 * 乘
	 * 
	 * @param gamegold
	 * @return
	 */
	public Money multiplication(Money money) {
		return new Money(this.value.multiply(money.getValue()));
	}

	/**
	 * 乘(根据乘数)
	 * 
	 * @param multiple
	 * @return
	 */
	public Money multiplication(BigDecimal multiple) {
		return new Money(this.value.multiply(multiple));
	}

	/**
	 * 除
	 * 
	 * @param gamegold
	 * @return
	 */
	public Money division(Money money) {
		return new Money(this.value.divide(money.getValue()));
	}

	/**
	 * 除(根据除数)
	 * 
	 * @param dividend
	 * @return
	 */
	public Money division(BigDecimal dividend) {
		return new Money(this.value.divide(dividend,2,BigDecimal.ROUND_HALF_UP));
	}
	
	/**
	 * 对两数值进行相除a/b;
	 * 
	 * @param a
	 *            BigDecimal 数值 dividend
	 *            int 精度
	 * @return BigDecimal eg:
	 */
	public  BigDecimal division(BigDecimal dividend, int scale) {
		if (dividend == null)
			throw new IllegalArgumentException("除数为空!");
		if (dividend.intValue() == 0)
			throw new IllegalArgumentException("除数为0!");
		return this.value.divide(dividend, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 大于
	 * 
	 * @param gamegold
	 * @return
	 */
	public boolean greaterThan(Money money) {
		if (this.value.compareTo(money.getValue()) == 1)
			return true;
		else
			return false;
	}

	/**
	 * 小于
	 * 
	 * @param gamegold
	 * @return
	 */
	public boolean lessThan(Money money) {
		if (this.value.compareTo(money.getValue()) == -1)
			return true;
		else
			return false;
	}
	
	/**
	 * 等于
	 * 
	 * @param gamegold
	 * @return
	 */
	public boolean equals(Money money) {
		if (this.value.compareTo(money.getValue()) == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * 等于或小于0
	 * @return
	 */
	public boolean equalsOrLessZero(){
		if (this.value.longValue() <= 0)
			return true;
		else
			return false;
	}
	
	/**
	 * 等于0
	 * @return
	 */
	public boolean equalsZero(){
		if (this.value.longValue() == 0)
			return true;
		else
			return false;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * 四舍五入
	 * @param scale 保留小数点位数
	 * void
	 *
	 */
	public Money round(int scale){
		return new Money(MathUtil.round(scale, this.value));
	}

}
