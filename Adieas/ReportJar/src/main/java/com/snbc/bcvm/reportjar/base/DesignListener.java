package com.snbc.bcvm.reportjar.base;

import android.content.Context;

/**
 * 設計者，將觀察者集中註冊到這裡。
 *
 * @author Darly/张宇辉/2017/11/23 14:43
 * @version 1.0/com.darly.dview.observer
 * Copyright (c) 2017 Organization D.L. zhangyuhui All rights reserved.
 */

public interface DesignListener {

    void addObserver(ObserverListener ob);

    void delObserver(ObserverListener ob);

    void init(boolean debug, Context context);

}
