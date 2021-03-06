package com.tokopedia.showcase.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tokopedia.showcase.ShowCaseContentPosition;
import com.tokopedia.showcase.ShowCaseDialog;
import com.tokopedia.showcase.ShowCaseBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    private Toolbar toolbar;

    private FloatingActionButton fab;

    private ShowCaseDialog showCaseDialog;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initShowCaseDialog();
    }

    private void initShowCaseDialog() {
        showCaseDialog = new ShowCaseBuilder()
                .textColorRes(android.R.color.white)
                .shadowColorRes(R.color.shadow)
                .titleTextSizeRes(R.dimen.text_title)
                .textSizeRes(R.dimen.text_normal)
                .spacingRes(R.dimen.spacing_normal)
                .backgroundContentColorRes(R.color.blue)
                .circleIndicatorBackgroundDrawableRes(R.drawable.selector_circle_green)
                .prevStringRes(R.string.previous)
                .nextStringRes(R.string.next)
                .finishStringRes(R.string.finish)
                .useCircleIndicator(true)
                .clickable(true)
                .build();
    }

    private void initViews() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.fab = (FloatingActionButton) findViewById(R.id.fab);

        View buttonShowCase = findViewById(R.id.button_show_case);
        buttonShowCase.setOnClickListener(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        llm = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(llm);

        SampleAdapter adapter = new SampleAdapter(Util.getSampleData());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        ArrayList <ShowCaseDialog.ShowCaseObject> showCaseList = new ArrayList<>();
        showCaseList.add(new ShowCaseDialog.ShowCaseObject(
                toolbar,
                null,
                "Above is the <b>toolbar</b>.<br/><br/>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suo enim quisque studio maxime ducitur. Scio enim esse quosdam, qui quavis lingua philosophari possint; Animum autem reliquis rebus ita perfecit, ut corpus; Quo modo autem optimum, si bonum praeterea nullum est? Dicet pro me ipsa virtus nec dubitabit isti vestro beato M. Sic enim censent, oportunitatis esse beate vivere."));
        showCaseList.add(new ShowCaseDialog.ShowCaseObject(
                fab,
                "This is example Title",
                "This description point to <font color=\"#FF0000\"> Floating Action Button </font> on the right.<br/><br/>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suo enim quisque studio maxime ducitur. Scio enim esse quosdam, qui quavis lingua philosophari possint; Animum autem reliquis rebus ita perfecit, ut corpus; Quo modo autem optimum, si bonum praeterea nullum est? Dicet pro me ipsa virtus nec dubitabit isti vestro beato M. Sic enim censent, oportunitatis esse beate vivere.",
                ShowCaseContentPosition.LEFT));
        showCaseList.add(new ShowCaseDialog.ShowCaseObject(
                null,
                null,
                "This is example without anchored View.<br/><br/>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suo enim quisque studio maxime ducitur. Scio enim esse quosdam, qui quavis lingua philosophari possint; Animum autem reliquis rebus ita perfecit, ut corpus; Quo modo autem optimum, si bonum praeterea nullum est? Dicet pro me ipsa virtus nec dubitabit isti vestro beato M. Sic enim censent, oportunitatis esse beate vivere."));

        int completelyVisiblePosition = llm.findFirstCompletelyVisibleItemPosition();
        View itemView = llm.findViewByPosition(completelyVisiblePosition);

        if (itemView!= null) {
            showCaseList.add(new ShowCaseDialog.ShowCaseObject(
                    itemView,
                    null,
                    "This is item in the recyclerView",
                    ShowCaseContentPosition.UNDEFINED,
                    Color.WHITE));

            showCaseList.add(new ShowCaseDialog.ShowCaseObject(
                    itemView.findViewById(R.id.iv_icon),
                    null,
                    "This is icon"));

            showCaseList.add(new ShowCaseDialog.ShowCaseObject(
                    itemView.findViewById(R.id.tv_title),
                    "Color Description",
                    "This describe the color. The white background is written in code. If it is not defined, default will be transparent",
                    ShowCaseContentPosition.UNDEFINED,
                    Color.WHITE));

            showCaseList.add(new ShowCaseDialog.ShowCaseObject(
                    itemView.findViewById(R.id.iv_fav),
                    null,
                    "This is icon (2)",
                    ShowCaseContentPosition.LEFT,
                    Color.WHITE));
        }

        showCaseDialog.show(this, showCaseList);
    }
}
