package info.blogbasbas.e_laporcurahhujan.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.blogbasbas.e_laporcurahhujan.R;
import info.blogbasbas.e_laporcurahhujan.json.FAQ;

/**
 * Created by User on 09/03/2018.
 */

public class CobaAdapter extends RecyclerView.Adapter<CobaAdapter.MyHolder> {
    private final FAQ[] mValues;
    private final OnListFragmentInteractionListener mListener;

    public CobaAdapter(FAQ[] mValues, OnListFragmentInteractionListener mListener) {
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public CobaAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_guide,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(CobaAdapter.MyHolder holder, int position) {

    //  holder.mItem = mValues[position];
      holder.tvTitle.setText(mValues[position].key);
        Log.d("", "faq: "+mValues[position].key);


    }

    @Override
    public int getItemCount() {
        return mValues.length;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(FAQ item);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        FAQ mItem;
        public MyHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTitle.getText() + "'";
        }
    }
}
