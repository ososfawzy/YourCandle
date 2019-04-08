package com.yourcandle.yourcandle.Activities.Home.ListenToFile;

/**
 * Created by osama on 6/22/2018.
 */

public class BidiResult
{
    private String Text;
    public final String getText()
    {
        return Text;
    }
    public final void setText(String value)
    {
        Text = value;
    }
    private boolean IsRtl;
    public final boolean getIsRtl()
    {
        return IsRtl;
    }
    public final void setIsRtl(boolean value)
    {
        IsRtl = value;
    }

    public BidiResult(String text, boolean isRtl)
    {
        this.setText(text);
        this.setIsRtl(isRtl);
    }
}

