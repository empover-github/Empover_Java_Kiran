
package Bricks;

public class BricksEntity {
	
	private String quantity;
	private String status;
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BricksEntity [quantity=" + quantity + ", status=" + status + "]";
	}
}
