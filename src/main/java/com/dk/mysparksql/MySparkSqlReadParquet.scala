package com.dk.mysparksql

import org.apache.spark.sql.SparkSession

/**
  * saprk sql读取parquet文件
  * parquet文件作为数据源
  */
object MySparkSqlReadParquet {
    def main(args: Array[String]): Unit = {
        val spark = SparkSession.builder().master("local[*]").appName("spark sql read parquet").getOrCreate()

//        将json文件保存为parquet文件
//        val json = spark.read.json("file:///Users/rikka/IdeaProjects/sparksql/src/main/resources/test.json")
//        json.write.parquet("file:///Users/rikka/IdeaProjects/sparksql/src/main/resources/test.parquet")

        val parquet = spark.read.parquet("file:///Users/rikka/IdeaProjects/sparksql/src/main/resources/test.parquet")
        parquet.createOrReplaceTempView("user1")

        val data = spark.sql("select * from user1")
//        data.show()
        data.foreach(x=>println(x(2)))

    }

}
