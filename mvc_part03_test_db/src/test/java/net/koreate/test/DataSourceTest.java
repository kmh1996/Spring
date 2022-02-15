package net.koreate.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"	
})
public class DataSourceTest {
	@Inject
	DataSource ds;
	
	@Test
	public void testDataSource() {
		Connection conn = null;
		
		try {
			System.out.println("ds : " + ds);
			conn = ds.getConnection();
			System.out.println("ds conn : " + conn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("실패");
		}finally {
			try {
				if(conn != null)conn.close();
			} catch (SQLException e) {}
		}
	}
}











