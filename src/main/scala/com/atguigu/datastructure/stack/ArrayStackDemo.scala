package com.atguigu.datastructure.stack

import scala.io.StdIn

object ArrayStackDemo {
  def main(args: Array[String]): Unit = {
    val stack = new ArrayStack(4)
    var key = ""
    while(true){
      println()
      println("push: 入栈操作")
      println("list: 遍历栈")
      println("pop: 出栈操作")
      println("peek: 查看栈顶")
      key = StdIn.readLine()
      key match {
        case "push" =>
          println("请输入一个数")
          val num = StdIn.readInt()
          stack.push(num)
        case "list" =>
          stack.list()
        case "pop" =>
          val res = stack.pop()
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception].getMessage())
          } else {
            println()
            printf("取出栈顶的值=%d",res)
          }
        case "peek" =>
          val res = stack.peek()
          if(res.isInstanceOf[Exception]) {
            println(res.asInstanceOf[Exception].getMessage)
          }else{
            println()
            printf("栈顶的值=%d", res)
          }
      }
    }

  }

}

class ArrayStack(arrMaxSize:Int){
  val maxSize = arrMaxSize

  //创建数组
  val arr = new Array[Int](maxSize)
  //栈顶
  var top = -1
  //判断栈满
  def isFull():Boolean = {
    top == maxSize - 1
  }
  //判断栈空
  def isEmpty():Boolean = {
    top == -1
  }

  //遍历
  def list():Unit = {
    if(isEmpty()){
      println("栈空，不能遍历")
      return
    }
    //逆序遍历
    for(i <- 0 to top reverse) {
      printf("arr(%d)=%d\n", i, arr(i))
    }
  }

  //入栈
  def push(num :Int): Unit ={
    if(isFull()){
      println("栈满，不能入栈")
      return
    }
    top += 1
    arr(top) = num
  }

  //出栈
  def pop(): Any ={
    if(isEmpty()){
      return new Exception("栈满")
    }
    val res = arr(top)
    top -= 1
    return res
  }

  //查看栈顶的值
  def peek(): Any ={
    if(isEmpty()){
      return new Exception("栈空")
    }
    return arr(top)

  }

}
