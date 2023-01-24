
package Controller;
public class ProductTable {
    private int id;
    private String name;
    private String category;
    private float price;

    public ProductTable(int id, String name, String category, float price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
}
