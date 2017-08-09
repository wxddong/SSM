package com.atwhere.p2p.myutil;

import java.util.Date;

import org.junit.Test;

import com.atwhere.p2p.util.JsonUtil;
import com.atwhere.p2p.vo.Student;

public class MyTest {

	@Test
	public void test() throws Exception {
		Student student = new Student(6,"小明",new Date());
		String object2str = JsonUtil.object2str(student);
		System.out.println(object2str);
		
		String sutdentStr = "{\"id\":6,\"name\":\"小明\",\"birthday\":1290196231431}";
		Object str2Object = JsonUtil.str2Object(sutdentStr, Student.class);
		System.out.println(str2Object);
	}

}
