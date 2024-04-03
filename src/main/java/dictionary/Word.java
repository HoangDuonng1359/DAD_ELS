package dictionary;
public class Word {
    private String target;
    private String explain;
    public Word(String target,String explain){
        this.explain=explain;
        this.target=target.trim().toLowerCase();
    }
    public Word(){
        explain="";
        target="";
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
