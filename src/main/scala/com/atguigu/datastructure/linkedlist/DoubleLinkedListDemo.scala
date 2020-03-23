package com.atguigu.datastructure.linkedlist

import util.control.Breaks._
object DoubleLinkedListDemo {
  def main(args: Array[String]): Unit = {
    val doubleLinkedList = new DoubleLinkedList()
    val node1 = new HeroNode2(1, "宋江", "及时雨")
    val node2 = new HeroNode2(2, "卢俊义", "玉麒麟")
    val node3 = new HeroNode2(3, "吴用", "智多星")
    val node4 = new HeroNode2(4, "张飞", "翼德")

    doubleLinkedList.add(node1)
    doubleLinkedList.add(node2)
    doubleLinkedList.add(node3)
    doubleLinkedList.add(node4)

    println("双向链表的情况")
    doubleLinkedList.list()

    val node5 = new HeroNode2(4, "公孙胜", "入云龙")
    doubleLinkedList.update(node5)
    println("双向链表的修改后情况")
    doubleLinkedList.list()

    //删除的测试
    doubleLinkedList.del(1)
    doubleLinkedList.del(2)


  }

}

class DoubleLinkedList{
  //创建头结点，指向该链表的头部
  val head = new HeroNode2(-1,"","")

  //删除
  def del(no : Int):Unit = {
    if (isEmpty()) {
      printf("链表为空")
      return
    }
    var temp = head.next
    var flag = false
    breakable {
      while (true) {
        if (temp.no == no) {
          flag = true
          break()
        }
        if (temp.next == null) {
          break()
        }
        temp = temp.next
      }
    }
    //当退出while循环后
    if (flag) {
      temp.pre.next = temp.next
      if (temp.next != null) {
        temp.next.pre = temp.pre
      }
    } else {
      printf("要删除的no=%d不存在", no)
    }
  }

  //添加英雄到单链表，默认直接将人加入到链表的最后
  def add(heroNode2: HeroNode2):Unit={
    var temp = head
    breakable{
      while(true){
        if(temp.next == null){
          break()//已经到链表的最后
        }
        temp = temp.next
      }
    }
    temp.next = heroNode2
    heroNode2.pre = temp
  }

  //遍历
  def list() : Unit = {
    if(isEmpty()){
      println("链表为空，无法遍历")
      return
    }
    var temp = head
    breakable{
      while(true){
        printf("no=%d name=%s nickname=%s --> ", temp.no, temp.name, temp.nickName)
        if(temp.next == null){
          break()
        }
        temp = temp.next
      }
    }
  }

  def update(heroNode2: HeroNode2):Unit = {
    if(isEmpty()){
      println("链表为空")
      return
    }
    var temp = head.next
    var flag = false
    breakable{
      while(true){
        if(temp.no == heroNode2.no){
          flag = true
          break()
        }
        if(temp.next == null){
          break()
        }
        temp = temp.next
      }
    }
    if(flag){
      temp.nickName = heroNode2.nickName
      temp.name = heroNode2.name
    } else {
      printf("你要修改的%d英雄不存在",heroNode2.no)
    }
  }

  def isEmpty(): Boolean ={
      head.next == null
  }

}

class HeroNode2(hNo:Int,hName:String,hNickname:String){
  val no = hNo
  var name = hName
  var nickName = hNickname
  var next :HeroNode2 = null //指向后一个节点
  var pre :HeroNode2 = null //指向前一个节点
}
