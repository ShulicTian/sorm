package shulictian.ssm.blog.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import shulictian.ssm.controller.TopicController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/springApplication.xml",
		"classpath*:/spring/springmvcApplication.xml" })
public class TopicControllerTest {
	
	private static MockMvc mm = null;

	private static TopicController ts = new TopicController();

	@BeforeClass
	public static void setUpBeforeClass() {
		mm = MockMvcBuilders.standaloneSetup(ts).build();
	}
	
	@Test
	public void testGetForIndex() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getForIndex").contentType("text/html;charset=UTF-8")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getNew() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getNew").contentType("text/html;charset=UTF-8")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getAuthorNew() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getAuthorNew").contentType("text/html;charset=UTF-8").param("id", "1")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getAuthorHot() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getAuthorHot").contentType("text/html;charset=UTF-8").param("id", "1")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getNewByType() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getNewByType").contentType("text/html;charset=UTF-8").param("type", "1")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getRelated() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getRelated").contentType("text/html;charset=UTF-8").param("type", "1")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getGlobalHot() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getGlobalHot").contentType("text/html;charset=UTF-8")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getByUserId() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getByUserId").contentType("text/html;charset=UTF-8").param("id", "1")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getOriginalByUserId() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getOriginalByUserId").contentType("text/html;charset=UTF-8").param("id", "1")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getTransmitByUserId() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getTransmitByUserId").contentType("text/html;charset=UTF-8").param("id", "1")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getTranslateByUserId() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getTranslateByUserId").contentType("text/html;charset=UTF-8").param("id", "1")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void getByType() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/getByType").contentType("text/html;charset=UTF-8").param("type", "1")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	public void findFromHome() throws Exception {
		System.out.println(mm.perform(post("/bigCow/topic/findFromHome").contentType("text/html;charset=UTF-8").param("id", "1").param("ctx", "Test")).andExpect(status().isOk()).andDo(print())
				.andReturn().getResponse().getContentAsString());
	}

}
