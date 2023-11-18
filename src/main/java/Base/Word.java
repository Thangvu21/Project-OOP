package Base;

public class Word {
    private String word_target;
    private String word_explain;
    private String pronounce;

    public Word(String word_target, String word_explain, String pronounce) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.pronounce = pronounce;
    }


    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String showWord() {
        return String.format("|%20s|%20s|%20s|\n",
                word_target, word_explain, pronounce);
    }
}
