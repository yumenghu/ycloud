package com.yumenghu.uaa.service;

import com.yumenghu.common.exception.ErrorCode;
import com.yumenghu.common.exception.YcloudException;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-21 11:20
 */
@Service
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

  @Autowired
  private RedisTemplate<Object, Object> redisTemplate;

  /**
   * 存储code到redis，并设置过期时间，10分钟<br>
   * value为OAuth2Authentication序列化后的字节<br>
   * 因为OAuth2Authentication没有无参构造函数<br>
   * redisTemplate.opsForValue().set(key, value, timeout, unit);
   * 这种方式直接存储的话，redisTemplate.opsForValue().get(key)的时候有些问题，
   * 所以这里采用最底层的方式存储，get的时候也用最底层的方式获取
   */
  @Override
  protected void store(String code, OAuth2Authentication authentication) {
    redisTemplate.opsForValue()
        .set(codeKey(code), SerializationUtils.serialize(authentication), 10,
            TimeUnit.MILLISECONDS);
  }

  @Override
  protected OAuth2Authentication remove(final String code) {
    Object authentication = redisTemplate.opsForValue().get(codeKey(code));
    if (authentication instanceof byte[]) {
      return (OAuth2Authentication) SerializationUtils.deserialize((byte[]) authentication);
    } else {
      throw new YcloudException(ErrorCode.FAIL, "authentication de");
    }
  }

  /**
   * 拼装redis中key的前缀
   *
   * @param code
   */
  private String codeKey(String code) {
    return "oauth2:codes:" + code;
  }
}