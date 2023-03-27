package com.example.trakker;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



    public class MvListAdapter extends BaseAdapter {

        Context context;

        String ListUser[];

        LayoutInflater inflator;





        public MvListAdapter(Context ctx, String [] UserList) {

            this.context = ctx;
            this.ListUser = UserList;
            inflator =LayoutInflater.from(ctx);



        }

        @Override
        public int getCount () {
            return ListUser.length;
        }

        @Override
        public Object getItem ( int i){
            return null;
        }

        @Override
        public long getItemId ( int i){
            return 0;
        }

        @Override
        public View getView ( int i, View convertView, ViewGroup viewGroup){

            convertView= inflator.inflate(R.layout.activity_list_page, null);

            TextView txtView = (TextView) convertView.findViewById(R.id.Listtext);
            txtView.setText(ListUser[i]);
            return convertView;






        }




    }
