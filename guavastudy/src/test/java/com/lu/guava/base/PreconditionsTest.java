package com.lu.guava.base;

import com.google.common.base.Preconditions;
import org.junit.Test;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/3/24.
 */
public class PreconditionsTest {
    @Test(expected = NullPointerException.class)
    public void checkNull() {
        checkNotNull(null);
    }

    @Test
    public void checkArgument() {
        try {
            Preconditions.checkArgument(5 > 10, "干嘛！%s","hello");
        } catch (Exception e) {
            assert e.getMessage().equals("干嘛！hello");
        }
    }

    @Test
    public void checkArgument2(){
        
    }


}