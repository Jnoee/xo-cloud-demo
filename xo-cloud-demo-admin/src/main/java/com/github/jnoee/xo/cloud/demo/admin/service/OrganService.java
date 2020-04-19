package com.github.jnoee.xo.cloud.demo.admin.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jnoee.xo.cloud.demo.admin.constants.AdminIds;
import com.github.jnoee.xo.cloud.demo.admin.entity.Organ;
import com.github.jnoee.xo.cloud.demo.admin.enums.EnabledStatus;
import com.github.jnoee.xo.jpa.audit.annotation.DetailLog;
import com.github.jnoee.xo.jpa.audit.annotation.DetailLog.LogType;
import com.github.jnoee.xo.jpa.audit.annotation.SimpleLog;
import com.github.jnoee.xo.jpa.dao.Dao;
import com.github.jnoee.xo.message.MessageSource;
import com.github.jnoee.xo.utils.BeanUtils;

@Service
public class OrganService {
  @Resource
  private Dao<Organ> organDao;
  @Autowired
  private MessageSource messageSource;

  @Transactional(readOnly = true)
  public Organ get(Long id) {
    Organ organ = organDao.get(id);
    if (null == organ) {
      messageSource.thrown("e.organ.get.not-exist", id);
    }
    return organ;
  }

  @Transactional
  @SimpleLog(code = "l.organ.add", vars = "organ.name")
  public void create(Organ organ) {
    if (organ.getParent() == null) {
      messageSource.thrown("e.organ.add.no-parent");
    }
    organDao.save(organ);
  }

  @Transactional
  @DetailLog(target = "organ", code = "l.organ.edit", vars = "organ.name", type = LogType.ALL)
  public void update(Organ organ) {
    Organ origOrgan = get(organ.getId());
    BeanUtils.copyFieldsExclude(organ, origOrgan, "ordinal,enabled");
  }

  @Transactional
  @DetailLog(target = "organ", code = "l.organ.del", vars = "organ.name", type = LogType.ORIG)
  public void delete(Organ organ) {
    organDao.remove(organ);
  }

  @Transactional
  @SimpleLog(code = "l.organ.enable", vars = "organ.name")
  public void enable(Organ organ) {
    organ.setStatus(EnabledStatus.ENABLED);
  }

  @Transactional
  @SimpleLog(code = "l.organ.disable", vars = "organ.name")
  public void disable(Organ organ) {
    organ.setStatus(EnabledStatus.DISABLED);
  }

  /**
   * 获取系统根机构。
   * 
   * @return 返回系统根机构。
   */
  public Organ getRoot() {
    return organDao.get(AdminIds.ORGAN_ID);
  }
}
