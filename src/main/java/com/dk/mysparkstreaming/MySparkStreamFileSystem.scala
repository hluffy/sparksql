package com.dk.mysparkstreaming

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * spark streaming监控文件系统
  */
object MySparkStreamFileSystem {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("sparkstreaming file stream")
        val ssc = new StreamingContext(conf,Seconds(1))


//        val lines = ssc.textFileStream("file:///Users/rikka/myfile/filestream")
//        val result = lines.flatMap(_.split(" ")).map(word=>(word,1)).reduceByKey(_+_)

        val lines = ssc.fileStream[LongWritable,Text,TextInputFormat]("file:///Users/rikka/myfile/filestream")
        val result = lines.flatMap(_._2.toString.split(" ")).map(word=>(word,1)).reduceByKey(_+_)
//
        result.print()


        ssc.start()
        ssc.awaitTermination()

    }

}
