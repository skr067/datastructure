package com.atguigu.datastructure.queue

import scala.io.StdIn

object CircleArrayDemo {
  def main(args: Array[String]): Unit = {
    val queue = new ArrayQueue2(4)
    //菜单演示
    var key = ""
    while(true) {
      println()
      println("请选择菜单")
      println("show: 显示队列")
      println("add : 添加数据")
      println("get : 取出数据")
      println("peek : 获取数据")
      println("exit: 退出程序")
      key = StdIn.readLine()
      key match {
        case "show" => queue.show()
        case "add" => {
          println("请输入一个数")
          val num = StdIn.readInt()
          queue.addQueue(num)
        }
        case "get" => {
          val res = queue.getQueue()
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception].getMessage)
          } else{
            println("队列取出的值=%d", res)
          }
        }
        case "peek" =>{
          //查看头元素值，进行判断
          val res = queue.peek()
          //如果是异常
          if(res.isInstanceOf[Exception]) {
            println(res.asInstanceOf[Exception].getMessage)
          } else {//Int
            printf("队列当前头元素=%d", res)
          }
        }

      }
    }

  }

}

//编写一个数据结构的基本思路：创建-遍历-修改-删除
class ArrayQueue2(arrMaxSize : Int) {
  val maxSize = arrMaxSize//指定队列大小
  val arr = new Array[Int](maxSize)//队列中的数据，存放数组
  //front初始化为0,表示队列的头，但是约定不包含头元素，指向前一个位置
  var front = 0
  //rear初始化0，指向队列尾部，包含最后这个元素
  var rear = 0

  //判断队列满
  def isFull():Boolean = {
    front == (rear+1)%maxSize
  }

  //判断队列空
  def isEmpty():Boolean = {
    rear == front
  }

  //查看队列的头元素，但是不取出
  def peek():Any = {
    if(isEmpty()){
      return new Exception("队列空，无数据")
    }
    return arr(front)
  }

  //向队列中添加数据
  def addQueue(num : Int):Unit = {
    if(isFull()){
      println("队列满，不能加入")
      return
    }
    arr(rear) = num
    //将rear后移
    rear = (rear + 1)%maxSize
  }

  //遍历显示队列
  def show() : Unit = {
    if (isEmpty()){
      println("队列空")
      return
    }
    //遍历
    println("队列数据情况是：")

    for(i <- front to front+size()){
      printf("arr(%d)=%d \t",(i%maxSize),arr(i%maxSize))
    }
  }

  //统计当前有多少个元素
  def size():Int = {
    return (rear + maxSize - front)%maxSize
  }

  //从队列中取出数据，可能取得数据，可能取不到数据
  def getQueue(): Any = {
    if(isEmpty()){
      return new Exception("队列空，没有数据")
    }
    //front指向队列中的第一个元素
    val res =arr(front)//保存到临时变量
    front = (front +1)%maxSize//将front后移
    return res
  }

}
