package com.jifan.pssmis.testbase;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //指定测试用例的运行器 这里是指定了Junit4   
@ContextConfiguration({"classpath:applicationContext.xml"})
public class BaseSpring3Test extends BaseTest{
	
}
