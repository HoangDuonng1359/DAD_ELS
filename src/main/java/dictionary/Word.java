package dictionary;
public class Word {
    private String target;
    private String explain;
    private boolean check(char c){
        return (c>='a'&&c<='z')||(c>='A'&&c<='Z')||(c>='0'&&c<='9');
    }
    public Word(String target,String explain){
        StringBuilder sb = new StringBuilder(target.trim());
        int i=0;
        while(i<sb.length()&& check(sb.charAt(i))==false){
            sb.deleteCharAt(i);
        }
        if(sb.charAt(0)>='a'&&sb.charAt(0)<='z'){
            sb.setCharAt(i,(char)((int)(sb.charAt(i))-32));
        }
        for(i=1;i<sb.length();i++){
            if(sb.charAt(i-1)==' '&& ((sb.charAt(i)>='a')&&(sb.charAt(i)<='z'))){
                sb.setCharAt(i,(char)((int)(sb.charAt(i))-32));
            }
        }
        this.target=sb.toString();
        this.explain=explain;
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
