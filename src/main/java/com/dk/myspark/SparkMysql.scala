package com.dk.myspark

import java.sql.{DriverManager, ResultSet}

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * spark访问本地mysql数据库
  */
object SparkMysql {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("spark mysql")
        val sc = new SparkContext(conf)

        val data = new JdbcRDD(sc,createConnect,"select * from city where code > ? and code < ?",lowerBound = 1,upperBound = 10000,numPartitions = 2,mapRow = extractValues)
        println(data.collect.toList)

    }

    def createConnect() = {
        Class.forName("com.mysql.jdbc.Driver")
        DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","hanxiao")
    }

    def extractValues(r:ResultSet) = {
        (r.getLong("code"),r.getNString("name"),r.getLong("countrycode"))
    }

}
