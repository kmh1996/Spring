package net.koreate.lombok;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class LombokTest {
	
	@Test
	public void lombok() {
		UserVO user1 = new UserVO();
		user1.setUid("aa");
		user1.setUpw("aa");
		System.out.println(user1.toString());
		
		UserVO user2 = new UserVO("bb","aa");
		System.out.println(user1.equals(user2));
		
		UserVO user3 = new UserVO("aa","aa");
		System.out.println(user1.equals(user3));
		
		UserVO user4 = UserVO.builder()
					   .uid("aaa") 
					   .upw("bbb")
					   .uname("최기근")
					   .regdate(new Date())
					   .friendList(new ArrayList<>())
					   .build();
		List<String> list = new ArrayList<>();
		list.add("조인성");
		list.add("이정재");
		list.add("이나영");
		list.add("쉬면석");
		user4.setFriendList(list);
		user4.getFriendList().add("이혜원");
		System.out.println(user4);
		
		
		
		UserVO user5 = UserVO.builder()
					   .uno(1)
					   .uid("id001")
					   .upw("pw001")
					   .uname("최기근")
					   .regdate(new Date())
					   .list("조인성")
					   .list("유재석")
					   .list("김혜수")
					   .build();
		System.out.println(user5);
		
		UserVO user6 = new UserVO(null,null);
		System.out.println(user6);
		
		
	}
}


