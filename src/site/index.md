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

Scala 料理教室希望藉由簡單的程式範例，讓大家快速上手 Scala，目標是讓從來沒寫過程式的朋友，也可以選擇 Scala 作為他的第一個程式語言。
本教室由 ScalaTaiwan 社群維護，如在學習上發現任何問題或建議，都歡迎到我們的 [Gitter聊天室](https://gitter.im/ScalaTaiwan/ScalaTaiwan) 聊聊。
在教室中常常會看到像上面的 ScalaFiddle 料理台，請盡情地亂改 code，跑跑看，有任何好奇的東西就用 `println()` 把它印出來瞧瞧吧。

## Dish 1: Variables

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

## Dish 2: If Expression

<img class="float-center" src="if.svg" style="height:100px"/>

Scala 的 if expression 由上面三個區塊構成，▢ 的部份需要是一個 `Boolean`，例如 `true`, `false`, `2 > 1`, `a == b`, `3 <= 4 && "a" != "b"`。

△ 與 ◯ 的部份則可以填入一個單純的值、一個 code block `{...}`、或是再塞入另一個 if expression。

由於整個 if expression 最後會帶著一個值，所以我們可以再用一個 `val` 去把整個 expression 的值接住:

```scalaFiddle
val a = if (1 < 2) 10 else 11

val b = if (a == 11) {
  "a" + "b"
} else "c" + "d"

//塞了另一個 if expression 到 △ 的位置
val c = if (b == "cd") if (a < 11) 1 else 2 else 3

//不過比較常見的應該是塞在後面的 ◯
val d = if (b != "cd") 3 else if (a < 11) 1 else 2

println("a = " + a + ", b = " + b + ", c = " + c + ", d = " + d)
```
