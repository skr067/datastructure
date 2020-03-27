package com.atguigu.datastructure.recursion

//汉诺塔递归
object HanoiTower {
  def main(args: Array[String]): Unit = {
    hanoiTower(10,'A','B','C')
  }

  /**
    * 如果只有一个盘，A->C
    * 两个以上 最下面和上面两部分
    */

  def hanoiTower(nums:Int,a:Char,b:Char,c:Char): Unit ={
    if(nums == 1){
      println("第一个盘从"+a+"->"+c)
    } else {
      hanoiTower(nums-1,a,c,b)
      println("第"+nums+"个盘从" + a + "->" + c)
      hanoiTower(nums-1, b,a,c) // 将nums-1个盘，从 b->c, 中间借助 a
    }
  }

}
