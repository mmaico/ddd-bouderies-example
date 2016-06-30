package com.crm.infrastructure.helpers.businessmodel;

import com.crm.infrastructure.helpers.businessmodel.annotations.CustomType;
import com.crm.infrastructure.helpers.businessmodel.annotations.Reference;
import com.google.common.collect.Lists;

import java.util.List;


public enum AnnotationsIgnoredOnCopy {

  IGNORED_ON_COPY(Lists.newArrayList(CustomType.class, Reference.class));

  private List<Class> annToIgnoreOnCopy;

  AnnotationsIgnoredOnCopy (List<Class> list) {
    this.annToIgnoreOnCopy = list;
  }

  public List<Class> getAnn() {
    return annToIgnoreOnCopy;
  }
}
