package dataProcessing;

import java.util.ArrayList;

import Util.Constants;
import businessDomain.OutputFromReader;
import businessDomain.Sale;
import businessDomain.SaleDetails;

public class OutputFromReaderProcessor {

	public static int getNumberOfCustomersInFile(OutputFromReader outPutFromReader) {
		return outPutFromReader.getCustomers().size();
	}

	public static int getNumberOfSalesmenInFile(OutputFromReader outPutFromReader) {
		return outPutFromReader.getSalesmen().size();
	}

	public static String getIDOfMostExpensiveSale(OutputFromReader outPutFromReader) {
		ArrayList<Sale> sales = outPutFromReader.getSales();
		String idOfMostExpensiveSale = Constants.NO_SALES_IN_FILE;
		Double mostExpensiveSalePrice = 0.00;
		for (Sale sale : sales) {
			Double salePrice = getSalePrice(sale);
			if (salePrice > mostExpensiveSalePrice) {
				mostExpensiveSalePrice = salePrice;
				idOfMostExpensiveSale = sale.getSaleID();
			}

		}

		return idOfMostExpensiveSale;
	}

	public static String getWorstSalesmanEver(OutputFromReader outPutFromReader) {
		ArrayList<Sale> sales = outPutFromReader.getSales();
		String idOfWorstSalesman = Constants.NO_SALESMEN_IN_FILE;
		Double leastExpensiveSalePrice = Double.MAX_VALUE;
		for (Sale sale : sales) {
			Double salePrice = getSalePrice(sale);
			if (salePrice < leastExpensiveSalePrice) {
				leastExpensiveSalePrice = salePrice;
				
				idOfWorstSalesman = sale.getSalesman().getName();
			}
			
		}

		return idOfWorstSalesman;
	}

	public static Double getSalePrice(Sale sale) {
		ArrayList<SaleDetails> saleDetails = sale.getSaleDetails();
		Double salePrice = 0.00;
		for (SaleDetails saleDetail : saleDetails) {
			Double saleDetailPrice = saleDetail.getItemPrice() * saleDetail.getItemQuantity();
			salePrice = salePrice + saleDetailPrice;
		}
		return salePrice;
	}
	
}
