package com.jifan.pssmis.model.bo.rpt;

/**
 * 查询条件展示方式
 * @author Administrator
 *
 */
public enum QueryShow {

	radio(0), checkbox(1), select(2),tree(3),time(4),input(5);
	private int value;

	QueryShow(){};

	QueryShow(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
