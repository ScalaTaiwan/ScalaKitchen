<script>
  ((window.gitter = {}).chat = {}).options = {
    room: 'ScalaTaiwan/ScalaTaiwan'
  };
</script>
<script src="https://sidecar.gitter.im/dist/sidecar.v1.js" async defer></script>

# Welcome to ScalaKitchen!

```scalaFiddle libraries="Java8 Time-0.1.0"
// 可以在這邊改程式喔，改好之後按下「左上角」的 Run
import java.time._

val hour = LocalTime.now().getHour()

val msg = if (hour <= 11) {
  "早安!"
} else if (hour <= 17) {
  "哈囉!"
} else {
  "晚上好!"
}

println(msg + " 歡迎來到 Scala 料理教室!")
```

Scala 料理教室希望藉由簡單的程式範例，讓大家快速上手 Scala，目標是讓對寫程式不熟的朋友，也可以選擇 Scala 作為他的第一個深入的程式語言。
本教室由 ScalaTaiwan 社群維護，如在學習上發現任何問題或建議，都歡迎到我們的 [Gitter聊天室](https://gitter.im/ScalaTaiwan/ScalaTaiwan) 聊聊。
在教室中常常會看到像上面的 ScalaFiddle 料理台，請盡情地亂改 code，跑跑看，有任何好奇的東西就用 `println()` 把它印出來瞧瞧吧。

## Variables

在 Scala 裡要宣告一個變數的寫法是:

<img class="float-center" src="val.svg" style="height:130px"/>

Scala 中的所有變數都是有固定型態的，我可以宣告一個整數(Int) `val a: Int = 10`；宣告一個字串(String) `val b: String = "abc"`，但我不能寫 `val c: Int = "abc"`

```scalaFiddle
val a: Int = 1

val b: Double = a + 0.5

val c: String = "The value of b is " + b

println(c)
```

當 Scala compiler 看到你寫 `val a: Int = 10` 的時候，因為等號右邊是 `10`，他就知道 `a` 一定是 `Int`。所以 `: Int` 的部份其實可以省略不寫:

```scalaFiddle
val a = 1

val b = a + 0.5

val c = "The value of b is " + b

println(c)
```

## If Expression

<img class="float-center" src="if.svg" style="height:100px"/>

Scala 的 if expression 由上面三個區塊構成，▢ 的部份需要是一個 `Boolean`，例如 `true`、`false`、`2 > 1`、`a == b`，或 `3 <= 4 && "a" != "b"`。

△ 與 ◯ 的部份則可以填入一個單純的值、一個 block `{...}` (後面會提到)、或是再塞入另一個 if expression。

由於整個 if expression 最後會帶著一個值，所以我們可以再用一個 `val` 去把整個 expression 的值接住:

```scalaFiddle
val a = if (1 < 2) 10 else 11

val b = if (a == 11) {
  "A" + "B"
} else "C" + "D"

//塞了另一個 if expression 到 △ 的位置
val c = if (b == "CD") if (a < 11) 1 else 2 else 3

//不過比較常見的應該是塞在後面 ◯ 的位置
val d = if (b != "CD") 3 else if (a < 11) 1 else 2

println("a = " + a + ", b = " + b + ", c = " + c + ", d = " + d)
```

## Functions

<img class="float-center" src="def.svg" style="height:130px"/>

上面是一個叫做 a 的 function，餵它吃一個 Int 參數 b 之後，經過 △ 的運算，會回傳另外一個 Int。

△ 的部份可以填入一個單純的值、一個 block `{...}`、或是一個 if expression:

```scalaFiddle
def repeat(w: String, count: Int): String =
  if (count % 2 == 0)
    w * count
  else
    w.toLowerCase() * count

val res = repeat(repeat("A", 4) + repeat("B", 3), 2)

println(res)
```

附帶一提，因為 Scala 很容易有巢狀的程式碼，官方建議的縮排寬度是兩個空格，但就算你的縮排亂七八糟，或在稀奇古怪的地方換行，Scala compiler 還是可以看得懂你的程式。當 compiler 看到某一行不是一個合法的結尾時，它會試著自動把下一行接上來，所以上面的那段程式其實也可以寫成這樣:

```scalaFiddle
def repeat(w: String,
  count: Int): String =
   if (count %
  2 == 0)
  w * count
   else
  w.toLowerCase() * 
    count

val res = repeat(repeat("A", 4) +
  repeat("B", 3),
  2)

println(res)
```

只是，compiler 看得懂不代表你看得懂，請善待自己的眼睛，把排版弄整齊一點。

其他一些可以注意的小地方:

* Compiler 在看完你寫的 △ 內容後，就已經可以自動判斷「回傳值型態」應該要是什麼了，所以回傳值型態的部份其實可以省略不寫。
* 當小括號中連一個參數都沒有時，整對小括號也可以省略不寫，但在呼叫時會有一些特殊規則，下面的範例有列出所有可能的呼叫方式(這不用記，不行的寫法 compiler 會告訴你)。

```scalaFiddle
def a1(i: Int): String = i.toString

//回傳值型態可以省略，compiler 還是知道回傳值是 String
def a2(i: Int) = i.toString

println("a1(1) = " + a1(1))
println("a2(1) = " + a2(1))

def b1() = "abc"

def b2 = "abc"

println("b1() = " + b1())
println("b1 = " + b1)
println("b2 = " + b2)
```

## Blocks

<img class="float-center" src="block.svg" style="height:100px"/>

Block 是由一對大括號包起來的區域，在 block 外面可以寫的東西，在 block 裡面也能寫，例如 variables、if expressions、functions、或是另外一層的 block:

```scalaFiddle
val a = 10
def b() = a + 1

{
  val a = 11
  def c() = a + 1
  
  {
    val a = 12
    def c() = a + 1
    
    println("b() = " + b())
    println("c() = " + c())
  }
  
  println("c() = " + c())
}
```

從上面的例子可以發現，block 能夠存取或遮蔽 block 外面的 variable 或 function，只要取了一樣的名字，就會產生遮蔽的效果，以最接近的那個宣告為準。

接下來，由於 block 本身也是一個 expression (跟 if 一樣)，整個 block 最後會帶著一個值，這個值由 block 中最後一行(或可以說最後一個動作，畢竟 Scala 可以亂換行)的運算結果來決定。我們一樣可以用一個 val 去把這個值接住:

```scalaFiddle
val res = {
  def ulu(str: String) =
    str.toUpperCase + str.toLowerCase + str.toUpperCase
  
  ulu("Scala") + " " + ulu("Taiwan")
}

println(res)
```

當整個 block 做完運算，取得最後帶的值之後，block 中所有的 variable 跟 function 都會被消滅。也就是說，如果你為了算出某個值，需要建立一些暫時的變數的話，可以把他們包在 block 裡面，避免他們持續佔用記憶體空間。

最後，我們可以直接把一個 block 塞在 if expression 裡面，或是 def 的後面:

```scalaFiddle
val a = if (1 > 2) {
  val b = 10
  b + 1
} else {
  val b = 11
  b + 1
}

def b(c: Int) = {
  val d = 20
  d + c
}

println("a = " + a)
println("b(3) = " + b(3))
```

要注意的是，這些大括號並不是 if else 或 def 的一部分，他就只是一個單純的 block，剛好被放在那個位置而已。

## def vs val

在這裡我們先暫停來釐清一下 `def` 跟 `val` 的差異，由於這兩個 keyword 可以寫出幾乎一模一樣的東西:

```scalaFiddle
val a1 = 10

def a2 = 10

println("a1 = " + a1)
println("a2 = " + a2)
```

可能有人會疑惑 `val a1` 跟 `def a2` 到底差在哪。簡單地說，寫在 def 後面的東西可能會跑很多次，但 val 後面的東西只會跑一次。這邊因為都是 `= 10` 可能比較不清楚，但如果其中有包含一些動作的話，就可以看出差異:

```scalaFiddle
val a1 = {
  println("computing a1")
  10
}

def a2 = {
  println("computing a2")
  10
}

println("=====")

println("a1 = " + a1)
println("a1 = " + a1)

println("a2 = " + a2)
println("a2 = " + a2)
```

可以看到我們在 `println()` 裡面各別取用了 `a1` 跟 `a2` 兩次，但 `"computing a1"` 從頭到尾只出現一次，而且是在 `println("=====")` 執行之前就先執行了。

## Sequences

<img class="float-center" src="Seq.svg" style="height:130px"/>

我們現在知道 `val` 可以拿來放一個 `Int` 或一個 `String` 這類的東西，但如果我們想一口氣擺很多個 `Int` 呢？這時候就可以讓 Scala 的 Collections 家族出場了。

`Seq` (全名 Sequence)應該可以說是 Scala Collections 中，甚至是 Scala 程式中最常見的成員，他可以幫你儲存一整排同樣型態的資料(例如一排的 `Int`、一排的 `String`，甚至是一排的 `Seq`)。利用如上的方式創造一個 `Seq` 之後，我們可以把他指給一個 `val`，接著我們就可以對這個 `Seq` 做些操作，例如查看 `Seq` 中第 3 個位置的值，或是取得 `Seq` 的前三個元素，藉此創造一個新的 `Seq` 等等:

```scalaFiddle
val s: Seq[Seq[Int]] = Seq(
  Seq(1,2),
  Seq(3,4,5),
  Seq(6,7,8,9)
)

//取得第 3 個元素(編號從 0 開始，所以這裡要給 2)
val third: Seq[Int] = s.apply(2)

//取得前 3 個元素
val three: Seq[Int] = third.take(3)

println("three = " + three)
println("third = " + third)
println("s = " + s)
```

從上面的例子我們可以發現 Sequence 實際的 type 其實是 `Seq[◯]` 而不是單純的 `Seq`，◯ 代表的是這個 `Seq` 中所儲存的元素型態。我們的第一個變數 `s` 的型態是 `Seq[Seq[Int]]`，代表他是一個內部裝著 `Seq` 的 `Seq`，並且內部的每個 `Seq` 又裝著 `Int`。一樣，`: Seq[Seq[Int]]` 的部份我們也可以省略不寫，交給 compiler 自動判斷。

一路看下來，我們遇到過幾種不同的資料型態，例如 `Int`、`Double`、`String`、或現在的 `Seq`。在 Scala 中，這些全部都是某一種 "class"，`Int`、`Double`、`String`、`Seq` 分別是四種不同的 class。一個 class 除了會帶著他當下的值(例如一個 `Int` 可能帶著 `10`，一個 `Seq` 可能帶著四個 `String` `"a","b","c","d"`)之外，也會帶著一些可以讓你呼叫的 functions。這些 functions 一樣是由 `def` 定義出來的，並且可以存取 class 身上帶的值，進行一些運算之後，回傳你想要的結果，例如之前看過的 `.toString`、`.toUpperCase`，或是這邊看到的 `.apply()`、`.take()`。當我們在一個變數或值的後面加上 "." 時，就可以啟動藏在 class 裡面的 functions。通常這些 functions 並不會修改 class 身上帶的值，因此我們可以發現，就算我們對 `s` 呼叫了 `.apply()`，或是對 `third` 呼叫了 `.take()`，`s` 跟 `third` 印出來的值依然跟一開始創建時一樣沒有改變。

附帶一提，你可能有注意到，用 `println()` 把 `Seq` 印出來之後，看到的卻是 `List(...)`，這是 class 的繼承關係造成的結果，可以先把 `List` 當成一種 `Seq` 來看待就好了。關於更多 class 的架構說明，就留待之後揭曉，我們先來看看 `Seq` 還有哪些方便的 functions 可以使用:

```scalaFiddle
val s = Seq(1,2,3,4,5)

println("head = " + s.head)
println("tail = " + s.tail)
println("init = " + s.init)
println("last = " + s.last)
println("drop(3) = " + s.drop(3))
println("takeRight(3) = " + s.takeRight(3))
println("dropRight(3) = " + s.dropRight(3))
println("s :+ 6 = " + (s :+ 6))
println("0 +: s = " + (0 +: s))
println("s ++ Seq(1,2) = " + (s ++ Seq(1,2)))
```

## infix operator

Infix operator就是放在兩個值中間的運算子。例如 `a + b`的`+`。
在Scala裡面operator就是一般的method。例如一般常見的數字加法`val a = 1 + 1`其實就是`val a = 1.+(1)`。

Scala有基本的規則讓我們可以使用空格代替`.`和`()`。

```
a b c d e == a.b(c).d(e)
```

這提供了我們在設計API上的便利性，也可以讓看起來很不順的程式變的比較容易閱讀。

```scalaFiddle

case class Complex(real: Int, imaginary: Int) {

  def add(a: Complex): Complex = Complex(real + a.real, imaginary + a.imaginary)
  def subtract(a: Complex): Complex = Complex(real - a.real, imaginary - a.imaginary)

  def +(a: Complex): Complex = add(a)
  def -(a: Complex): Complex = subtract(a)
}

val x = Complex(8, 7)
val y = Complex(4, 9)

//可以直接這樣寫
println("x add y = " + (x add y))
println("x subract y = " + (x subtract y))

//只有更簡潔
println("x + y = " + (x + y))
println("x - y = " + (x - y))

```

#### 優先順序

當有多個operator的時候就會有有限順序的問題。如果沒有用`()`包起來的話就會依照下面的順序

```
(characters not shown below)
* / %
+ -
:
= !
< >
&
^
|
(all letters)
```

#### Advanced
如果在operator的後面加上`:`就可以讓在使用的時候將整個被呼叫的物件和參數的順序調換。

```scalaFiddle
//建立一個List(List就是一種Seq)
val a = List(1, 2, 3, 4, 5)

//append
println("6 appended = " + (a :+ 6))

//prepend
println("0 prepended = " + (0 +: a))

//prepend using dot notation
println("(0 +: a) == (a.+:(0)) => " + ((0 +: a) == a.+:(0))) 

```

## String

字串的宣告方式主要有兩種`"`和`"""`兩種。`"`就跟大部分其它語言一樣。`"""`跟`"`的方式是一樣的，可是我們在裡面就可以很自由的使用「換行」或`"`都不用擔心要escape。

String的合併是用`+`。

```scalaFiddle

val s = "Hello " + "ScalaKitchen"
val s1 = "Hello\"Scala\nKitchen"

val s2 = """Hello"Scala
Kitchen"""
println(s)
println("=========")
println(s1)
println("=========")
println(s2)
println("=========")
println("s1 == s2: " + (s1 == s2))

```

#### String interpolation

String interpolation有兩種基本的用法。
1. 在String的開頭加上`s`就可以在String裡面使用環境變數讓字串的組成更好用。

1. 在String的開頭加上`f`就可以有`printf`的功效。然後再結合上面的功能就可以同時format參數了。

```scalaFiddle

val name = "ScalaKitchen"

//如果變數名稱比較複雜可以使用${name}
val hello = s"Hello $name" 

println(hello)
println("=========")

val kitchenWidth = 10
val kitchenSize = f"$name is $kitchenWidth%2.2f meters wide"

println(kitchenSize)

```
[more](http://docs.scala-lang.org/overviews/core/string-interpolation.html)

## =>

這通常被叫做 "Fat Arrow"，可是在Scala裡這叫 "[Right Arrow](http://docs.scala-lang.org/tutorials/scala-for-java-programmers.html)"。
這主要用途有：
  1. 宣告anonymous function
  1. 表示一個變數的類型為某種型態的function
  1. 一個特別的參數類型, Called-by-name parameter

#### __Anonymous Function__

```scalaFiddle
//用anonymous function的方式宣告一個function再assign給addOne這個變數
val addOne = (i: Int) => i + 1

//等同於
def addOneF(i: Int): Int = i + 1

println("addOne == addOneF: " + (addOne(1) == addOneF(1)))

```

#### __Function Type__

```scalaFiddle
//跟上面的基本一樣，只是明確的定義addOne的類型
val addOne: Int => Int = (i: Int) => i + 1

//表示參數也是一個function，而這個function是拿一個Int然後產出一個Int
def doSomethingWithOne(f: Int => Int): Int = f(1)
println("multiply by 10: " + doSomethingWithOne((i: Int) => i * 10))
println("=========")
//直接把類型符合的function傳進去
def multiplyBy10(i: Int) = i * 10
println("multiply by 10 second try: " + doSomethingWithOne(multiplyBy10))
println("=========")
//直接用上面的addOne
println("add one: " + doSomethingWithOne(addOne))

```
#### __Called-by-name parameter__

這種parameter比較特別。通常參數會被在傳遞到function之前就被執行完而且得到一個預期的值。
Called-by-name parameter在被傳入function的時候並不會被執行，而是在function裡面被使用的時候才執行而求出期望的值。
這樣可以在當此參數沒用被使用的時候節省運算的資源。

Note: 這個類型的parameter跟lazy很像可是卻不像lazy再被使用過一次就不會再被計算，每一次使用都會執行一次裡面的邏輯。

```scalaFiddle

def addOrNot(i: => Int, toAdd: Boolean): Int = {
  println("add or not? that is the question")
  val magic = 42
  if(toAdd) {
    magic + i
  }
  else {
    magic
  }
}

//"add i"不會被印出來
print("magic number: " + addOrNot({println("add i"); 10}, false))

println("=========")

//注意"add i"會在"add or not? that is the question"之後才被印出
print("no a magic number: " + addOrNot({println("add i"); 10}, true))

println("=========")

//執行parameter的邏輯兩次
def add(i: => Int): Int = i + i

add({println("call me twice"); 10})

```

## Tuple

Tuple跟List/Seq在集合的概念上是很像的。只是它的類型不被限定只有一種，而是每一個元素都可以指定類型。
Tuple的大小也是在宣告的時候就決定的，不像List可以無限延展。
可是在Scala裡面，它最多只能有22個元素。

Tuple的宣告就是用小括號包起來。要取得裡面的元素就用`._1`, `._2`依照位置取得，從1開始。
通常只有2個元素的Tuple也被稱為Pair。

```scalaFiddle

val tuple2 = (1, "one")
println(tuple2)
val tuple3 = (2, "two", 2.2f)
println("=======")
println(tuple3)
println(s"first: ${tuple3._1}, second: ${tuple3._2}, third: ${tuple3._3}")

```


