package inventory;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InventoryApplication.class)
@WebAppConfiguration
public abstract class AbstractIntegrationTests {

	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	protected MockMvc mockMvc;
	
	protected MediaType contentType = new MediaType(MediaType.TEXT_HTML.getType(),
			MediaType.TEXT_HTML.getSubtype(), Charset.forName("utf8")); 
	
	@Before
	public void setupMockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
}
