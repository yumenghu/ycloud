package com.yumenghu.common.util;

import java.util.Arrays;
import java.util.function.Supplier;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 *@program: cem_service
 *@description: 并行任务工具类
 *@author: yu meng hu
 *@create: 2019-10-25 10:11
 */
@Slf4j
public class ParallelUtil {


  @FunctionalInterface
  public interface ParallelFunction<T> extends Supplier<T> {

  }

  @Data
  @Accessors(chain = true)
  public static class ParallelJob<T> {

    private ParallelFunction<T> function;
    private T resutl;
  }

  public static void execute(@NonNull ParallelJob... jobs) {
    Arrays.stream(jobs).parallel().forEach(job ->
        job.setResutl(job.getFunction().get())
    );
  }

}