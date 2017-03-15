# Welcome!

<iframe height="300" frameborder="0" style="width: 100%; overflow: hidden;" src="https://embed.scalafiddle.io/embed?sfid=o46O43H/3"></iframe>

Scala 料理教室希望藉由簡單的程式範例，讓大家快速上手 Scala，目標是讓從來沒寫過程式的朋友，也可以選擇 Scala 作為他的第一個程式語言。
本教室由 ScalaTaiwan 社群維護，如在學習上發現任何問題或建議，都歡迎到我們的 [Gitter聊天室](https://gitter.im/ScalaTaiwan/ScalaTaiwan) 聊聊。
在教室中常常會看到像上面的 ScalaFiddle 料理台，請盡情地亂改 code，跑跑看，有任何好奇的東西就用 `println()` 把它印出來瞧瞧吧。

# variable

在 Scala 裡要宣告一個變數的寫法是:

<div style="text-align: center"><img src="val.svg" height="130px"/></div>

Scala 中的所有變數都是有固定型態的，我可以宣告一個整數(Int) `val a: Int = 10`；宣告一個字串(String) `val b: String = "abc"`，但我不能寫 `val c: Int = "abc"`

<iframe height="300" frameborder="0" style="width: 100%; overflow: hidden;" src="https://embed.scalafiddle.io/embed?sfid=PylqMcb/0"></iframe>

當 Scala compiler 看到你寫 `val a: Int = 10` 的時候，因為等號右邊是 `10`，他就知道 `a` 一定是 `Int`。所以 `: Int` 的部份其實可以省略不寫:

<iframe height="300" frameborder="0" style="width: 100%; overflow: hidden;" src="https://embed.scalafiddle.io/embed?sfid=Ua6OWKm/2"></iframe>

# if & else

在 Scala 裡的 if 判斷式長這樣:

<div style="text-align: center"><img src="if.svg" width="300px"/></div>

條件式的地方需要是一個值為 `true` 或 `false` 的 `Boolean`，而紅色跟藍色的位置，可以是一個單純的值:

<div style="text-align: center"><img src="if-value.svg" width="350px"/></div>

或是一段 code (這個後面會講到):

<div style="text-align: center"><img src="if-block.svg" width="450px"/></div>

或是再塞一組 if else 進去:

<div style="text-align: center"><img src="if-if.svg" width="600px"/></div>

比較常見的或許會像這樣:

<div style="text-align: center"><img src="if-if2.svg" width="600px"/></div>
