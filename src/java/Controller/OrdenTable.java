    package Controller;
    public class OrdenTable {
        private int id;
        private String client;
        private String product;
        private String category;
        private String date;
        private int amount;
        public OrdenTable(int id, String client,String product, String category,String date, int amount) {
            this.id = id;
            this.client = client;
            this.product=product;
            this.category = category;
            this.date = date;
            this.amount = amount;  
        }
            public int getId() {
                    return id;
            }

            public void setId(int id) {
                    this.id = id;
            }

            public String getClient() {
                    return client;
            }

            public void setClient(String client) {
                    this.client = client;
            }

            public String getDate() {
                    return date;
            }

            public void setDate(String date) {
                    this.date = date;
            }

            public String getCategory() {
                    return category;
            }

            public void setCategory(String category) {
                    this.category = category;
            }

            public int getAmount() {
                    return amount;
            }

            public void setAmount(int amount) {
                    this.amount = amount;
            }

            public String getProduct() {
                    return product;
            }

            public void setProduct(String product) {
                    this.product = product;
            }
    }
