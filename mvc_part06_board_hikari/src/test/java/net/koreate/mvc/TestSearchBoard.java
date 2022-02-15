package net.koreate.mvc;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import net.koreate.mvc.sboard.dao.SearchBoardDAO;
import net.koreate.mvc.sboard.vo.SearchBoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations= {"classpath:/context/root-context.xml"}
)
@Log4j
public class TestSearchBoard {
	
	@Inject
	SearchBoardDAO dao;
	
	@Test
	public void test() throws Exception{
		SearchBoardVO vo = new SearchBoardVO();
		vo.setTitle("테스트 제목");
		vo.setContent("테스트 내용");
		vo.setWriter("최기근");
		int result = dao.create(vo);
		log.info("실행된 행의 개수 : " + result);
		log.warn("실행된 행의 개수 : " + result);
		log.error("실행된 행의 개수 : " + result);
		log.debug("실행된 행의 개수 : " + result);
	}
}










