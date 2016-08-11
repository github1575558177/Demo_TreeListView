package com.example.demo_treelistview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘荣达 on 0011,2016/8/11.
 */
public class Node {


    //当前节点层级
    private int deepLevel;

    //当前节点内容
    private String label;

    //父节点
    private Node parentNode;

    //是否展开了
    private boolean isExpand;

    //子节点列表
    private List<Node> childList;

    public Node(Node parent, String label) {
        this.parentNode = parent;
        this.label = label;
        if (parent != null) {
            if (parent.childList == null) {
                parent.childList = new ArrayList<Node>();
            }
            parent.childList.add(this);
            this.deepLevel = parent.deepLevel + 1;
        }
    }

    public int getDeepLevel() {
        return deepLevel;
    }

    public void setDeepLevel(int deepLevel) {
        this.deepLevel = deepLevel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public List<Node> getChildList() {
        return childList;
    }

    public int getChildCount() {
        if (childList == null) {
            return 0;
        } else {
            return childList.size();
        }
    }
}
