package dts.logic;

import java.util.List;

import boundaries.ItemBoundary;

public interface ItemsService {

	public ItemBoundary create(String managerSpace, String managerEmail, ItemBoundary newItem);

	public ItemBoundary update(String managerSpace, String managerEmail, String itemSpace, String itemId,
			ItemBoundary update);

	public List<ItemBoundary> getAll(String userSpace, String userEmail, int size, int page);

	public ItemBoundary getSpecificItem(String userSpace, String userEmail, String itemSpace, String itemId);

	public void deleteAll(String adminSpace, String adminEmail);

}