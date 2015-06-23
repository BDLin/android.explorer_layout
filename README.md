android.explorer_layout
=======================

此專案的功能為，提供兩個Fragment，一個為Tab頁面，一個為Content頁面，而有兩種Layout方式，如下圖：

**Phone**

![](https://github.com/BDLin/android.explorer_layout/raw/master/phone_demo1.png "Phone Demo1 png") ![](https://github.com/BDLin/android.explorer_layout/raw/master/phone_demo2.png "Phone Demo2 png")

**Tablet**

![](https://github.com/BDLin/android.explorer_layout/raw/master/demo1.png "Tablet Demo1 png")

![](https://github.com/BDLin/android.explorer_layout/raw/master/demo2.png "Tablet Demo2 png")

Usage
-----
*1.*在於您自己開的專案裡 res/layout資料夾，且在於此資料夾裡新增一個xml檔內容為：

**此為Phone Layout**	

`res\layout\news_articles.xml：`
```
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
*2.*在於您自己開的專案裡 res/ 路徑中新增一個 layout-large資料夾，且在於此資料夾裡新增一個xml檔內容為：

**此為Tablet Layout**

`res/layout-large/news_articles.xml`
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
        setContentView(R.layout.news_articles);
    }
}
```
**Note:**
   對於以上的圖片，所顯示的頁面及內容必須實作此專案所提供的`TabView` interface來自行新增想要呈現的頁面。

*3.*在Activity裡取出xml檔裡的兩個Fragment如下：
```
public class MainActivity extends FragmentActivity {
    
    private TabFragment mTabFragment;
	private ContentFragment mContentFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlayout);
        
        mTabFragment = (TabFragment) getSupportFragmentManager().findFragmentById( R.id.headlines_fragment);
	mContentFragment = (ContentFragment)getSupportFragmentManager().findFragmentById(R.id.article_fragment);
    }
}
```
取出之後，將TabFraqgment設定給ContentFragment，如下：
`mContentFragment.setTabFragment(mTabFragment);`

*3.*將您實做`TabView`的類別new出來並且加到TabFragment裡，如下：
```
YourTabView tabview = new YourTabView();
mTabFragment.addView(tabView);
            .
            .
            .
```
**Note：**再加入TabView時，TabFragment不能是Null。

如要看更詳細的實作可以到此連結 **[Example](https://github.com/BDLin/example.explorer.layout)** 將專案下載下來參考。

**此連結 [This library document](http://htmlpreview.github.com/?https://github.com/BDLin/android.explorer_layout/blob/master/doc/index.html) 為此專案所有class檔的解析及功能用法。**

Library
-------
此專案有利用到其他的library(**請自行下載這兩個library，並設定路徑**)，如下：

[android.view.list](https://github.com/poisondog/android.view.list)

[ViewPagerIndicator](https://github.com/JakeWharton/ViewPagerIndicator)

Reference
---------
[Creating a Fragment](http://developer.android.com/training/basics/fragments/creating.html) 
