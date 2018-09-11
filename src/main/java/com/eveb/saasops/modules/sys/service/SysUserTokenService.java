package com.eveb.saasops.modules.sys.service;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.sys.dao.SysUserTokenDao;
import com.eveb.saasops.modules.sys.entity.SysUserTokenEntity;
import com.eveb.saasops.modules.sys.oauth2.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class SysUserTokenService {

    @Autowired
    private SysUserTokenDao sysUserTokenDao;
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    public SysUserTokenEntity queryByUserId(Long userId) {
        return sysUserTokenDao.queryByUserId(userId);
    }

    public void save(SysUserTokenEntity token) {
        sysUserTokenDao.save(token);
    }

    public void update(SysUserTokenEntity token) {
        sysUserTokenDao.update(token);
    }

    public R createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = queryByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }

        R r = R.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }

    public void logout(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        update(tokenEntity);
    }
}
