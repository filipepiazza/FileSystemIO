package businessDomain;

public class SaleDetails {
	
	private String ItemID;
	private int itemQuantity;
	private double itemPrice;
	
	public SaleDetails(String itemID, int itemQuantity, double itemPrice) {
		super();
		ItemID = itemID;
		this.itemQuantity = itemQuantity;
		this.itemPrice = itemPrice;
	}
	
	@Override
	public String toString() {
		return "SaleDetails [ItemID=" + ItemID + ", itemQuantity=" + itemQuantity + ", itemPrice=" + itemPrice + "]";
	}

	public String getItemID() {
		return ItemID;
	}
	public void setItemID(String itemID) {
		ItemID = itemID;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	
	
}
