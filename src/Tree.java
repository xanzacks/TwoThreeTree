import java.util.*;

public class Tree<T extends Comparable> {
    class Node<T extends Comparable>{
        private ArrayList<T> datas = new ArrayList<T>();
        //0 root, 1 left, 3, middle, if 2 right
        private ArrayList<Node<T>> nodes = new ArrayList<Node<T>>(Arrays.asList(null, null, null, null));
        private int subsize = 0;
        private Node<T> rootOfTree;

        Node(T data){
            datas.add(data);
            subsize = 1;
        }

        public boolean add(Node treeRoot, Node preNode, T data, Node left, Node right){
            if(IsLeaf()){
                rootOfTree = treeRoot;
                for (T temp: datas) {
                    if (temp.equals(data)){
                        return false;
                    }
                }
                datas.add(data);
                subsize++;
                Collections.sort(datas);
                nodes.set(0, preNode);
                split(treeRoot, preNode, data);
                while(preNode != null){preNode.addSizes(); preNode = (Node)preNode.nodes.get(0);}
                treeRoot = rootOfTree;
                return true;
            }
            else{
                rootOfTree = treeRoot;
                int compsmall = data.compareTo(datas.get(0));
                int size = nodes.size();
                for (int i = 1; i < size; i++) {
                    if(datas.size() == 2){
                        int complarge = data.compareTo(datas.get(1));
                        if(compsmall == 0 || complarge == 0){
                            return false;
                        }
                        else if(compsmall < 0 && i == 1){
                            return nodes.get(i).add(treeRoot, this, data, left, right);
                        }
                        else if(complarge > 0 && i == 2){
                            return nodes.get(i).add(treeRoot, this, data, left, right);
                        }
                        else if(i == 3){
                            return nodes.get(i).add(treeRoot, this, data, left, right);
                        }
                    }
                    else{
                        if(compsmall == 0){
                            return false;
                        }
                        else if(compsmall < 0 && i == 1){
                            return nodes.get(i).add(treeRoot, this, data, left, right);
                        }
                        else if(i == 2){
                            return nodes.get(i).add(treeRoot, this, data, left, right);
                        }
                    }
                }
                return true;
            }
        }

        public void split(Node treeRoot, Node<T> preNode, T data){
            if(SplitCondition() && preNode == null){//(1,7,9)
                Node leftNode = new Node(datas.get(0));
                T middleData = datas.get(1);
                Node rightNode = new Node(datas.get(2));
                if(nodes.size() == 5){
                    messyHandler(leftNode, rightNode);
                }
                leftNode.addSizes();
                rightNode.addSizes();
                Node<T> newNode = new Node<T>(middleData);
                treeRoot = newNode;
                newNode.nodes.set(1, leftNode);
                newNode.nodes.set(2, rightNode);
                newNode.nodes.get(1).nodes.set(0, treeRoot);
                newNode.nodes.get(2).nodes.set(0, treeRoot);
                newNode.addSizes();
                rootOfTree = treeRoot;
                treeRoot = rootOfTree;
            } else if(SplitCondition()&&(preNode != null && preNode.datas.size() == 2)){
                Node leftNode = new Node(datas.get(0));
                T middleData = datas.get(1);
                Node rightNode = new Node(datas.get(2));
                preNode.datas.add(middleData);
                Collections.sort(preNode.datas);
                preNode.nodes.add(null);
                if(nodes.size() == 5){
                    messyHandler(leftNode, rightNode);
                }
                leftNode.addSizes();
                rightNode.addSizes();
                if(datas.get(0).compareTo(preNode.datas.get(0)) < 0){
                    preNode.nodes.set(1, leftNode);
                    preNode.nodes.set(4, rightNode);
                }else if((datas.get(0).compareTo(preNode.datas.get(0)) > 0 && datas.get(0).compareTo(preNode.datas.get(1)) < 0)) {
                    preNode.nodes.set(3, leftNode);
                    preNode.nodes.set(4, rightNode);
                }else{
                    preNode.nodes.set(2, rightNode);
                    preNode.nodes.set(4, leftNode);
                }
                leftNode.nodes.set(0, preNode);
                rightNode.nodes.set(0, preNode);
                preNode.split(treeRoot, preNode.nodes.get(0), middleData);
            } else if(SplitCondition()){
                Node leftNode = new Node(datas.get(0));
                T middleData = datas.get(1);
                Node rightNode = new Node(datas.get(2));
                preNode.datas.add(middleData);
                Collections.sort(preNode.datas);
                if(nodes.size() == 5){
                    messyHandler(leftNode, rightNode);
                }
                leftNode.addSizes();
                rightNode.addSizes();
                if(datas.get(0).compareTo(preNode.datas.get(0)) < 0){
                    preNode.nodes.set(1, leftNode);
                    preNode.nodes.set(3, rightNode);
                }else{
                    preNode.nodes.set(2, rightNode);
                    preNode.nodes.set(3, leftNode);
                }
                leftNode.nodes.set(0, preNode);
                rightNode.nodes.set(0, preNode);
                preNode.split(treeRoot, preNode.nodes.get(0), middleData);
                treeRoot = rootOfTree;
            }
        }

        public void messyHandler(Node<T> leftNode, Node<T> rightNode){
            leftNode.nodes.set(1, nodes.get(1));
            nodes.get(1).nodes.set(0, leftNode);
            rightNode.nodes.set(2, nodes.get(2));
            nodes.get(2).nodes.set(0, rightNode);
            T middleData = (T) nodes.get(3).datas.get(0);
            T newdata = (T) nodes.get(4).datas.get(0);
            if(middleData.compareTo(newdata) > 0){
                leftNode.nodes.set(2, nodes.get(4));
                nodes.get(4).nodes.set(0, leftNode);
                rightNode.nodes.set(1, nodes.get(3));
                nodes.get(3).nodes.set(0, rightNode);
            } else{
                leftNode.nodes.set(2, nodes.get(3));
                nodes.get(3).nodes.set(0, leftNode);
                rightNode. nodes.set(1, nodes.get(4));
                nodes.get(4).nodes.set(0, rightNode);
            }
        }

        public boolean IsLeaf(){
            for(int i = 1; i < nodes.size(); i++){
                if (nodes.get(i) != null){
                    return false;
                }
            }
            return true;
        }

        void addSizes(){
            subsize = datas.size();
            for (int i = 1; i < nodes.size(); i++) {
                if(nodes.get(i) != null){subsize = nodes.get(i).subsize + subsize;}
            }
        }

        T getHelper(int count){
            if(IsLeaf()){
                if(count == 0){return datas.get(0);}
                else{return datas.get(1);}
            }
            Node<T> left= nodes.get(1);
            Node<T> right= nodes.get(2);
            Node<T> middle = new Node<T>(null);
            if(datas.size() == 2){ middle = nodes.get(3); }
            int num = left.subsize;
            if(count - num < 0){
                return left.getHelper(count);
            }
            if(datas.size() == 1 && count - 1 - left.subsize < 0){
                return datas.get(0);
            }
            else if(datas.size() == 2 && count - middle.subsize - 2 - left.subsize < 0){
                count = count - num;
                middle = nodes.get(3);
                if(count == 0){
                    return datas.get(0);
                }
                count--;
                if(count - middle.subsize < 0){
                    return middle.getHelper(count);
                }
                count = count - middle.subsize;
                if(count == 0){
                    return datas.get(1);
                }
            }
            else{
                if(datas.size() == 2){
                    count = count - num -middle.subsize - 2;
                }
                else{
                    count = count - num - 1;
                }
                return right.getHelper(count);
            }
            return null;
        }

        public boolean SplitCondition() {
            return datas.size() == 3;
        }

    }
    Node<T> root;
    int TreeSize = 0;

    public void insert(T data){
        if(root == null){
            Node temp = new Node(data);
            root = temp;
            TreeSize++;
        }else{
            Node<T> temp = root;
            if(temp.add(root, null, data, null, null)){
                TreeSize++;
            };
            root = temp.rootOfTree;
        }
    }

    public Node find(T tar){
        Node<T> tempRoot = root;
        while(tempRoot != null){
            if(tempRoot.datas.size() == 1){
                if(tempRoot.datas.get(0).compareTo(tar) > 0){
                    tempRoot = tempRoot.nodes.get(1);
                }
                else if(tempRoot.datas.get(0).equals(tar)){
                    return tempRoot;
                }
                else{
                    tempRoot = tempRoot.nodes.get(2);
                }
            }
            else if(tempRoot.datas.size() == 2){
                if(tempRoot.datas.get(0).compareTo(tar) > 0){
                    tempRoot = tempRoot.nodes.get(1);
                }
                else if(tempRoot.datas.get(0).equals(tar) || tempRoot.datas.get(1).equals(tar)){
                    return tempRoot;
                }
                else if(tempRoot.datas.get(1).compareTo(tar) < 0){
                    tempRoot = tempRoot.nodes.get(2);
                }
                else{
                    tempRoot = tempRoot.nodes.get(3);
                }
            }
        }
        return tempRoot;
    }

    public int size(T tar){
        Node TarNode = find(tar);
        if(TarNode == null){
            return 0;
        }
        else{
            return TarNode.subsize;
        }
    }

    public int size(){
        return TreeSize;
    }

    public T get(int count){
         T tar = root.getHelper(count);
         return tar;
    }

}
