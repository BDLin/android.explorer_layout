package com.example.android.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

@TargetApi(Build.VERSION_CODES.ECLAIR)
public class ImageData implements Viewable {
	
	private String title;
	private Context context;
	private ImageView imageView;
	private LinearLayout linear;
	
	/****************************************************************/
	private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    private Bitmap bitmap;

    private static final int NONE = 0;// 初始狀態
    private static final int DRAG = 1;// 拖曳狀態
    private static final int ZOOM = 2;// 縮放狀態

    private int mode = NONE;
    private int lastaction = 0;

    private PointF pA = new PointF();
    private PointF pB = new PointF();
    private PointF prev = new PointF();
    private PointF mid = new PointF();
    private PointF lastClickPos = new PointF();

    private float dist = 1f;
    private DisplayMetrics dm;
    private float minScaleR = 0.1f; // 最少缩放比例
    private static final float MAX_SCALE = 10f; // 最大缩放比例
    private static final float DOUBLECLICK_SCALE = 2f; // 雙擊放大比例

    long lastClickTime = 0;
    float viewW;
    float viewH;
    private long down_time = 0;

    /**紀錄圖片狀態是否超出螢幕**/
    private boolean isZoom;
	/****************************************************************/
	
	public ImageData(String title,Context context, Bitmap bitmap){
		this.title = title;
		this.context = context;
	    this.imageView = new ImageView(context);
	    this.dm = new DisplayMetrics();
        this.bitmap = bitmap;
        this.linear = linear;
        setImageSize();
        minZoom();
        center();
        imageView.setImageMatrix(matrix);
        isZoom = false;
	}
	
	@Override
	public View getView(Context context) {
		imageView.setScaleType(ScaleType.MATRIX);
		imageView.setAdjustViewBounds(true);
		imageView.setImageResource(R.drawable.winter);
		imageView.setPadding(5, 50, 5, 5);
		imageView.setId(0x00515011);
		return imageView;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	public Matrix getMatrix() {
        return matrix;
    }

	// 取得最小的比例, 假設圖片比螢幕大
    // 則螢幕(寬/長)/圖片(寬/長)會小於1 那麼也就是將圖片進行縮小
    // 反之 則進行放大 而圖片越小 放大倍數則會越大
    // 如果螢幕跟圖片大小相同 則倍數會為1 即不變
    public void minZoom() {
          Context c = imageView .getContext();
           minScaleR = Math. min(( float) dm. widthPixels / ( float) bitmap.getWidth(),
                                 ( float) dm. heightPixels / ( float) bitmap.getHeight());
           if ( minScaleR > 1) {
                minScaleR = 1;
          }
           matrix.postScale( minScaleR, minScaleR);
    }

    public void center() {
          center( true, true);
    }

    // 橫向、縱向置中
    public void center(boolean horizontal, boolean vertical) {

          Matrix m = new Matrix();
          m.set( matrix);
          RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
          m.mapRect(rect);

           float height = rect.height();
           float width = rect.width();

           float deltaX = 0, deltaY = 0;

           isZoom = true;

           if (vertical) {
                // 圖片小於螢幕大小，則置中顯示。
                // 大於螢幕，上方則留空白則往上移，下方留空白則往下移
                int screenHeight = dm. heightPixels;
                if (height < screenHeight) {
                    deltaY = (screenHeight - height) / 2 - rect.top;
                     isZoom = false;
               } else if (rect. top > 0) {
                    deltaY = -rect. top;
               } else if (rect. bottom < screenHeight) {
                    deltaY = imageView.getHeight() - rect.bottom;
               }
          }

           if (horizontal) {
                int screenWidth = dm. widthPixels;
                if (width < screenWidth) {
                    deltaX = (screenWidth - width) / 2 - rect.left;
                     isZoom = false;
               } else if (rect. left > 0) {
                    deltaX = -rect. left;
               } else if (rect. right < screenWidth) {
                    deltaX = screenWidth - rect. right;
               }
          }
           matrix.postTranslate(deltaX, deltaY);
    }

    // 兩點的距離
    public float spacing(MotionEvent event) {
           float x = event.getX(0) - event.getX(1);
           float y = event.getY(0) - event.getY(1);
           return FloatMath. sqrt(x * x + y * y);
    }

    public float spacing( float x1, float y1, float x2, float y2) {
           float x = x1 - x2;
           float y = y1 - y2;
           return FloatMath. sqrt(x * x + y * y);
    }

    // 兩點的中點
    public void midPoint(PointF point, MotionEvent event) {
           float x = event.getX(0) + event.getX(1);
           float y = event.getY(0) + event.getY(1);
          point.set(x / 2, y / 2);
    }

    protected void doubleClick( float x, float y) {
           float p[] = new float[9];
           matrix.getValues(p);
           if ( mode == DRAG) {
                if (p[0] > minScaleR) {
                     matrix.setScale( minScaleR, minScaleR);
               } else {
                     matrix.setScale( DOUBLECLICK_SCALE, DOUBLECLICK_SCALE, x, y);
               }
               center();
          }
           imageView.setImageMatrix( matrix);
    }

    // 限制最大最小缩放比例
    protected void CheckScale() {
           float p[] = new float[9];
           matrix.getValues(p);
           if ( mode == ZOOM) {
                if (p[0] < minScaleR) {
                     matrix.setScale( minScaleR, minScaleR);
               }
                if (p[0] > MAX_SCALE) {
                     matrix.set( savedMatrix);
               }
          }
    }
    
 // 建立圖片移動縮放事件
    public void setImageSize() {

           imageView.setOnTouchListener( new OnTouchListener() {

                @Override
                public boolean onTouch(View arg0, MotionEvent event) {

                     switch (event.getAction() & MotionEvent.ACTION_MASK) {
                     case MotionEvent. ACTION_DOWN:
                    	   Log.i("ImageData", "Action_down\n" + matrix + "\n" + imageView.getImageMatrix());
                           //savedMatrix.set(matrix);
                           savedMatrix.set(imageView.getImageMatrix());
                    	   prev.set(event.getX(), event.getY());
                           // pA.set(event.getX(), event.getY());
                           // pB.set(event.getX(), event.getY());
                           mode = DRAG; // 設定為拖拉模式
                           down_time = System.currentTimeMillis();
                           break;

                     case MotionEvent. ACTION_POINTER_DOWN:
                           dist = spacing(event);
                           // 如果兩點距離超過10, 就判斷為多點觸控模式 即為縮放模式
                           if (spacing(event) > 10f) {
                                //savedMatrix.set(matrix);
                                savedMatrix.set(imageView.getImageMatrix());
                        	    midPoint( mid, event);
                                mode = ZOOM;
                           }//End of if
                           break;
                           
                     case MotionEvent. ACTION_UP:
                     case MotionEvent. ACTION_POINTER_UP:
                           if ( mode == DRAG) {
                                long now = System.currentTimeMillis();
                                if ((now - lastClickTime) < 300) {
                                    Context c = imageView.getContext();
                                     // Toast.makeText(c, "click time:" + (now -
                                     // lastClickTime) , Toast.LENGTH_SHORT).show();
                                    doubleClick(event.getX(), event.getY());
                                    now = 0;
                                }else{
                                     if (now - down_time >= 300 && now - down_time < 1000) {
                                          Context c = imageView.getContext();
                                     }//End of if
                               }//End of if-else
                                lastClickTime = now;
                           }//End of if
                           mode = NONE;
                           break;
                           
                     case MotionEvent. ACTION_MOVE:
                           if ( mode == DRAG && isZoom) { // 超出圖片
                                // if (isZoom) { // 超出圖片
                                matrix.set( savedMatrix);
                                matrix.postTranslate(event.getX() - prev.x,
                                                     event.getY() - prev.y);
                                // matrix.postTranslate(event.getX() - prev.x, 0);
                                
                               // 父類別onTouch()不可使用,圖片放大後才能左右查看圖片
                               arg0.getParent().requestDisallowInterceptTouchEvent(true);

                           }else if( mode == ZOOM) {
                                float newDist = spacing(event);// 偵測兩根手指移動的距離
                                if (newDist > 10f) {
                                     matrix.set( savedMatrix);
                                     float tScale = newDist / dist;
                                     matrix.postScale(tScale, tScale, mid. x, mid. y);
                                }//End if
                           }//End if else if
                           break;
                    }//End of Switch-case
                    imageView.setImageMatrix(matrix);
                    CheckScale(); // 限制缩放范围
                    center();
                    lastaction = event.getAction();
                    return true;
               }//End of onTouch
          });
    }//End of setImageSize Function
}//End of class ImageData
