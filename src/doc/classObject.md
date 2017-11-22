<script>
  ((window.gitter = {}).chat = {}).options = {
    room: 'ScalaTaiwan/ScalaTaiwan'
  };
</script>
<script src="https://sidecar.gitter.im/dist/sidecar.v1.js" async defer></script>

# class/object

## class

class跟一般其它的語言是一樣的。它是一個靜態的模板，可以在執行期間建立出多個不同的instances。
每一個class都會有一個default的建立子(constructor)。這個default的constructor會是這個class的基礎。
不像Java，在Scala如果有overload constructor，最後都還是會call回來這個default的constructor。
而這個default的constructor的內容就是直接定義在這class body裡的內容，這點跟JavaScript比較像。等於是說這個class body裡面的內容會在這個instance被建立起來的時候全部執行一遍。