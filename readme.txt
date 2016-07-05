Android Design Support Library:
控件：
    Snackbar:提供了一个介于Toast和ArlertDialog之间轻量级控件，它可以很方便的使用提示和
动作反馈。
    FloatActionButton:是一个负责显示界面基本操作的圆形按钮。
        app:backgroundTint：为背景着色
        android:tint
        android:backgroudTint:
    TabLayout:滚动tab,tabLayout.setupWithViewPager(mPager)和ViewPager组合起来
    NavigationView:实现导航菜单界面，它有两个重要的属性
        app:headerLayout:头布局
        app:menu:菜单
    Toolbar:替代Actionbar,更具有灵活性
    AppBarLayout:把容器类的组件全部作为AppBar.
    CoordinatorLayout:增强型的FrameLayout。
    CollapsingToolbarLayout:提供一个可以折叠的Toolbar.
    SwripeRefreshLayout:自带的下来刷新控件
    DrawerLayout:自带的侧滑栏
    RecylerView:替代ListView,性能更好
    CardView:卡片型布局

主题：

动画：
    响应View的Touch事件的触摸反馈动画
    隐藏和显示View的循环展示动画
    两个Activity间的切换动画
    更自然的曲线运动的动画
    使用View的状态更改动画，能改变一个或多个View的属性
    在View的状态更改时显示状态列表动画

    Touch feedback
    Circular Reveal 循环显示
    Activity transitions 活动过度
    Curved motion 曲线运动
    View state changes 视图状态变化

Touch Feedback:提供了一种在用户与UI进行交互时及时可视化的确认接触点。
关于Button的默认的触摸反馈动画使用了RippleDrawable类，提供了一种波纹
效果在两种不同的状态间过度
?android:attr/selectableItemBackground:有界限的波纹
?android:attr/selectableItemBackgourndBorderless:

ViewAnimationUtil.createCircularReveal()方法使您能够激活一个循环显示
或隐藏一个视图

Activity Transition:5.0以后新加的动画效果
其有两个类型进入和退出，而这两种类型又分为普通Transition和共享元素Transition
普通Transition：
    explode:从场景的中心移入或移出
    slide:从场景的边缘移入或移出
    fade:调整透明度产生渐变效果
共享元素Transition：他的作用是共享两个activity中共同的元素
    changeBounds:改变目标视图的布局边界
    changeClipBounds:裁剪目标视图边界
    changeTransform:改变目标视图的缩放比例和旋转角度
    changeImageTransform:改变目标图片的缩放比例和旋转角度

 启动普通Transition startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
 启动共享

android:elevation:设置阴影大小，其决定着绘制的顺序





1、基础知识回顾

2、安卓5.0 6.0 7.0特性以及material design设计风格
    1. 5.0特性
    2. 6.0特性
        新的权限体系
        protection_normal类权限包括
           access_location_extra_commands,access_network_state,access_notification_policy,access_wifi_state
           bluetooth,bluetooth_admin,broadcast_sticky,change_network_state,change_wifi_multicast_state,
           change_wifi_state,change_wimax_state,disable_keyguard,expand_status_bar,flashlight,get_accounts
           get_package_size,internet,kill_backgroud_processes,modify_audio_settings,nfc,read_sync_settings,
           read_sync_stats,receive_boot_completed,reorder_task,request_install_packages,set_time_zone,
           set_wallpaper......
        只需要声明，安装时就会授权，不需要每次使用时检测，而且用户不能取消权限
        权限的自动调整：例如app targetSdkVersions是3，而运行的系统是4，则4中新添加的权限会自动添加到app中

        dengerous权限：需要在使用时在代码中请求
            calendar:read_calendar write_calendar
            camera:camera
            contacts:read_contacts,write_contacts,get_accounts
            location:access_fine_location,access_coarse_location
            microphone:record_audio
            phone:read_phone_state,call_phone,read_call_log,write_call_log,add_voicemail,use_sip,process_outgoing_calls
            sensors:body_sensors
            sms:send_sms,receive_sms,read_sms,receive_wap_push,receive_mms
            storage:read_external_storage,write_external_storage

         * 在6.0以上，使用了新的权限体系，当targetSdkVersion=23时才会使用
         * 当申请dangerous权限时需要每次申请，用户可以取消授权，所以需要特殊处理
         * 普通权限则不需要，其在安装时已经授权，而且无法取消
        使用框架来简化6.0权限的操作:
            1.PermissionsDispatcher 使用注解的方式
            2.Grant
            3.android-RuntimePermissions  谷歌官方例子

        Data Binding Guide（数据绑定指南）
        Direct Share（直接分享）


    3. material design设计风格

3、框架以及jar包的学习
    1.Java注解
        Annotation就是Java提供了一种元程序中的元素关联任何信息和任何元数据的途径和方法
        Annotation不能影响程序代码的执行，无论增加删除Annotation代码始终如一的执行
        metadata:元数据
            编写文档：通过代码里标识的元数据生成文档
            代码分析：通过代码里标识的元数据分析代码
            编译检查：通过代码里标识的元数据让编译器实现基本的编译检查
        Java 5中定义是的元数据@Target @Retention @Documented @Inherited
        @Target:说明了所修饰的对象范围，取值有
            constructor:构造器
            field:
            local_variable
            method:
            package:
            parameter:
            type:类、接口或enum
        @Retention:表示需要在什么级别保存该注释信息，用于描述注释的生命周期
            source:
            class:
            runtime:运行时
        @Doucmented:标记注解

        @Inherited:标记注解，阐述了某个被标注的类型是被继承的

    2.rxjava rxandroid retrofit rxbus
        rxjava:实现异步，实现代码逻辑的简洁。
            概念：扩展的观察者模式
            rxjava有四个概念，observable(可观察者，即被观察者),observer(观察者),subscribe(订阅),事件。
            除了回到方法onNext()还有两个特殊的方法onCompleted(),onError()
            Observable.create().subcribe(Observer)

            unsubscribe()取消订阅，当不在使用时需要取消订阅，否则会出现内存泄漏

            Scheduler:线程控制,调度器，通过它来指定每一段代码应该运行在什么样的程序。
            Schedulers.immediate():直接在当前线程，想当于不指定线程。
            Schedulers.newThread():总是启动新的线程，并在新线程中执行
            Schedulers.io():读写，内部是一个无上限的线程池，效率高，但是不要把计算放在此中
            Schedulers.computation():计算所使用。内部是固定的线程池，即cpu核数

            subscribeOn:指定subscribe()所发生的线程，即事件产生的线程，只能调用一次，控制的是从事件发出的开端就造成影响
            observerOn:指定subscriber所运行的线程，即事件消费的线程，其可以多次调用，实现线程的切换。控制的是后面的线程
            .subscribeOn(Scheduler.io()).observerOn(AndroidSchedulers.mainThread())后台取数据，前台显示
            doOnSubscribe():与onStart类似，在subscribe()调用后事件执行前执行，其可以指定线程，默认的是执行在subscribe
            发生的线程，但是如果其后有subscribeOn()的话，他将执行离他最近的。

            变换：将事件序列中的对象或者整个序列进行加工处理，转换成不同的事件或序列。
            map()
            flatMap():返回的是Observable对象，而且这个Observable对象并不是直接发送到Subscribe的回调方法。其原理是
            1.使用传入的事件对像创建一个Observable对象2.并不是发送这个Observable，而是激活它，于是他开始发送事件。3.
            每一个创建处理的Observable发送的事件，都汇入同一个Observable，这个Observable负责将这些事件统一交给Subscribe
            的回调方法。
            lift():其过程有点像一种代理机制，通过事件拦截和处理实现事件序列的变幻。这个比较难理解，但是map flatmap都是由lift变换而来的
            compose():对Observable整体的变换。

        rxandroid：在安卓中使用的rxjava

        retrofit:网络请求包
          ConcurrentHashMap替代HashMap，线程安全

        RxBus：实现EventBus otto 以及Broadcast 类似的功能


    3.picasso glide photoView等(图片处理相关包)
        picasso:实现图片下载和缓存功能。特点：加载网络图片或本地图片并自动缓存处理。链式调用。图形转换操作。在adapter中取消和回收下载
           Picasso.with(context).load(url).resize(50,50).centerCrop().into(imageview)

        glide:图片加载库，与picasso类似，只是他的克隆，但是还是有些许差别
            picasso需要传递的是context,glide可以是context，activity，fragment等，这样可以与其生命周期保持一致。
            picasso bitmap格式是argb_8888 glide是rgb_565所以内存开销是不一样的，glide内存开销小
            picasso加载了全尺寸的图片到内存，然后重汇大小，glide加载的大小与imageview是一样的，所以内存消耗也是不一样的
            picasso缓存到磁盘上的是全尺寸的，而且只缓存一张，glide缓存的跟imageview大小相同，而且他会给每种大小不同的
            iamgeview缓存一张图片,需要大的磁盘空间，所以glide加载图片的速度会快一点，因为Picasso加载时需要重置图片大小。

        photoView:用于图片的显示，支持缩放超出边界，点击触控和双击事件，滑动滚动等。
            可以对其代码进行分析。

    4.greendao等(数据库操作相关包)
        greendao是一个ORM框架。
        DaoMaster：以一定的模式持有数据库对象并管理一些DAO类
        DaoSession:管理制定模式下所有可用的DAO对象，你可以通过某个get方法获取到，其提供了一些通用的持久化方法
        DAOs:数据访问对象，用于实体的持久化和查询

    5.vitamio (视频处理相关包)

    6.okhttp (http请求相关包)

    7.changeSkin (切换皮肤相关处理)

    8.slidr (滑动关闭)

    9.richtext (富文本)


    10.Dagger2(依赖注入)
        @Inject:在需要依赖的地方使用这个注解，告诉Dagger这个类或者字段需要依赖注入。
            构造器注入
            注解成员变量
            方法注入
        @Module

        @Provides
            按照习惯，@Provides方法都会用provide做前缀，@Module类都是用Module作为后缀
            如果@Provides方法有参数，这个参数也要保证能够被Dagger得到，通过@Provides或者@Inject

        @Component
            是@Inject和@Module之间的桥梁

        @Scope:
            作用域，用于标记自定义的作用域。简单来说，他可以类似单列的标记依赖，被做注解的依赖会变成单列，但会和Component的生命周期关联
        @Qualifier
            限定符，当类的类型不足以鉴定一个依赖的时候会使用到。

    11.ButterKnife
        注入框架

    12.RecyclerView上拉与下拉

4、设计模式
    1.MVVM设计模式
        即Model-View-ViewModel，model提供数据，view负责显示，viewmodel跟view和model进行双向绑定。当view输入数据后
        viewmodel通知model更新数据，反之同理亦然。

    2.MWP设置模式

    3.Flux设计模式
        Flux最大的特点是单向的数据流，它的UI状态更新模式继承了MVC模式的设计思想。

        Dispatcher:是中心枢纽，管理所有的数据流。他实际上是管理Store注册的一系列回调接口，本身没有逻辑。它仅仅用来将Action发送到
            各个Store的一套简单的机制。
        Stores:包含应用的状态和逻辑。他扮演的角色类似于MVC中Model，但是他负责管理多个对象的状态。
        Controller-View:负责监听Store状态并跟新页面。当View收到来自Store的跟新事件时，先从Store的getter方法获取数据，然后调用自己的
            setState或者更新页面。
        Actions:Dispather提供一个方法来分发事件到Store,并包含一些数据，这通常封装成一个Action。Action创建一般被封装到有语境意义的Helper方法
            （ActionCreator）。


5、
    1.强引用：普遍的引用，垃圾回收期不会回收
    2.软引用：内存空间足够，不会回收，空间不足，则回收
    3.弱引用：垃圾回收器扫描到就会回收掉
    4.虚引用：不会决定对象的生命周期，在任何时候可能被回收掉
    6.LruCache:硬缓存，比弱引用更好的解决了图片缓存问题。


6.
    Paint.setXfermode()设置图形重叠时的处理方法，比如合并。
    在已有的图像上绘图将会在其上面添加一层新的形状。如果新的paint是完全不透明的，那么他将会完全遮挡下面的paint。
    如果是部分透明的，那么它将会染上下面的颜色。Xfermode可以改变这种行为:
    AvoidXfermode:指定一个颜色和容差，强制paint避免在他的上面绘图（或只在他上面绘图）
    PixelXorXfermode:像素xor操作
    PorterDuffXfermode:
        Mode.ADD:饱和度相加，对src和det相交区域的饱和度叠加
        Mode.LIGHTEN:变亮，两个图像重合的地方会变亮
        Mode.DARKEN:变暗
        Mode.MULTIPLY:正片叠加。[sa*da,sc*dc]
        Mode.OVERLAY:叠加
        Mode.SCREEN:虑色，只有叠加的部分会变色
        Mode.SRC:处理相交时会以源图显示
        Mode.SRC_IN:[sa*da,sc*da]，当目标图像为透明的时候，会显示透明。相交时利用目标图片的透明度来改变源图的透明度和饱和度
        Mode.SRC_OUT:[sa*(1-da),sc*(1-da)]以目标图片的透明度的补值来调节源图的透明度和色彩饱和度。即当目标图片为空白像素时，则显示源图，相反依然。
        Mode.SRC_OVER:在目标图的顶部绘制源图
        Mode.SRC_ATOP:与src_in相似

        Mode.DEST:相交时以目标图显示
        Mode.DEST_IN:正好Src_in相反，相交时以源图透明度好改变目标图的透明度
        Mode.DEST_OUT:
        Mode.DEST_OVER:
        Mode.DEST_ATOP:

        Mode.CLEAR:清空
        Mode.Xor:



    setStrokeCap(Paint.Cap)设置线帽样式，即一段线的前后加了一个样式
    setStrokeJoin(Paint.Join)设置连接处的样式
    setPathEffect(PathEffect)设置线的效果
    setLinearText(boolean)设置线性文本，true不需要文本缓存,目前用处不大
    setSubpixelText(boolean)是否打开亚像素打开文本，可以是文本更清晰

    Canvas:
        onDraw:绘制自身
        dispatchDraw:绘制子视图

        saveLayer:创建一个全新透明的bitmap，大小与指定保存的区域一致，其后的绘图操作都在这个bitmap上进行。结束后，会直接覆盖在上一层bitmap上
            其后的所有动作只对新建画布有效，在参数中rect的大小就是画布大小
        图层（Layer）:每一次调用canvas.drawXX都会生成一个透明的图层
        画布（Bitmap）:

    画布的保存与恢复：
    save:把当前的画布状态保存，然后放入特定的栈中
    restore:把栈中最顶端的画布状态放出了，并按照这个状态恢复当前的画布，并在此画布上作画。
    restoreToCount:一直退栈，直到指定count的层数为栈顶为止。

7.动画：
    插值器:
        AccelerateDecelerateInterpolator:
        AccelerateInterpolator:
        AnticipateIntepolator:
        AnticipateOvershootIntepolator:
        BounceIntepolator:
        CycleIntepolator:
        DecelerateInterpolator:
        LinearInterpolator:
        OvershootInterpolator:

    动画分为视图动画和属性动画，视图动画(View animation)分为补间动画和帧动画。视图动画包括ValueAnimator和ObjectAnimation。

    ValueAnimator：可以为其加插值器以及转换器（Evaluator）。
    ObjectAnimator与ValueAnimator类似，只是向其传递参数
    ObjectAnimator ofFloat(Object target,String propertyName,float ...values)
        target:指定动画要操作哪个控件
        propertyName:操作控件的哪一个属性，其实也不是属性，是target控件中set方法
        values:属性值的变化
        要操作的控件中，必须存在对应的属性的set方法。
        当且仅当动画只有一个过渡值，系统才会调用对应属性的get方法来获取默认值
    组合动画：playSequeentially,playTogether,AnimatorSet

    LayoutAnimation:ViewGroup进入时的统一动画，动画仅第一次创建时有效，后期加入时就不在有效
    GridLayoutAnimation:Gridview item的动画,与LayoutAnimation类似


    5.0中新增的动画:触摸反馈，activity transitions,Reveal animations
8.Webview

    WebviewClient:
        shouldOverrideUrlLoading:post请求时时不会回调此函数，true主程序接管，false由webview接管
        onPageStarted:页面加载，对于每个main frame只调用一次。loadurl加载url时，先加载onPageStarted,然后上边函数。打开网页中的link，则相反
        onPageFinished:
        onLoadResource:

    WebChromeClient:
        onProgressChanged:
        onShowCustomView:全屏模式，主要用于html5视频全屏
        onCreateWindow:创建新窗口
        onJsAlert:true程序自己处理，false内核自己处理。如果true,必须调用result.cancel或result.commit
        onShowFileChooser:


9.ViewGroup绘制流程
    测量、布局、绘制
    onMeasure:测量自己的大小，为正式布局提供数据，真实布局时不一定按照其测量的数据
    onLayout:使用layout对子控件布局
    onDraw:绘制

    onMeasure(int,int):参数是父类传递过来的建议值
    参数是有mode+size组成，二进制，30位，前两位模式，后28size
    Unspecified(未指定):父元素不施加任何束缚，子元素可以得到其想要的大小
    Exacity(完全):父元素决定自身的大小，子元素的大小将被限定
    At_most(之多):子元素至多达到指定大小的值

    wrap_content-->at_most
    match_parent-->exacity
    具体值-->exacity

    getMearsureWidth:mearsure方法结束后的值，是通过setMearsureDimension()设置的
    getWidth:是layout之后才获取到的值

    PopupWindow:
        setConttentView:设置view
        setBackgroundDrawable:设置背景，如果背景不为空，背景是FrameLayout，contentview在FrameLayout上
        PopupWindow显示时，会将FrameLayout在添加在FrameLayout上来显示，Height,Width控制其大小。

10:apk的安装过程
    apk安装时会将apk文件复制到data/app下面，将classes.dex文件复制到dalvik-cache下面。
    data/data文件夹下面会创建应用数据文件夹

    2.3之前的虚拟机内存只分配了5M,所以dex中的方法数不能超过65k(65535)
    Dex优化，DexOpt。DexOpt优化时会把每个方法的ID检索起来，存在一个链表中，short型。大小为65k
    DexOpt使用LinearAlloc存储应用的方法信息，其也是有一定得大小的。2.2 2.3 5M 4.0以上提升到8M或16M

    有两种解决方案，插件化和分包技术：
  分包技术：

  插件化：

SurfaceView:
    可以直接从内存或DMA等硬件接口取得数据，是个重要的绘图容器。
    特性：可以在主线程之外的线程中向屏幕绘图。这样可以避免画图任务繁重时造成主线程阻塞。
    SurfaceView和SurfaceHolder.Callback都是在主线程调用的。有关资源状态要注意和绘制线程之间的同步。在绘制线程中
    必须先合法的获取Surface才能开始绘制，在SurfaceHolder.Callback.surfaceCreate和SurfaceHolder.Callback.surfaceDestory
    直接为合法。
    使用SurfaceView只要继承SurfaceView并实现SurfaceHolder.Callback,SurfaceHolder.Callback在底层的Surface发生变化后会
    通知View。
    surfaceCreated:Surface第一次创建时调用，一般情况下都是在另一线程中绘制界面
    surfaceChangeed:
    surfaceDestoryed:

