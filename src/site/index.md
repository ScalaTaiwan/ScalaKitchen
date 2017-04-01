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
def repeat(w: String, count: Int): String = if (count % 2 == 0) {
  w * count
} else {
  w.toLowerCase() * count
}

val res = repeat(repeat("A", 4) + repeat("B", 3), 2)

println(res)
```

附帶一提，因為 Scala 很容易有巢狀的程式碼，官方建議的縮排寬度是兩個空格，但就算你的縮排亂七八糟，或在稀奇古怪的地方換行，Scala compiler 還是可以看得懂你的程式。當 compiler 看到某一行不是一個合法的結尾時，它會試著自動把下一行接上來，所以上面的那段程式其實也可以寫成這樣:

```scalaFiddle
def repeat(w: String,
  count: Int): String = 
   if (count %
  2 == 0) {
  w * count
} else {
  w.toLowerCase() * 
    count
}

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
