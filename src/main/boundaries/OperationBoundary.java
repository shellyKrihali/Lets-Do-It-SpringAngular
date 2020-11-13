package main.boundaries;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import main.models.operation.Identifier;
import main.models.operation.Item;

public class OperationBoundary {
	
	public Identifier operationId;
	public String type;
	public Item item;
	public Date createdTimestamp;
	public String invokedBy; //TODO: Change it to User
	public Map<String, String> operationAttributes;
	
	public OperationBoundary() {
		this.operationId = new Identifier("space","id");
		this.type = "type";
		this.item = new Item(new Identifier("space","id"));
		this.createdTimestamp = new Date();
		this.invokedBy = "Ben & Aviram Hagvarim";
		this.operationAttributes = new HashMap<String, String>(){{put("Ben","Ze Noten li");}};
	}
	
	
	public Identifier getOperationId() {
		return operationId;
	}
	public void setOperationId(Identifier operationId) {
		this.operationId = operationId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public String getInvokedBy() {
		return invokedBy;
	}
	public void setInvokedBy(String invokedBy) {
		this.invokedBy = invokedBy;
	}
	public Map<String, String> getOperationAttributes() {
		return operationAttributes;
	}
	public void setOperationAttributes(Map<String, String> operationAttributes) {
		this.operationAttributes = operationAttributes;
	}
}
