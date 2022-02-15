package example;

import java.util.List;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


public class SecondSimpleApp {

	public static void main(String[] args) throws AnalysisException {
		SparkSession spark = SparkSession
				.builder()
				.appName("Java Spark SQL basic example")
				.config("spark.master", "local")
				.getOrCreate();

		String path = "src/main/resources/joomlatools__joomla-platform-categories.tsv";
		//String path = "src/main/resources/try.tsv";

		Encoder<MonthlyActivityRecord> dataEncoder = Encoders.bean(MonthlyActivityRecord.class);
		//String schemaString = "Month SchActivity PrjActivity cumulPtime cumulSchActivity cumulPrjActivity";
		Dataset<MonthlyActivityRecord> dataset = spark.read()
				.option("delimiter", "\t")
				.option("header", "true")
				.option("inferSchema","true")
				//.schema(dataEncoder.schema())
				//.schema(schemaString)
				//see also https://stackoverflow.com/questions/62664789/spark-streaming-convert-datasetrow-to-datasetcustomobject-in-java
				.csv(path).as(dataEncoder);

		dataset.printSchema();
		dataset.show((int)dataset.count(),false);


		List<MonthlyActivityRecord> dataAsList = dataset.collectAsList();
		for(MonthlyActivityRecord r: dataAsList) {
			System.out.println(r.toString());
		}

		  dataset.createOrReplaceTempView("joomlatoolsPlatformCategories");
		  //Dataset<Row> firstQueryResult = spark.sql("SELECT * FROM joomlatoolsPlatformCategories WHERE MONTH = 2");
		  Dataset<Row> firstQueryResult = spark.sql("SELECT SchActivity, count(*) as cnt FROM joomlatoolsPlatformCategories GROUP BY SchActivity");
		  System.out.println("\n\n\n   QUERY RESULT OEO    \n");
		  List<Row> resultList = firstQueryResult.sort("SchActivity", "cnt").collectAsList();
		  for(Row r: resultList) {
			  System.out.println( r.toString());
		  }

		  spark.stop();
	}//end main

}
