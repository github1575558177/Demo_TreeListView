# Demo_TreeListView
用ListView打造多层级的树形结构

原理分析：
本质上就是当点击item时，对ListView的数据集合中的相应位置上进行 添加 或 删除 数据，然后 notifyDataSetChanged()

重点：
1. Node 类的数据结构的设计（也是item对应的数据结构的类型）
2. TreeAdapter 中 getView() 方法的设计（包括item对象的重用及对item内容的设置：ImageView和TextView）
3. 点击item时，展开或是隐藏子节点的递归代码的实现（TreeUtils）
