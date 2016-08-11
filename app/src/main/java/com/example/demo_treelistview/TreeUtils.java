package com.example.demo_treelistview;

import java.util.List;

/**
 * Created by dada on 0011,2016/8/11.
 */
public class TreeUtils {

    //每次单击的item位置
    private static int clickPosition;

    /**
     * 增加或者删除clickItem里面的子列表
     *
     * @param showList  ListView中的item数据集合
     * @param clickItem 点击的item所对应的node数据
     */
    public static void filterNodeList(List<Node> showList, Node clickItem) {
        if (!clickItem.isExpand()) {// 需要展示,添加
            for (int i = 0; i < showList.size(); i++) {// 找到对应位置插入
                if (showList.get(i) == clickItem) {
                    clickPosition = i;
                    showChildNode(showList, clickItem);
                    clickItem.setExpand(true);
                    break;
                }
            }
        } else {// 需要隐藏,删除
            hideChildNode(showList, clickItem);
            clickItem.setExpand(false);
        }
    }

    /**
     * 递归隐藏(删除)某一个节点下的所有子node
     *
     * @param showList 装载node的集合，即ListView中的item数据
     * @param nodeItem 要从showList集合中删除该节点下的所有子节点
     */
    private static void hideChildNode(List<Node> showList, Node nodeItem) {
        for (Node item : nodeItem.getChildList()) {
            // item.setExpand(false);//还原每个item状态
            showList.remove(item);
            // 如果当前item存在孩子节点,递归删除
            if (item.getChildCount() > 0) {
                hideChildNode(showList, item);
            }
        }
    }

    /**
     * 递归获取之前状态,如果之前已经展开过就一并加入到List中，即展开nodeItem这个节点下的所有子节点
     *
     * @param showList 装载node的集合，即ListView中的item数据
     * @param nodeItem 要添加此节点下的所有子节点到showList集合中
     */
    private static void showChildNode(List<Node> showList, Node nodeItem) {
        for (int i = 0; i < nodeItem.getChildCount(); i++) {
            // 如果添加的节点是展开的就递归去加入他所有的子节点
            Node node = nodeItem.getChildList().get(i);
            showList.add(++clickPosition, node); //这里的clickPosition必须是全局的
            if (node.isExpand()) {
                showChildNode(showList, node);
            }
        }
    }
}
