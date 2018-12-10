# CustomScrollbarScrollviewSimple
![](https://raw.githubusercontent.com/machao0727/CustomScrollbarScrollview/master/simplegif/GIF.gif)
</br>
</br>
USE
====

```java
//addContentView 添加内部滑动内容
((CustomScrollViewLayout) findViewById(R.id.mScrollView)).addContentView(View.inflate(this, R.layout.scroll_content, null));
```

```xml
<com.machao.customscrollbarscrollview.customscrollbar.CustomScrollViewLayout
        android:id="@+id/mScrollView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:thumb_drawable="@mipmap/ic_indicator"
        app:track_drawable="@drawable/scroll_track">

</com.machao.customscrollbarscrollview.customscrollbar.CustomScrollViewLayout>
```