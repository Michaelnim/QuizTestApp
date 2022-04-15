package android.bignerdranch.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public void setmTextResId(int textResId) {
        mTextResId = textResId;
    }
}

