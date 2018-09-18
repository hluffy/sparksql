package com.dk.mysparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object MySparkStreamWordCount {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("network wordcount")
        val ssc = new StreamingContext(conf,Seconds(1))

        val lines = ssc.socketTextStream("localhost",19999)

        val result = lines.flatMap(_.split(" ")).map(word=>(word,1)).reduceByKey(_+_)

        result.print()

        ssc.start()
        ssc.awaitTermination()
    }

}
