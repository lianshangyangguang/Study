package com.zxy.study;


import java.util.Formatter;

import static java.util.Locale.CANADA;

/**
 * Created by xiyingzhu on 2017/10/9.
 */

public class Format {

    StringBuilder sb = new StringBuilder();
    // Send all output to the Appendable object sb
    Formatter formatter = new Formatter(sb, CANADA);
// Explicit argument indices may be used to re-order output.
//            formatter.format(%4$2s %3$3s)
//                    ("%4$2s %3$2s %2$2s %1$2s","a","b","c","d")
// -> " d  c  b  a"

}
