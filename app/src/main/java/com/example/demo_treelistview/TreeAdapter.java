package com.example.demo_treelistview;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by dada on 0011,2016/8/11.
 */
public class TreeAdapter extends BaseAdapter {

    private Context context;

    //当前需要显示的数据
    private List<Node> mDataList;

    //节点的点击事件
    NodeOnClickListener mNodeOnClickListener;

    public TreeAdapter(Context context, List<Node> data) {
        this.context = context;
        this.mDataList = data;
        mNodeOnClickListener = new NodeOnClickListener();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Node getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item, null);//实际上就是调用LayoutInflater.from(context).inflate()。这里若是第三个参数写成parent的话，运行时会出错，不知道为什么
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.label = (TextView) convertView.findViewById(R.id.label);
            convertView.setTag(holder);
            convertView.setOnClickListener(mNodeOnClickListener);//只需要给要创建的几个item设置监听器，其他的都是复用回收池中的item对象
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Node node = getItem(position);
        // 设置深度,层级越深距离左边越远
        convertView.setPadding(30 * node.getDeepLevel() + 10, 10, 10, 10);
        if (node.getChildCount() == 0) {// 如果沒有子节点说明为叶子节点,去掉icon
            holder.icon.setVisibility(View.INVISIBLE);//View.INVISIBLE：虽然隐藏了，但是还在父视图上占据空间，View.GONE：隐藏此View，并且在父视图中不再占据空间
        } else {
            holder.icon.setVisibility(View.VISIBLE);
            if (node.isExpand()) {//如果需要显示判断一下是否是需要显示展开的样式
                holder.icon.setImageResource(R.mipmap.expand);
            } else {
                holder.icon.setImageResource(R.mipmap.unexpand);
            }
        }

        holder.label.setTag(node);//label的tag里面存放Node,方便点击事件处理
        holder.label.setText(node.getLabel());
        return convertView;
    }

    /**
     * 目录点击事件
     */
    class NodeOnClickListener implements OnClickListener {

        @Override
        public void onClick(android.view.View v) {
            ViewHolder holder = (ViewHolder) v.getTag();
            Node node = (Node) holder.label.getTag();
            // 如果存在孩子节点就需要做展开或者隐藏操作
            if (node.getChildCount() > 0) {
                TreeUtils.filterNodeList(mDataList, node);
            } else {
                Toast.makeText(context, "点击了:" + node.getLabel(), Toast.LENGTH_SHORT).show();
                return;
            }
            notifyDataSetChanged();
        }
    }

    private class ViewHolder {
        private ImageView icon;
        private TextView label;
    }
}
