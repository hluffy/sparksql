package com.dk.mysparkgraphx

import org.apache.spark.graphx.{Edge, Graph}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * spark GraphX
  */
object MySparkGraphX {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[*]").setAppName("my spark graphx")
        val sc = new SparkContext(conf)

//        定义顶点
        val users = sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")),
            (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))

//        定义边
        val relationships = sc.parallelize(Array(Edge(3L, 7L, "collab"),    Edge(5L, 3L, "advisor"),
            Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))


        val defaultUser = ("John Doe", "Missing")

//        初始化图
        val graph = Graph(users,relationships,defaultUser)


//        图操作
//        统计顶点数据pos等于postdoc的个数
        val count = graph.vertices.filter({case (id,(name,pos))=> pos == "postdoc"}).count()
        println("-----------------count:"+count)

//        统计边数据起始顶点id大于目的顶点id的个数
//        val ecount = graph.edges.filter(e=>e.srcId>e.dstId).count()
        val ecount = graph.edges.filter({case Edge(src,dst,prop) => src > dst}).count
        println("-----------------ecount:"+ecount)


//        遍历triplet
        val facts = graph.triplets.map(triplet=>triplet.srcAttr._1 + " is the " + triplet.attr + " of " + triplet.dstAttr._1)
        facts.collect().foreach(println(_))

//        图中所有顶点的下行程度
        val inDegrees = graph.inDegrees
//        图中所有顶点的下行程度
        val outDegress = graph.outDegrees

        println("-------------------inDegrees----------------")
        inDegrees.collect().foreach(println(_))
        println("-------------------outDegrees----------------")
        outDegress.collect().foreach(println(_))

    }

}
