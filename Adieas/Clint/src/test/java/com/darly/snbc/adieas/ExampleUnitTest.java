package com.darly.snbc.adieas;

import com.darly.snbc.clint.common.Key;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void KEy(){
        String key = "{\"addr\":12,\"boxid\":12,\"chspeed\":0,\"count\":0,\"elcspeed\":0,\"filepath\":\"3333333333333lafksdjflakjsdflkj\",\"goodsnum\":0,\"intentposition\":0,\"jboxid\":0,\"laser\":0,\"mode\":0,\"nLay\":0,\"nRow\":0,\"offTemp\":0,\"onTemp\":0,\"pickup\":0,\"positionX\":0,\"positionY\":0,\"price\":0,\"querytype\":0,\"size\":0,\"startposition\":0,\"state\":0,\"timeout\":0,\"type\":0,\"workmode\":0,\"xselect\":0,\"xyselect\":0,\"yselect\":0}";
        String encode = Key.encrypt(key);
        System.out.printf(encode);

        String decode = Key.decrypt(encode);
        System.out.printf(decode);
    }

}