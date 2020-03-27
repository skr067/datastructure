package com.atguigu.datastructure.hashtable

import util.control.Breaks._
object HashTableDemo {
  def main(args: Array[String]): Unit = {
    val hashTable = new HashTable(5)
    val emp1 = new Emp(5,"aa")
    val emp2 = new Emp(15,"aa")
    val emp3 = new Emp(2,"aa")
    val emp4 = new Emp(3,"aa")
    val emp5 = new Emp(4,"aa")
    hashTable.addEmp(emp1)
    hashTable.addEmp(emp2)
    hashTable.addEmp(emp3)
    hashTable.addEmp(emp4)
    hashTable.addEmp(emp5)
    hashTable.list()

    hashTable.findEmpById(11)

  }
}

class HashTable(size : Int){
  val empLinkedListArr = new Array[EmpLinkedList](size)
  for (i <- 0 until size){
    empLinkedListArr(i) = new EmpLinkedList()
  }
  //遍历雇员时
  def list():Unit = {
    for (i <- 0 until size){
      empLinkedListArr(i).list(i)
    }
  }
  //查找一个雇员时
  def findEmpById(no : Int): Unit = {
    val empLinkedListNo = hash(no)
    val emp = empLinkedListArr(empLinkedListNo).findEmpByNo(no)
    if(emp!=null){
      println()
      printf("找到no=%d name=%s",no,emp.name)
    } else{
      println()
      println("没找到雇员="+no)
    }
  }

   //添加雇员
  def addEmp(emp : Emp): Unit ={
    val empLinkedListNo = hash(emp.no)
    empLinkedListArr(empLinkedListNo).addEmp(emp)
  }

  //编写散列函数
  def hash(no :Int):Int = {
    no%size
  }

}

class Emp(empNo : Int,eName : String){
  val no = empNo
  var name = eName
  var next : Emp = null
}

//存放雇员单链表
class EmpLinkedList{
  var head : Emp = null

  //遍历
  def list(no : Int) : Unit = {
    if(head == null){
      println()
      println("第"+no+"链表情况")
      return
    }
    //使用辅助指针
    var curEmp = head
    println()
    println("第"+no+"链表情况")
    breakable {
      while (true) {
        if (curEmp.next == null) {
          break()
        }
        curEmp = curEmp.next
      }
    }
  }

  //添加雇员
  def addEmp(emp : Emp): Unit ={
    if(head == null){
      head = emp
    } else {
      var curEmp = head
      breakable {
        while (true) {
          if (curEmp.next == null) {
            break()
          }
          curEmp = curEmp.next
        }
      }
      curEmp.next = emp
    }
  }

  //查询雇员
  def findEmpByNo(no : Int):Emp = {
    if(head == null){
      return null
    }
    var curEmp = head
    breakable{
      while(true){
        if(curEmp.no == no){
          break()
        }
        if(curEmp.next == null){
          curEmp = null
          break()
        }
        curEmp = curEmp.next
      }
    }
    curEmp
  }

}