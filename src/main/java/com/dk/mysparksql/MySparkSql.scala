package com.dk.mysparksql

import org.apache.spark.sql.SparkSession

/**
  * spark sql读取json文件
  * json文件作为数据源
  */
object MySparkSql {
    def main(args: Array[String]): Unit = {
        val spark = SparkSession.builder().master("local[*]").appName("spark sql").getOrCreate()

        val json = spark.read.json("file:///Users/rikka/IdeaProjects/sparksql/src/main/resources/test.json")

        json.createOrReplaceTempView("user")

        json.printSchema()

        val data = spark.sql("select id,name,age from user where age <15")

        data.show
    }

}
