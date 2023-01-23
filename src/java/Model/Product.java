package Model;

public class Product {
	private int id;
	private String name;
        private String description;
	private String category;
	private Double price;
        private int exist;
        private String marcas;
        private String orders;
	private String image;
	
	
	public Product() {
	}

        public Product(int id, String name, String description, String category, Double price, int exist, String marcas, String orders, String image) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.category = category;
            this.price = price;
            this.exist = exist;
            this.marcas = marcas;
            this.orders = orders;
            this.image = image;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getExist() {
            return exist;
        }

        public void setExist(int exist) {
            this.exist = exist;
        }

        public String getMarcas() {
            return marcas;
        }

        public void setMarcas(String marcas) {
            this.marcas = marcas;
        }

        public String getOrders() {
            return orders;
        }

        public void setOrders(String orders) {
            this.orders = orders;
        }

        

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", image="
				+ image + "]";
	}
	
	
}
