package com.dhw.propertyanimation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.dhw.propertyanimation.adapter.MyAdapter;
import com.dhw.propertyanimation.bean.GoodsBean;
import com.dhw.propertyanimation.presenter.GoodPresenter;
import com.dhw.propertyanimation.view.GoodsContract;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import java.util.List;

//HomeActivity类
public class HomeActivity extends AppCompatActivity implements GoodsContract.IGoodView{
    private RecyclerView recyclerView;
    private GoodPresenter presenter;
    private MyAdapter adapter;
    private SpringView springView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = new GoodPresenter(this);

        recyclerView = (RecyclerView)findViewById(R.id.my_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);
        springView = (SpringView)findViewById(R.id.my_springview);

        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultFooter(this));



        presenter.showData();
    }

    @Override
    public void setData(final List<GoodsBean.DataBean> data) {
        adapter.addData(data);
        springView.onFinishFreshAndLoad();
        //点击事件
        adapter.setListener(new MyAdapter.SetRecyclerViewListener() {
            @Override
            public void setRecyclerViewListener(View view, int position) {
                GoodsBean.DataBean bean = data.get(position);
                Intent intent = new Intent(HomeActivity.this,DeatilsActivity.class);
                intent.putExtra("name",bean.title);
                startActivity(intent);
            }
        });
        //上拉刷新，下拉加载
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

                adapter.updateData(data);
                springView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                adapter.addData(data);
                springView.onFinishFreshAndLoad();
            }
        });
    }
}