package businessDomain;

import java.util.ArrayList;

public class Sale {

	private String saleID;
	private ArrayList<SaleDetails> saleDetails;
	private Salesman salesman;

	public Sale(String saleID, ArrayList<SaleDetails> saleDetails, Salesman salesman) {
		super();
		this.saleID = saleID;
		this.saleDetails = saleDetails;
		this.salesman = salesman;
	}

	@Override
	public String toString() {
		return "Sale [saleID=" + saleID + ", saleDetails=" + saleDetails + ", salesman=" + salesman + "]";
	}

	public String getSaleID() {
		return saleID;
	}

	public void setSaleID(String saleID) {
		this.saleID = saleID;
	}

	public ArrayList<SaleDetails> getSaleDetails() {
		return saleDetails;
	}

	public void setSaleDetails(ArrayList<SaleDetails> saleDetails) {
		this.saleDetails = saleDetails;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

}
