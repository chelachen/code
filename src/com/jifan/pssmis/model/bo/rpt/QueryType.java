package com.jifan.pssmis.model.bo.rpt;

/**
 * 查询条件类别
 * @author Administrator
 *
 */
public enum QueryType {

	time(0), style(1), node(2),warehouse(3);
	private int value;

	QueryType(){};

	QueryType(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
