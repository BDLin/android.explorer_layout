android.explorer_layout(Unfinished...Please wait for me finish it.)
=======================

此專案的功能為，以檔案總管介面的方式瀏覽檔案，如下圖：

![alt text](https://github.com/BDLin/android.explorer_layout/raw/master/demo1.png "Demo1 png")

![alt text](https://github.com/BDLin/android.explorer_layout/raw/master/demo2.png "Demo2 png")

Usage
-----
首先在於您自己開的專案裡 res/ 路徑中新增一個 layout-large資料夾，且在於此資料夾裡新增一個xml檔內容為：

*res/layout-large/newlayout.xml*
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/tablet_linear_layout"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <fragment android:name="nkfust.selab.android.explorer.layout.model.TabFragment"
              android:id="@+id/headlines_fragment"
              android:layout_weight="1"
        	  android:layout_width="0dp"
        	  android:layout_height="match_parent" />

    <fragment android:name="nkfust.selab.android.explorer.layout.model.ContentFragment"
              android:id="@+id/article_fragment"
              android:layout_weight="2"
              android:layout_width="0dp"
              android:layout_height="match_parent" />        
</LinearLayout>
```

之後將此layout應用於Activity裡，如下：
```
public class MainActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlayout);
    }
}
```
**Note:**
    此layout只是幫您把螢幕分割為1：2，對於以上的圖片，所顯示的頁面及內容必須實作此專案所提供的`TabView` interface來自行新增想要呈現的頁面。
    
[aaa](https://github.com/BDLin/android.explorer_layout/blob/master/doc/index.html)
    
    
Reference
--------
[Creating a Fragment](http://developer.android.com/training/basics/fragments/creating.html) 
