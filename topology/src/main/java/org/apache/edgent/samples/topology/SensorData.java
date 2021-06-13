import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.edgent.connectors.iot.QoS;
import org.apache.edgent.providers.direct.DirectProvider;
import org.apache.edgent.topology.TStream;
import org.apache.edgent.topology.Topology;
import org.apache.edgent.topology.json.JsonFunctions;


import java.util.concurrent.TimeUnit;
import com.google.gson.JsonObject;

public class SensorData {
	SensorData(){
        //rand = new Random();
    }
	public static void main(String[] args) {
		//TempSensor sensor = new TempSensor();
		Solarplant sensor = new Solarplant();
        //GpsSensor gsensor = new GpsSensor(latitude, longitude, altitude, speedMetersPerSec, time, course)
		DirectProvider dp = new DirectProvider();
        Topology topology = dp.newTopology();
        
		// Polling data from sensor once a minute
		TStream<Double> tempReadings = topology.poll(sensor, 1, TimeUnit.MINUTES).tag("read");
		
		// Execute anomaly detection and flag anomalous data
		/*
		 * TStream<Solarplant> analysedSensorData = sensorData.modify(sd -> { Boolean
		 * result = ad.isOutlier(sd.toAnalysisFormat()); sd.setAnomalous(result); return
		 * sd; }).tag("analyzed");
		 */
        TStream<Double> filteredReadings = tempReadings.filter(reading -> reading < 50 || reading > 80);
        TStream<JsonObject> events = filteredReadings.map(JsonFunctions.valueOfNumber("temp"));
        
        //filteredReadings.print();
        
        events.print();

        dp.submit(topology);

	}

}
