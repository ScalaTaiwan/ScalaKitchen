<script>
  ((window.gitter = {}).chat = {}).options = {
    room: 'ScalaTaiwan/ScalaTaiwan'
  };
</script>
<script src="https://sidecar.gitter.im/dist/sidecar.v1.js" async defer></script>

# 遞迴

在functional programming世界中是不准許有mutable變數，意味著我們熟悉的 `while`, `for` 迴圈都是禁止使用。
那我們應該怎麼辦呢？ 這邊會舉個數個例子，來解釋是透過遞迴方式來完成 `while` 或 `for` 迴圈。

## 從一個串列中找出最大值
如果在 `Java` 我們會怎麼做呢？
1. 宣個一個變數(max)存放目前的最大值
2. 透過`while` 或 `for` 迴圈，將串列中的每個元素跟我們宣告的變數(max)比大小
  2.1 若元素的值大於max，max的值就改為此元素的值
  2.2 若元素的值小於或等於max，不做任何事
   
```scalaFiddle
var max = Int.MinValue

for(i <- List(1, 2, 3, 4, 5)){
  if(i > max)
    max = i
}

println(max)
```

透過遞迴方式我們會怎麼解決呢？

```scalaFiddle
val max = findMax(List(1, 2, 3, 4, 5))

def findMax(list: List[Int]): Int = {
   list match{
     // 假如是空的串列，我們回傳0
     case Nil => 0
     // 假如只有一個元素的串列，那最大值就為該元素
     case h :: Nil => h
     
     // 主要邏輯
     case h :: t => Math.max(h, findMax(t))
   }
}

println(max)
```
* 在迴圈版本中，逐一每個元素跟某一個mutable變數比大小。
* 在遞迴版本中，第一個元素跟去除第一個元素的串列的最大值比大小。

遞迴的寫法更是簡潔和表達出整個程式主要邏輯，我們再來多試看看幾個題目。


## 從一個串列中找出最小值
做法跟找出最大值類似，第一個元素跟去除第一個元素的串列的最小值比大小。

```scalaFiddle
val min = findMin(List(1, 2, 3, 4, 5))

def findMin(list: List[Int]): Int = {
   list match{
     // 假如是空的串列，我們回傳0
     case Nil => 0
     // 假如只有一個元素的串列，那最小值就為該元素
     case h :: Nil => h
     
     // 主要邏輯
     case h :: t => Math.min(h, findMin(t))
   }
}

println(min)
```


## 檢查一個串列中是否存在某個特定值
檢查第一個元素是否等於目標值或是去除第一個元素的串列有目標值

```scalaFiddle
val target = find(List(1, 2, 3, 4, 5), 5)

def find(list: List[Int], target: Int): Boolean = {
   list match{
     // 假如是空的串列，回傳false
     case Nil => false
     // 假如只有一個元素的串列，而且該元素等於target，回傳true
     case h :: Nil if (h == target) => true
     
     // 主要邏輯
     case h :: t => (h == target) || find(t, target)
   }
}

println(target)
```

## 反轉一個字串
先反轉去除第一個字元的串列，之後再將第一字元放在最後面

```scalaFiddle
val result = reverse("I should learn scala seriously!".toList)

def reverse(list: List[Char]): String = {
  list match {
    // 假如是空的串列，回傳false
    case Nil => ""
    // 假如只有一個元素的串列，回傳該元素
    case h :: Nil => h.toString

    // 主要邏輯
    case h :: t => reverse(t) + h
  }
}

println(result)
```

## 判斷一個字串是否為另外一個字串的子字串
子字串的定義：字數，內容和順序需要符合，空字串為任何字串的子字串
例如：
* "abc" 為 "XXXYYYabc"的子字串
* "XXXY" 為 "XXXYYYabc"的子字串
* "" 為 "XXXYYYabc"的子字串
* "ABC" 不為 "XXXYYYabc"的子字串
* "QQWW" 不為 "XXXYYYabc"的子字串
* "XXXaYYY" 不為 "XXXYYYabc"的子字串

先比較主要字串和子字串的第一個字母是否一樣，
* 若一樣則這兩個字串去除第一個字母繼續比
* 若不一樣，主要字串去除第一個字母和子字串繼續比

例如：
主要字串："abcdefghi"
子字串: "abc"
檢查主要字串前三個字母（abc）是否和子字串（abc）一樣，若一樣就回傳`true`

主要字串："abcdefghi"
子字串: "defgh"
檢查主要字串前五個字母（abcde）是否和子字串（defgh）一樣，若不一樣，
則 "bcdefghi" 和 "defgh" 繼續比

```scalaFiddle
println(isSubString("I should learn scala seriously!".toList, "scala".toList))
println(isSubString("I should learn scala seriously!".toList, "XXOO".toList))
println(isSubString("I should learn scala seriously!".toList, "scaxxyyla".toList))

def isSubString(original: List[Char], target: List[Char]): Boolean = {
  (original, target) match {
    case (Nil, _) => false
    case (_, Nil) => true
    case (o :: Nil, t :: Nil) if (o == t) => true
    case (o :: Nil, t :: Nil) if (o != t) => false
    case (o, t) if (o.take(t.length) == t) => true
    case (oh :: ot, t) => isSubString(ot, t)
  }
}
```

## 將一個串列由小排到大
我們來實作[Quicksort](https://zh.wikipedia.org/wiki/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F)

1. 從數列中挑出一個元素，稱為"基準"（pivot）。
2. 重新排序數列，所有比基準值小的元素擺放在基準前面，所有比基準值大的元素擺在基準後面（相同的數可以到任一邊）。在這個分割結束之後，該基準就處於數列的中間位置。這個稱為分割（partition）操作。
3. 遞迴地（recursively）把小於基準值元素的子數列和大於基準值元素的子數列排序。

```scalaFiddle

val sortResult = quicksort(List(4, 3, 2, 9, 10))

def quicksort(list: List[Int]): List[Int] = {
  list match {
    case Nil => Nil
    case h :: Nil => List(h)
    case h :: t => quicksort(t.filter(_ <= h)) ::: h :: quicksort(t.filter(_ > h))
  }
}

println(sortResult)
```

## 寫出費氏數列
何謂費氏數列：[費氏數列](https://zh.wikipedia.org/wiki/%E6%96%90%E6%B3%A2%E9%82%A3%E5%A5%91%E6%95%B0%E5%88%97)

```scalaFiddle
val result = fib(6)

def fib(n: Int): Int = {
  n match {
    case 0 => 0
    case 1 => 1
    case _ => fib(n - 1) + fib(n - 2)
  }
}

println(result)
```

經過幾次的練習，我們應該大概可以慢慢掌握到怎麼運用遞迴來達到迴圈目的。
1. 先把主要邏輯寫出來
2. 再把邊界條件設好，沒有邊界條件就會無窮的跑下去...
遞迴主要是透過自我呼叫，把每一步的狀態放在 `stack`，等走到邊界回傳邊界值或完成邊界條件後，再回溯回去。

把問題透過[Divide and conquer](https://zh.wikipedia.org/wiki/%E5%88%86%E6%B2%BB%E6%B3%95)方式，
大問題分解成數個小問題，若小問題規模較小且易於解決時，則直接解。否則，遞歸地解決各小問題。
最後再將每個小問題的結果逐一合併起來。

遞迴的寫法很簡潔，但是最大的問題是效能不好和容易 `Stack Overflow`。
主要原因是會去嘗試走所有可能的路徑而且把每一個狀態放在stack，當狀態一多就爆了。
去嘗試走所有可能的路徑也是造成效能不好的原因。

假如：
1. 去嘗試走所有可能的路徑不多，但是每一個步計算很花時間，遞迴容易轉換成非同步程式。

以排序為例，一個串列數量小於一千，我們可以使用[Insertion Sort](https://zh.wikipedia.org/wiki/%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F)；大於一千改使用Quicksort：

```scala
def sort(list: List[Int]): List[Int] = {
  if(n < 1000){
    // 需要大量時間計算才可以得出結果
    insertionSort(list)
  }
  else{
    list match {
      case Nil => Nil
      case h :: Nil => List(h)
      case h :: t => sort(t.filter(_ <= h)) ::: h :: sort(t.filter(_ > h))
    }
  }
}
```

我們可以輕易將它轉換成非同步程式。
```scala
def sort(list: List[Int]): Future[List[Int]] = {
  if(n < 1000){
    // 需要大量時間計算才可以得出結果 
    Future(insertionSort(list))
  }
  else{
    list match {
      case Nil => Future(Nil)
      case h :: Nil => Future(List(h))
      case h :: t => Future(sort(t.filter(_ <= h)) ::: h :: t.filter(_ > h))
    }
  }
}
```
非遞迴的版本就很難改了，因為主要存在共享的mutable變數，多個thread會共享同一個變數，就需要做同步處理。
同步問題沒有小心處理，結果很容易出錯。

2. 嘗試走所有可能的路徑很多

例如：計算第五十個費氏數列
根據計算費氏數列的遞迴公式， `n = 50` 的樹狀結構會相當大。我們可以透過 `Tail recursion` 來解決。
把上一個狀態的結果直接當成帶入參數，而不是依賴上一個狀態的結果。

因為 `fib(n) = fib(n - 1) + fib(n - 2)`，假如可以把 `fib(n - 1)` 和 `fib(n - 2)` 當參數帶入，
這樣就可以馬上得出 `fib(n)`，而不是等算完 `fib(n - 1)` 和 `fib(n - 2)` 後才可以得出 `fib(n)`。

```scalaFiddle
import scala.annotation.tailrec

val result = fib(0, 1, 5)

@tailrec
def fib(previous: Int, current: Int, n: Int): Int = {
  n match {
    case 0 => previous
    case 1 => current
    case _ => fib(current, previous + current, n - 1)
  }
}

println(result)
``` 
