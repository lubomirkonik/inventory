package inventory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import inventory.model.Item;
import inventory.repository.ItemRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IntegrationTests extends AbstractIntegrationTests {

	private MvcResult mvcResult;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void initFormAndGetItems() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(model().size(2));
	}
	
	@Test
	public void addItem() throws Exception {
		addItem("Book", "1", "0.01", 302);
		addItem("Book", "0", "0", 200);
		addItem("Watch", "1001", "9999.991", 200);
		addItem("\t  ", "100.1", "100,1", 200);
		addItem("", "", "", 200);
		addItem("   ", " ", " ", 200);
		addItem("  s", "s", "s", 200);
	}
	
	@Test
	public void deleteItem() throws Exception {
//		TODO remove all and add one
		Item item = itemRepository.getAllItems().get(0);
		mockMvc.perform(get("/deleteItem/{itemId}", item.getId()))
			.andDo(print())
			.andExpect(status().is(302));
//		TODO perform get "/" and verify items size is 0
	}
	
	private void addItem(String name, String qty, String price, int status) throws Exception {
		mockMvc.perform(post("/addItem")
			.param("name", name)
			.param("qty", qty)
			.param("price", price))
			.andDo(print())
			.andExpect(status().is(status));
	}
	
}
