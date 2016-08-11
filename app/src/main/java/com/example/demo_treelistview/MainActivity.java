package com.example.demo_treelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<Node> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.lv);

        initDatas();

        mListView.setAdapter(new TreeAdapter(this, mDataList));
    }

    private void initDatas() {
        mDataList = new ArrayList<Node>();
        // is not used 的都是叶子节点
        Node root1 = new Node(null, "root1");
        Node node1_1 = new Node(root1, "node1_1");
        Node node1_2 = new Node(root1, "node1_2");
        Node node1_2_1 = new Node(node1_2, "node1_2_1");
        Node node1_3 = new Node(root1, "node1_3");
        Node root2 = new Node(null, "root2");
        Node node2_1 = new Node(root2, "node_2_1");
        Node node2_2 = new Node(root2, "node_2_2");
        Node node2_3 = new Node(root2, "node_2_3");
        Node node2_4 = new Node(root2, "node_2_4");
        Node node2_5 = new Node(root2, "node_2_5");
        Node root3 = new Node(null, "root3");
        // 添加根节点到ListView中
        mDataList.add(root1);
        mDataList.add(root2);
        mDataList.add(root3);
    }
}
