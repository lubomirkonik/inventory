package inventory.repository;

import inventory.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

	private Map<UUID, Item> items = new HashMap<>();
	
	public List<Item> getAllItems() {
		return new ArrayList<>(items.values());
	}
	
	public Item findByName(String name) {
		for (Item item : items.values()) {
			if (item.getName().equalsIgnoreCase(name))
				return item;
		}
		return null;
	}
	
	public void saveItem(Item item) {
		item.setId(UUID.randomUUID());
		items.put(item.getId(), item);
	}

	public void deleteItem(UUID key) {
		items.remove(key);
	}

}
