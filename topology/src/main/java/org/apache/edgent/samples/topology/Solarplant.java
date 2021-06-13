import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.edgent.function.Supplier;

import org.apache.edgent.function.Supplier;

public class Solarplant  implements Supplier<Double> {
	double currentTemp = 0.0;
	long counter = 0;
	private  String DATASET_URL = "https://raw.githubusercontent.com/stritti/thermal-solar-plant-dataset/master/data/2018/06/20180629.csv";
	Solarplant(){
        //rand = new Random();
    }
	@Override
	public Double get(){
		try (InputStream inputStream = new BufferedInputStream(new URL(DATASET_URL).openStream())) 
		{
			List<CSVRecord> records =
	                CSVParser.parse(inputStream, Charset.defaultCharset(), CSVFormat.TDF.withFirstRecordAsHeader().withTrim()).getRecords();


	        for (CSVRecord record : records) {
				System.out.println(parseDouble(record));
				if (record.getRecordNumber()== counter) {
					//double newTemp = rand.nextGaussian() + currentTemp;
					double newTemp = Double.parseDouble(record.getParser().toString());
					currentTemp = newTemp;
					counter++;
					return currentTemp;
				}
	        }
	     } 
		 catch (Exception e) {
	            System.out.println("Error reading data set");
	            e.printStackTrace();
	     }
		return null;
		
	}
	private double parseDouble(CSVRecord record) {
	        return Double.parseDouble(record.get(1).replace(',', '.'));
	}
}
