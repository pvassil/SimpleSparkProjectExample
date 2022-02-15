package example;


import java.util.List;


import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.AnalysisException;

/**
 * Tailoring stuff from 
 *     https://github.com/apache/spark/blob/master/examples/src/main/java/org/apache/spark/examples/sql/JavaSparkSQLExample.java
 *     
 * @author pvassil
 *
 */
public class FirstSimpleApp {

	  public static void main(String[] args) throws AnalysisException {
		  SparkSession spark = SparkSession
				  .builder()
				  .appName("Java Spark SQL basic example")
				  .config("spark.master", "local")
				  //.config("spark.some.config.option", "some-value")
				  .getOrCreate();

		  System.out.println("\n\n\n   HERE WE GO HERE WE GO HERE WE GO     \n");
		  
		  String path = "src/main/resources/joomlatools__joomla-platform-categories.tsv";

		  //See https://spark.apache.org/docs/latest/sql-data-sources-csv.html
		  //read the file with the appropriate delimiter: here it is tab
		  //also treat the first row as header too
		  Dataset<Row> df = spark.read().option("delimiter", "\t").option("header", "true").csv(path);
		  df.show();
		  
		  System.out.println("\n\n\n   SHOW ALL     \n");
		  df.show((int)df.count(), false);
		  
		  // Print the schema in a tree format
		  df.printSchema();
		  
		  // ////////////// HOW TO QUERY ////////////////////
		  //Create a temporary alias for the data set $df
		  df.createOrReplaceTempView("joomlatoolsPlatformCategories");
		  //Global would be...
		  //df.createGlobalTempView("joomlatoolsPlatformCategories");
		  
		  //pose a query dear boy
		  Dataset<Row> firstQueryResult = spark.sql("SELECT * FROM joomlatoolsPlatformCategories");
		  System.out.println("\n\n\n   QUERY RESULT     \n");
		  firstQueryResult.show();

		  System.out.println("\n\n\n   QUERY RESULT ALL    \n");
		  List<Row> resultList = firstQueryResult.collectAsList();
		  for(Row r: resultList) {
			  //all data types for the file are considered to be Strings anyway
			  //but getAs() does not know it => cast toString()
			  System.out.println(r.getAs(4).toString());
			  System.out.println( r.toString());
		  }
		  


		    spark.stop();
		  }

}
