package test.study;

/**
 * 二叉搜索树
 * Created by ${杨明哲} on 2016/5/15.
 */
public class TestSerachSecTree {
    private int data;
    private TestSerachSecTree parentNode;
    private TestSerachSecTree leftNode;
    private TestSerachSecTree rightNode;

    public TestSerachSecTree() {

    }

    public TestSerachSecTree (int data, TestSerachSecTree parentNode,TestSerachSecTree leftNode, TestSerachSecTree rightNode) {
        this.data = data;
        this.parentNode = parentNode;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }


    /**
     * 添加节点
     * @param data
     * @param targetTree
     * @return
     */
    public static void addTree(int data, TestSerachSecTree targetTree) {
        if (data == targetTree.data) {
            return ;
        } else if (data < targetTree.data) {
            if (targetTree.leftNode == null) {
                targetTree.leftNode = new TestSerachSecTree(data, targetTree,null,null);
            } else {
                addTree(data, targetTree.leftNode);
            }
        } else {
            if (targetTree.rightNode == null) {
                targetTree.rightNode = new TestSerachSecTree(data, targetTree,null,null);
            } else {
                addTree(data, targetTree.rightNode);
            }
        }
    }

    /**
     * 删除节点
     * @param data
     * @param targetTree
     * @return
     */
    public static TestSerachSecTree delTree(int data, TestSerachSecTree targetTree) {
        //找到节点
        TestSerachSecTree curNode = searchNode(data, targetTree);
        if (curNode.parentNode == null) {
            if (curNode.leftNode == null && curNode.rightNode != null) {
                targetTree = curNode.rightNode;
                targetTree.parentNode = null;
            } else if (curNode.leftNode != null && curNode.rightNode == null) {
                targetTree = curNode.leftNode;
                targetTree.parentNode = null;
            } else {

                TestSerachSecTree  rightNode = getRightNode(curNode.leftNode);
                targetTree = curNode.leftNode;
                rightNode.rightNode = curNode.rightNode;
                curNode.rightNode.parentNode = targetTree;
                targetTree.parentNode = null;
            }
        }else  if (curNode.leftNode == null && curNode.rightNode == null) {
            if (curNode.parentNode.data > curNode.data) {
                curNode.parentNode.leftNode = null;
            } else {
                curNode.parentNode.rightNode = null;
            }
        } else if (curNode.leftNode != null && curNode.rightNode == null) {
            if (curNode.parentNode.data > curNode.data) {
                curNode.parentNode.leftNode = curNode.leftNode;
                curNode.leftNode.parentNode = curNode.parentNode;
            } else {
                curNode.parentNode.rightNode = curNode.leftNode;
                curNode.leftNode.parentNode=curNode.parentNode;
            }
        } else if (curNode.leftNode == null && curNode.rightNode != null) {
            if (curNode.parentNode.data > curNode.data) {
                curNode.parentNode.leftNode = curNode.rightNode;
                curNode.rightNode.parentNode = curNode.parentNode;
            } else {
                curNode.parentNode.rightNode = curNode.rightNode;
                curNode.rightNode.parentNode = curNode.parentNode;
            }
        } else {
            // 当前左节点的最右侧节点
            TestSerachSecTree  lasetRightNode = getRightNode(curNode.leftNode);
            if (curNode.data > curNode.parentNode.data) {
                curNode.parentNode.rightNode = curNode.leftNode;
            } else {
                curNode.parentNode.leftNode = curNode.leftNode;
            }
            curNode.leftNode.parentNode = curNode.parentNode;

//            if (lasetRightNode.data == curNode.leftNode.data) {
//                curNode.leftNode.rightNode = curNode.rightNode;;
//            } else {
                lasetRightNode.rightNode = curNode.rightNode;
                curNode.rightNode.parentNode = lasetRightNode;
//            }


        }
        return targetTree;
    }

    /**
     * 删除节点
     * @param data
     * @param targetTree
     * @return
     */
    public static TestSerachSecTree searchNode(int data, TestSerachSecTree targetTree) {
        if (targetTree.data == data) {
            return targetTree;
        } else if (targetTree.data < data) {
            return searchNode(data, targetTree.rightNode);
        } else {
            return searchNode(data, targetTree.leftNode);
        }
    }

    /**
     * 获取最右侧节点
     * @param targetTree
     * @return
     */
    public static TestSerachSecTree getRightNode(TestSerachSecTree targetTree) {
        if (targetTree.rightNode == null) {
            return targetTree;
        } else {
            return  getRightNode(targetTree.rightNode);
        }
    }



    public static void leftOrder(TestSerachSecTree tree) {
        if (tree != null) {
            leftOrder(tree.leftNode);
            System.out.print(tree.data + "--");
            leftOrder(tree.rightNode);
        }
    }


    public static void main(String[] args) {
        Integer[] array = new Integer[]{60,49,64,40,52,70,20,46,50,58,65,90,45,47,48};
        //Integer[] array = new Integer[]{60,50,70,51,80,52,90};
        //Integer[] array = new Integer[]{60,50,70,40,65,30,64};
        TestSerachSecTree parentTree = new TestSerachSecTree(array[0], null,null,null);
        int arayLen = array.length;
        for (int i = 1; i < arayLen; i++) {
            addTree(array[i], parentTree);
        }
        // 删除节点
        leftOrder(parentTree);
        System.out.println();
//        System.out.println();
        parentTree = delTree(49, parentTree);
        leftOrder(parentTree);
        System.out.println();
        parentTree = delTree(52, parentTree);
        leftOrder(parentTree);
        System.out.println();
        addTree(51, parentTree);
        leftOrder(parentTree);
        System.out.println();
//        delTree(51, parentTree);
//        leftOrder(parentTree);
    }

}
