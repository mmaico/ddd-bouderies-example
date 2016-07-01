package com.crm.infrastructure.helpers.businessmodel.node;


public class TargetNode {
    private final Object target;


    public TargetNode(Object object) {
        this.target = object;
    }

    public Object getObject() {
        return target;
    }

    public static TargetNode newTarget(Object object) {
        return new TargetNode(object);
    }
}
