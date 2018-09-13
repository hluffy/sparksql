package com.dk.myspark

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.hadoop.hbase.util.Bytes

/**
  * spark操作Hbase
  * scala版本：2.12.6
  */
object SparkHbase {
    def main(args: Array[String]): Unit = {
        val c = new SparkConf().setMaster("local[*]").setAppName("spark hbase")
        val sc = new SparkContext(c)

        val conf = HBaseConfiguration.create()
        conf.set(TableInputFormat.INPUT_TABLE,"test")

        val rdd = sc.newAPIHadoopRDD(conf,classOf[TableInputFormat],classOf[ImmutableBytesWritable],classOf[Result])

//        rdd.foreach(x=> println(Bytes.toString(x._1.get())))
        rdd.foreachPartition(x => {
            x.foreach(x => println(Bytes.toString(x._1.get())))
        })

    }

}
