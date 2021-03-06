package com.qingmei2.simplerecyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.qingmei2.multitype_binding.binding.Linker;
import com.qingmei2.simplerecyclerview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public final ObservableArrayList<Object> showDatas = new ObservableArrayList<>();

    public final ObservableArrayList<Linker> linkers = new ObservableArrayList<>();

    {
        for (int i = 0; i < 20; i++) {
            if (i % 3 != 0)
                showDatas.add(new Student("学生:" + i));
            else
                showDatas.add(new Teacher("老师" + i, "年龄：" + (20 + i)));
        }
        linkers.add(
                new Linker(
                        o -> o instanceof Student,
                        R.layout.item_student_list
                )
        );
        linkers.add(
                new Linker(
                        o -> o instanceof Teacher,
                        R.layout.item_teacher_list
                )
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
    }

    public void onBindItem(ViewDataBinding binding, Object data, int position) {
        binding.getRoot().setOnClickListener(v ->
                Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show()
        );
    }
}
