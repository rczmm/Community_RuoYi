package com.community.framework.web.service;

import java.util.Set;

import com.community.common.constant.Constants;
import com.community.common.utils.CacheUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

/**
 * 缓存操作处理
 * 
 * @author ruoyi
 */
@Service
public class CacheService
{
    /**
     * 获取所有缓存名称
     * 
     * @return 缓存列表
     */
    public String[] getCacheNames()
    {
        String[] cacheNames = com.community.common.utils.CacheUtils.getCacheNames();
        return ArrayUtils.removeElement(cacheNames, Constants.SYS_AUTH_CACHE);
    }

    /**
     * 根据缓存名称获取所有键名
     * 
     * @param cacheName 缓存名称
     * @return 键名列表
     */
    public Set<String> getCacheKeys(String cacheName)
    {
        return com.community.common.utils.CacheUtils.getCache(cacheName).keys();
    }

    /**
     * 根据缓存名称和键名获取内容值
     * 
     * @param cacheName 缓存名称
     * @param cacheKey 键名
     * @return 键值
     */
    public Object getCacheValue(String cacheName, String cacheKey)
    {
        return com.community.common.utils.CacheUtils.get(cacheName, cacheKey);
    }

    /**
     * 根据名称删除缓存信息
     * 
     * @param cacheName 缓存名称
     */
    public void clearCacheName(String cacheName)
    {
        com.community.common.utils.CacheUtils.removeAll(cacheName);
    }

    /**
     * 根据名称和键名删除缓存信息
     * 
     * @param cacheName 缓存名称
     * @param cacheKey 键名
     */
    public void clearCacheKey(String cacheName, String cacheKey)
    {
        com.community.common.utils.CacheUtils.remove(cacheName, cacheKey);
    }

    /**
     * 清理所有缓存
     */
    public void clearAll()
    {
        String[] cacheNames = getCacheNames();
        for (String cacheName : cacheNames)
        {
            CacheUtils.removeAll(cacheName);
        }
    }
}
