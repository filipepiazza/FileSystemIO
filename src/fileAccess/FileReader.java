package fileAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Util.Constants;
import businessDomain.Customer;
import businessDomain.OutputFromReader;
import businessDomain.Sale;
import businessDomain.SaleDetails;
import businessDomain.Salesman;

public class FileReader {

	public static OutputFromReader readFileLines(File file) {

		String[] partsOfLine;
		String line;
		String typeOfLine;
		ArrayList<Salesman> salesmen = new ArrayList<Salesman>();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<Sale> sales = new ArrayList<Sale>();
		OutputFromReader outputFromReader = null;

		System.out.println("Reading info from file: ".concat(file.getName()));
		
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			while ((line = input.readLine()) != null) {
				partsOfLine = getPartsOfLine(line);
				typeOfLine = String.valueOf(partsOfLine[0].trim());
				switch (typeOfLine) {
				case Constants.SALESMAN_ID:
					Salesman salesman = getSalesmanObjectFromLine(partsOfLine);
					salesmen.add(salesman);
					break;
				case Constants.CUSTOMER_ID:
					Customer customer = getCustomerObjectFromLine(partsOfLine);
					customers.add(customer);
					break;
				case Constants.SALE_ID:
					Sale sale = getSaleObjectFromLine(partsOfLine);
					sales.add(sale);
					break;
				default:
					break;
				}
			}

			input.close();

			outputFromReader = new OutputFromReader(salesmen, customers, sales);

			return outputFromReader;

		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	private static String[] getPartsOfLine(String line){
		String[] partsOfLine = line.split("ç");
		return partsOfLine;
	}

	private static Salesman getSalesmanObjectFromLine(String[] partsOfLine) {
		Salesman salesman = new Salesman(String.valueOf(partsOfLine[1].trim()), String.valueOf(partsOfLine[2].trim()),
				Double.valueOf(partsOfLine[3].trim()));
		System.out.println(salesman.toString());

		return salesman;
	}

	private static Customer getCustomerObjectFromLine(String[] partsOfLine) {
		Customer customer = new Customer(String.valueOf(partsOfLine[1].trim()), String.valueOf(partsOfLine[2].trim()),
				String.valueOf(partsOfLine[3].trim()));
		System.out.println(customer.toString());
		
		return customer;
	}

	private static Sale getSaleObjectFromLine(String[] partsOfLine) {

		String[] itemsFromSale;
		String[] itemFromSaleDetails;
		String partOfSaleWithoutBrackets;

		ArrayList<SaleDetails> listOfsaleDetails = new ArrayList<SaleDetails>();
		Salesman salesmanThatMadeSale = new Salesman();
		partOfSaleWithoutBrackets = partsOfLine[2].trim().substring(1, partsOfLine[2].trim().length() - 2);
		itemsFromSale = partOfSaleWithoutBrackets.trim().split(",");
		for (String itemFromSale : itemsFromSale) {
			itemFromSaleDetails = itemFromSale.trim().split("-");
			SaleDetails saleDetails = new SaleDetails(String.valueOf(itemFromSaleDetails[0].trim()),
					Integer.valueOf(itemFromSaleDetails[1].trim()), Double.valueOf(itemFromSaleDetails[2].trim()));
			listOfsaleDetails.add(saleDetails);
		}

		salesmanThatMadeSale.setName(String.valueOf(partsOfLine[3].trim()));

		Sale sale = new Sale(String.valueOf(partsOfLine[1].trim()), listOfsaleDetails, salesmanThatMadeSale);

		System.out.println(sale.toString());
		
		return sale;
	}

}
