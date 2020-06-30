package com.example.fyp.Util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.fyp.R;

public class CustomDialogClass extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button yes, no;
    public CustomDialogClass(@NonNull Context context) {
        super(context);
    }
    public CustomDialogClass(Activity a)
    {
        super(a);
        this.c=a;

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                c.finish();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
