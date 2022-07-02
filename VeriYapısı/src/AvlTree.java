
public class AvlTree 
{
    private Node kök;
     public int size = 0;
     
    public AvlTree(){
        kök=null;
        size=0;
    }
    public AvlTree(Node kök){
        this.kök=kök;
    }
     
    public void ekle(String data) {
       kök=ekle(kök,data);
    }
  
    public void traverse() {
         if(kök==null){
            return;
        }
         OrderTraverse(kök);
    }

    public void delete(String data) {
     kök=delete(kök,data);
    }
 
    public void yaz() {
       Node nesne=getKök();
        if(nesne==null){
            System.out.println("Ağaç yokk");
        }
        else{
            System.out.print("kök -> "+getKök().getDeger());
            yazdirmaislemi(nesne);
        }
    }
     
    public void yazdirmaislemi(Node gelen){
        if(gelen.getSolNode()!=null){
            System.out.print(" Sol -> "+gelen.getSolNode().getDeger());
            yazdirmaislemi(gelen.getSolNode());
        }
        if(gelen.getSagNode()!=null) {
           System.out.print(" Sag -> "+gelen.getSagNode().getDeger());
           yazdirmaislemi(gelen.getSagNode());
        }
    }



    public void OrderTraverse(Node node){
        if(node.getSolNode()!=null){
            OrderTraverse(node.getSolNode());
        }
        System.out.println(node);
        if(node.getSagNode()!=null){
             OrderTraverse(node.getSagNode());
        }
    } 
    public Node rightRotate(Node R) {
 
        Node x = R.getSolNode();
        Node T2 = x.getSagNode();

        x.setSagNode(R);
        R.setSolNode(T2);

        R.setYükseklik(Math.max(yukseklik(R.getSolNode()), yukseklik(R.getSagNode())) + 1);
        x.setYükseklik(Math.max(yukseklik(x.getSolNode()), yukseklik(x.getSagNode())) + 1);
        return x;
    }

    public Node leftRotate(Node S) {
     
        Node y = S.getSagNode();

        Node T2 = S.getSolNode();
   
        y.setSolNode(S);
        S.setSagNode(T2);
        System.out.println("leftRotate..");
        S.setYükseklik(Math.max(yukseklik(S.getSolNode()), yukseklik(S.getSagNode())) + 1);
        y.setYükseklik(Math.max(yukseklik(y.getSolNode()), yukseklik(y.getSagNode())) + 1);
        System.out.println("leftRotate..");
        return y;
    }
    public Node leftRightRotation(Node node) { 
        node.setSolNode(leftRotate(node.getSolNode()));
        return rightRotate(node);
    }
    public Node rightLeftRotation(Node node) {
        node.setSagNode(rightRotate(node.getSagNode()));
        return leftRotate(node);
    }
    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return  yukseklik(node.getSolNode()) - yukseklik(node.getSagNode());
    }
    
    

    public Node ekle(Node node,String veri){ 
        Node n = new Node(veri);
	if(kök == null){
	    kök= n;
            //System.out.println(kök.getDeger()); 
        }
	else{
            Node n2=node;
         
            if(n2.getDeger().compareTo(veri)>0){
                if(n2.getSolNode() == null){
                    n2.setSolNode(n);
                    //System.out.println(n2.getSolNode().getDeger());
                    //System.out.println("");
                }
                else{
                    node=n2.getSolNode();
                    ekle(node,veri); 
                }
            }
            else if(n2.getDeger().compareTo(veri)<0){
                if(n2.getSagNode()== null){
                    n2.setSagNode(n);
                    //System.out.println(n2.getSagNode().getDeger()); 
                }
                else{
                    node=n2.getSagNode();
                    ekle(node,veri); 
                }
               
            } 
        
        node.setYükseklik(Math.max(yukseklik(node.getSolNode()),yukseklik(node.getSagNode()))+1);
        node=settleViolation(node,veri);
        }
        return node;
    }
    
    public Node delete(Node node, String data) {
        if (node == null) {
            return node;
        }
        if (data.compareTo(node.getDeger()) < 0) {
            node.setSolNode(delete(node.getSolNode(), data));
        } else if (data.compareTo(node.getDeger()) > 0) {
            node.setSolNode(delete(node.getSagNode(), data));
        } 
        else {
            
            if (node.getSolNode() == null && node.getSagNode() == null) {
                System.out.println("Yaprak düğüm kaldırıldı...");
                return null;
            }
            if (node.getSolNode() == null) {
                System.out.println("Çocuk kaldırıldı...");
                Node geciciNode = node.getSagNode();
                node = null;
                return geciciNode;
            } else if (node.getSagNode() == null) {
                System.out.println("Sol çocuk kaldırılıyor");
                Node geciciNode = node.getSolNode();
                node = null;
                return geciciNode;

            }
            System.out.println("İki çocuklu düğümü kaldır...");

            Node geciciNode = getPredecessor(node.getSolNode());

            node.setDeger(geciciNode.getDeger());

            node.setSolNode(delete(node.getSolNode(), geciciNode.getDeger()));

            node.setYükseklik(Math.max(yukseklik(node.getSolNode()), yukseklik(node.getSagNode())) + 1);

            node = settileDeletion(node);
        }


        return node;
    }
    
    public Node getPredecessor(Node node) {
        Node getPredecessor = node;
        while (getPredecessor.getSagNode() != null) {
            getPredecessor = getPredecessor.getSagNode();
        }
        return getPredecessor;

    }
    private Node settileDeletion(Node node) {
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.getSolNode()) > 0) {
                return rightRotate(node);
            } else {
                node.setSolNode(leftRotate(node.getSolNode()));
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.getSagNode()) < 0) {
                return leftRotate(node);
            } else {
                node.setSolNode(rightRotate(node.getSagNode()));
                return leftRotate(node);
            }
        }
       
        return node;
    }
    public Node settleViolation(Node node,String veri){
       
        int denge=getBalanceFactor(node);
        if(denge>1 && veri.compareTo(node.getSolNode().getDeger())==-1)//solun solu
            return rightRotate(node);
        if(denge<-1 && veri.compareTo(node.getSagNode().getDeger())==1)//sagın sagı
            return leftRotate(node);
        if(denge>1 && veri.compareTo(node.getSolNode().getDeger())==1){//solun sagı
            node.setSolNode(leftRotate(node.getSolNode()));
            return  rightRotate(node);
        }
         if(denge<-1 && veri.compareTo(node.getSagNode().getDeger())==1){//sagın solu
            node.setSagNode(rightRotate(node.getSagNode()));
             return leftRotate(node);
        }
        
        
        return node;
    }

    private int yukseklik (Node node) {
        if (node == null)
            return -1;
        return node.getYükseklik();
    }
    
   
    public Node getKök() {
        return kök;
    }

    public void setKök(Node kök) {
        this.kök = kök;
    }
    public void preOrderHelper(Node node) {
        if (node!= null) {
            System.out.println(node.getDeger() + " ");
            preOrderHelper(node.getSolNode());
            preOrderHelper(node.getSagNode());

        }
    }
   
   
     public void ataBul(Node n,String veri){

	if(kök.getDeger().equals(veri)){
	    System.out.println("null");
        }
        else if(kök.getSolNode().getDeger().equals(veri)){
            System.out.println("null");
        }
        else if(kök.getSagNode().getDeger().equals(veri)){
            System.out.println("null");
        }
        while(true){
            if(veri.compareTo(n.getDeger())<0){
                Node n2=n;
                    if(n.getSolNode().getDeger().equals(veri)){    
                        break;
                    }
                    else if(veri.compareTo(n.getSolNode().getDeger())<0){
                        System.out.println("\n");
                        System.out.print(n.getDeger()+" ");
                         n=n.getSolNode();
                    }
                    else if(veri.compareTo(n.getSolNode().getDeger())>0){
                        System.out.println("\n");
                        System.out.print(n.getDeger()+" ");
                          n=n.getSagNode();
                    }     
            }
           else if(veri.compareTo(n.getDeger())>0){
                Node n2=n;

                   if(n.getSagNode().getDeger().equals(veri)){ 
                        break;
                    }
                    else if(veri.compareTo(n.getSagNode().getDeger())<0){
                        System.out.print(n.getDeger()+" ");
                        System.out.println("\n");
                        n=n.getSolNode();

                    }
                    else if(veri.compareTo(n.getSagNode().getDeger())>0){
                        System.out.print(n.getDeger()+" ");
                        System.out.println("\n");
                        n=n.getSagNode();

                    }     
            }
            else{
                break;
            }
        }
    }
}


