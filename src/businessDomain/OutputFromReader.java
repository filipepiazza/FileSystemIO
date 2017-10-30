package businessDomain;

import java.util.ArrayList;

public class OutputFromReader {

	ArrayList<Salesman> salesmen = new ArrayList<Salesman>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Sale> sales = new ArrayList<Sale>();

	public OutputFromReader(ArrayList<Salesman> salesmen, ArrayList<Customer> customers, ArrayList<Sale> sales) {
		super();
		this.salesmen = salesmen;
		this.customers = customers;
		this.sales = sales;
	}

	public ArrayList<Salesman> getSalesmen() {
		return salesmen;
	}

	public void setSalesmen(ArrayList<Salesman> salesmen) {
		this.salesmen = salesmen;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public ArrayList<Sale> getSales() {
		return sales;
	}

	public void setSales(ArrayList<Sale> sales) {
		this.sales = sales;
	}

}
