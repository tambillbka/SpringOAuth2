package com.education.tutoringappserver.common.utils;

import java.util.List;

public interface DataThreadRunner<T> {
    void run(List<T> dataList);
}
