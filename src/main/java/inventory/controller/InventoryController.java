package inventory.controller;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import inventory.model.Item;
import inventory.repository.ItemRepository;

@Controller
@RequestMapping("/")
public class InventoryController {

	private final ItemRepository itemRepository;
	
	private final MessageSource messageSource;
	
	@Autowired
	public InventoryController(ItemRepository itemRepository, MessageSource messageSource) {
		this.itemRepository = itemRepository;
		this.messageSource = messageSource;
	}
	
	@ModelAttribute("items")
	private List<Item> getAllItems() {
		return itemRepository.getAllItems();
	}
	
	@RequestMapping
	public String initFormAndGetItems(Model model) {
		model.addAttribute("itemForm", new Item());
		return "inventory";
	}
	
	@RequestMapping(value = "addItem", method = RequestMethod.POST)
	public String addItem(@Valid @ModelAttribute("itemForm") Item item,
			Errors errors, RedirectAttributes redirectAttrs) {
		if (errors.hasErrors() | hasDuplicateName(item.getName(), errors)) {
			return "inventory";
		}
		itemRepository.saveItem(item);
		redirectAttrs.addFlashAttribute("message",
				messageSource.getMessage("item.added", null, Locale.getDefault()));
		return "redirect:/";
	}
	
	@RequestMapping(value = "deleteItem/{itemId}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable UUID itemId, RedirectAttributes redirectAttrs) {
		itemRepository.deleteItem(itemId);
		redirectAttrs.addFlashAttribute("message",
				messageSource.getMessage("item.deleted", null, Locale.getDefault()));
		return "redirect:/";
	}
	
	private boolean hasDuplicateName(String name, Errors errors) {
		if (itemRepository.findByName(name) == null)
			return false;
		errors.rejectValue("name", "DuplicateName",
				messageSource.getMessage("item.duplicate", null, Locale.getDefault()));
		return true;
	}
	
}