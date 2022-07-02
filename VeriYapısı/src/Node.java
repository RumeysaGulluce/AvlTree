
public class Node {
    private String deger;
    private  int yukseklik;
    private Node node;
    private Node sagNode;
    private Node solNode;
    
    
    public Node(String deger){
        this.deger=deger;
        
    }
    Node(){
        this.deger=null;
        this.node=null;
        this.sagNode=null;
        this.solNode=null;
        this.yukseklik=0;
    }

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
    }

    public int getYükseklik() {
        return yukseklik;
    }

    public void setYükseklik(int yukseklik) {
        this.yukseklik = yukseklik;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getSagNode() {
        return sagNode;
    }

    public void setSagNode(Node sagNode) {
        this.sagNode = sagNode;
    }

    public Node getSolNode() {
        return solNode;
    }

    public void setSolNode(Node solNode) {
        this.solNode = solNode;
    }
    
}
