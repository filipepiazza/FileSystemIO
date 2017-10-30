package fileAccess;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import conf.Configuration;

public class FileWriter {

	public static void writeResultsToOutputFile(String name, int numberOfCustomersInFile, int numberOfSalesmenInFile,
			String idOfMostExpensiveSale, String ifOfworstSalesmanEver) {

		BufferedWriter output;

		try {
			output = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(Configuration.OUTPUT_DIRECTORY_LOCATION.concat(name.replace(".dat", ".done.dat")))));
			output.write("Amount of clients in the input file:");
			output.write(String.valueOf(numberOfCustomersInFile));
			output.newLine();
			output.write("Amount of salesman in the input file:");
			output.write(String.valueOf(numberOfSalesmenInFile));
			output.newLine();
			output.write("ID of the most expensive sale:");
			output.write(idOfMostExpensiveSale);
			output.newLine();
			output.write("Worst salesman ever:");
			output.write(ifOfworstSalesmanEver);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
