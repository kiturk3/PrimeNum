package com.primenum;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout ll_text;
    EditText ed_number_limit;
    RecyclerView rv_prime_numbers;
    ArrayList<Integer> arr;
    Button btn_prime;

    int t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_text = (LinearLayout) findViewById(R.id.ll_text);
        ed_number_limit = (EditText) findViewById(R.id.ed_number_limit);
        rv_prime_numbers = (RecyclerView) findViewById(R.id.rv_prime_numbers);
        btn_prime = (Button) findViewById(R.id.button);
        arr = new ArrayList<Integer>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_prime_numbers.setLayoutManager(layoutManager);
        ed_number_limit.requestFocus();

        btn_prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setprime();
            }
        });

    }

    public void setprime()
    {

        if (ed_number_limit.getText().toString().length()>0) {
            t = Integer.parseInt(ed_number_limit.getText().toString());
            if (t > 2) {
                arr = new ArrayList<Integer>();
                arr = getPrime();
                if (arr.size() > 0) {
                    RecyclerAdapter rv = new RecyclerAdapter(arr, MainActivity.this);
                    rv_prime_numbers.setAdapter(rv);
                }

            }
        }


    }


    private ArrayList<Integer> getPrime() {
        ArrayList<Integer> arr_prime = new ArrayList<Integer>();
        for (int i = 1; i <= t; ++i) {
            if (isPrime(i))
                arr_prime.add(i);
        }

        return arr_prime;
    }

    public static boolean isPrime(int value) {
        if (value <= 1)
            return false;

        if ((value % 2) == 0)
            return (value == 2);

        int from = (int) (Math.sqrt(value) + 1);

        for (int i = 3; i <= from; i += 2)
            if ((value % i) == 0)
                return false;

        return true;
    }


    //Recycler Adapter
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        private ArrayList<Integer> mDataset;
        private Context mContext;

        public RecyclerAdapter(ArrayList<Integer> dataset, Context context) {
            mDataset = dataset;
            mContext = context;
        }

        // Not use static
        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView raw_tv_prime;

            public ViewHolder(View itemView) {
                super(itemView);
                raw_tv_prime = (TextView) itemView.findViewById(R.id.raw_tv_prime);

            }
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.raw_tv_prime.setText(mDataset.get(position)+ "");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_prime_list, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
    }
}
