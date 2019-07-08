 
 ### 在Application中初始化
 
 * AppConfig.getInstance().init(this);
 
 
 ### 使用
 
 - 1、实现AutoSizeAdaptation
 
 ~~~
 public class MainActivity extends Activity implements AutoSizeAdaptation{}
 public class MainActivity extends Fragment implements AutoSizeAdaptation{}
 ~~~
 
 - 2.返回设计的宽高
 
 ~~~
 @Override
 public DesignInfo designInfo(){
    return new DesignInfo(designWidth, designHeight);
 }
 ~~~
 
 