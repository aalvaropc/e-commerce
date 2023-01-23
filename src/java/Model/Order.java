/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Order extends Product{
	private int orderId;
	private int uid;
	private int qunatity;
	private String date;
        private String estadoP;
	
	public Order() {
	}

        public Order(int orderId, int uid, int qunatity, String date, String estadoP) {
            this.orderId = orderId;
            this.uid = uid;
            this.qunatity = qunatity;
            this.date = date;
            this.estadoP = estadoP;
        }
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQunatity() {
		return qunatity;
	}
	public void setQunatity(int qunatity) {
		this.qunatity = qunatity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

        public String getEstadoP() {
            return estadoP;
        }

        public void setEstadoP(String estadoP) {
            this.estadoP = estadoP;
        }
        
}
