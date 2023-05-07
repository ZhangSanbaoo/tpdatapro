# Practices - Data engineering

## TP - Data processing with Apache Spark
To process a large amount of data partitioned on a data lake, you can use data processing frameworks such as Apache Spark :
1. Read : https://spark.apache.org/docs/latest/sql-programming-guide.html

Some questions :
* What is Spark RDD API ?

The Spark RDD API is the original programming interface provided by Spark for working with distributed datasets.The RDD API is a strongly typed API that allows users to create, manipulate, and store datasets distributed in memory.The RDD API can handle complex data manipulation, including operations such as data filtering, transformation, and aggregation, but lacks optimization and type safety features.

* What is Spark Dataset API ?

Spark Dataset API for working with structured data The Dataset API is a strongly typed API that allows users to create, manipulate, and distribute datasets in memory The Dataset API draws on the distributed nature of the RDD API and the structured nature of the DataFrame API to provide better type safety and optimization support. Operations supported by the Dataset API include filtering, grouping, aggregation, and joins.

* With which languages can you use Spark ? 

Spark can use a variety of programming languages, including Java, Scala, Python, and R. Among them, Java and Scala are the most commonly used programming languages.

* Which data sources or data sinks can Spark work with ? 

Spark can be integrated with a variety of data sources and data repositories, including Hadoop Distributed File System, Apache Cassandra, Apache HBase, Apache Hive, and Amazon S3, among others. Spark also supports exporting data to multiple file formats, such as CSV, JSON, and Parquet.


### Analyse data with Apache Spark and Scala 
One engineering team of your company created for you a TV News data stored as JSON inside the folder `data-news-json/`.

Your goal is to analyze it with your savoir-faire, enrich it with metadata, and store it as [a column-oriented format](https://parquet.apache.org/).

1. Look at `src/main/scala/com/github/polomarcus/main/Main.scala` and update the code 

**Important note:** As you work for a top-notch software company following world-class practices, and you care about your project quality, you'll write a test for every function you write.

You can see tests inside `src/test/scala/` and run them with `sbt test`

### How can you deploy your app to a cluster of machines ?
* https://spark.apache.org/docs/latest/cluster-overview.html

Prepare the code and required dependencies for the Spark application and package them in an executable format such as a Jar file or a Python script.
Configure the Spark cluster's runtime environment, including the address of the Spark master node, the number of worker nodes, and resource allocation. These configurations can be configured through the configuration file provided by Spark or through command line arguments when starting the application.
Upload the packaged application files to the cluster and start the application. This can be done using the command-line tools or programming interfaces provided by Spark, such as the spark-submit command or the Spark programming interface.
Once the application is started, Spark will automatically assign tasks to worker nodes for computation. When the computation is complete, Spark will return the results to the master node and write the results to disk or other external storage media if needed.

### Business Intelligence (BI)
How could use we Spark to display data on a BI tool such as [Metabase](https://www.metabase.com/) ?

Tips: https://github.com/polomarcus/television-news-analyser#spin-up-1-postgres-metabase-nginxand-load-data-to-pg

### Continuous build and test
**Pro Tips** : https://www.scala-sbt.org/1.x/docs/Running.html#Continuous+build+and+test

Make a command run when one or more source files change by prefixing the command with ~. For example, in sbt shell try:
```bash
sbt
> ~ testQuick
```