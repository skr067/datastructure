package com.atguigu.datastructure.recursion

//迷宫，递归
object MiGong {
  def main(args: Array[String]): Unit = {
    val map = Array.ofDim[Int](8,7)
    //上下是墙
    for(i <- 0 until 7 ){
      map(0)(i) = 1
      map(7)(i) = 1
    }
    //左右是墙
    for(i <- 0 until 8){
      map(i)(0) = 1
      map(i)(6) = 1
    }
    //墙
    map(3)(1) = 1
    map(3)(2) = 1
    map(2)(2) = 1
    println("迷宫的情况是：")
    for(row <- map){
      for(item <- row){
        printf("%d ",item)
      }
      println()
    }

    setWay(map,1,1)

    println("小球递归回溯后迷宫的情况")
    for(row <- map) {
      for(item<-row) {
        printf("%d ", item)
      }
      println()
    }

  }


  //找路 i 横坐标  j 纵坐标
  def setWay(map : Array[Array[Int]],i : Int,j : Int): Boolean = {
    //策略：下右上左
    if(map(i)(j) == 0){
      map(i)(j) = 2
      if(setWay(map,i+1,j)){//下
        return true
      } else if(setWay(map,i,j+1)){
        return true
      } else if(setWay(map,i-1,j)){
        return true
      } else if(setWay(map,i,j-1)){
        return true
      } else {
        //四个方向都无法到达终点
        map(i)(j) = 3
        return false
      }
    } else {
      return false
    }
  }

}
/*
 *0表示没有走过
 * 1表示墙
 * 2表示经过探测，是通路
 * 3经过探测，是死路
 */


