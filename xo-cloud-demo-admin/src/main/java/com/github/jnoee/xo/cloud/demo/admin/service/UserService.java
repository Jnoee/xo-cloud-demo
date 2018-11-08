package com.github.jnoee.xo.cloud.demo.admin.service;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jnoee.xo.auth.AuthHelper;
import com.github.jnoee.xo.auth.server.AuthUserService;
import com.github.jnoee.xo.cloud.demo.admin.constants.AdminIds;
import com.github.jnoee.xo.cloud.demo.admin.entity.Actor;
import com.github.jnoee.xo.cloud.demo.admin.entity.User;
import com.github.jnoee.xo.cloud.demo.core.token.UserAuthToken;
import com.github.jnoee.xo.jpa.audit.annotation.DetailLog;
import com.github.jnoee.xo.jpa.audit.annotation.DetailLog.LogType;
import com.github.jnoee.xo.jpa.audit.annotation.SimpleLog;
import com.github.jnoee.xo.jpa.dao.Dao;
import com.github.jnoee.xo.message.MessageSource;
import com.github.jnoee.xo.utils.StringUtils;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements AuthUserService<User> {
  @Resource
  private Dao<User> userDao;
  @Resource
  private Dao<Actor> actorDao;
  @Autowired
  private AuthHelper authHelper;
  @Autowired
  private MessageSource messageSource;

  @Override
  @SimpleLog(code = "l.user.logon", vars = "username")
  public void login(String username, String password) {
    AuthenticationToken token = new UsernamePasswordToken(username, password);
    Subject subject = SecurityUtils.getSubject();
    subject.login(token);
  }

  @Override
  public User getByUsername(String username) {
    return userDao.findUnique("username", username);
  }

  @Override
  public UserAuthToken genAuthToken(String username) {
    User user = getByUsername(username);
    UserAuthToken token = new UserAuthToken();
    token.setId(user.getId());
    token.setUsername(username);
    token.setPrivilegs(user.getDefaultActor().getRole().getPrivilegs());
    return token;
  }

  @Override
  public User getLogonUser() {
    UserAuthToken token = getAuthToken();
    return get(token.getId());
  }

  public User get(String id) {
    return userDao.get(id);
  }

  @Transactional
  @DetailLog(target = "user", type = LogType.NEW, code = "l.user.add", vars = "user.username")
  public void create(User user) {
    if (!userDao.isUnique(user, "username")) {
      messageSource.thrown("E001");
    }
    if (StringUtils.isEmpty(user.getPassword())) {
      user.setPassword(authHelper.encodePassword(AdminIds.NEW_SALT));
    }
    userDao.save(user);

    Actor defaultActor = user.getDefaultActor();
    defaultActor.setUser(user);
    actorDao.save(defaultActor);
  }
}
