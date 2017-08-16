package com.viva.photo.view.ysfragment;

import android.support.test.runner.AndroidJUnit4;

import com.viva.photo.control.LoadHtml;
import com.viva.photo.control.OnLoadListener;
import com.viva.photo.control.ys.MainParser;

import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Test
    public void load() {
        MainParser mainParser = new MainParser();
        mainParser.connect();
        ArrayList<Object> array = mainParser.parser();
        System.out.println("ccck " + array.toString());
    }

}