package com.atguigu.datastructure.linkedlist

import util.control.Breaks._
//环形单向链表：约瑟夫问题
object JosephuDemo {
  def main(args: Array[String]): Unit = {
    val josephu = new Josephu
    josephu.addBoy(5)
    josephu.list()

    josephu.countBoy(2,2,5)
  }

}

class Josephu{
  //定义一个初始first
  var first : Boy = null


  //小孩出圈
  def countBoy2(startNo:Int,countNum:Int,boyNums:Int): Unit ={
    //做下数据的验证
    if(first == null || startNo > boyNums || startNo <= 0){
      println("输出参数有误，不能玩游戏")
      return
    }
    //创建辅助指针
    var helper = first
    //helper移动到first的上一个
    breakable{
      while(true){
        if(helper.next == first){
          break()
        }
        helper = helper.next
      }
    }
    //移动位置
    for(j <- 0 until startNo-1){
      helper = helper.next
    }
    breakable{
      while(true){
        for(i <- 0 until countNum - 1){
          helper = helper.next
        }
        //first指向了要删除的小孩
        printf("小孩no=%d出圈\n",helper.next.no)
        helper.next = helper.next.next

      }
      //判断是否是最后一个孩子
      if(helper == helper.next){
        break()
      }
    }
    printf("最后留在圈中的小孩是no=%d\n",helper.no)
  }

  //小孩出圈
  def countBoy(startNo:Int,countNum:Int,boyNums:Int): Unit ={
    //做下数据的验证
    if(first == null || startNo > boyNums || startNo <= 0){
      println("输出参数有误，不能玩游戏")
      return
    }
    //创建辅助指针
    var helper = first
    //helper移动到first的上一个
    breakable{
      while(true){
        if(helper.next == first){
          break()
        }
        helper = helper.next
      }
    }
    //移动位置
    for(j <- 0 until startNo-1){
      first = first.next
      helper = helper.next
    }
    breakable{
      while(true){
        for(i <- 0 until countNum - 1){
          first = first.next
          helper = helper.next
        }
        //first指向了要删除的小孩
        printf("小孩no=%d出圈\n",first.no)
        first = first.next
        helper.next = first
      }
      //判断是否是最后一个孩子
      if(first == helper){
        break()
      }
    }
    printf("最后留在圈中的小孩是no=%d\n",first.no)
  }

  //添加小孩，形成环形链表
  def addBoy(boyNums : Int):Unit = {
    var curBoy : Boy = null
    for(i <- 1 to boyNums){
      //创建小孩对象
      val boy = new Boy(i)
      //处理第一个小孩，就形成环形
      if(i==1){
        first = boy
        curBoy = boy
        first.next = first
      } else {
        curBoy.next = boy
        boy.next = first
        curBoy = boy
      }
    }
  }

  //遍历环形链表
  def list() : Unit = {
    //判断链表是否为空
    if(first == null ){
      printf("没有小孩")
      return
    }
    var curBoy = first
    breakable{
      while(true){
        printf("no=%d\n",curBoy.no)
        if(curBoy.next == first){
          break()
        }
        curBoy = curBoy.next
      }
    }
  }


}

//小孩类
class Boy(bNo :Int){
  val no = bNo
  var next:Boy = null
}
