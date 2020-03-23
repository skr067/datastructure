package com.atguigu.datastructure.linkedlist

import util.control.Breaks._
object SingleLinkedListDemo {
  def main(args: Array[String]): Unit = {
    val singleLinkedList = new SingleLinkedList
    //创建三个英雄
    val node1 = new HeroNode(1,"宋江","及时雨")
    val node2 = new HeroNode(2, "卢俊义", "玉麒麟")
    val node3 = new HeroNode(3, "吴用", "智多星")
    val node4 = new HeroNode(4, "张飞", "翼德")

    singleLinkedList.addByOrder(node4)
    singleLinkedList.addByOrder(node3)
    singleLinkedList.addByOrder(node1)
    singleLinkedList.addByOrder(node2)

    println("链表的情况是")
    singleLinkedList.list()

    //对接点进行更改
    val node5 = new HeroNode(4,"公孙胜","入云龙")
    singleLinkedList.update(node5)
    printf("链表修改后的情况是\n\n")
    singleLinkedList.list()




  }
}

//创建单链表的类
class SingleLinkedList {
  //创建头结点，指向该链表的头部
  val head = new HeroNode(-1,"","")

  //遍历单向链表
  //1.仍然让temp帮助进行遍历
  //2.判断链表是否空，空退出，不为空就遍历，直到最后结点
  def list():Unit = {
    if (isEmpty()){
      println("链表为空，无法遍历")
      return
    }
    //有效数据不包括head
    var temp = head.next
    breakable {
      while (true) {
        //输出当前这个结点的信息
        printf("no=%d name=%s nickname=%s --> ", temp.no, temp.name, temp.nickname)
        if (temp.next == null) {
          println("最后的节点")
          break
        }
        temp = temp.next
      }
    }
  }

  //修改节点信息
  def update(heroNode: HeroNode):Unit = {
    //如果链表为空
    if(isEmpty()){
      println("链表为空")
      return
    }
    //辅助指针，帮助我们定位到修改的节点
    var temp = head.next
    //定义变量，标识是否找到该节点
    var flag = false
    breakable {
      while (true) {
        //比较
        if (temp.no == heroNode.no) {
          flag = true
          break()
        }
        //判断temp是否到最后
        if (temp.next == null) {
          break()
        }
        //后推
        temp = temp.next
      }
    }
    if(flag){
      temp.nickname = heroNode.nickname
      temp.name = heroNode.name
    } else {
      printf("你要修改的%d 英雄不存在",heroNode.no)
    }
  }

  //新增一个节点
  def add(heroNode: HeroNode):Unit={
    //先找到最后结点
    //最后结点.next = 新的节点
    var temp = head
    breakable {
      while (true) {
        if (temp.next == null) {
          //链表的最后
          break()
        }
        temp = temp.next
      }
    }
    temp.next = heroNode
  }

  //按照no编号的从小到大进行插入
  def addByOrder(heroNode: HeroNode):Unit = {
    var temp = head
    var flag = false//标识是否已存在这个编号的节点
    breakable{
      while(true){
        if(temp.next == null){
          //最后一个
          break()
        }
        if(temp.next.no == heroNode.no){
          //说明no已经存在
          flag = true
          break()
        } else if(temp.next.no > heroNode.no){
          break()
        }
        //将temp后移，实现遍历
        temp = temp.next


      }
    }
    if(flag){
      printf("已经存在no为%d的人物",heroNode.no)
    } else{
      heroNode.next = temp.next
      temp.next = heroNode
    }
  }


  //删除一个节点
  def del(no : Int):Unit = {
    if(isEmpty()){
      println("链表为空")
      return
    }
    var temp = head
    var flag = false
    //遍历，指向要删除节点的前一个节点
    breakable{
      while(true){
        if(temp.next.no == no){
          flag = true
          break()
        }
        if(temp.next.next == null){
          break()
        }

        //让temp后移，实现遍历
        temp = temp.next
      }
    }
    if(flag){
      temp.next = temp.next.next
    } else {
      printf("你要删除的节点%d 不存在",no)
    }
  }

  def isEmpty():Boolean = {
    head.next == null
  }
}

class HeroNode(hNo : Int,hName : String,hNickname : String){
  val no = hNo
  var name = hName
  var nickname = hNickname
  var next : HeroNode = null//默认为null

}
