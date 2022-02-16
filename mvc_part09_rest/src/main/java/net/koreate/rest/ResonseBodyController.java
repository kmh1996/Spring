package net.koreate.rest;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import net.koreate.vo.SampleVO;

@RestController
public class ResonseBodyController {

	@RequestMapping("/hello")
	public String hello() {
		return "This is message";
	}
	
	@PostMapping(value="xmlTest", consumes="application/x-www-form-urlencoded")
	public ResponseEntity<String> xmlTest(SampleVO vo) throws Exception{
		System.out.println(vo);
		HttpHeaders header = new HttpHeaders();
		// response.setContextType("text/xml;charset=utf-8");
		header.setContentType(new MediaType("text","xml",Charset.forName("utf-8")));
//		header.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML.toString());
		System.out.println(MediaType.TEXT_XML);
		System.out.println(MediaType.APPLICATION_JSON_VALUE);
		System.out.println(Charset.forName("utf-8"));
		ResponseEntity<String> entity = null;
		/**
		 * <sample>
		 * 	<name>최기근</name>
			<age>26</age>
		   </sample>
		 */
		String xml = "<sample><name>"+vo.getName()+"</name>";
			   xml += "<age>"+vo.getAge()+"</age></sample>";
		System.out.println(xml);
//		entity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
//		entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//		entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		entity = new ResponseEntity<String>(xml,header,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping(value="xmlTest",consumes="application/json",
			produces = "text/xml;charset=utf-8")
	public String xmlTestJSON(
			@RequestBody SampleVO vo
			) throws Exception{
//		"{name : input_name, age : input_age}"
		ObjectMapper mapper = new XmlMapper();
		String xml = mapper.writeValueAsString(vo);
		System.out.println(xml);
		SampleVO reborn = mapper.readValue(xml, SampleVO.class);
		System.out.println(reborn);
		return xml;
	}
	
	
	
}





