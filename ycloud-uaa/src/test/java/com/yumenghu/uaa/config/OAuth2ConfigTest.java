package com.yumenghu.uaa.config;

import static org.junit.Assert.*;

import java.util.Stack;
import org.apache.commons.lang.StringUtils;

public class OAuth2ConfigTest {

  static int cnt = 0;
  static Stack<Integer> s = new Stack<Integer>();




  /*
   *
   * case when
   *     avg_first_http_responce_delay=-1
   * and business_success_rate<>-1
   * and download_rate<>-1
   * and up_ip_packet_rate<>-1
   * and down_ip_packet_rate<>-1
   * and attach_rate<>-1
   * and pdn_rate<>-1
   * */


  public static String[] a = {
      "avg_first_http_responce_delay",
      "business_success_rate",
      "download_rate",
      "up_ip_packet_rate",
      "down_ip_packet_rate",
      "attach_rate",
      "pdn_rate"
  };

  public static String ZERO = " =-1 and ";

  public static String UNZERO = " <>-1 and ";
  public static String PREFIX = "case when ";

  /**
   * 递归方法，当前已抽取的小球个数与要求抽取小球个数相同时，退出递归
   * @param curnum - 当前已经抓取的小球数目
   * @param curmaxv - 当前已经抓取小球中最大的编号
   * @param maxnum - 需要抓取小球的数目
   * @param maxv - 待抓取小球中最大的编号
   */
  public static void kase3(int curnum, int curmaxv, int maxnum, int maxv) {
    if (curnum == maxnum) {
      cnt++;
      System.out.print(PREFIX);
      for (int i = 1; i <= 7; i++) {
        if(s.contains(i)){
          System.out.print(a[i-1]+ZERO);
        }else {
          System.out.print(a[i-1]+UNZERO);
        }
      }
      System.out.println();
//      System.out.println(s);
      return;
    }

    for (int i = curmaxv + 1; i <= maxv; i++) { // i <= maxv - maxnum + curnum + 1
      s.push(i);
      kase3(curnum + 1, i, maxnum, maxv);
      s.pop();
    }
  }

  public static void main(String[] args) {

    for (int i = 1; i <= 7; i++) {

      kase3(0, 0, i, 7);
    }
    System.out.println(cnt);
  }

}