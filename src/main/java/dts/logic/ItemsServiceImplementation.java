package dts.logic;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boundaries.ItemBoundary;

import dts.converter.ItemConverter;
import dts.data.ItemEntity;

import models.operations.CreatedBy;
import models.operations.ItemId;
import models.users.UserId;

//@Service
public class ItemsServiceImplementation implements ItemsService {

	private Map<String, ItemEntity> itemStore;
	private AtomicLong idGenerator;
	private ItemConverter itemConvertor;

	@Autowired
	public void setMessageConverter(ItemConverter itemConvertor) {
		this.itemConvertor = itemConvertor;
	}

	@PostConstruct
	public void init() {
		this.itemStore = Collections.synchronizedMap(new HashMap<>());
		this.idGenerator = new AtomicLong(1l);
	}

	@Override
	public ItemBoundary create(String managerSpace, String managerEmail, ItemBoundary newItem) {
		ItemEntity itemEntity = this.itemConvertor.toEntity(newItem);
		String id = "" + this.idGenerator.getAndIncrement();
		itemEntity.setCreatedTimestamp(new Date());
		itemEntity.setItemId(new ItemId(managerSpace, id).toString());
		itemEntity.setCreatedBy(new CreatedBy(new UserId(managerSpace, managerEmail)).toString());
		itemStore.put(itemEntity.getItemId().toString(), itemEntity);
		return this.itemConvertor.toBoundary(itemEntity);
	}

	@Override
	public ItemBoundary update(String managerSpace, String managerEmail, String itemSpace, String itemId,
			ItemBoundary update) {
		ItemEntity existingItem = this.itemStore.get(new ItemId(itemSpace, itemId).toString());
		if (existingItem == null) {
			throw new RuntimeException("The item don't exist");
		}
		ItemEntity updatedItem = this.itemConvertor.toEntity(update);
		if (update.getType() != null)
			existingItem.setType(updatedItem.getType());
		if (update.getName() != null)
			existingItem.setName(updatedItem.getName());
		if (update.getActive() != null)
			existingItem.setActive(updatedItem.getActive());
		if (update.getLocation() != null) {
			existingItem.setLat(updatedItem.getLat());
			existingItem.setLng(updatedItem.getLng());
		}
		if (update.getItemAttributes() != null)
			existingItem.setItemAttributes(updatedItem.getItemAttributes());
		itemStore.put(existingItem.getItemId(), existingItem);
		return this.itemConvertor.toBoundary(existingItem);
	}

	@Override
	public List<ItemBoundary> getAll(String userSpace, String userEmail, int size, int page) {
		return this.itemStore.values().stream().map(entity -> itemConvertor.toBoundary(entity))
				.collect(Collectors.toList());
	}

	@Override
	public ItemBoundary getSpecificItem(String userSpace, String userEmail, String itemSpace, String itemId) {
		ItemEntity item = this.itemStore.get(new ItemId(itemSpace, itemId).toString());
		if (item == null) {
			throw new RuntimeException("The item don't exist");
		}
		return this.itemConvertor.toBoundary(item);
	}

	@Override
	public void deleteAll(String adminSpace, String adminEmail) {
		this.itemStore.clear();
		this.idGenerator = new AtomicLong(1l);
	}

}